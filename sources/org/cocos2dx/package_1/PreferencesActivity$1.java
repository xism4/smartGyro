package org.cocos2dx.package_1;

import java.io.File;
import java.io.FilenameFilter;

final class PreferencesActivity$1 implements FilenameFilter {
    PreferencesActivity$1() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith("main.") && str.endsWith(".obb");
    }
}
