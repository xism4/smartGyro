package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.org.android.view.TintableBackgroundView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.attr;

public class AppCompatSpinner
  extends Spinner
  implements TintableBackgroundView
{
  private static final int[] ATTRS_ANDROID_SPINNERMODE = { 16843505 };
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  int mDropDownWidth;
  private ListPopupWindow.ForwardingListener mForwardingListener;
  DropdownPopup mPopup;
  private final Context mPopupContext;
  private final boolean mPopupSet;
  private SpinnerAdapter mTempAdapter;
  final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  /* Error */
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, android.content.res.Resources.Theme paramTheme)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial 55	android/widget/Spinner:<init>	(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new 57	android/graphics/Rect
    //   11: dup
    //   12: invokespecial 59	android/graphics/Rect:<init>	()V
    //   15: putfield 61	android/support/v7/widget/AppCompatSpinner:mTempRect	Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic 66	com/org/v4/util/R$styleable:Spinner	[I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic 72	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   28: astore 11
    //   30: aload_0
    //   31: new 74	android/support/v7/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 77	android/support/v7/widget/AppCompatBackgroundHelper:<init>	(Landroid/view/View;)V
    //   39: putfield 79	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   42: aload 5
    //   44: ifnull +24 -> 68
    //   47: new 81	com/org/v4/view/ContextThemeWrapper
    //   50: dup
    //   51: aload_1
    //   52: aload 5
    //   54: invokespecial 84	com/org/v4/view/ContextThemeWrapper:<init>	(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   57: astore 5
    //   59: aload_0
    //   60: aload 5
    //   62: putfield 86	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   65: goto +57 -> 122
    //   68: aload 11
    //   70: getstatic 89	com/org/v4/util/R$styleable:Spinner_popupTheme	I
    //   73: iconst_0
    //   74: invokevirtual 93	android/support/v7/widget/TintTypedArray:getResourceId	(II)I
    //   77: istore 6
    //   79: iload 6
    //   81: ifeq +18 -> 99
    //   84: new 81	com/org/v4/view/ContextThemeWrapper
    //   87: dup
    //   88: aload_1
    //   89: iload 6
    //   91: invokespecial 96	com/org/v4/view/ContextThemeWrapper:<init>	(Landroid/content/Context;I)V
    //   94: astore 5
    //   96: goto -37 -> 59
    //   99: getstatic 101	android/os/Build$VERSION:SDK_INT	I
    //   102: bipush 23
    //   104: if_icmpge +9 -> 113
    //   107: aload_1
    //   108: astore 5
    //   110: goto +6 -> 116
    //   113: aconst_null
    //   114: astore 5
    //   116: aload_0
    //   117: aload 5
    //   119: putfield 86	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   122: aload_0
    //   123: getfield 86	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   126: ifnull +251 -> 377
    //   129: iload 4
    //   131: istore 7
    //   133: iload 4
    //   135: iconst_m1
    //   136: if_icmpne +138 -> 274
    //   139: getstatic 33	android/support/v7/widget/AppCompatSpinner:ATTRS_ANDROID_SPINNERMODE	[I
    //   142: astore 5
    //   144: aload_1
    //   145: aload_2
    //   146: aload 5
    //   148: iload_3
    //   149: iconst_0
    //   150: invokevirtual 106	android/content/Context:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   153: astore 10
    //   155: aload 10
    //   157: astore 5
    //   159: aload 5
    //   161: astore 9
    //   163: aload 10
    //   165: iconst_0
    //   166: invokevirtual 112	android/content/res/TypedArray:hasValue	(I)Z
    //   169: istore 8
    //   171: iload 4
    //   173: istore 6
    //   175: iload 8
    //   177: ifeq +16 -> 193
    //   180: aload 5
    //   182: astore 9
    //   184: aload 10
    //   186: iconst_0
    //   187: iconst_0
    //   188: invokevirtual 115	android/content/res/TypedArray:getInt	(II)I
    //   191: istore 6
    //   193: iload 6
    //   195: istore 7
    //   197: aload 10
    //   199: ifnull +75 -> 274
    //   202: iload 6
    //   204: istore 4
    //   206: aload 5
    //   208: invokevirtual 118	android/content/res/TypedArray:recycle	()V
    //   211: iload 4
    //   213: istore 7
    //   215: goto +59 -> 274
    //   218: astore 10
    //   220: goto +15 -> 235
    //   223: astore_1
    //   224: aconst_null
    //   225: astore 9
    //   227: goto +35 -> 262
    //   230: astore 10
    //   232: aconst_null
    //   233: astore 5
    //   235: aload 5
    //   237: astore 9
    //   239: ldc 120
    //   241: ldc 122
    //   243: aload 10
    //   245: invokestatic 128	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   248: pop
    //   249: iload 4
    //   251: istore 7
    //   253: aload 5
    //   255: ifnull +19 -> 274
    //   258: goto -52 -> 206
    //   261: astore_1
    //   262: aload 9
    //   264: ifnull +8 -> 272
    //   267: aload 9
    //   269: invokevirtual 118	android/content/res/TypedArray:recycle	()V
    //   272: aload_1
    //   273: athrow
    //   274: iload 7
    //   276: iconst_1
    //   277: if_icmpne +100 -> 377
    //   280: new 8	android/support/v7/widget/AppCompatSpinner$DropdownPopup
    //   283: dup
    //   284: aload_0
    //   285: aload_0
    //   286: getfield 86	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   289: aload_2
    //   290: iload_3
    //   291: invokespecial 131	android/support/v7/widget/AppCompatSpinner$DropdownPopup:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   294: astore 5
    //   296: aload_0
    //   297: getfield 86	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   300: aload_2
    //   301: getstatic 66	com/org/v4/util/R$styleable:Spinner	[I
    //   304: iload_3
    //   305: iconst_0
    //   306: invokestatic 72	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   309: astore 9
    //   311: aload_0
    //   312: aload 9
    //   314: getstatic 134	com/org/v4/util/R$styleable:Spinner_android_dropDownWidth	I
    //   317: bipush -2
    //   319: invokevirtual 137	android/support/v7/widget/TintTypedArray:getLayoutDimension	(II)I
    //   322: putfield 139	android/support/v7/widget/AppCompatSpinner:mDropDownWidth	I
    //   325: aload 5
    //   327: aload 9
    //   329: getstatic 142	com/org/v4/util/R$styleable:Spinner_android_popupBackground	I
    //   332: invokevirtual 146	android/support/v7/widget/TintTypedArray:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   335: invokevirtual 152	android/support/v7/widget/ListPopupWindow:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   338: aload 5
    //   340: aload 11
    //   342: getstatic 155	com/org/v4/util/R$styleable:Spinner_android_prompt	I
    //   345: invokevirtual 159	android/support/v7/widget/TintTypedArray:getString	(I)Ljava/lang/String;
    //   348: invokevirtual 163	android/support/v7/widget/AppCompatSpinner$DropdownPopup:setPromptText	(Ljava/lang/CharSequence;)V
    //   351: aload 9
    //   353: invokevirtual 164	android/support/v7/widget/TintTypedArray:recycle	()V
    //   356: aload_0
    //   357: aload 5
    //   359: putfield 166	android/support/v7/widget/AppCompatSpinner:mPopup	Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
    //   362: aload_0
    //   363: new 168	android/support/v7/widget/ActivityChooserView$3
    //   366: dup
    //   367: aload_0
    //   368: aload_0
    //   369: aload 5
    //   371: invokespecial 171	android/support/v7/widget/ActivityChooserView$3:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
    //   374: putfield 173	android/support/v7/widget/AppCompatSpinner:mForwardingListener	Landroid/support/v7/widget/ListPopupWindow$ForwardingListener;
    //   377: aload 11
    //   379: getstatic 176	com/org/v4/util/R$styleable:Spinner_android_entries	I
    //   382: invokevirtual 180	android/support/v7/widget/TintTypedArray:getTextArray	(I)[Ljava/lang/CharSequence;
    //   385: astore 5
    //   387: aload 5
    //   389: ifnull +28 -> 417
    //   392: new 182	android/widget/ArrayAdapter
    //   395: dup
    //   396: aload_1
    //   397: ldc -73
    //   399: aload 5
    //   401: invokespecial 186	android/widget/ArrayAdapter:<init>	(Landroid/content/Context;I[Ljava/lang/Object;)V
    //   404: astore_1
    //   405: aload_1
    //   406: getstatic 191	com/org/v4/util/R$layout:support_simple_spinner_dropdown_item	I
    //   409: invokevirtual 195	android/widget/ArrayAdapter:setDropDownViewResource	(I)V
    //   412: aload_0
    //   413: aload_1
    //   414: invokevirtual 199	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   417: aload 11
    //   419: invokevirtual 164	android/support/v7/widget/TintTypedArray:recycle	()V
    //   422: aload_0
    //   423: iconst_1
    //   424: putfield 201	android/support/v7/widget/AppCompatSpinner:mPopupSet	Z
    //   427: aload_0
    //   428: getfield 203	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   431: astore_1
    //   432: aload_1
    //   433: ifnull +13 -> 446
    //   436: aload_0
    //   437: aload_1
    //   438: invokevirtual 199	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   441: aload_0
    //   442: aconst_null
    //   443: putfield 203	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   446: aload_0
    //   447: getfield 79	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   450: aload_2
    //   451: iload_3
    //   452: invokevirtual 207	android/support/v7/widget/AppCompatBackgroundHelper:loadFromAttributes	(Landroid/util/AttributeSet;I)V
    //   455: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	456	0	this	AppCompatSpinner
    //   0	456	1	paramContext	Context
    //   0	456	2	paramAttributeSet	AttributeSet
    //   0	456	3	paramInt1	int
    //   0	456	4	paramInt2	int
    //   0	456	5	paramTheme	android.content.res.Resources.Theme
    //   77	126	6	i	int
    //   131	147	7	j	int
    //   169	7	8	bool	boolean
    //   161	191	9	localObject	Object
    //   153	45	10	localTypedArray	android.content.res.TypedArray
    //   218	1	10	localException1	Exception
    //   230	14	10	localException2	Exception
    //   28	390	11	localTintTypedArray	TintTypedArray
    // Exception table:
    //   from	to	target	type
    //   163	171	218	java/lang/Exception
    //   184	193	218	java/lang/Exception
    //   144	155	223	java/lang/Throwable
    //   144	155	230	java/lang/Exception
    //   163	171	261	java/lang/Throwable
    //   184	193	261	java/lang/Throwable
    //   239	249	261	java/lang/Throwable
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable)
  {
    int k = 0;
    if (paramSpinnerAdapter == null) {
      return 0;
    }
    int i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int i2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int i = Math.max(0, getSelectedItemPosition());
    int i3 = Math.min(paramSpinnerAdapter.getCount(), i + 15);
    i = Math.max(0, i - (15 - (i3 - i)));
    Object localObject = null;
    int j = 0;
    while (i < i3)
    {
      int n = paramSpinnerAdapter.getItemViewType(i);
      int m = k;
      if (n != k)
      {
        localObject = null;
        m = n;
      }
      View localView = paramSpinnerAdapter.getView(i, (View)localObject, this);
      localObject = localView;
      if (localView.getLayoutParams() == null) {
        localView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
      }
      localView.measure(i1, i2);
      j = Math.max(j, localView.getMeasuredWidth());
      i += 1;
      k = m;
    }
    if (paramDrawable != null)
    {
      paramDrawable.getPadding(mTempRect);
      paramSpinnerAdapter = mTempRect;
      return j + (left + right);
    }
    return j;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.applySupportBackgroundTint();
    }
  }
  
  public int getDropDownHorizontalOffset()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getHorizontalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownHorizontalOffset();
    }
    return 0;
  }
  
  public int getDropDownVerticalOffset()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getVerticalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownVerticalOffset();
    }
    return 0;
  }
  
  public int getDropDownWidth()
  {
    if (mPopup != null) {
      return mDropDownWidth;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownWidth();
    }
    return 0;
  }
  
  public Drawable getPopupBackground()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getBackground();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getPopupBackground();
    }
    return null;
  }
  
  public Context getPopupContext()
  {
    if (mPopup != null) {
      return mPopupContext;
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return super.getPopupContext();
    }
    return null;
  }
  
  public CharSequence getPrompt()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getHintText();
    }
    return super.getPrompt();
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintMode();
    }
    return null;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    DropdownPopup localDropdownPopup = mPopup;
    if ((localDropdownPopup != null) && (localDropdownPopup.isShowing())) {
      mPopup.dismiss();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if ((mPopup != null) && (View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)) {
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight());
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    ListPopupWindow.ForwardingListener localForwardingListener = mForwardingListener;
    if ((localForwardingListener != null) && (localForwardingListener.onTouch(this, paramMotionEvent))) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      if (!localDropdownPopup.isShowing()) {
        mPopup.show();
      }
      return true;
    }
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    if (!mPopupSet)
    {
      mTempAdapter = paramSpinnerAdapter;
      return;
    }
    super.setAdapter(paramSpinnerAdapter);
    if (mPopup != null)
    {
      Context localContext2 = mPopupContext;
      Context localContext1 = localContext2;
      if (localContext2 == null) {
        localContext1 = getContext();
      }
      mPopup.setAdapter(new SpinnerCompat.DropDownAdapter(paramSpinnerAdapter, localContext1.getTheme()));
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundResource(paramInt);
    }
  }
  
  public void setDropDownHorizontalOffset(int paramInt)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setAdapter(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownHorizontalOffset(paramInt);
    }
  }
  
  public void setDropDownVerticalOffset(int paramInt)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setVerticalOffset(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownVerticalOffset(paramInt);
    }
  }
  
  public void setDropDownWidth(int paramInt)
  {
    if (mPopup != null)
    {
      mDropDownWidth = paramInt;
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownWidth(paramInt);
    }
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setBackgroundDrawable(paramDrawable);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setPopupBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setPopupBackgroundResource(int paramInt)
  {
    setPopupBackgroundDrawable(com.org.v4.text.view.Resources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setPromptText(paramCharSequence);
      return;
    }
    super.setPrompt(paramCharSequence);
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode);
    }
  }
  
  class DropdownPopup
    extends ListPopupWindow
  {
    ListAdapter mAdapter;
    private CharSequence mHintText;
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      a(AppCompatSpinner.this);
      dismiss(true);
      setPromptPosition(0);
      setOnItemClickListener(new AppCompatSpinner.DropdownPopup.1(this, AppCompatSpinner.this));
    }
    
    void computeContentWidth()
    {
      Object localObject = getBackground();
      int i = 0;
      if (localObject != null)
      {
        ((Drawable)localObject).getPadding(mTempRect);
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
          i = mTempRect.right;
        } else {
          i = -mTempRect.left;
        }
      }
      else
      {
        localObject = mTempRect;
        right = 0;
        left = 0;
      }
      int n = getPaddingLeft();
      int i1 = getPaddingRight();
      int i2 = getWidth();
      localObject = AppCompatSpinner.this;
      int j = mDropDownWidth;
      if (j == -2)
      {
        int k = ((AppCompatSpinner)localObject).compatMeasureContentWidth((SpinnerAdapter)mAdapter, getBackground());
        j = k;
        int m = getContext().getResources().getDisplayMetrics().widthPixels;
        localObject = mTempRect;
        m = m - left - right;
        if (k > m) {
          j = m;
        }
        j = Math.max(j, i2 - n - i1);
      }
      else
      {
        if (j != -1) {
          break label240;
        }
        j = i2 - n - i1;
      }
      setContentWidth(j);
      break label245;
      label240:
      setContentWidth(j);
      label245:
      if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
        i += i2 - i1 - getWidth();
      } else {
        i += n;
      }
      setAdapter(i);
    }
    
    public CharSequence getHintText()
    {
      return mHintText;
    }
    
    boolean isVisibleToUser(View paramView)
    {
      return (ViewCompat.isAttachedToWindow(paramView)) && (paramView.getGlobalVisibleRect(mVisibleRect));
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      mAdapter = paramListAdapter;
    }
    
    public void setPromptText(CharSequence paramCharSequence)
    {
      mHintText = paramCharSequence;
    }
    
    public void show()
    {
      boolean bool = isShowing();
      computeContentWidth();
      show(2);
      super.show();
      add().setChoiceMode(1);
      setSelection(getSelectedItemPosition());
      if (bool) {
        return;
      }
      ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
      if (localViewTreeObserver != null)
      {
        AppCompatSpinner.DropdownPopup.2 local2 = new AppCompatSpinner.DropdownPopup.2(this);
        localViewTreeObserver.addOnGlobalLayoutListener(local2);
        setOnDismissListener(new AppCompatSpinner.DropdownPopup.3(this, local2));
      }
    }
  }
}
