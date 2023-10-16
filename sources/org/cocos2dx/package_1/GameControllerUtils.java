package org.cocos2dx.package_1;

import java.io.File;
import java.io.FileInputStream;

public class GameControllerUtils {
    public static void ensureDirectoryExist(String str) {
        File $r0 = new File(str);
        if (!$r0.exists()) {
            $r0.mkdirs();
        }
    }

    public static String readJsonFile(String str) {
        File $r1 = new File(str);
        if (!$r1.exists()) {
            return null;
        }
        try {
            FileInputStream $r2 = new FileInputStream($r1);
            byte[] $r3 = new byte[$r2.available()];
            $r2.read($r3);
            $r2.close();
            return new String($r3, "UTF-8");
        } catch (Exception $r4) {
            $r4.printStackTrace();
            return null;
        }
    }
}
