package org.cocos2dx.lib;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: org.cocos2dx.lib.ha */
class C0981ha implements FilenameFilter {
    C0981ha() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith("main.") && str.endsWith(".obb");
    }
}
