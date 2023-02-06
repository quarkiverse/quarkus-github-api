package io.quarkus.it.githubapi;

import java.io.IOException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

@Path("/test")
public class GitHubApiResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String extractOuterText() throws IOException {
        GitHub gitHub = GitHub.connectAnonymously();
        GHRepository repository = gitHub.getRepository("quarkiverse/quarkus-github-api");

        return repository.getFullName();
    }

}
