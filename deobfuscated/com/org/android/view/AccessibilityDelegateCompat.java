package com.org.android.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.org.android.view.accessibility.AccessibilityNodeInfoCompat;
import com.org.android.view.accessibility.AccessibilityNodeProviderCompat;

public class AccessibilityDelegateCompat
{
  private static final View.AccessibilityDelegate IMPL = new View.AccessibilityDelegate();
  private final View.AccessibilityDelegate mBridge = new AccessibilityDelegateCompatJellyBean.1(this);
  
  public AccessibilityDelegateCompat() {}
  
  public boolean dispatchPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return IMPL.dispatchPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView = IMPL.getAccessibilityNodeProvider(paramView);
      if (paramView != null) {
        return new AccessibilityNodeProviderCompat(paramView);
      }
    }
    return null;
  }
  
  View.AccessibilityDelegate getBridge()
  {
    return mBridge;
  }
  
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    IMPL.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    IMPL.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat.obtain());
  }
  
  public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    IMPL.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return IMPL.onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
  }
  
  public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return IMPL.performAccessibilityAction(paramView, paramInt, paramBundle);
    }
    return false;
  }
  
  public void sendAccessibilityEvent(View paramView, int paramInt)
  {
    IMPL.sendAccessibilityEvent(paramView, paramInt);
  }
  
  public void sendAccessibilityEventUnchecked(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    IMPL.sendAccessibilityEventUnchecked(paramView, paramAccessibilityEvent);
  }
}
