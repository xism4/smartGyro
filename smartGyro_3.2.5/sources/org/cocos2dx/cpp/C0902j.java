package org.cocos2dx.cpp;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

/* renamed from: org.cocos2dx.cpp.j */
class C0902j extends Handler {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2461a;

    C0902j(AppActivity appActivity) {
        this.f2461a = appActivity;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            if (i == 100) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(AppActivity._url));
                AppActivity.f2449me.startActivity(intent);
            } else if (i == 10003) {
                this.f2461a.showMissingGPSPermissionDialog();
            } else if (i == 20011) {
                this.f2461a.downloadApk();
            } else if (i == 30011) {
                AppActivity.CallBackOnTouchCopy(this.f2461a.GetCopyString());
            } else if (i == 40011) {
                AppActivity appActivity = this.f2461a;
                appActivity.CheckCode(appActivity._checkCode, this.f2461a._fun, this.f2461a._deviceID);
            } else if (i == 50011) {
                this.f2461a.AddNewDevice2();
            } else if (i != 264830) {
                switch (i) {
                    case 789461:
                        AppActivity appActivity2 = this.f2461a;
                        appActivity2.checkPsw(appActivity2._userName, this.f2461a._psw);
                        break;
                    case 789462:
                    case 789464:
                        AppActivity appActivity3 = this.f2461a;
                        appActivity3.checkCode(appActivity3._checkCode, AppActivity._phoneNum, true);
                        break;
                    case 789463:
                        this.f2461a.addUserToService(AppActivity._phoneNum, "", AppActivity._phoneNum);
                        break;
                }
            } else {
                this.f2461a.sendCode(AppActivity._phoneNum);
            }
        }
        super.handleMessage(message);
    }
}
