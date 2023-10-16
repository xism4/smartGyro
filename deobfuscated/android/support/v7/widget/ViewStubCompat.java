package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.org.v4.util.R.styleable;
import java.lang.ref.WeakReference;

public final class ViewStubCompat
  extends View
{
  private a mInflateListener;
  private int mInflatedId;
  private WeakReference<View> mInflatedViewRef;
  private LayoutInflater mInflater;
  private int mLayoutResource = 0;
  
  public ViewStubCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ViewStubCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ViewStubCompat, paramInt, 0);
    mInflatedId = paramContext.getResourceId(R.styleable.ViewStubCompat_android_inflatedId, -1);
    mLayoutResource = paramContext.getResourceId(R.styleable.ViewStubCompat_android_layout, 0);
    setId(paramContext.getResourceId(R.styleable.ViewStubCompat_android_id, -1));
    paramContext.recycle();
    setVisibility(8);
    setWillNotDraw(true);
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {}
  
  public void draw(Canvas paramCanvas) {}
  
  public int getInflatedId()
  {
    return mInflatedId;
  }
  
  public LayoutInflater getLayoutInflater()
  {
    return mInflater;
  }
  
  public int getLayoutResource()
  {
    return mLayoutResource;
  }
  
  public View inflate()
  {
    Object localObject1 = getParent();
    if ((localObject1 != null) && ((localObject1 instanceof ViewGroup)))
    {
      if (mLayoutResource != 0)
      {
        Object localObject2 = (ViewGroup)localObject1;
        localObject1 = mInflater;
        if (localObject1 == null) {
          localObject1 = LayoutInflater.from(getContext());
        }
        localObject1 = ((LayoutInflater)localObject1).inflate(mLayoutResource, (ViewGroup)localObject2, false);
        int i = mInflatedId;
        if (i != -1) {
          ((View)localObject1).setId(i);
        }
        i = ((ViewGroup)localObject2).indexOfChild(this);
        ((ViewGroup)localObject2).removeViewInLayout(this);
        ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
        if (localLayoutParams != null) {
          ((ViewGroup)localObject2).addView((View)localObject1, i, localLayoutParams);
        } else {
          ((ViewGroup)localObject2).addView((View)localObject1, i);
        }
        mInflatedViewRef = new WeakReference(localObject1);
        localObject2 = mInflateListener;
        if (localObject2 != null)
        {
          ((a)localObject2).onInflate(this, (View)localObject1);
          return localObject1;
        }
      }
      else
      {
        throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
      }
    }
    else {
      throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }
    return localObject1;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(0, 0);
  }
  
  public void setInflatedId(int paramInt)
  {
    mInflatedId = paramInt;
  }
  
  public void setLayoutInflater(LayoutInflater paramLayoutInflater)
  {
    mInflater = paramLayoutInflater;
  }
  
  public void setLayoutResource(int paramInt)
  {
    mLayoutResource = paramInt;
  }
  
  public void setOnInflateListener(a paramA)
  {
    mInflateListener = paramA;
  }
  
  public void setVisibility(int paramInt)
  {
    Object localObject = mInflatedViewRef;
    if (localObject != null)
    {
      localObject = (View)((WeakReference)localObject).get();
      if (localObject != null)
      {
        ((View)localObject).setVisibility(paramInt);
        return;
      }
      throw new IllegalStateException("setVisibility called on un-referenced view");
    }
    super.setVisibility(paramInt);
    if ((paramInt == 0) || (paramInt == 4)) {
      inflate();
    }
  }
  
  public static abstract interface a
  {
    public abstract void onInflate(ViewStubCompat paramViewStubCompat, View paramView);
  }
}
