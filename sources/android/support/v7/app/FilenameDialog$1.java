package android.support.v7.app;

import android.view.View;

class FilenameDialog$1 implements View.OnClickListener {
    final /* synthetic */ AlertController this$0;

    FilenameDialog$1(AlertController alertController) {
        this.this$0 = alertController;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r2 = r0.mButtonNeutralMessage;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r7) {
        /*
            r6 = this;
            android.support.v7.app.AlertController r0 = r6.this$0
            android.widget.Button r1 = r0.mButtonPositive
            if (r7 != r1) goto L_0x000f
            android.os.Message r2 = r0.mButtonPositiveMessage
            if (r2 == 0) goto L_0x000f
        L_0x000a:
            android.os.Message r2 = android.os.Message.obtain(r2)
            goto L_0x002a
        L_0x000f:
            android.support.v7.app.AlertController r0 = r6.this$0
            android.widget.Button r1 = r0.mButtonNegative
            if (r7 != r1) goto L_0x001a
            android.os.Message r2 = r0.mButtonNegativeMessage
            if (r2 == 0) goto L_0x001a
            goto L_0x000a
        L_0x001a:
            android.support.v7.app.AlertController r0 = r6.this$0
            android.widget.Button r1 = r0.mButtonNeutral
            if (r7 != r1) goto L_0x0029
            android.os.Message r2 = r0.mButtonNeutralMessage
            if (r2 == 0) goto L_0x0029
            android.os.Message r2 = android.os.Message.obtain(r2)
            goto L_0x002a
        L_0x0029:
            r2 = 0
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.sendToTarget()
        L_0x002f:
            android.support.v7.app.AlertController r0 = r6.this$0
            android.os.Handler r3 = r0.mHandler
            android.support.v7.app.AppCompatDialog r4 = r0.mDialog
            r5 = 1
            android.os.Message r2 = r3.obtainMessage(r5, r4)
            r2.sendToTarget()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.FilenameDialog$1.onClick(android.view.View):void");
    }
}
