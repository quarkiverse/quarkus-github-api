# Quarkus GitHub API

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.githubapi/quarkus-github-api?logo=apache-maven&style=for-the-badge)](https://central.sonatype.com/artifact/io.quarkiverse.githubapi/quarkus-github-api)
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-4-orange.svg?style=for-the-badge)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

This is the Quarkus extension for [Hub4j GitHub API](https://github.com/hub4j/github-api).

The main purpose of this extension is to make it possible to build native executables with GraalVM and Mandrel.

## Coordinates

```xml
<dependency>
    <groupId>io.quarkiverse.githubapi</groupId>
    <artifactId>quarkus-github-api</artifactId>
    <version>LATEST</version>
</dependency>
```

Note: using the OkHttp client is not supported anymore so please stick to the default of using the JDK 11+ HTTP client
(you don't have to do anything for that, it is the default behavior).

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
  <tbody>
    <tr>
      <td align="center"><a href="https://www.redhat.com/"><img src="https://avatars1.githubusercontent.com/u/1279749?v=4?s=100" width="100px;" alt="Guillaume Smet"/><br /><sub><b>Guillaume Smet</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-github-api/commits?author=gsmet" title="Code">💻</a> <a href="#maintenance-gsmet" title="Maintenance">🚧</a></td>
      <td align="center"><a href="https://twitter.com/r_svoboda"><img src="https://avatars.githubusercontent.com/u/925259?v=4?s=100" width="100px;" alt="Rostislav Svoboda"/><br /><sub><b>Rostislav Svoboda</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-github-api/commits?author=rsvoboda" title="Code">💻</a></td>
      <td align="center"><a href="http://riadiscuss.jeffmaury.com"><img src="https://avatars.githubusercontent.com/u/695993?v=4?s=100" width="100px;" alt="Jeff MAURY"/><br /><sub><b>Jeff MAURY</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-github-api/commits?author=jeffmaury" title="Code">💻</a></td>
      <td align="center"><a href="https://github.com/yrodiere"><img src="https://avatars.githubusercontent.com/u/412878?v=4?s=100" width="100px;" alt="Yoann Rodière"/><br /><sub><b>Yoann Rodière</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-github-api/commits?author=yrodiere" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
