package org.cocos2dx.package_1;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ResizeLayout
  extends FrameLayout
{
  private boolean mEnableForceDoLayout = false;
  
  public ResizeLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public ResizeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (mEnableForceDoLayout) {
      new Handler().postDelayed(new EventInfoFragment.2(this), 41L);
    }
  }
  
  public void setEnableForceDoLayout(boolean paramBoolean)
  {
    mEnableForceDoLayout = paramBoolean;
  }
}
