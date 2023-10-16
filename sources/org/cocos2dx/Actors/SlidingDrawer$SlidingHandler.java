package org.cocos2dx.Actors;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

class SlidingDrawer$SlidingHandler extends Handler {
    final /* synthetic */ AppActivity this$0;

    SlidingDrawer$SlidingHandler(AppActivity appActivity) {
        this.this$0 = appActivity;
    }

    public void handleMessage(Message message) {
        int $i0 = message.what;
        if ($i0 != 1) {
            if ($i0 == 100) {
                Intent $r6 = new Intent("android.intent.action.VIEW");
                $r6.setData(Uri.parse(AppActivity._url));
                AppActivity.a.startActivity($r6);
            } else if ($i0 == 10003) {
                this.this$0.showMissingGPSPermissionDialog();
            } else if ($i0 == 20011) {
                this.this$0.downloadApk();
            } else if ($i0 == 30011) {
                AppActivity.CallBackOnTouchCopy(this.this$0.GetCopyString());
            } else if ($i0 == 40011) {
                AppActivity $r2 = this.this$0;
                $r2.CheckCode($r2._checkCode, this.this$0._fun, this.this$0._deviceID);
            } else if ($i0 == 50011) {
                this.this$0.AddNewDevice2();
            } else if ($i0 != 264830) {
                switch ($i0) {
                    case 789461:
                        AppActivity $r22 = this.this$0;
                        $r22.checkPsw($r22._userName, this.this$0._psw);
                        break;
                    case 789462:
                    case 789464:
                        AppActivity $r23 = this.this$0;
                        $r23.checkCode($r23._checkCode, AppActivity._phoneNum, true);
                        break;
                    case 789463:
                        this.this$0.addUserToService(AppActivity._phoneNum, "", AppActivity._phoneNum);
                        break;
                }
            } else {
                this.this$0.sendCode(AppActivity._phoneNum);
            }
        }
        super.handleMessage(message);
    }
}
