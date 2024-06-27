package io.quarkiverse.githubapi.deployment;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;

import io.quarkus.deployment.index.IndexingUtil;

class GitHubApiDotNamesTest {

    // These types will be expected NOT to be registered for reflection, as well as their subtypes.
    // Their inner types, however, won't be affected by the exclusion.
    // Useful for "helper" types, such as clients, or builders in all but name.
    private static final Set<String> EXCLUDED_TYPES = Set.of(
            "org.kohsuke.github.AbuseLimitHandler",
            "org.kohsuke.github.GHDiscussion$Creator",
            "org.kohsuke.github.GHDiscussion$Setter",
            "org.kohsuke.github.GHDiscussion$Updater",
            "org.kohsuke.github.GHException",
            "org.kohsuke.github.GHFileNotFoundException",
            "org.kohsuke.github.GHGistUpdater",
            "org.kohsuke.github.GHHooks",
            "org.kohsuke.github.GHHooks$Context",
            "org.kohsuke.github.GHHooks$OrgContext",
            "org.kohsuke.github.GHHooks$RepoContext",
            "org.kohsuke.github.GHIOException",
            "org.kohsuke.github.GHPersonSet",
            "org.kohsuke.github.GHReleaseUpdater",
            "org.kohsuke.github.GitHub",
            "org.kohsuke.github.GitHub$DependentAuthorizationProvider",
            "org.kohsuke.github.GitHub$LoginLoadingUserAuthorizationProvider",
            "org.kohsuke.github.GitHubAbuseLimitHandler",
            "org.kohsuke.github.GitHubClient",
            "org.kohsuke.github.GitHubClient$BodyHandler",
            "org.kohsuke.github.GitHubClient$GHApiInfo",
            "org.kohsuke.github.GitHubClient$RetryRequestException",
            "org.kohsuke.github.GitHubConnectorResponseErrorHandler",
            "org.kohsuke.github.GitHubPageIterator",
            "org.kohsuke.github.GitHubRateLimitChecker",
            "org.kohsuke.github.GitHubRateLimitHandler",
            "org.kohsuke.github.HttpConnector",
            "org.kohsuke.github.HttpException",
            "org.kohsuke.github.PagedIterator",
            "org.kohsuke.github.RateLimitChecker",
            "org.kohsuke.github.RateLimitHandler",
            "org.kohsuke.github.Reactable",
            "org.kohsuke.github.Refreshable",
            "org.kohsuke.github.Requester",
            "org.kohsuke.github.TrafficInfo",
            "org.kohsuke.github.authorization",
            "org.kohsuke.github.connector",
            "org.kohsuke.github.example.dataobject",
            "org.kohsuke.github.extras",
            "org.kohsuke.github.extras.authorization",
            "org.kohsuke.github.extras.okhttp3",
            "org.kohsuke.github.function",
            "org.kohsuke.github.internal",
            "org.kohsuke.github.EnterpriseManagedSupport");

    private static Index ghApiIndex;

    @BeforeAll
    public static void index() throws IOException {
        ghApiIndex = IndexingUtil.indexJar(determineJarLocation(GitHub.class, "github-api"));
    }

    @Test
    public void testNoMissingGitHubClasses() {
        Set<DotName> expectedClasses = new TreeSet<>();
        for (ClassInfo clazz : ghApiIndex.getKnownClasses()) {
            if (shouldBeRegisteredForReflection(clazz)) {
                expectedClasses.add(clazz.name());
            }
        }

        // Simulate what happens when we create build items to register classes for reflection
        Set<DotName> actualClasses = new TreeSet<>();
        actualClasses.addAll(GitHubApiDotNames.GH_SIMPLE_OBJECTS);
        for (DotName clazz : GitHubApiDotNames.GH_ROOT_OBJECTS) {
            actualClasses.add(clazz);
            actualClasses.addAll(ghApiIndex.getAllKnownSubclasses(clazz).stream()
                    .map(ClassInfo::name).collect(Collectors.toList()));
        }

        // No idea why this appears in the result, since the class doesn't event exist;
        // that's probably a bug in Jandex?
        actualClasses.remove(DotName.createSimple("org.kohsuke.github.GHCommit$ShortInfo$Tree"));

        assertThat(expectedClasses).isNotEmpty();
        assertThat(actualClasses).containsExactlyInAnyOrderElementsOf(expectedClasses);
    }

    private boolean shouldBeRegisteredForReflection(ClassInfo clazz) {
        return !clazz.isAnnotation() && !clazz.isEnum() && !clazz.isSynthetic()
                && !ClassInfo.NestingType.ANONYMOUS.equals(clazz.nestingType())
                && !ClassInfo.NestingType.LOCAL.equals(clazz.nestingType())
                && !isExcluded(clazz);
    }

    private boolean isExcluded(ClassInfo clazz) {
        if (clazz.simpleName().endsWith("Builder") || clazz.simpleName().endsWith("PagedIterable")) {
            return true;
        }
        if (EXCLUDED_TYPES.contains(clazz.name().toString())
                || EXCLUDED_TYPES.contains(clazz.name().packagePrefix())) {
            return true;
        }
        var superClass = ghApiIndex.getClassByName(clazz.superName());
        if (superClass != null) {
            return isExcluded(superClass);
        }
        return false;
    }

    private static Path determineJarLocation(Class<?> classFromJar, String jarName) {
        URL url = classFromJar.getProtectionDomain().getCodeSource().getLocation();
        if (!url.getProtocol().equals("file")) {
            throw new IllegalStateException(jarName + " JAR is not a local file? " + url);
        }
        try {
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

}