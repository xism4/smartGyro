package com.org.shortcuts.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.org.android.asm.PathParser;
import com.org.android.asm.PathParser.PathDataNode;
import com.org.android.ui.asm.TypedArrayUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Context
{
  private static void build(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, int paramInt, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    paramValueAnimator = (ObjectAnimator)paramValueAnimator;
    String str1 = TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "pathData", 1);
    if (str1 != null)
    {
      String str2 = TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "propertyXName", 2);
      paramXmlPullParser = TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "propertyYName", 3);
      if ((paramInt == 2) || ((str2 == null) && (paramXmlPullParser == null)))
      {
        paramValueAnimator = new StringBuilder();
        paramValueAnimator.append(paramTypedArray.getPositionDescription());
        paramValueAnimator.append(" propertyXName or propertyYName is needed for PathData");
        throw new InflateException(paramValueAnimator.toString());
      }
      parse(PathParser.createPathFromPathData(str1), paramValueAnimator, paramFloat * 0.5F, str2, paramXmlPullParser);
      return;
    }
    paramValueAnimator.setPropertyName(TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "propertyName", 0));
  }
  
  private static PropertyValuesHolder create(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, String paramString, int paramInt)
  {
    Object localObject1 = null;
    int j = paramInt;
    for (;;)
    {
      paramInt = paramXmlPullParser.next();
      if ((paramInt == 3) || (paramInt == 1)) {
        break;
      }
      if (paramXmlPullParser.getName().equals("keyframe"))
      {
        paramInt = j;
        if (j == 4) {
          paramInt = inflate(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramXmlPullParser);
        }
        Keyframe localKeyframe = inflate(paramContext, paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramInt, paramXmlPullParser);
        Object localObject2 = localObject1;
        if (localKeyframe != null)
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArrayList();
          }
          ((ArrayList)localObject2).add(localKeyframe);
        }
        paramXmlPullParser.next();
        localObject1 = localObject2;
        j = paramInt;
      }
    }
    if (localObject1 != null)
    {
      int k = localObject1.size();
      int i = k;
      if (k > 0)
      {
        int m = 0;
        paramContext = (Keyframe)localObject1.get(0);
        paramResources = (Keyframe)localObject1.get(k - 1);
        float f = paramResources.getFraction();
        paramInt = i;
        if (f < 1.0F) {
          if (f < 0.0F)
          {
            paramResources.setFraction(1.0F);
            paramInt = i;
          }
          else
          {
            localObject1.add(localObject1.size(), toString(paramResources, 1.0F));
            paramInt = k + 1;
          }
        }
        f = paramContext.getFraction();
        k = paramInt;
        if (f != 0.0F) {
          if (f < 0.0F)
          {
            paramContext.setFraction(0.0F);
            k = paramInt;
          }
          else
          {
            localObject1.add(0, toString(paramContext, 0.0F));
            k = paramInt + 1;
          }
        }
        paramContext = new Keyframe[k];
        localObject1.toArray(paramContext);
        paramInt = m;
        while (paramInt < k)
        {
          paramResources = paramContext[paramInt];
          if (paramResources.getFraction() < 0.0F) {
            if (paramInt == 0)
            {
              paramResources.setFraction(0.0F);
            }
            else
            {
              int n = k - 1;
              if (paramInt == n)
              {
                paramResources.setFraction(1.0F);
              }
              else
              {
                i = paramInt + 1;
                m = paramInt;
                while ((i < n) && (paramContext[i].getFraction() < 0.0F))
                {
                  m = i;
                  i += 1;
                }
                getValue(paramContext, paramContext[(m + 1)].getFraction() - paramContext[(paramInt - 1)].getFraction(), paramInt, m);
              }
            }
          }
          paramInt += 1;
        }
        paramContext = PropertyValuesHolder.ofKeyframe(paramString, paramContext);
        if (j != 3) {
          return paramContext;
        }
        paramContext.setEvaluator(Method.getMethod());
        return paramContext;
      }
    }
    return null;
    return paramContext;
  }
  
  private static Animator createAnimatorFromXml(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, float paramFloat)
  {
    return createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0, paramFloat);
  }
  
  private static Animator createAnimatorFromXml(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt, float paramFloat)
  {
    int k = paramXmlPullParser.getDepth();
    Object localObject3 = null;
    Object localObject2 = null;
    int j;
    int i;
    for (;;)
    {
      int m = paramXmlPullParser.next();
      j = 0;
      i = 0;
      if (((m == 3) && (paramXmlPullParser.getDepth() <= k)) || (m == 1)) {
        break label335;
      }
      if (m == 2)
      {
        Object localObject1 = paramXmlPullParser.getName();
        if (((String)localObject1).equals("objectAnimator")) {
          localObject1 = loadObjectAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, paramFloat, paramXmlPullParser);
        }
        for (;;)
        {
          break;
          if (((String)localObject1).equals("animator"))
          {
            localObject1 = loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, null, paramFloat, paramXmlPullParser);
          }
          else if (((String)localObject1).equals("set"))
          {
            localObject1 = new AnimatorSet();
            localObject3 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.AnimatorSet);
            createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, paramAttributeSet, (AnimatorSet)localObject1, TypedArrayUtils.getString((TypedArray)localObject3, paramXmlPullParser, "ordering", 0, 0), paramFloat);
            ((TypedArray)localObject3).recycle();
          }
          else
          {
            if (!((String)localObject1).equals("propertyValuesHolder")) {
              break label297;
            }
            localObject1 = inflate(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser));
            if ((localObject1 != null) && (localObject3 != null) && ((localObject3 instanceof ValueAnimator))) {
              ((ValueAnimator)localObject3).setValues((PropertyValuesHolder[])localObject1);
            }
            i = 1;
            localObject1 = localObject3;
          }
        }
        localObject3 = localObject1;
        if (paramAnimatorSet != null)
        {
          localObject3 = localObject1;
          if (i == 0)
          {
            Object localObject4 = localObject2;
            if (localObject2 == null) {
              localObject4 = new ArrayList();
            }
            ((ArrayList)localObject4).add(localObject1);
            localObject3 = localObject1;
            localObject2 = localObject4;
          }
        }
      }
    }
    label297:
    paramContext = new StringBuilder();
    paramContext.append("Unknown animator name: ");
    paramContext.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramContext.toString());
    label335:
    if ((paramAnimatorSet != null) && (localObject2 != null))
    {
      paramContext = new Animator[localObject2.size()];
      paramResources = localObject2.iterator();
      i = j;
      while (paramResources.hasNext())
      {
        paramContext[i] = ((Animator)paramResources.next());
        i += 1;
      }
      if (paramInt == 0)
      {
        paramAnimatorSet.playTogether(paramContext);
        return localObject3;
      }
      paramAnimatorSet.playSequentially(paramContext);
    }
    return localObject3;
  }
  
  public static Animator getDrawable(android.content.Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return AnimatorInflater.loadAnimator(paramContext, paramInt);
    }
    return getDrawable(paramContext, paramContext.getResources(), paramContext.getTheme(), paramInt);
  }
  
  public static Animator getDrawable(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt)
  {
    return loadAnimator(paramContext, paramResources, paramTheme, paramInt, 1.0F);
  }
  
  private static int getType(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    TypedValue localTypedValue = paramTypedArray.peekValue(paramInt1);
    int j = 1;
    if (localTypedValue != null) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    int i;
    if (paramInt1 != 0) {
      i = type;
    } else {
      i = 0;
    }
    paramTypedArray = paramTypedArray.peekValue(paramInt2);
    if (paramTypedArray != null) {
      paramInt2 = j;
    } else {
      paramInt2 = 0;
    }
    if (paramInt2 != 0) {
      j = type;
    } else {
      j = 0;
    }
    if (((paramInt1 != 0) && (open(i))) || ((paramInt2 != 0) && (open(j)))) {
      return 3;
    }
    return 0;
  }
  
  private static PropertyValuesHolder getType(TypedArray paramTypedArray, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    Object localObject1 = paramTypedArray.peekValue(paramInt2);
    int j;
    if (localObject1 != null) {
      j = 1;
    } else {
      j = 0;
    }
    int m;
    if (j != 0) {
      m = type;
    } else {
      m = 0;
    }
    localObject1 = paramTypedArray.peekValue(paramInt3);
    int k;
    if (localObject1 != null) {
      k = 1;
    } else {
      k = 0;
    }
    int n;
    if (k != 0) {
      n = type;
    } else {
      n = 0;
    }
    int i = paramInt1;
    if (paramInt1 == 4) {
      if (((j != 0) && (open(m))) || ((k != 0) && (open(n)))) {
        i = 3;
      } else {
        i = 0;
      }
    }
    if (i == 0) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    localObject1 = null;
    Object localObject2;
    if (i == 2)
    {
      localObject1 = paramTypedArray.getString(paramInt2);
      paramTypedArray = paramTypedArray.getString(paramInt3);
      localObject2 = PathParser.createNodesFromPathData((String)localObject1);
      PathParser.PathDataNode[] arrayOfPathDataNode = PathParser.createNodesFromPathData(paramTypedArray);
      if ((localObject2 != null) || (arrayOfPathDataNode != null))
      {
        if (localObject2 != null)
        {
          Label localLabel = new Label();
          if (arrayOfPathDataNode != null)
          {
            if (PathParser.canMorph((PathParser.PathDataNode[])localObject2, arrayOfPathDataNode)) {
              return PropertyValuesHolder.ofObject(paramString, localLabel, new Object[] { localObject2, arrayOfPathDataNode });
            }
            paramString = new StringBuilder();
            paramString.append(" Can't morph from ");
            paramString.append((String)localObject1);
            paramString.append(" to ");
            paramString.append(paramTypedArray);
            throw new InflateException(paramString.toString());
          }
          return PropertyValuesHolder.ofObject(paramString, localLabel, new Object[] { localObject2 });
        }
        if (arrayOfPathDataNode != null) {
          return PropertyValuesHolder.ofObject(paramString, new Label(), new Object[] { arrayOfPathDataNode });
        }
      }
    }
    else
    {
      if (i == 3) {
        localObject2 = Method.getMethod();
      } else {
        localObject2 = null;
      }
      if (paramInt1 != 0)
      {
        float f1;
        if (j != 0)
        {
          if (m == 5) {
            f1 = paramTypedArray.getDimension(paramInt2, 0.0F);
          } else {
            f1 = paramTypedArray.getFloat(paramInt2, 0.0F);
          }
          if (k != 0)
          {
            float f2;
            if (n == 5) {
              f2 = paramTypedArray.getDimension(paramInt3, 0.0F);
            } else {
              f2 = paramTypedArray.getFloat(paramInt3, 0.0F);
            }
            localObject1 = PropertyValuesHolder.ofFloat(paramString, new float[] { f1, f2 });
          }
          else
          {
            localObject1 = PropertyValuesHolder.ofFloat(paramString, new float[] { f1 });
          }
        }
        else
        {
          if (n == 5) {
            f1 = paramTypedArray.getDimension(paramInt3, 0.0F);
          } else {
            f1 = paramTypedArray.getFloat(paramInt3, 0.0F);
          }
          localObject1 = PropertyValuesHolder.ofFloat(paramString, new float[] { f1 });
        }
      }
      else if (j != 0)
      {
        if (m == 5) {
          paramInt1 = (int)paramTypedArray.getDimension(paramInt2, 0.0F);
        } else if (open(m)) {
          paramInt1 = paramTypedArray.getColor(paramInt2, 0);
        } else {
          paramInt1 = paramTypedArray.getInt(paramInt2, 0);
        }
        if (k != 0)
        {
          if (n == 5) {
            paramInt2 = (int)paramTypedArray.getDimension(paramInt3, 0.0F);
          } else if (open(n)) {
            paramInt2 = paramTypedArray.getColor(paramInt3, 0);
          } else {
            paramInt2 = paramTypedArray.getInt(paramInt3, 0);
          }
          localObject1 = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1, paramInt2 });
        }
        else
        {
          localObject1 = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1 });
        }
      }
      else if (k != 0)
      {
        if (n == 5) {
          paramInt1 = (int)paramTypedArray.getDimension(paramInt3, 0.0F);
        } else if (open(n)) {
          paramInt1 = paramTypedArray.getColor(paramInt3, 0);
        } else {
          paramInt1 = paramTypedArray.getInt(paramInt3, 0);
        }
        localObject1 = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1 });
      }
      if ((localObject1 == null) || (localObject2 == null)) {
        break label708;
      }
      ((PropertyValuesHolder)localObject1).setEvaluator((TypeEvaluator)localObject2);
      return localObject1;
    }
    return null;
    label708:
    return localObject1;
  }
  
  private static void getValue(Keyframe[] paramArrayOfKeyframe, float paramFloat, int paramInt1, int paramInt2)
  {
    paramFloat /= (paramInt2 - paramInt1 + 2);
    while (paramInt1 <= paramInt2)
    {
      paramArrayOfKeyframe[paramInt1].setFraction(paramArrayOfKeyframe[(paramInt1 - 1)].getFraction() + paramFloat);
      paramInt1 += 1;
    }
  }
  
  private static int inflate(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser)
  {
    paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableGroup);
    int k = 0;
    paramTheme = TypedArrayUtils.getNamedFloat(paramResources, paramXmlPullParser, "value", 0);
    int i;
    if (paramTheme != null) {
      i = 1;
    } else {
      i = 0;
    }
    int j = k;
    if (i != 0)
    {
      j = k;
      if (open(type)) {
        j = 3;
      }
    }
    paramResources.recycle();
    return j;
  }
  
  private static Keyframe inflate(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int paramInt, XmlPullParser paramXmlPullParser)
  {
    paramTheme = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableGroup);
    float f = TypedArrayUtils.getNamedFloat(paramTheme, paramXmlPullParser, "fraction", 3, -1.0F);
    paramResources = TypedArrayUtils.getNamedFloat(paramTheme, paramXmlPullParser, "value", 0);
    int j;
    if (paramResources != null) {
      j = 1;
    } else {
      j = 0;
    }
    int i = paramInt;
    if (paramInt == 4) {
      if ((j != 0) && (open(type))) {
        i = 3;
      } else {
        i = 0;
      }
    }
    if (j != 0)
    {
      if (i != 0)
      {
        if ((i != 1) && (i != 3)) {
          paramResources = null;
        } else {
          paramResources = Keyframe.ofInt(f, TypedArrayUtils.getString(paramTheme, paramXmlPullParser, "value", 0, 0));
        }
      }
      else {
        paramResources = Keyframe.ofFloat(f, TypedArrayUtils.getNamedFloat(paramTheme, paramXmlPullParser, "value", 0, 0.0F));
      }
    }
    else if (i == 0) {
      paramResources = Keyframe.ofFloat(f);
    } else {
      paramResources = Keyframe.ofInt(f);
    }
    paramInt = TypedArrayUtils.getResourceId(paramTheme, paramXmlPullParser, "interpolator", 1, 0);
    if (paramInt > 0) {
      paramResources.setInterpolator(ArgbEvaluator.loadAnimator(paramContext, paramInt));
    }
    paramTheme.recycle();
    return paramResources;
  }
  
  private static PropertyValuesHolder[] inflate(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
  {
    Object localObject1 = null;
    int j = paramXmlPullParser.getEventType();
    int i = 0;
    if ((j != 3) && (j != 1))
    {
      if (j != 2) {}
      for (;;)
      {
        paramXmlPullParser.next();
        break;
        if (paramXmlPullParser.getName().equals("propertyValuesHolder"))
        {
          TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.LayerDrawableItem);
          String str = TypedArrayUtils.getString(localTypedArray, paramXmlPullParser, "propertyName", 3);
          i = TypedArrayUtils.getString(localTypedArray, paramXmlPullParser, "valueType", 2, 4);
          Object localObject2 = create(paramContext, paramResources, paramTheme, paramXmlPullParser, str, i);
          Object localObject3 = localObject2;
          if (localObject2 == null) {
            localObject3 = getType(localTypedArray, i, 0, 1, str);
          }
          localObject2 = localObject1;
          if (localObject3 != null)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localObject3);
          }
          localTypedArray.recycle();
          localObject1 = localObject2;
        }
      }
    }
    if (localObject1 != null)
    {
      j = localObject1.size();
      paramContext = new PropertyValuesHolder[j];
      while (i < j)
      {
        paramContext[i] = ((PropertyValuesHolder)localObject1.get(i));
        i += 1;
      }
    }
    return null;
    return paramContext;
  }
  
  private static void load(ValueAnimator paramValueAnimator, TypedArray paramTypedArray1, TypedArray paramTypedArray2, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    long l1 = TypedArrayUtils.getString(paramTypedArray1, paramXmlPullParser, "duration", 1, 300);
    long l2 = TypedArrayUtils.getString(paramTypedArray1, paramXmlPullParser, "startOffset", 2, 0);
    int k = TypedArrayUtils.getString(paramTypedArray1, paramXmlPullParser, "valueType", 7, 4);
    int i = k;
    int j = i;
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueFrom"))
    {
      j = i;
      if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueTo"))
      {
        if (k == 4) {
          i = getType(paramTypedArray1, 5, 6);
        }
        PropertyValuesHolder localPropertyValuesHolder = getType(paramTypedArray1, i, 5, 6, "");
        j = i;
        if (localPropertyValuesHolder != null)
        {
          paramValueAnimator.setValues(new PropertyValuesHolder[] { localPropertyValuesHolder });
          j = i;
        }
      }
    }
    paramValueAnimator.setDuration(l1);
    paramValueAnimator.setStartDelay(l2);
    paramValueAnimator.setRepeatCount(TypedArrayUtils.getString(paramTypedArray1, paramXmlPullParser, "repeatCount", 3, 0));
    paramValueAnimator.setRepeatMode(TypedArrayUtils.getString(paramTypedArray1, paramXmlPullParser, "repeatMode", 4, 1));
    if (paramTypedArray2 != null) {
      build(paramValueAnimator, paramTypedArray2, j, paramFloat, paramXmlPullParser);
    }
  }
  
  public static Animator loadAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt, float paramFloat)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      XmlResourceParser localXmlResourceParser = paramResources.getAnimation(paramInt);
      localObject2 = localXmlResourceParser;
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramContext = createAnimatorFromXml(paramContext, paramResources, paramTheme, localXmlResourceParser, paramFloat);
      if (localXmlResourceParser == null) {
        return paramContext;
      }
      localXmlResourceParser.close();
      return paramContext;
    }
    catch (Throwable paramContext) {}catch (IOException paramContext)
    {
      localObject1 = localObject3;
      paramResources = new StringBuilder();
      localObject1 = localObject3;
      paramResources.append("Can't load animation resource ID #0x");
      localObject1 = localObject3;
      paramResources.append(Integer.toHexString(paramInt));
      localObject1 = localObject3;
      paramResources = new Resources.NotFoundException(paramResources.toString());
      localObject1 = localObject3;
      paramResources.initCause(paramContext);
      localObject1 = localObject3;
      throw paramResources;
    }
    catch (XmlPullParserException paramContext)
    {
      localObject1 = localObject2;
      paramResources = new StringBuilder();
      localObject1 = localObject2;
      paramResources.append("Can't load animation resource ID #0x");
      localObject1 = localObject2;
      paramResources.append(Integer.toHexString(paramInt));
      localObject1 = localObject2;
      paramResources = new Resources.NotFoundException(paramResources.toString());
      localObject1 = localObject2;
      paramResources.initCause(paramContext);
      localObject1 = localObject2;
      throw paramResources;
    }
    if (localObject1 != null) {
      localObject1.close();
    }
    throw paramContext;
    return paramContext;
  }
  
  private static ValueAnimator loadAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.Animator);
    paramTheme = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawablePath);
    paramResources = paramValueAnimator;
    if (paramValueAnimator == null) {
      paramResources = new ValueAnimator();
    }
    load(paramResources, localTypedArray, paramTheme, paramFloat, paramXmlPullParser);
    int i = TypedArrayUtils.getResourceId(localTypedArray, paramXmlPullParser, "interpolator", 0, 0);
    if (i > 0) {
      paramResources.setInterpolator(ArgbEvaluator.loadAnimator(paramContext, i));
    }
    localTypedArray.recycle();
    if (paramTheme != null) {
      paramTheme.recycle();
    }
    return paramResources;
  }
  
  private static ObjectAnimator loadObjectAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    ObjectAnimator localObjectAnimator = new ObjectAnimator();
    loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, localObjectAnimator, paramFloat, paramXmlPullParser);
    return localObjectAnimator;
  }
  
  private static boolean open(int paramInt)
  {
    return (paramInt >= 28) && (paramInt <= 31);
  }
  
  private static void parse(Path paramPath, ObjectAnimator paramObjectAnimator, float paramFloat, String paramString1, String paramString2)
  {
    PathMeasure localPathMeasure = new PathMeasure(paramPath, false);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Float.valueOf(0.0F));
    float f1 = 0.0F;
    float f2;
    do
    {
      f2 = f1 + localPathMeasure.getLength();
      localArrayList.add(Float.valueOf(f2));
      f1 = f2;
    } while (localPathMeasure.nextContour());
    paramPath = new PathMeasure(paramPath, false);
    int n = Math.min(100, (int)(f2 / paramFloat) + 1);
    float[] arrayOfFloat2 = new float[n];
    float[] arrayOfFloat1 = new float[n];
    float[] arrayOfFloat3 = new float[2];
    f1 = f2 / (n - 1);
    int i = 0;
    paramFloat = 0.0F;
    int k;
    for (int j = 0;; j = k)
    {
      localPathMeasure = null;
      if (i >= n) {
        break;
      }
      paramPath.getPosTan(paramFloat - ((Float)localArrayList.get(j)).floatValue(), arrayOfFloat3, null);
      arrayOfFloat2[i] = arrayOfFloat3[0];
      arrayOfFloat1[i] = arrayOfFloat3[1];
      paramFloat += f1;
      int m = j + 1;
      k = j;
      if (m < localArrayList.size())
      {
        k = j;
        if (paramFloat > ((Float)localArrayList.get(m)).floatValue())
        {
          paramPath.nextContour();
          k = m;
        }
      }
      i += 1;
    }
    if (paramString1 != null) {
      paramPath = PropertyValuesHolder.ofFloat(paramString1, arrayOfFloat2);
    } else {
      paramPath = null;
    }
    paramString1 = localPathMeasure;
    if (paramString2 != null) {
      paramString1 = PropertyValuesHolder.ofFloat(paramString2, arrayOfFloat1);
    }
    if (paramPath == null)
    {
      paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramString1 });
      return;
    }
    if (paramString1 == null)
    {
      paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramPath });
      return;
    }
    paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramPath, paramString1 });
  }
  
  private static Keyframe toString(Keyframe paramKeyframe, float paramFloat)
  {
    if (paramKeyframe.getType() == Float.TYPE) {
      return Keyframe.ofFloat(paramFloat);
    }
    if (paramKeyframe.getType() == Integer.TYPE) {
      return Keyframe.ofInt(paramFloat);
    }
    return Keyframe.ofObject(paramFloat);
  }
}
