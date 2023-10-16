package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListAdapter;

public class ByteVector
{
  private final int a;
  private final AlertController.a g;
  
  public ByteVector(Context paramContext)
  {
    this(paramContext, e.a(paramContext, 0));
  }
  
  public ByteVector(Context paramContext, int paramInt)
  {
    g = new AlertController.a(new ContextThemeWrapper(paramContext, e.a(paramContext, paramInt)));
    a = paramInt;
  }
  
  public ByteVector a(Drawable paramDrawable)
  {
    g.mIcon = paramDrawable;
    return this;
  }
  
  public ByteVector a(ListAdapter paramListAdapter, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertController.a localA = g;
    mAdapter = paramListAdapter;
    mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public ByteVector a(CharSequence paramCharSequence)
  {
    g.mMessage = paramCharSequence;
    return this;
  }
  
  public ByteVector a(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertController.a localA = g;
    mPositiveButtonText = paramCharSequence;
    mPositiveButtonListener = paramOnClickListener;
    return this;
  }
  
  public e a()
  {
    e localE = new e(g.this$0, a);
    g.apply(mAlert);
    localE.setCancelable(g.c);
    if (g.c) {
      localE.setCanceledOnTouchOutside(true);
    }
    localE.setOnCancelListener(g.p);
    localE.setOnDismissListener(g.q);
    DialogInterface.OnKeyListener localOnKeyListener = g.r;
    if (localOnKeyListener != null) {
      localE.setOnKeyListener(localOnKeyListener);
    }
    return localE;
  }
  
  public ByteVector b(DialogInterface.OnKeyListener paramOnKeyListener)
  {
    g.r = paramOnKeyListener;
    return this;
  }
  
  public ByteVector b(View paramView)
  {
    g.g = paramView;
    return this;
  }
  
  public ByteVector b(CharSequence paramCharSequence)
  {
    g.d = paramCharSequence;
    return this;
  }
  
  public ByteVector b(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertController.a localA = g;
    mNegativeButtonText = paramCharSequence;
    mNegativeButtonListener = paramOnClickListener;
    return this;
  }
  
  public ByteVector b(boolean paramBoolean)
  {
    g.c = paramBoolean;
    return this;
  }
  
  public e b()
  {
    e localE = a();
    localE.show();
    return localE;
  }
  
  public Context get()
  {
    return g.this$0;
  }
}
