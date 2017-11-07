#include <jni.h>
#include <stdio.h>
#include <Windows.h>

extern "C"
JNIEXPORT jlong JNICALL Java_lab04_JNI_getTotalRAM(JNIEnv* env, jclass clazz)
{
	MEMORYSTATUSEX objMEMORYSTATUSEX;
	objMEMORYSTATUSEX.dwLength = sizeof(MEMORYSTATUSEX);
	GlobalMemoryStatusEx(&objMEMORYSTATUSEX);
	return objMEMORYSTATUSEX.ullTotalPhys;
}

extern "C"
JNIEXPORT jlong JNICALL Java_lab04_JNI_getCurrentlyUsedRAM(JNIEnv* env, jclass clazz)
{
	MEMORYSTATUSEX objMEMORYSTATUSEX;
	objMEMORYSTATUSEX.dwLength = sizeof(MEMORYSTATUSEX);
	GlobalMemoryStatusEx(&objMEMORYSTATUSEX);
	return objMEMORYSTATUSEX.ullTotalPhys - objMEMORYSTATUSEX.ullAvailPhys;
}