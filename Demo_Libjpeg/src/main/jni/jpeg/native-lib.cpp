#include <jni.h>
#include <string>
//#include "cpp/jpeglib.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_fanyiran_demo_1libjpeg_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
//    jpeg_start_compress(nullptr, false);
    return env->NewStringUTF(hello.c_str());
}
