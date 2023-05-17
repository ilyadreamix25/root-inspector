package ua.ilyadreamix.rootinspector.features.systemintegrity

const val TEST_KEYS = "test-keys"
const val MOUNT = "mount"

val DangerousApps = listOf(
    "com.koushikdutta.rommanager",
    "com.koushikdutta.rommanager.license",
    "com.dimonvideo.luckypatcher",
    "com.chelpus.lackypatch",
    "com.ramdroid.appquarantine",
    "com.ramdroid.appquarantinepro",
    "com.android.vending.billing.InAppBillingService.COIN",
    "com.android.vending.billing.InAppBillingService.LUCK",
    "com.chelpus.luckypatcher",
    "com.blackmartalpha",
    "org.blackmart.market",
    "com.allinone.free",
    "com.repodroid.app",
    "org.creeplays.hack",
    "com.baseappfull.fwd",
    "com.zmapp",
    "com.dv.marketmod.installer",
    "org.mobilism.android",
    "com.android.wp.net.log",
    "com.android.camera.update",
    "cc.madkite.freedom",
    "com.solohsu.android.edxp.manager",
    "org.meowcat.edxposed.manager",
    "com.xmodgame",
    "com.cih.game_cih",
    "com.charles.lpoqasert",
    "catch_.me_.if_.you_.can_"
)

val PathsThatShouldNotBeWriteable = listOf(
    "/",
    "/system",
    "/system/bin",
    "/system/sbin",
    "/system/xbin",
    "/vendor/bin",
    "/sbin",
    "/etc"
)
