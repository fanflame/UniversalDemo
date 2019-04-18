#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"{
    extern int bspatch_main(int argc,char * argv[]);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_fanyiran_incrementalupdatedemo_MainActivity_mergePatch(JNIEnv *env, jobject instance,
                                                                jstring oldApk_, jstring patch_,

                                                                jstring newApkPath_) {
    char *argv[4];
    argv[0] = const_cast<char *>("");
    argv[1] = const_cast<char *>(env->GetStringUTFChars(oldApk_, 0));
    argv[2] = const_cast<char *>(env->GetStringUTFChars(newApkPath_, 0));
    argv[3] = const_cast<char *>(env->GetStringUTFChars(patch_, 0));

    __android_log_write(ANDROID_LOG_ERROR,"tag","before");
    bspatch_main(4,argv);

    //TODO 需要释放资源
//    env->ReleaseStringUTFChars(oldApk_, argv[1]);
//    env->ReleaseStringUTFChars(patch_, argv[2]);
//    env->ReleaseStringUTFChars(newApkPath_, argv[3]);
//    free(argv);
}