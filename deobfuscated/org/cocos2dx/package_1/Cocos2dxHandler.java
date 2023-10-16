package org.cocos2dx.package_1;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public class Cocos2dxHandler
  extends Handler
{
  public static final int HANDLER_SHOW_DIALOG = 1;
  private WeakReference<org.cocos2dx.lib.Cocos2dxActivity> mActivity;
  
  public Cocos2dxHandler(Cocos2dxActivity paramCocos2dxActivity)
  {
    mActivity = new WeakReference(paramCocos2dxActivity);
  }
  
  private void showDialog(Message paramMessage)
  {
    Cocos2dxActivity localCocos2dxActivity = (Cocos2dxActivity)mActivity.get();
    paramMessage = (DialogMessage)obj;
    new AlertDialog.Builder(localCocos2dxActivity).setTitle(title).setMessage(message).setPositiveButton("Ok", new SettingsActivity.15(this)).create().show();
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (what != 1) {
      return;
    }
    showDialog(paramMessage);
  }
  
  public class DialogMessage
  {
    public String message;
    
    public DialogMessage(String paramString)
    {
      message = paramString;
    }
  }
}
