import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.File

const val DefaultLocalVersion = "0.1.0"

fun Project.getProperty(key: String, file: String = "local.properties"): String? {
    val properties = java.util.Properties()
    val localProperties = File(rootDir, file)
    if (!localProperties.exists()) {
        localProperties.createNewFile()
    }

    return if (localProperties.isFile) {
        java.io.InputStreamReader(java.io.FileInputStream(localProperties), Charsets.UTF_8).use { reader -> properties.load(reader) }
        properties.getProperty(key)
    } else project.findProperty(key) as String?
}
