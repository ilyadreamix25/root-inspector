/**
 * This code was found at github.com/scottyab/rootbeer and rewritten in Kotlin.
 * I am not the author of the implementation of these methods
 */

#include <jni.h>
#include <android/log.h>

#include <cstring>
#include <cstdio>

#define LOG_TAG   "RootInspector"
#define LOGD(...) if (DEBUG) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__);

static int DEBUG = 1;

extern "C" void Java_ua_ilyadreamix_rootinspector_features_root_RootNative_setLogDebugMessages(JNIEnv* env, jobject thiz, jboolean debug)
{
    if (debug) DEBUG = 1;
    else DEBUG = 0;

    LOGD("Set DEBUG variable (%d)", DEBUG)
}
