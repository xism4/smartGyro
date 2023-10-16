package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.org.android.util.ArrayMap;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.styleable;
import com.org.v4.view.ContextThemeWrapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater
{
  private static final String[] sClassPrefixList = { "android.widget.", "android.view.", "android.webkit." };
  private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
  private static final Class<?>[] sConstructorSignature = { Context.class, AttributeSet.class };
  private static final int[] sOnClickAttrs = { 16843375 };
  private final Object[] mConstructorArgs = new Object[2];
  
  public AppCompatViewInflater() {}
  
  private void checkOnClickListener(View paramView, AttributeSet paramAttributeSet)
  {
    Object localObject = paramView.getContext();
    if ((localObject instanceof ContextWrapper))
    {
      if ((Build.VERSION.SDK_INT >= 15) && (!ViewCompat.hasOnClickListeners(paramView))) {
        return;
      }
      paramAttributeSet = ((Context)localObject).obtainStyledAttributes(paramAttributeSet, sOnClickAttrs);
      localObject = paramAttributeSet.getString(0);
      if (localObject != null) {
        paramView.setOnClickListener(new a(paramView, (String)localObject));
      }
      paramAttributeSet.recycle();
    }
  }
  
  private View createView(Context paramContext, String paramString1, String paramString2)
  {
    Constructor localConstructor = (Constructor)sConstructorMap.get(paramString1);
    Object localObject = localConstructor;
    if (localConstructor == null) {}
    try
    {
      localObject = paramContext.getClassLoader();
      if (paramString2 != null)
      {
        paramContext = new StringBuilder();
        paramContext.append(paramString2);
        paramContext.append(paramString1);
        paramContext = paramContext.toString();
      }
      else
      {
        paramContext = paramString1;
      }
      paramContext = ((ClassLoader)localObject).loadClass(paramContext).asSubclass(View.class);
      paramString2 = sConstructorSignature;
      paramContext = paramContext.getConstructor(paramString2);
      localObject = paramContext;
      paramString2 = sConstructorMap;
      paramString2.put(paramString1, paramContext);
      ((Constructor)localObject).setAccessible(true);
      paramContext = mConstructorArgs;
      paramContext = ((Constructor)localObject).newInstance(paramContext);
      return (View)paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 6
    //   3: aload_2
    //   4: ldc -102
    //   6: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +14 -> 23
    //   12: aload_3
    //   13: aconst_null
    //   14: ldc -96
    //   16: invokeinterface 164 3 0
    //   21: astore 6
    //   23: aload_0
    //   24: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   27: iconst_0
    //   28: aload_1
    //   29: aastore
    //   30: aload_0
    //   31: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   34: iconst_1
    //   35: aload_3
    //   36: aastore
    //   37: aload 6
    //   39: bipush 46
    //   41: invokevirtual 168	java/lang/String:indexOf	(I)I
    //   44: istore 4
    //   46: iconst_m1
    //   47: iload 4
    //   49: if_icmpne +78 -> 127
    //   52: iconst_0
    //   53: istore 4
    //   55: getstatic 42	android/support/v7/app/AppCompatViewInflater:sClassPrefixList	[Ljava/lang/String;
    //   58: arraylength
    //   59: istore 5
    //   61: iload 4
    //   63: iload 5
    //   65: if_icmpge +47 -> 112
    //   68: getstatic 42	android/support/v7/app/AppCompatViewInflater:sClassPrefixList	[Ljava/lang/String;
    //   71: iload 4
    //   73: aaload
    //   74: astore_2
    //   75: aload_0
    //   76: aload_1
    //   77: aload 6
    //   79: aload_2
    //   80: invokespecial 170	android/support/v7/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   83: astore_2
    //   84: aload_2
    //   85: ifnull +18 -> 103
    //   88: aload_0
    //   89: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   92: astore_1
    //   93: aload_1
    //   94: iconst_0
    //   95: aconst_null
    //   96: aastore
    //   97: aload_1
    //   98: iconst_1
    //   99: aconst_null
    //   100: aastore
    //   101: aload_2
    //   102: areturn
    //   103: iload 4
    //   105: iconst_1
    //   106: iadd
    //   107: istore 4
    //   109: goto -54 -> 55
    //   112: aload_0
    //   113: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   116: astore_1
    //   117: aload_1
    //   118: iconst_0
    //   119: aconst_null
    //   120: aastore
    //   121: aload_1
    //   122: iconst_1
    //   123: aconst_null
    //   124: aastore
    //   125: aconst_null
    //   126: areturn
    //   127: aload_0
    //   128: aload_1
    //   129: aload 6
    //   131: aconst_null
    //   132: invokespecial 170	android/support/v7/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   135: astore_1
    //   136: aload_0
    //   137: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   140: astore_2
    //   141: aload_2
    //   142: iconst_0
    //   143: aconst_null
    //   144: aastore
    //   145: aload_2
    //   146: iconst_1
    //   147: aconst_null
    //   148: aastore
    //   149: aload_1
    //   150: areturn
    //   151: astore_1
    //   152: aload_0
    //   153: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   156: astore_2
    //   157: aload_2
    //   158: iconst_0
    //   159: aconst_null
    //   160: aastore
    //   161: aload_2
    //   162: iconst_1
    //   163: aconst_null
    //   164: aastore
    //   165: aload_1
    //   166: athrow
    //   167: aload_0
    //   168: getfield 53	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   171: astore_1
    //   172: aload_1
    //   173: iconst_0
    //   174: aconst_null
    //   175: aastore
    //   176: aload_1
    //   177: iconst_1
    //   178: aconst_null
    //   179: aastore
    //   180: aconst_null
    //   181: areturn
    //   182: astore_1
    //   183: goto -16 -> 167
    //   186: astore_1
    //   187: goto -20 -> 167
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	AppCompatViewInflater
    //   0	190	1	paramContext	Context
    //   0	190	2	paramString	String
    //   0	190	3	paramAttributeSet	AttributeSet
    //   44	64	4	i	int
    //   59	7	5	j	int
    //   1	129	6	str	String
    // Exception table:
    //   from	to	target	type
    //   37	46	151	java/lang/Throwable
    //   55	61	151	java/lang/Throwable
    //   75	84	151	java/lang/Throwable
    //   127	136	151	java/lang/Throwable
    //   37	46	182	java/lang/Exception
    //   75	84	182	java/lang/Exception
    //   127	136	186	java/lang/Exception
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    int i;
    if (paramBoolean1) {
      i = paramAttributeSet.getResourceId(R.styleable.View_android_theme, 0);
    } else {
      i = 0;
    }
    int j = i;
    if (paramBoolean2)
    {
      j = i;
      if (i == 0)
      {
        int k = paramAttributeSet.getResourceId(R.styleable.View_theme, 0);
        i = k;
        j = i;
        if (k != 0)
        {
          Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
          j = i;
        }
      }
    }
    paramAttributeSet.recycle();
    if ((j != 0) && ((!(paramContext instanceof ContextThemeWrapper)) || (((ContextThemeWrapper)paramContext).getThemeResId() != j))) {
      return new ContextThemeWrapper(paramContext, j);
    }
    return paramContext;
  }
  
  private void wrap(View paramView, String paramString)
  {
    if (paramView != null) {
      return;
    }
    paramView = new StringBuilder();
    paramView.append(AppCompatViewInflater.class.getName());
    paramView.append(" asked to inflate view for <");
    paramView.append(paramString);
    paramView.append(">, but returned null");
    throw new IllegalStateException(paramView.toString());
  }
  
  protected AppCompatCheckedTextView build(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatCheckedTextView(paramContext, paramAttributeSet);
  }
  
  protected SwitchCompat createListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new SwitchCompat(paramContext, paramAttributeSet);
  }
  
  protected AppCompatButton createView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatButton(paramContext, paramAttributeSet);
  }
  
  protected View createView(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    return null;
  }
  
  final View createView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if ((paramBoolean1) && (paramView != null)) {
      localObject = paramView.getContext();
    } else {
      localObject = paramContext;
    }
    if (!paramBoolean2)
    {
      paramView = (View)localObject;
      if (!paramBoolean3) {}
    }
    else
    {
      paramView = themifyContext((Context)localObject, paramAttributeSet, paramBoolean2, paramBoolean3);
    }
    Object localObject = paramView;
    if (paramBoolean4) {
      localObject = TintContextWrapper.wrap(paramView);
    }
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 2001146706: 
      if (paramString.equals("Button")) {
        i = 2;
      }
      break;
    case 1666676343: 
      if (paramString.equals("EditText")) {
        i = 3;
      }
      break;
    case 1601505219: 
      if (paramString.equals("CheckBox")) {
        i = 6;
      }
      break;
    case 1413872058: 
      if (paramString.equals("AutoCompleteTextView")) {
        i = 9;
      }
      break;
    case 1125864064: 
      if (paramString.equals("ImageView")) {
        i = 1;
      }
      break;
    case 776382189: 
      if (paramString.equals("RadioButton")) {
        i = 7;
      }
      break;
    case -339785223: 
      if (paramString.equals("Spinner")) {
        i = 4;
      }
      break;
    case -658531749: 
      if (paramString.equals("SeekBar")) {
        i = 12;
      }
      break;
    case -937446323: 
      if (paramString.equals("ImageButton")) {
        i = 5;
      }
      break;
    case -938935918: 
      if (paramString.equals("TextView")) {
        i = 0;
      }
      break;
    case -1346021293: 
      if (paramString.equals("MultiAutoCompleteTextView")) {
        i = 10;
      }
      break;
    case -1455429095: 
      if (paramString.equals("CheckedTextView")) {
        i = 8;
      }
      break;
    case -1946472170: 
      if (paramString.equals("RatingBar")) {
        i = 11;
      }
      break;
    }
    switch (i)
    {
    default: 
      paramView = createView((Context)localObject, paramString, paramAttributeSet);
      break;
    case 12: 
      paramView = createListView((Context)localObject, paramAttributeSet);
      break;
    case 11: 
      paramView = newInstance((Context)localObject, paramAttributeSet);
      break;
    case 10: 
      paramView = init((Context)localObject, paramAttributeSet);
      break;
    case 9: 
      paramView = getDrawable((Context)localObject, paramAttributeSet);
      break;
    case 8: 
      paramView = build((Context)localObject, paramAttributeSet);
      break;
    case 7: 
      paramView = createViewFromTag((Context)localObject, paramAttributeSet);
      break;
    case 6: 
      paramView = wrap((Context)localObject, paramAttributeSet);
      break;
    case 5: 
      paramView = get((Context)localObject, paramAttributeSet);
      break;
    case 4: 
      paramView = themifyContext((Context)localObject, paramAttributeSet);
      break;
    case 3: 
      paramView = inflate((Context)localObject, paramAttributeSet);
      break;
    case 2: 
      paramView = createView((Context)localObject, paramAttributeSet);
      break;
    case 1: 
      paramView = onCreateView((Context)localObject, paramAttributeSet);
      break;
    case 0: 
      paramView = setTextAppearance((Context)localObject, paramAttributeSet);
    }
    wrap(paramView, paramString);
    View localView = paramView;
    if (paramView == null)
    {
      localView = paramView;
      if (paramContext != localObject) {
        localView = createViewFromTag((Context)localObject, paramString, paramAttributeSet);
      }
    }
    if (localView != null) {
      checkOnClickListener(localView, paramAttributeSet);
    }
    return localView;
  }
  
  protected AppCompatRadioButton createViewFromTag(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatRadioButton(paramContext, paramAttributeSet);
  }
  
  protected AppCompatImageButton get(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatImageButton(paramContext, paramAttributeSet);
  }
  
  protected AppCompatAutoCompleteTextView getDrawable(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatEditText inflate(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatEditText(paramContext, paramAttributeSet);
  }
  
  protected AppCompatMultiAutoCompleteTextView init(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatMultiAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatRatingBar newInstance(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatRatingBar(paramContext, paramAttributeSet);
  }
  
  protected AppCompatImageView onCreateView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatImageView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatTextView setTextAppearance(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatSpinner themifyContext(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatSpinner(paramContext, paramAttributeSet);
  }
  
  protected AppCompatCheckBox wrap(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatCheckBox(paramContext, paramAttributeSet);
  }
  
  private static class a
    implements View.OnClickListener
  {
    private final View mHostView;
    private final String mMethodName;
    private Context mResolvedContext;
    private Method mResolvedMethod;
    
    public a(View paramView, String paramString)
    {
      mHostView = paramView;
      mMethodName = paramString;
    }
    
    private void resolveMethod(Context paramContext, String paramString)
    {
      while (paramContext != null)
      {
        try
        {
          boolean bool = paramContext.isRestricted();
          if (!bool)
          {
            paramString = paramContext.getClass();
            String str = mMethodName;
            paramString = paramString.getMethod(str, new Class[] { View.class });
            if (paramString != null)
            {
              mResolvedMethod = paramString;
              mResolvedContext = paramContext;
              return;
            }
          }
        }
        catch (NoSuchMethodException paramString)
        {
          int i;
          for (;;) {}
        }
        if ((paramContext instanceof ContextWrapper)) {
          paramContext = ((ContextWrapper)paramContext).getBaseContext();
        } else {
          paramContext = null;
        }
      }
      i = mHostView.getId();
      if (i == -1)
      {
        paramContext = "";
      }
      else
      {
        paramContext = new StringBuilder();
        paramContext.append(" with id '");
        paramContext.append(mHostView.getContext().getResources().getResourceEntryName(i));
        paramContext.append("'");
        paramContext = paramContext.toString();
      }
      paramString = new StringBuilder();
      paramString.append("Could not find method ");
      paramString.append(mMethodName);
      paramString.append("(View) in a parent or ancestor Context for android:onClick ");
      paramString.append("attribute defined on view ");
      paramString.append(mHostView.getClass());
      paramString.append(paramContext);
      paramContext = new IllegalStateException(paramString.toString());
      throw paramContext;
    }
    
    public void onClick(View paramView)
    {
      if (mResolvedMethod == null) {
        resolveMethod(mHostView.getContext(), mMethodName);
      }
      Method localMethod = mResolvedMethod;
      Context localContext = mResolvedContext;
      try
      {
        localMethod.invoke(localContext, new Object[] { paramView });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new IllegalStateException("Could not execute method for android:onClick", paramView);
      }
      catch (IllegalAccessException paramView)
      {
        throw new IllegalStateException("Could not execute non-public method for android:onClick", paramView);
      }
    }
  }
}
