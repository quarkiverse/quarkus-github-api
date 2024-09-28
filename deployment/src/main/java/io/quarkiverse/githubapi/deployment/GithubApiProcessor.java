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
    void registerForReflection(CombinedIndexBuildItem combinedIndex,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClasses) {
        for (DotName rootModelObject : GH_ROOT_OBJECTS) {
            reflectiveClasses.produce(ReflectiveClassBuildItem.builder(rootModelObject.toString()).methods().fields().build());

            reflectiveClasses.produce(ReflectiveClassBuildItem.builder(
                    combinedIndex.getIndex().getAllKnownSubclasses(rootModelObject).stream()
                            .map(ci -> ci.name().toString())
                            .toArray(String[]::new))
                    .methods().fields().build());
        }

        reflectiveClasses.produce(ReflectiveClassBuildItem.builder(GH_SIMPLE_OBJECTS).methods().fields().build());
    }

    @BuildStep
    void runtimeInitialized(BuildProducer<RuntimeInitializedClassBuildItem> runtimeInitializedClasses) {
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem(HttpClientGitHubConnector.class.getName()));
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem(GitHubConnector.class.getName()));
    }
}
