

pluginManagement {
    fun getProperty(key: String?, file: String = "local.properties"): String? {
        val properties = java.util.Properties()
        val localProperties = File(rootDir, file)
        if (!localProperties.exists()) {
            localProperties.createNewFile()
        }

        if (localProperties.isFile) {
            java.io.InputStreamReader(java.io.FileInputStream(localProperties), Charsets.UTF_8).use { reader -> properties.load(reader) }
            return properties.getProperty(key)
        }
        throw IllegalStateException()
    }

    val repoRegistryUrl = getProperty("githubPackageUrl") ?: System.getProperty("githubPackageUrl")
    val repoUsername = getProperty("githubUsername") ?: System.getProperty("githubUsername")
    val repoPassword = getProperty("githubPrivateToken") ?: System.getProperty("githubPrivateToken")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven {
            url = uri(repoRegistryUrl)
            name = "Github"
            credentials {
                username = repoUsername
                password = repoPassword
            }
            authentication {
                create("basic", BasicAuthentication::class)
            }
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
 