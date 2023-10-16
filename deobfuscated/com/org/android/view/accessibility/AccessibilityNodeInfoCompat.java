package com.org.android.view.accessibility;

import a.a.c.g.a.a;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityNodeInfoCompat
{
  private final AccessibilityNodeInfo IMPL;
  public int b = -1;
  
  private AccessibilityNodeInfoCompat(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    IMPL = paramAccessibilityNodeInfo;
  }
  
  private static String getActionSymbolicName(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        switch (paramInt)
        {
        default: 
          return "ACTION_UNKNOWN";
        case 131072: 
          return "ACTION_SET_SELECTION";
        case 65536: 
          return "ACTION_CUT";
        case 32768: 
          return "ACTION_PASTE";
        case 16384: 
          return "ACTION_COPY";
        case 8192: 
          return "ACTION_SCROLL_BACKWARD";
        case 4096: 
          return "ACTION_SCROLL_FORWARD";
        case 2048: 
          return "ACTION_PREVIOUS_HTML_ELEMENT";
        case 1024: 
          return "ACTION_NEXT_HTML_ELEMENT";
        case 512: 
          return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
        case 256: 
          return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
        case 128: 
          return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
        case 64: 
          return "ACTION_ACCESSIBILITY_FOCUS";
        case 32: 
          return "ACTION_LONG_CLICK";
        case 16: 
          return "ACTION_CLICK";
        case 8: 
          return "ACTION_CLEAR_SELECTION";
        }
        return "ACTION_SELECT";
      }
      return "ACTION_CLEAR_FOCUS";
    }
    return "ACTION_FOCUS";
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    return new AccessibilityNodeInfoCompat(paramAccessibilityNodeInfo);
  }
  
  public void addAction(int paramInt)
  {
    IMPL.addAction(paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (a.class != paramObject.getClass()) {
      return false;
    }
    paramObject = (AccessibilityNodeInfoCompat)paramObject;
    AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
    if (localAccessibilityNodeInfo == null)
    {
      if (IMPL != null) {
        return false;
      }
    }
    else if (!localAccessibilityNodeInfo.equals(IMPL)) {
      return false;
    }
    return true;
  }
  
  public int getActions()
  {
    return IMPL.getActions();
  }
  
  public void getBoundsInParent(Rect paramRect)
  {
    IMPL.getBoundsInParent(paramRect);
  }
  
  public void getBoundsInScreen(Rect paramRect)
  {
    IMPL.getBoundsInScreen(paramRect);
  }
  
  public CharSequence getClassName()
  {
    return IMPL.getClassName();
  }
  
  public CharSequence getContentDescription()
  {
    return IMPL.getContentDescription();
  }
  
  public CharSequence getPackageName()
  {
    return IMPL.getPackageName();
  }
  
  public CharSequence getText()
  {
    return IMPL.getText();
  }
  
  public String getViewIdResourceName()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return IMPL.getViewIdResourceName();
    }
    return null;
  }
  
  public int hashCode()
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
    if (localAccessibilityNodeInfo == null) {
      return 0;
    }
    return localAccessibilityNodeInfo.hashCode();
  }
  
  public boolean isCheckable()
  {
    return IMPL.isCheckable();
  }
  
  public boolean isChecked()
  {
    return IMPL.isChecked();
  }
  
  public boolean isClickable()
  {
    return IMPL.isClickable();
  }
  
  public boolean isEnabled()
  {
    return IMPL.isEnabled();
  }
  
  public boolean isFocusable()
  {
    return IMPL.isFocusable();
  }
  
  public boolean isFocused()
  {
    return IMPL.isFocused();
  }
  
  public boolean isLongClickable()
  {
    return IMPL.isLongClickable();
  }
  
  public boolean isPassword()
  {
    return IMPL.isPassword();
  }
  
  public boolean isScrollable()
  {
    return IMPL.isScrollable();
  }
  
  public boolean isSelected()
  {
    return IMPL.isSelected();
  }
  
  public AccessibilityNodeInfo obtain()
  {
    return IMPL;
  }
  
  public void setClassName(CharSequence paramCharSequence)
  {
    IMPL.setClassName(paramCharSequence);
  }
  
  public void setScrollable(boolean paramBoolean)
  {
    IMPL.setScrollable(paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(super.toString());
    Object localObject = new Rect();
    getBoundsInParent((Rect)localObject);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInParent: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    getBoundsInScreen((Rect)localObject);
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInScreen: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append("; packageName: ");
    localStringBuilder1.append(getPackageName());
    localStringBuilder1.append("; className: ");
    localStringBuilder1.append(getClassName());
    localStringBuilder1.append("; text: ");
    localStringBuilder1.append(getText());
    localStringBuilder1.append("; contentDescription: ");
    localStringBuilder1.append(getContentDescription());
    localStringBuilder1.append("; viewId: ");
    localStringBuilder1.append(getViewIdResourceName());
    localStringBuilder1.append("; checkable: ");
    localStringBuilder1.append(isCheckable());
    localStringBuilder1.append("; checked: ");
    localStringBuilder1.append(isChecked());
    localStringBuilder1.append("; focusable: ");
    localStringBuilder1.append(isFocusable());
    localStringBuilder1.append("; focused: ");
    localStringBuilder1.append(isFocused());
    localStringBuilder1.append("; selected: ");
    localStringBuilder1.append(isSelected());
    localStringBuilder1.append("; clickable: ");
    localStringBuilder1.append(isClickable());
    localStringBuilder1.append("; longClickable: ");
    localStringBuilder1.append(isLongClickable());
    localStringBuilder1.append("; enabled: ");
    localStringBuilder1.append(isEnabled());
    localStringBuilder1.append("; password: ");
    localStringBuilder1.append(isPassword());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("; scrollable: ");
    ((StringBuilder)localObject).append(isScrollable());
    localStringBuilder1.append(((StringBuilder)localObject).toString());
    localStringBuilder1.append("; [");
    int i = getActions();
    while (i != 0)
    {
      int k = 1 << Integer.numberOfTrailingZeros(i);
      int j = i & k;
      localStringBuilder1.append(getActionSymbolicName(k));
      i = j;
      if (j != 0)
      {
        localStringBuilder1.append(", ");
        i = j;
      }
    }
    localStringBuilder1.append("]");
    return localStringBuilder1.toString();
  }
}
