package org.cocos2dx.package_1;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.Cocos2dxActivity;

public class Cocos2dxHandler extends Handler {
    public static final int HANDLER_SHOW_DIALOG = 1;
    private WeakReference<Cocos2dxActivity> mActivity;

    public class DialogMessage {
        public String message;
        public String title;

        public DialogMessage(String str, String str2) {
            this.title = str;
            this.message = str2;
        }
    }

    public Cocos2dxHandler(Cocos2dxActivity cocos2dxActivity) {
        this.mActivity = new WeakReference(cocos2dxActivity);
    }

    private void showDialog(Message message) {
        DialogMessage $r5 = (DialogMessage) message.obj;
        new AlertDialog.Builder((Cocos2dxActivity) this.mActivity.get()).setTitle($r5.title).setMessage($r5.message).setPositiveButton("Ok", new SettingsActivity$15(this)).create().show();
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            showDialog(message);
        }
    }
}
