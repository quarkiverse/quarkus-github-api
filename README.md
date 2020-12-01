# Quarkiverse GitHub API

This is the Quarkus extension for [Hub4j GitHub API](https://github.com/hub4j/github-api).

The main purpose of this extension is to make it possible to build native executables with GraalVM and Mandrel.

## Native support

The default HTTP connector is not working in native.
Use the OkHttp connector provided by the GitHub API:

```java
GitHub gitHub = GitHubBuilder.fromEnvironment()
    .withConnector(new OkHttpConnector(new OkHttpClient()))
    .build();
```
