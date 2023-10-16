package org.cocos2dx.lib;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class Cocos2dxReflectionHelper {
    public static <T> T getConstantValue(Class cls, String str) {
        String str2;
        StringBuilder sb;
        String str3;
        try {
            return cls.getDeclaredField(str).get((Object) null);
        } catch (NoSuchFieldException unused) {
            str2 = "can not find " + str + " in " + cls.getName();
            Log.e("error", str2);
            return null;
        } catch (IllegalAccessException unused2) {
            sb = new StringBuilder();
            sb.append(str);
            str = " is not accessible";
            sb.append(str);
            str2 = sb.toString();
            Log.e("error", str2);
            return null;
        } catch (IllegalArgumentException unused3) {
            sb = new StringBuilder();
            str3 = "arguments error when get ";
            sb.append(str3);
            sb.append(str);
            str2 = sb.toString();
            Log.e("error", str2);
            return null;
        } catch (Exception unused4) {
            sb = new StringBuilder();
            str3 = "can not get constant";
            sb.append(str3);
            sb.append(str);
            str2 = sb.toString();
            Log.e("error", str2);
            return null;
        }
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        StringBuilder sb;
        String str2;
        Class<?> cls = obj.getClass();
        try {
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        } catch (NoSuchMethodException unused) {
            sb = new StringBuilder();
            sb.append("can not find ");
            sb.append(str);
            sb.append(" in ");
            str = cls.getName();
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (IllegalAccessException unused2) {
            sb = new StringBuilder();
            sb.append(str);
            str = " is not accessible";
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (IllegalArgumentException unused3) {
            sb = new StringBuilder();
            str2 = "arguments are error when invoking ";
            sb.append(str2);
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (InvocationTargetException unused4) {
            sb = new StringBuilder();
            str2 = "an exception was thrown by the invoked method when invoking ";
            sb.append(str2);
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        }
    }
}
