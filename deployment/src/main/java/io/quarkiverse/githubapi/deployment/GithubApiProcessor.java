package io.quarkiverse.githubapi.deployment;

import static io.quarkiverse.githubapi.deployment.GitHubApiDotNames.GH_ROOT_OBJECTS;
import static io.quarkiverse.githubapi.deployment.GitHubApiDotNames.GH_SIMPLE_OBJECTS;

import org.jboss.jandex.DotName;
import org.kohsuke.github.connector.GitHubConnector;
import org.kohsuke.github.extras.HttpClientGitHubConnector;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.ExtensionSslNativeSupportBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;

class GithubApiProcessor {

    private static final String FEATURE = "github-api";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ExtensionSslNativeSupportBuildItem requireSsl() {
        return new ExtensionSslNativeSupportBuildItem(FEATURE);
    }

    @BuildStep
    IndexDependencyBuildItem indexGitHubApiJar() {
        return new IndexDependencyBuildItem("org.kohsuke", "github-api");
    }

    @BuildStep
    void classesWithBridgeMethods(BuildProducer<GitHubApiClassWithBridgeMethodsBuildItem> classesWithBridgeMethods) {
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHAppInstallationToken",
                        "getExpiresAt"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHCheckRun", "getStatus",
                        "getConclusion"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHCommit", "getAuthor",
                        "getCommitter"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHCompare", "getAuthor",
                        "getCommitter"));
        classesWithBridgeMethods.produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHIssue", "comment",
                "addLabels", "removeLabel", "removeLabels"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHObject", "getCreatedAt",
                        "getUrl", "getHtmlUrl", "getId"));
        classesWithBridgeMethods.produce(new GitHubApiClassWithBridgeMethodsBuildItem(
                "org.kohsuke.github.GHPullRequestCommitDetail$Commit", "getAuthor", "getCommitter"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHRepository", "getCollaborators"));
        classesWithBridgeMethods.produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GHUser", "getFollows",
                "getFollowers", "getOrganizations"));
        classesWithBridgeMethods
                .produce(new GitHubApiClassWithBridgeMethodsBuildItem("org.kohsuke.github.GitHub", "getMyself"));
    }

    @BuildStep
    void registerForReflection(CombinedIndexBuildItem combinedIndex,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClasses) {
        for (DotName rootModelObject : GH_ROOT_OBJECTS) {
            reflectiveClasses.produce(new ReflectiveClassBuildItem(true, true, rootModelObject.toString()));

            reflectiveClasses.produce(new ReflectiveClassBuildItem(true, true,
                    combinedIndex.getIndex().getAllKnownSubclasses(rootModelObject).stream()
                            .map(ci -> ci.name().toString())
                            .toArray(String[]::new)));
        }

        reflectiveClasses.produce(new ReflectiveClassBuildItem(true, true,
                GH_SIMPLE_OBJECTS.stream().map(DotName::toString).toArray(String[]::new)));
    }

    @BuildStep
    void runtimeInitialized(BuildProducer<RuntimeInitializedClassBuildItem> runtimeInitializedClasses) {
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem(HttpClientGitHubConnector.class.getName()));
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem(GitHubConnector.class.getName()));
    }
}
