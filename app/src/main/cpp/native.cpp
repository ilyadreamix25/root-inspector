/**
 * Original code was found at github.com/scottyab/rootbeer.
 * I am not the author of the implementation of these methods
 */

#include <jni.h>
#include <android/log.h>

#include <cstring>
#include <cstdio>

#define LOG_TAG   "RootInspector"
#define LOGD(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__);

static int VALUE = 1;

extern "C" void Java_ua_ilyadreamix_rootinspector_RootInspectorNative_setValue(JNIEnv* env, jobject thiz, jint value)
{
    VALUE = value;
    LOGD("Set native value (%d)", VALUE)
}
