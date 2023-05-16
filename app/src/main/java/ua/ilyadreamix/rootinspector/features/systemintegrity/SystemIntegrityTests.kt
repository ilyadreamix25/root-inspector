/**
 * This code was found at github.com/scottyab/rootbeer and rewritten in Kotlin.
 * I am not the author of the implementation of these methods
 */

package ua.ilyadreamix.rootinspector.features.systemintegrity

import android.content.Context
import android.os.Build
import ua.ilyadreamix.rootinspector.common.features.CommonTester
import ua.ilyadreamix.rootinspector.common.features.DetectionResult
import java.io.IOException
import java.util.Scanner

data class SystemIntegrityTestsResult(
    val dangerousApps: DetectionResult,
    val testKeys: DetectionResult,
    val rwPaths: DetectionResult
)

class SystemIntegrityTests(context: Context) : CommonTester(context) {
    fun checkSystemIntegrity() =
        SystemIntegrityTestsResult(
            dangerousApps = detectDangerousApps(),
            testKeys = detectTestKeys(),
            rwPaths = detectRwPaths()
        )

    private fun detectDangerousApps() =
        isAnyPackageOfListInstalled(DangerousApps)

    private fun detectTestKeys(): DetectionResult =
        (TEST_KEYS in Build.TAGS) to listOf()

    private fun detectRwPaths(): DetectionResult {
        val rwPaths = mutableListOf<String>()
        val mountedLines = mountReader() ?: return false to listOf()
        val sdkVersion = Build.VERSION.SDK_INT

        for (mountedLine in mountedLines) {
            val args = mountedLine.split(" ")
            val hasEnoughArgs = if (sdkVersion <= Build.VERSION_CODES.M) args.size >= 4 else args.size >= 6
            if (!hasEnoughArgs) continue

            val mountPoint = if (sdkVersion > Build.VERSION_CODES.M) args[2] else args[1]
            val mountOptions = if (sdkVersion > Build.VERSION_CODES.M) args[5] else args[3]
            val cleanMountOptions = mountOptions.replace("(", "").replace(")", "")

            for (pathToCheck in PathsThatShouldNotBeWriteable) {
                val isMountPointMatched = mountPoint.equals(pathToCheck, ignoreCase = true)
                val hasReadWriteAccess = "rw" in cleanMountOptions.split(",")

                if (isMountPointMatched && hasReadWriteAccess) {
                    rwPaths += pathToCheck
                    break
                }
            }
        }

        return rwPaths.isNotEmpty() to rwPaths
    }


    private fun mountReader(): List<String>? {
        try {
            val inputStream = Runtime.getRuntime().exec(MOUNT).inputStream ?: return null
            val propVal = Scanner(inputStream).useDelimiter("\\A").next()
            return propVal.split("\n")
        } catch (_: IOException) {
            return null
        } catch (_: NoSuchElementException) {
            return null
        }
    }
}
