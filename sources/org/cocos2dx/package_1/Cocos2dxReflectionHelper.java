package org.cocos2dx.package_1;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class Cocos2dxReflectionHelper {
    public static Object getConstantValue(Class cls, String $r5) {
        StringBuilder $r8;
        String $r9;
        String $r52;
        try {
            return cls.getDeclaredField($r5).get((Object) null);
        } catch (NoSuchFieldException e) {
            $r52 = "can not find " + $r5 + " in " + cls.getName();
            Log.e("error", $r52);
            return null;
        } catch (IllegalAccessException e2) {
            $r8 = new StringBuilder();
            $r8.append($r5);
            $r5 = " is not accessible";
            $r8.append($r5);
            $r52 = $r8.toString();
            Log.e("error", $r52);
            return null;
        } catch (IllegalArgumentException e3) {
            $r8 = new StringBuilder();
            $r9 = "arguments error when get ";
            $r8.append($r9);
            $r8.append($r5);
            $r52 = $r8.toString();
            Log.e("error", $r52);
            return null;
        } catch (Exception e4) {
            $r8 = new StringBuilder();
            $r9 = "can not get constant";
            $r8.append($r9);
            $r8.append($r5);
            $r52 = $r8.toString();
            Log.e("error", $r52);
            return null;
        }
    }

    public static Object invokeInstanceMethod(Object obj, String $r6, Class[] clsArr, Object[] objArr) {
        StringBuilder $r10;
        String $r11;
        Class $r8 = obj.getClass();
        try {
            return $r8.getMethod($r6, clsArr).invoke(obj, objArr);
        } catch (NoSuchMethodException e) {
            $r10 = new StringBuilder();
            $r10.append("can not find ");
            $r10.append($r6);
            $r10.append(" in ");
            $r6 = $r8.getName();
            $r10.append($r6);
            Log.e("error", $r10.toString());
            return null;
        } catch (IllegalAccessException e2) {
            $r10 = new StringBuilder();
            $r10.append($r6);
            $r6 = " is not accessible";
            $r10.append($r6);
            Log.e("error", $r10.toString());
            return null;
        } catch (IllegalArgumentException e3) {
            $r10 = new StringBuilder();
            $r11 = "arguments are error when invoking ";
            $r10.append($r11);
            $r10.append($r6);
            Log.e("error", $r10.toString());
            return null;
        } catch (InvocationTargetException e4) {
            $r10 = new StringBuilder();
            $r11 = "an exception was thrown by the invoked method when invoking ";
            $r10.append($r11);
            $r10.append($r6);
            Log.e("error", $r10.toString());
            return null;
        }
    }
}
