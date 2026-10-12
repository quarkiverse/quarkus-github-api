package io.quarkiverse.githubapi.runtime;

import io.quarkiverse.githubapi.runtime.config.GitHubApiRuntimeConfig;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class GitHubApiRecorder {

    private final RuntimeValue<GitHubApiRuntimeConfig> config;

    public GitHubApiRecorder(RuntimeValue<GitHubApiRuntimeConfig> config) {
        this.config = config;
    }

    public void configureRateLimiting() {
        var rateLimitConfig = config.getValue().rateLimit();

        var throttle = rateLimitConfig.throttle();
        boolean enabled = throttle.enabled()
                .orElseGet(() -> LaunchMode.current() != LaunchMode.TEST);
        GitHubApiRateLimitConfig.instance = new GitHubApiRateLimitConfig(
                enabled, throttle.read().toMillis(), throttle.write().toMillis());

        rateLimitConfig.retry().maxAttempts().ifPresent(maxAttempts -> System
                .setProperty("org.kohsuke.github.GitHubClient.retryCount", String.valueOf(maxAttempts)));
    }
}
