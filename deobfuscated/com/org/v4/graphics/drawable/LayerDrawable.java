package com.org.v4.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;

class LayerDrawable
  extends DrawableContainer
{
  private Document mLayerState;
  private boolean mMutated;
  
  LayerDrawable(Document paramDocument)
  {
    if (paramDocument != null) {
      mutate(paramDocument);
    }
  }
  
  LayerDrawable(Document paramDocument, Resources paramResources)
  {
    mutate(new Document(paramDocument, this, paramResources));
    onStateChange(getState());
  }
  
  int[] a(AttributeSet paramAttributeSet)
  {
    int i1 = paramAttributeSet.getAttributeCount();
    int[] arrayOfInt = new int[i1];
    int i = 0;
    int k;
    for (int j = 0; i < i1; j = k)
    {
      int n = paramAttributeSet.getAttributeNameResource(i);
      int m = n;
      k = j;
      if (n != 0)
      {
        k = j;
        if (n != 16842960)
        {
          k = j;
          if (n != 16843161)
          {
            if (!paramAttributeSet.getAttributeBooleanValue(i, false)) {
              m = -n;
            }
            arrayOfInt[j] = m;
            k = j + 1;
          }
        }
      }
      i += 1;
    }
    return StateSet.trimStateSet(arrayOfInt, j);
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    super.applyTheme(paramTheme);
    onStateChange(getState());
  }
  
  Document draw()
  {
    return new Document(mLayerState, this, null);
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  public Drawable mutate()
  {
    if (!mMutated)
    {
      super.mutate();
      mLayerState.init();
      mMutated = true;
    }
    return this;
  }
  
  protected void mutate(DrawableContainer.DrawableContainerState paramDrawableContainerState)
  {
    super.mutate(paramDrawableContainerState);
    if ((paramDrawableContainerState instanceof Document)) {
      mLayerState = ((Document)paramDrawableContainerState);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    boolean bool = super.onStateChange(paramArrayOfInt);
    int j = mLayerState.indexOf(paramArrayOfInt);
    int i = j;
    if (j < 0) {
      i = mLayerState.indexOf(StateSet.WILD_CARD);
    }
    return (draw(i)) || (bool);
  }
}
