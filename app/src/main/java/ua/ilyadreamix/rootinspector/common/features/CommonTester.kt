package ua.ilyadreamix.rootinspector.common.features

import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.StringRes
import ua.ilyadreamix.rootinspector.features.root.PATH
import java.io.File

open class CommonTester(private val context: Context) {
    @Suppress("DEPRECATION")
    protected fun isAnyPackageOfListInstalled(packages: List<String>): Pair<Boolean, List<String>> {
        val installedPackages = packages.filter { pkg ->
            try {
                context.packageManager.getPackageInfo(pkg, 0)
                true
            } catch (_: PackageManager.NameNotFoundException) {
                false
            }
        }
        return installedPackages.isNotEmpty() to installedPackages
    }

    protected fun doesAnyPathExist(paths: List<String>): Pair<Boolean, List<String>> {
        val existingPaths = paths.filter { File(it).exists() }
        return existingPaths.isNotEmpty() to existingPaths
    }

    protected fun isAnyValueInPath(values: List<String>): Pair<Boolean, List<String>> {
        var result = false
        val valuesInPath = mutableListOf<String>()

        values.forEach { value ->
            System.getenv(PATH)?.split(":")?.forEach { pathDir ->
                if (File(pathDir, value).exists()) {
                    result = true
                    valuesInPath.add(value)
                }
            }
        }

        return result to valuesInPath
    }
}

data class CommonTestResult(
    val detected: Boolean,
    val values: List<String>,
    @StringRes val titleRes: Int
)
