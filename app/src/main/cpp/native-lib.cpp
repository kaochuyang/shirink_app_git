#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_tw_com_cct_ms2_shirink_1app_1git_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
