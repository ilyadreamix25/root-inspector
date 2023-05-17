/**
 * This code was found at github.com/scottyab/rootbeer and rewritten in Kotlin.
 * I am not the author of the implementation of these methods
 */

package ua.ilyadreamix.rootinspector.features.systemintegrity

import android.content.Context
import android.os.Build
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.features.CommonTester
import ua.ilyadreamix.rootinspector.common.features.CommonTestResult
import java.io.BufferedReader
import java.io.InputStreamReader

class SystemIntegrityTests(context: Context) : CommonTester(context) {
    fun checkSystemIntegrity(): List<CommonTestResult> {
        val dangerousApps = detectDangerousApps()
        val testKeys = detectTestKeys()
        val rwPaths = detectRwPaths()

        return listOf(
            CommonTestResult(
                dangerousApps.first,
                dangerousApps.second,
                R.string.dangerous_apps,
                "dangerousApps"
            ),
            CommonTestResult(
                testKeys.first,
                testKeys.second,
                R.string.test_keys,
                "testKeys"
            ),
            CommonTestResult(
                rwPaths.first,
                rwPaths.second,
                R.string.rw_paths,
                "pathsThatShouldNotBeWriteable"
            )
        )
    }

    private fun detectDangerousApps() =
        isAnyPackageOfListInstalled(DangerousApps)

    private fun detectTestKeys(): Pair<Boolean, List<String>> =
        (TEST_KEYS in Build.TAGS) to listOf()

    private fun detectRwPaths(): Pair<Boolean, List<String>> {
        val rwMounts = mutableListOf<String>()

        try {
            val process = Runtime.getRuntime().exec("mount")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val mountInfo = line!!.split(" ")
                val mountPath = mountInfo[2]
                val mountOptions = mountInfo[5]
                    .removeSurrounding("(", ")")
                    .split(",")

                if (mountPath in PathsThatShouldNotBeWriteable && "rw" in mountOptions) {
                    rwMounts += mountPath
                }
            }

            reader.close()
        } catch (_: Exception) {
            // ...
        }

        return rwMounts.isNotEmpty() to rwMounts
    }
}
