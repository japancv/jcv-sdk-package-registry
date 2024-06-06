
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven {
            url = uri("https://maven.pkg.github.com/japancv/jcv-sdk-package-registry")
            name = "Github"
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        listOf(pluginManagement.repositories).forEach { repos ->
            repos.forEach {
                if (it.name == "Github") {
                    add(it)
                }
            }
        }
    }
}

rootProject.name = "JCVSdkSample"
include(":sample")
 