package io.quarkiverse.githubapi.deployment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.quarkus.builder.item.MultiBuildItem;

public final class GitHubApiClassWithBridgeMethodsBuildItem extends MultiBuildItem {

    private final String className;
    private final Set<String> methodNames;

    GitHubApiClassWithBridgeMethodsBuildItem(String className, String... methodsWithBridges) {
        this.className = className;
        this.methodNames = new HashSet<>(Arrays.asList(methodsWithBridges));
    }

    public String getClassName() {
        return className;
    }

    public Set<String> getMethodsWithBridges() {
        return methodNames;
    }
}
