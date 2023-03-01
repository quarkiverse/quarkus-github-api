package io.quarkus.it.githubapi;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

@Path("/test")
public class GitHubApiResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String extractOuterText() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("quarkiverse/quarkus-github-api");

        return repository.getFullName();
    }

}
