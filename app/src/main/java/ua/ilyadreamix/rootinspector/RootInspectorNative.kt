package ua.ilyadreamix.rootinspector

object RootInspectorNative {
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

    external fun setValue(value: Int)
}
