package org.cocos2dx.package_1;

import java.util.Map;
import lombok.eclipse.handlers.http.RequestHandle;

final class SpiceManager$7 implements Runnable {
    final /* synthetic */ Cocos2dxDownloader this$0;

    SpiceManager$7(Cocos2dxDownloader cocos2dxDownloader) {
        this.this$0 = cocos2dxDownloader;
    }

    public void run() {
        for (Map.Entry $r6 : this.this$0._taskMap.entrySet()) {
            RequestHandle $r8 = ((Page) $r6.getValue()).status;
            if ($r8 != null) {
                $r8.cancel(true);
            }
        }
    }
}
