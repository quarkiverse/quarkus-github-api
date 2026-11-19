package io.quarkus.it.githubapi;

import java.io.IOException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

@Path("/test")
public class GitHubApiResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String extractOuterText() throws IOException {
        // the Quarkiverse ecosystem CI workflow only exposes the token as GH_TOKEN, which GitHub.connect() does not read
        String ghToken = System.getenv("GH_TOKEN");
        GitHub gitHub = ghToken != null ? new GitHubBuilder().withOAuthToken(ghToken).build() : GitHub.connect();
        GHRepository repository = gitHub.getRepository("quarkiverse/quarkus-github-api");

        return repository.getFullName();
    }

}
