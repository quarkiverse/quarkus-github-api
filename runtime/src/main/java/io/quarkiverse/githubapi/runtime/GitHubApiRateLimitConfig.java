package io.quarkiverse.githubapi.runtime;

public record GitHubApiRateLimitConfig(boolean throttlingEnabled, long readThrottleMillis, long writeThrottleMillis) {

    static volatile GitHubApiRateLimitConfig instance;

    public static GitHubApiRateLimitConfig get() {
        return instance;
    }
}
