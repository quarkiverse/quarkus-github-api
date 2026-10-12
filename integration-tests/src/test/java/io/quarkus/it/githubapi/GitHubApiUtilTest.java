package io.quarkus.it.githubapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedIterator;
import org.mockito.quality.Strictness;

import io.quarkiverse.githubapi.GitHubApiUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@TestProfile(GitHubApiUtilTest.ThrottlingEnabledProfile.class)
class GitHubApiUtilTest {

    static final long THROTTLE_MILLIS = 100;

    public static class ThrottlingEnabledProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of(
                    "quarkus.github-api.rate-limit.throttle.enabled", "true",
                    "quarkus.github-api.rate-limit.throttle.read", "0.1s");
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void toStream() {
        String item1 = "a";
        String item2 = "b";
        String item3 = "c";

        PagedIterable<String> iterable = mockPagedIterable(item1, item2, item3);

        List<String> result = GitHubApiUtil.toStream(iterable).toList();

        assertThat(result).containsExactly(item1, item2, item3);
    }

    @Test
    void toStream_empty() {
        PagedIterable<String> iterable = mockPagedIterable();

        assertThat(GitHubApiUtil.toStream(iterable)).isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Test
    void toStream_throttlesBetweenPages() {
        List<String> page1 = List.of("a", "b");
        List<String> page2 = List.of("c", "d");
        List<String> page3 = List.of("e");

        PagedIterator<String> iteratorMock = mock(PagedIterator.class,
                withSettings().stubOnly().strictness(Strictness.LENIENT));
        List<List<String>> pages = new ArrayList<>(List.of(page1, page2, page3));
        when(iteratorMock.hasNext()).thenAnswer(ignored -> !pages.isEmpty());
        when(iteratorMock.nextPage()).thenAnswer(ignored -> pages.remove(0));

        PagedIterable<String> iterableMock = mock(PagedIterable.class,
                withSettings().stubOnly().strictness(Strictness.LENIENT));
        when(iterableMock.iterator()).thenReturn(iteratorMock);

        long start = System.nanoTime();
        List<String> result = GitHubApiUtil.toStream(iterableMock).toList();
        long elapsedMillis = (System.nanoTime() - start) / 1_000_000;

        assertThat(result).containsExactly("a", "b", "c", "d", "e");
        // 3 pages = 2 inter-page throttle delays
        assertThat(elapsedMillis).isGreaterThanOrEqualTo(2 * THROTTLE_MILLIS);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static <T> PagedIterable<T> mockPagedIterable(T... elements) {
        PagedIterable<T> iterableMock = mock(PagedIterable.class,
                withSettings().stubOnly().strictness(Strictness.LENIENT));

        List<T> list = List.of(elements);
        PagedIterator<T> iteratorMock = mock(PagedIterator.class,
                withSettings().stubOnly().strictness(Strictness.LENIENT));

        when(iterableMock.iterator()).thenReturn(iteratorMock);

        Iterator<T> actualIterator = list.iterator();
        when(iteratorMock.hasNext()).thenAnswer(ignored -> actualIterator.hasNext());
        when(iteratorMock.next()).thenAnswer(ignored -> actualIterator.next());
        when(iteratorMock.nextPage()).thenAnswer(ignored -> {
            List<T> page = new ArrayList<>();
            while (actualIterator.hasNext()) {
                page.add(actualIterator.next());
            }
            return page;
        });

        return iterableMock;
    }
}
