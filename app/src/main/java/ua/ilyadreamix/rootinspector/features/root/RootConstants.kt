package ua.ilyadreamix.rootinspector.features.root

const val SU = "su"
const val PATH = "PATH"
const val WHICH = "which"
const val BUSYBOX = "busybox"
const val MAGISK = "magisk"

val RootManagementApps = listOf(
    "com.noshufou.android.su",
    "com.noshufou.android.su.elite",
    "eu.chainfire.supersu",
    "com.koushikdutta.superuser",
    "com.thirdparty.superuser",
    "com.yellowes.su",
    "com.topjohnwu.magisk",
    "com.kingroot.kinguser",
    "com.kingo.root",
    "com.smedialink.oneclickroot",
    "com.zhiqupk.root.global",
    "com.alephzain.framaroot"
)

val SuBinariesPaths = listOf(
    "/system/app/Superuser.apk",
    "/sbin/su",
    "/system/bin/su",
    "/system/xbin/su",
    "/data/local/xbin/su",
    "/data/local/bin/su",
    "/system/sd/xbin/su",
    "/system/bin/failsafe/su",
    "/data/local/su",
    "/su/bin/su"
)

val SuspiciousValuesInPath = listOf(SU, MAGISK, BUSYBOX)
