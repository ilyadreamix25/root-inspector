/**
 * This code was found at github.com/scottyab/rootbeer and rewritten in Kotlin.
 * I am not the author of the implementation of these methods
 */

package ua.ilyadreamix.rootinspector.features.root

import android.content.Context
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.RootInspectorNative
import ua.ilyadreamix.rootinspector.common.features.CommonTester
import ua.ilyadreamix.rootinspector.common.features.CommonTestResult

class RootTests(context: Context) : CommonTester(context) {
    fun checkRootAccess(): List<CommonTestResult> {
        val suBinariesResult = detectSuBinaries()
        val suCommandResult = detectSuCommand() to listOf<String>()
        val suspiciousValuesInPathResult = detectSuspiciousValuesInPath()
        val rootManagementApps = detectRootManagementApps()
        val rootCloakingApps = detectRootCloakingApps()

        return listOf(
            CommonTestResult(
                suBinariesResult.first,
                suBinariesResult.second,
                R.string.su_binaries
            ),
            CommonTestResult(
                suCommandResult.first,
                suCommandResult.second,
                R.string.su_command
            ),
            CommonTestResult(
                suspiciousValuesInPathResult.first,
                suspiciousValuesInPathResult.second,
                R.string.suspicious_values_in_path
            ),
            CommonTestResult(
                rootManagementApps.first,
                rootManagementApps.second,
                R.string.root_management_apps
            ),
            CommonTestResult(
                rootCloakingApps.first,
                rootCloakingApps.second,
                R.string.root_cloaking_apps
            )
        )
    }

    private fun detectRootCloakingApps(): Pair<Boolean, List<String>> {
        val packagesTest = isAnyPackageOfListInstalled(RootCloakingApps)
        val nativeTest = RootInspectorNative.loaded && !checkNativeReadAccess()

        return (packagesTest.first && nativeTest) to packagesTest.second
    }

    private fun checkNativeReadAccess() =
        try {
            RootInspectorNative.setValue(0)
            true
        } catch (_: UnsatisfiedLinkError) {
            false
        }

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
