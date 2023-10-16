package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.org.v4.util.R.styleable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class f
{
  private static final RectF g = new RectF();
  private static ConcurrentHashMap<String, Method> m = new ConcurrentHashMap();
  private final TextView a;
  private TextPaint b;
  private final Context c;
  private boolean h = false;
  private int k = 0;
  private boolean l = false;
  private int[] n = new int[0];
  private float x = -1.0F;
  private float y = -1.0F;
  private float z = -1.0F;
  
  f(TextView paramTextView)
  {
    a = paramTextView;
    c = a.getContext();
  }
  
  private int a(RectF paramRectF)
  {
    int i = n.length;
    if (i != 0)
    {
      int i1 = i - 1;
      i = 1;
      int j = 0;
      while (i <= i1)
      {
        int i2 = (i + i1) / 2;
        if (a(n[i2], paramRectF))
        {
          j = i;
          i = i2 + 1;
        }
        else
        {
          j = i2 - 1;
          i1 = j;
        }
      }
      return n[j];
    }
    paramRectF = new IllegalStateException("No available text sizes to choose from.");
    throw paramRectF;
  }
  
  private StaticLayout a(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt)
  {
    float f1;
    float f2;
    boolean bool;
    if (Build.VERSION.SDK_INT >= 16)
    {
      f1 = a.getLineSpacingMultiplier();
      f2 = a.getLineSpacingExtra();
      bool = a.getIncludeFontPadding();
    }
    else
    {
      f1 = ((Float)a(a, "getLineSpacingMultiplier", Float.valueOf(1.0F))).floatValue();
      f2 = ((Float)a(a, "getLineSpacingExtra", Float.valueOf(0.0F))).floatValue();
      bool = ((Boolean)a(a, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue();
    }
    return new StaticLayout(paramCharSequence, b, paramInt, paramAlignment, f1, f2, bool);
  }
  
  private StaticLayout a(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt1, int paramInt2)
  {
    TextDirectionHeuristic localTextDirectionHeuristic = (TextDirectionHeuristic)a(a, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
    paramCharSequence = StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), b, paramInt1).setAlignment(paramAlignment).setLineSpacing(a.getLineSpacingExtra(), a.getLineSpacingMultiplier()).setIncludePad(a.getIncludeFontPadding()).setBreakStrategy(a.getBreakStrategy()).setHyphenationFrequency(a.getHyphenationFrequency());
    paramInt1 = paramInt2;
    if (paramInt2 == -1) {
      paramInt1 = Integer.MAX_VALUE;
    }
    return paramCharSequence.setMaxLines(paramInt1).setTextDirection(localTextDirectionHeuristic).build();
  }
  
  private Object a(Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      localObject = get(paramString);
      paramObject1 = ((Method)localObject).invoke(paramObject1, new Object[0]);
      return paramObject1;
    }
    catch (Throwable paramObject1) {}catch (Exception paramObject1)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Failed to invoke TextView#");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("() method");
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject).toString(), paramObject1);
      return paramObject2;
    }
    throw paramObject1;
  }
  
  private void a(float paramFloat)
  {
    if (paramFloat != a.getPaint().getTextSize())
    {
      a.getPaint().setTextSize(paramFloat);
      boolean bool;
      if (Build.VERSION.SDK_INT >= 18) {
        bool = a.isInLayout();
      } else {
        bool = false;
      }
      if (a.getLayout() != null)
      {
        l = false;
        try
        {
          Method localMethod = get("nullLayouts");
          if (localMethod != null)
          {
            TextView localTextView = a;
            localMethod.invoke(localTextView, new Object[0]);
          }
        }
        catch (Exception localException)
        {
          Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", localException);
        }
        if (!bool) {
          a.requestLayout();
        } else {
          a.forceLayout();
        }
        a.invalidate();
      }
    }
  }
  
  private void a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 > 0.0F)
    {
      if (paramFloat2 > paramFloat1)
      {
        if (paramFloat3 > 0.0F)
        {
          k = 1;
          z = paramFloat1;
          x = paramFloat2;
          y = paramFloat3;
          h = false;
          return;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("The auto-size step granularity (");
        localStringBuilder.append(paramFloat3);
        localStringBuilder.append("px) is less or equal to (0px)");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Maximum auto-size text size (");
      localStringBuilder.append(paramFloat2);
      localStringBuilder.append("px) is less or equal to minimum auto-size ");
      localStringBuilder.append("text size (");
      localStringBuilder.append(paramFloat1);
      localStringBuilder.append("px)");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Minimum auto-size text size (");
    localStringBuilder.append(paramFloat1);
    localStringBuilder.append("px) is less or equal to (0px)");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  private boolean a(int paramInt, RectF paramRectF)
  {
    CharSequence localCharSequence = a.getText();
    Object localObject2 = localCharSequence;
    TransformationMethod localTransformationMethod = a.getTransformationMethod();
    Object localObject1 = localObject2;
    if (localTransformationMethod != null)
    {
      localCharSequence = localTransformationMethod.getTransformation(localCharSequence, a);
      localObject1 = localObject2;
      if (localCharSequence != null) {
        localObject1 = localCharSequence;
      }
    }
    int i;
    if (Build.VERSION.SDK_INT >= 16) {
      i = a.getMaxLines();
    } else {
      i = -1;
    }
    localObject2 = b;
    if (localObject2 == null) {
      b = new TextPaint();
    } else {
      ((Paint)localObject2).reset();
    }
    b.set(a.getPaint());
    b.setTextSize(paramInt);
    localObject2 = (Layout.Alignment)a(a, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
    if (Build.VERSION.SDK_INT >= 23) {
      localObject2 = a((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right), i);
    } else {
      localObject2 = a((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right));
    }
    if (i != -1)
    {
      if (((StaticLayout)localObject2).getLineCount() > i) {
        break label254;
      }
      if (((Layout)localObject2).getLineEnd(((StaticLayout)localObject2).getLineCount() - 1) != ((CharSequence)localObject1).length()) {
        return false;
      }
    }
    return ((Layout)localObject2).getHeight() <= bottom;
    label254:
    return false;
  }
  
  private boolean b()
  {
    boolean bool = size();
    int j = 0;
    if ((bool) && (k == 1))
    {
      if ((!h) || (n.length == 0))
      {
        float f = Math.round(z);
        int i = 1;
        while (Math.round(y + f) <= Math.round(x))
        {
          i += 1;
          f += y;
        }
        int[] arrayOfInt = new int[i];
        f = z;
        while (j < i)
        {
          arrayOfInt[j] = Math.round(f);
          f += y;
          j += 1;
        }
        n = sort(arrayOfInt);
      }
      l = true;
    }
    else
    {
      l = false;
    }
    return l;
  }
  
  private void c()
  {
    k = 0;
    z = -1.0F;
    x = -1.0F;
    y = -1.0F;
    n = new int[0];
    l = false;
  }
  
  private boolean d()
  {
    int i = n.length;
    boolean bool;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    }
    h = bool;
    if (h)
    {
      k = 1;
      int[] arrayOfInt = n;
      z = arrayOfInt[0];
      x = arrayOfInt[(i - 1)];
      y = -1.0F;
    }
    return h;
  }
  
  private Method get(String paramString)
  {
    Object localObject1 = m;
    try
    {
      localObject1 = ((ConcurrentHashMap)localObject1).get(paramString);
      localObject2 = (Method)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = TextView.class.getDeclaredMethod(paramString, new Class[0]);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          ((Method)localObject2).setAccessible(true);
          localObject1 = m;
          ((ConcurrentHashMap)localObject1).put(paramString, localObject2);
          return localObject2;
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Failed to retrieve TextView#");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("() method");
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject2).toString(), localException);
      return null;
    }
    return localException;
  }
  
  private void init(TypedArray paramTypedArray)
  {
    int j = paramTypedArray.length();
    int[] arrayOfInt = new int[j];
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        arrayOfInt[i] = paramTypedArray.getDimensionPixelSize(i, -1);
        i += 1;
      }
      n = sort(arrayOfInt);
      d();
    }
  }
  
  private boolean size()
  {
    return !(a instanceof AppCompatEditText);
  }
  
  private int[] sort(int[] paramArrayOfInt)
  {
    int i1 = paramArrayOfInt.length;
    if (i1 == 0) {
      return paramArrayOfInt;
    }
    Arrays.sort(paramArrayOfInt);
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    int i = 0;
    while (i < i1)
    {
      int i2 = paramArrayOfInt[i];
      if ((i2 > 0) && (Collections.binarySearch(localArrayList, Integer.valueOf(i2)) < 0)) {
        localArrayList.add(Integer.valueOf(i2));
      }
      i += 1;
    }
    if (i1 == localArrayList.size()) {
      return paramArrayOfInt;
    }
    i1 = localArrayList.size();
    paramArrayOfInt = new int[i1];
    i = j;
    while (i < i1)
    {
      paramArrayOfInt[i] = ((Integer)localArrayList.get(i)).intValue();
      i += 1;
    }
    return paramArrayOfInt;
  }
  
  void a()
  {
    if (!i()) {
      return;
    }
    if (l)
    {
      if (a.getMeasuredHeight() <= 0) {
        return;
      }
      if (a.getMeasuredWidth() <= 0) {
        return;
      }
      int i;
      if (((Boolean)a(a, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue()) {
        i = 1048576;
      } else {
        i = a.getMeasuredWidth() - a.getTotalPaddingLeft() - a.getTotalPaddingRight();
      }
      int j = a.getHeight() - a.getCompoundPaddingBottom() - a.getCompoundPaddingTop();
      if (i <= 0) {
        return;
      }
      if (j <= 0) {
        return;
      }
      RectF localRectF = g;
      try
      {
        g.setEmpty();
        gright = i;
        gbottom = j;
        float f = a(g);
        if (f != a.getTextSize()) {
          b(0, f);
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    l = true;
  }
  
  void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (size())
    {
      DisplayMetrics localDisplayMetrics = c.getResources().getDisplayMetrics();
      a(TypedValue.applyDimension(paramInt4, paramInt1, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt2, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt3, localDisplayMetrics));
      if (b()) {
        a();
      }
    }
  }
  
  void a(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = c.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
      k = paramAttributeSet.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
    }
    float f1;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
      f1 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F);
    } else {
      f1 = -1.0F;
    }
    float f2;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
      f2 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F);
    } else {
      f2 = -1.0F;
    }
    float f3;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
      f3 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F);
    } else {
      f3 = -1.0F;
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes))
    {
      paramInt = paramAttributeSet.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
      if (paramInt > 0)
      {
        TypedArray localTypedArray = paramAttributeSet.getResources().obtainTypedArray(paramInt);
        init(localTypedArray);
        localTypedArray.recycle();
      }
    }
    paramAttributeSet.recycle();
    if (size())
    {
      if (k == 1)
      {
        if (!h)
        {
          paramAttributeSet = c.getResources().getDisplayMetrics();
          float f4 = f2;
          if (f2 == -1.0F) {
            f4 = TypedValue.applyDimension(2, 12.0F, paramAttributeSet);
          }
          f2 = f3;
          if (f3 == -1.0F) {
            f2 = TypedValue.applyDimension(2, 112.0F, paramAttributeSet);
          }
          f3 = f1;
          if (f1 == -1.0F) {
            f3 = 1.0F;
          }
          a(f4, f2, f3);
        }
        b();
      }
    }
    else {
      k = 0;
    }
  }
  
  void a(int[] paramArrayOfInt, int paramInt)
  {
    if (size())
    {
      int j = paramArrayOfInt.length;
      int i = 0;
      if (j > 0)
      {
        int[] arrayOfInt = new int[j];
        Object localObject;
        if (paramInt == 0)
        {
          localObject = Arrays.copyOf(paramArrayOfInt, j);
        }
        else
        {
          DisplayMetrics localDisplayMetrics = c.getResources().getDisplayMetrics();
          for (;;)
          {
            localObject = arrayOfInt;
            if (i >= j) {
              break;
            }
            arrayOfInt[i] = Math.round(TypedValue.applyDimension(paramInt, paramArrayOfInt[i], localDisplayMetrics));
            i += 1;
          }
        }
        n = sort((int[])localObject);
        if (!d())
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("None of the preset sizes is valid: ");
          ((StringBuilder)localObject).append(Arrays.toString(paramArrayOfInt));
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        h = false;
      }
      if (b()) {
        a();
      }
    }
  }
  
  int add()
  {
    return Math.round(z);
  }
  
  void b(int paramInt)
  {
    if (size()) {
      if (paramInt != 0)
      {
        Object localObject;
        if (paramInt == 1)
        {
          localObject = c.getResources().getDisplayMetrics();
          a(TypedValue.applyDimension(2, 12.0F, (DisplayMetrics)localObject), TypedValue.applyDimension(2, 112.0F, (DisplayMetrics)localObject), 1.0F);
          if (b()) {
            a();
          }
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Unknown auto-size text type: ");
          ((StringBuilder)localObject).append(paramInt);
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        c();
      }
    }
  }
  
  void b(int paramInt, float paramFloat)
  {
    Object localObject = c;
    if (localObject == null) {
      localObject = Resources.getSystem();
    } else {
      localObject = ((Context)localObject).getResources();
    }
    a(TypedValue.applyDimension(paramInt, paramFloat, ((Resources)localObject).getDisplayMetrics()));
  }
  
  int clear()
  {
    return Math.round(x);
  }
  
  int[] close()
  {
    return n;
  }
  
  int getHeight()
  {
    return Math.round(y);
  }
  
  boolean i()
  {
    return (size()) && (k != 0);
  }
  
  int k()
  {
    return k;
  }
}
