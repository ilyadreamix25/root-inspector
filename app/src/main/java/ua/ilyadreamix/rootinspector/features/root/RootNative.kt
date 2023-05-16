package ua.ilyadreamix.rootinspector.features.root

object RootNative {
    @JvmStatic
    var loaded = false
        private set

    init {
        try {
            System.loadLibrary("native")
            loaded = true
        } catch (_: UnsatisfiedLinkError) {
            // ...
        }
    }

    external fun setLogDebugMessages(value: Boolean)
}
