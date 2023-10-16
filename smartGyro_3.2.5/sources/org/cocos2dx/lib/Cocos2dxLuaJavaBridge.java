package org.cocos2dx.lib;

public class Cocos2dxLuaJavaBridge {
    public static native int callLuaFunctionWithString(int i, String str);

    public static native int callLuaGlobalFunctionWithString(String str, String str2);

    public static native int releaseLuaFunction(int i);

    public static native int retainLuaFunction(int i);
}
