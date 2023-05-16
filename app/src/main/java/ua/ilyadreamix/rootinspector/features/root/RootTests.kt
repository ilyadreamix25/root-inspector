/**
 * This code was found at github.com/scottyab/rootbeer and rewritten in Kotlin.
 * I am not the author of the implementation of these methods
 */

package ua.ilyadreamix.rootinspector.features.root

import android.content.Context
import ua.ilyadreamix.rootinspector.common.features.CommonTester
import ua.ilyadreamix.rootinspector.common.features.DetectionResult

data class RootTestsResult(
    val suBinaries: DetectionResult,
    val suCommand: DetectionResult,
    val suspiciousValuesInPath: DetectionResult,
    val rootManagementApps: DetectionResult
)

class RootTests(context: Context) : CommonTester(context) {
    fun checkRootAccess() =
        RootTestsResult(
            suBinaries = detectSuBinaries(),
            suCommand = detectSuCommand() to listOf(),
            suspiciousValuesInPath = detectSuspiciousValuesInPath(),
            rootManagementApps = detectRootManagementApps()
        )

    private fun detectSuBinaries() =
        doesAnyPathExist(SuBinariesPaths)

    private fun detectRootManagementApps() =
        isAnyPackageOfListInstalled(RootManagementApps)

    private fun detectSuCommand() = try {
        Runtime.getRuntime().exec(arrayOf(WHICH, SU)).waitFor() == 0
    } catch (_: Exception) {
        false
    }

    private fun detectSuspiciousValuesInPath() =
        isAnyValueInPath(SuspiciousValuesInPath)
}
