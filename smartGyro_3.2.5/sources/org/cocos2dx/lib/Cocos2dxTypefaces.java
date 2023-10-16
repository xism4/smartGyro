package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class Cocos2dxTypefaces {
    private static final HashMap<String, Typeface> sTypefaceCache = new HashMap<>();

    public static synchronized Typeface get(Context context, String str) {
        Typeface typeface;
        synchronized (Cocos2dxTypefaces.class) {
            if (!sTypefaceCache.containsKey(str)) {
                sTypefaceCache.put(str, str.startsWith("/") ? Typeface.createFromFile(str) : Typeface.createFromAsset(context.getAssets(), str));
            }
            typeface = sTypefaceCache.get(str);
        }
        return typeface;
    }
}
