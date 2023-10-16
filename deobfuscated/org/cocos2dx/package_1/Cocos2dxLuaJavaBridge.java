package org.cocos2dx.package_1;

public class Cocos2dxLuaJavaBridge
{
  public Cocos2dxLuaJavaBridge() {}
  
  public static native int callLuaFunctionWithString(int paramInt, String paramString);
  
  public static native int callLuaGlobalFunctionWithString(String paramString1, String paramString2);
  
  public static native int releaseLuaFunction(int paramInt);
  
  public static native int retainLuaFunction(int paramInt);
}
