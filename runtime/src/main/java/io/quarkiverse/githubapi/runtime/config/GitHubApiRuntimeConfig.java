package io.quarkiverse.githubapi.runtime.config;

import java.time.Duration;
import java.util.Optional;
import java.util.OptionalInt;

import io.quarkus.runtime.annotations.ConfigDocSection;
import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "quarkus.github-api")
public interface GitHubApiRuntimeConfig {

    /**
     * Rate limiting configuration.
     */
    @ConfigDocSection
    RateLimit rateLimit();

    @ConfigGroup
    interface RateLimit {

        /**
         * Proactive throttling configuration to avoid triggering GitHub's secondary rate limits.
         */
        Throttle throttle();

        /**
         * Retry configuration for GitHub API calls that fail due to secondary rate limits
         * or other transient errors.
         */
        Retry retry();
    }

    @ConfigGroup
    interface Retry {

        /**
         * Maximum number of retry attempts when a GitHub API call is denied due to a secondary rate limit.
         * <p>
         * Maps to the hub4j/github-api retry count.
         * If not set, the default from hub4j/github-api applies (2 retries).
         */
        OptionalInt maxAttempts();
    }

    @ConfigGroup
    interface Throttle {

        /**
         * Whether proactive throttling is enabled.
         * <p>
         * Defaults to {@code true} in production and dev mode, {@code false} in tests.
         */
        Optional<Boolean> enabled();

        /**
         * Delay between page fetches when using
         * {@link io.quarkiverse.githubapi.GitHubApiUtil#toStream(org.kohsuke.github.PagedIterable)}
         * or {@link io.quarkiverse.githubapi.GitHubApiUtil#sleepForReadThrottling()}.
         */
        @WithDefault("0.2s")
        Duration read();

        /**
         * Delay between write/delete operations when using
         * {@link io.quarkiverse.githubapi.GitHubApiUtil#sleepForWriteThrottling()}.
         */
        @WithDefault("1s")
        Duration write();
    }
}
