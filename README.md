# Quarkiverse GitHub API
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

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

## License

While this extension itself is licensed under the Apache License version 2.0,
please note that the underlying library is licensed under the MIT License.

Please refer to the [Hub4j GitHub API repository](https://github.com/hub4j/github-api) for additional license and contributors information.

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://www.redhat.com/"><img src="https://avatars1.githubusercontent.com/u/1279749?v=4" width="100px;" alt=""/><br /><sub><b>Guillaume Smet</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkiverse-github-api/commits?author=gsmet" title="Code">💻</a> <a href="#maintenance-gsmet" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
