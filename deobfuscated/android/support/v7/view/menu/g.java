package android.support.v7.view.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.app.ByteVector;
import android.support.v7.app.e;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import com.org.v4.util.R.layout;

class g
  implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, l.a
{
  ListMenuPresenter a;
  private MenuBuilder b;
  private e c;
  private l.a d;
  
  public g(MenuBuilder paramMenuBuilder)
  {
    b = paramMenuBuilder;
  }
  
  public void a()
  {
    e localE = c;
    if (localE != null) {
      localE.dismiss();
    }
  }
  
  public void a(IBinder paramIBinder)
  {
    Object localObject = b;
    ByteVector localByteVector = new ByteVector(((MenuBuilder)localObject).getContext());
    a = new ListMenuPresenter(localByteVector.get(), R.layout.abc_list_menu_item_layout);
    a.a(this);
    b.addMenuPresenter(a);
    localByteVector.a(a.getAdapter(), this);
    View localView = ((MenuBuilder)localObject).b();
    if (localView != null)
    {
      localByteVector.b(localView);
    }
    else
    {
      localByteVector.a(((MenuBuilder)localObject).getHeaderIcon());
      localByteVector.b(((MenuBuilder)localObject).getValue());
    }
    localByteVector.b(this);
    c = localByteVector.a();
    c.setOnDismissListener(this);
    localObject = c.getWindow().getAttributes();
    type = 1003;
    if (paramIBinder != null) {
      token = paramIBinder;
    }
    flags |= 0x20000;
    c.show();
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    b.performItemAction((MenuItemImpl)a.getAdapter().getItem(paramInt), 0);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if ((paramBoolean) || (paramMenuBuilder == b)) {
      a();
    }
    l.a localA = d;
    if (localA != null) {
      localA.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    a.a(b, true);
  }
  
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 82) || (paramInt == 4)) {
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        paramDialogInterface = c.getWindow();
        if (paramDialogInterface != null)
        {
          paramDialogInterface = paramDialogInterface.getDecorView();
          if (paramDialogInterface != null)
          {
            paramDialogInterface = paramDialogInterface.getKeyDispatcherState();
            if (paramDialogInterface != null)
            {
              paramDialogInterface.startTracking(paramKeyEvent, this);
              return true;
            }
          }
        }
      }
      else if ((paramKeyEvent.getAction() == 1) && (!paramKeyEvent.isCanceled()))
      {
        Object localObject = c.getWindow();
        if (localObject != null)
        {
          localObject = ((Window)localObject).getDecorView();
          if (localObject != null)
          {
            localObject = ((View)localObject).getKeyDispatcherState();
            if ((localObject != null) && (((KeyEvent.DispatcherState)localObject).isTracking(paramKeyEvent)))
            {
              b.close(true);
              paramDialogInterface.dismiss();
              return true;
            }
          }
        }
      }
    }
    return b.performShortcut(paramInt, paramKeyEvent, 0);
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    l.a localA = d;
    if (localA != null) {
      return localA.onOpenSubMenu(paramMenuBuilder);
    }
    return false;
  }
}
