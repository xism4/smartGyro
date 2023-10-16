package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.org.android.view.Settings;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.dimen;
import com.org.v4.util.R.id;
import com.org.v4.util.R.layout;
import com.org.v4.util.R.string;
import com.org.v4.util.R.styleable;
import com.org.v4.view.CollapsibleActionView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView
  extends LinearLayoutCompat
  implements CollapsibleActionView
{
  static final a HIDDEN_METHOD_INVOKER = new a();
  private int[] a = new int[2];
  private int[] f = new int[2];
  private Rect left = new Rect();
  private f m;
  private Bundle mAppSearchData;
  private boolean mClearingFocus;
  final ImageView mCloseButton;
  private final ImageView mCollapsedIcon;
  private int mCollapsedImeOptions;
  private final CharSequence mDefaultQueryHint;
  private final View mDropDownAnchor;
  private boolean mExpandedInActionView;
  final ImageView mGoButton;
  private boolean mIconified;
  private boolean mIconifiedByDefault;
  private int mMaxWidth;
  private CharSequence mOldQueryText;
  private final View.OnClickListener mOnClickListener = new FilenameDialog.1(this);
  private b mOnCloseListener;
  private final TextView.OnEditorActionListener mOnEditorActionListener = new LockActivity.1(this);
  private final AdapterView.OnItemClickListener mOnItemClickListener = new ActivityDlgActionInput.DlgAttributes.1(this);
  private final AdapterView.OnItemSelectedListener mOnItemSelectedListener = new MessageFragment.1(this);
  private c mOnQueryChangeListener;
  View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
  private View.OnClickListener mOnSearchClickListener;
  private d mOnSuggestionListener;
  private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache = new WeakHashMap();
  private CharSequence mQueryHint;
  private boolean mQueryRefinement;
  private Runnable mReleaseCursorRunnable = new EventInfoFragment.1(this);
  final ImageView mSearchButton;
  private final View mSearchEditFrame;
  private final Drawable mSearchHintIcon;
  private final View mSearchPlate;
  final SearchAutoComplete mSearchSrcTextView;
  SearchableInfo mSearchable;
  private final View mSubmitArea;
  private boolean mSubmitButtonEnabled;
  private final int mSuggestionCommitIconResId;
  private final int mSuggestionRowLayout;
  CursorAdapter mSuggestionsAdapter;
  View.OnKeyListener mTextKeyListener = new TimePickerDialog.KeyboardListener(this);
  private TextWatcher mTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable) {}
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      onTextChanged(paramAnonymousCharSequence);
    }
  };
  private final Runnable mUpdateDrawableStateRunnable = new MonthByWeekFragment.2(this);
  private CharSequence mUserQuery;
  private final Intent mVoiceAppSearchIntent;
  final ImageView mVoiceButton;
  private boolean mVoiceButtonEnabled;
  private final Intent mVoiceWebSearchIntent;
  private Rect width = new Rect();
  
  public SearchView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SearchView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.searchViewStyle);
  }
  
  public SearchView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.SearchView, paramInt, 0);
    LayoutInflater.from(paramContext).inflate(paramAttributeSet.getResourceId(R.styleable.SearchView_layout, R.layout.abc_search_view), this, true);
    mSearchSrcTextView = ((SearchAutoComplete)findViewById(R.id.search_src_text));
    mSearchSrcTextView.setSearchView(this);
    mSearchEditFrame = findViewById(R.id.search_edit_frame);
    mSearchPlate = findViewById(R.id.search_plate);
    mSubmitArea = findViewById(R.id.submit_area);
    mSearchButton = ((ImageView)findViewById(R.id.search_button));
    mGoButton = ((ImageView)findViewById(R.id.search_go_btn));
    mCloseButton = ((ImageView)findViewById(R.id.search_close_btn));
    mVoiceButton = ((ImageView)findViewById(R.id.search_voice_btn));
    mCollapsedIcon = ((ImageView)findViewById(R.id.search_mag_icon));
    ViewCompat.setBackground(mSearchPlate, paramAttributeSet.getDrawable(R.styleable.SearchView_queryBackground));
    ViewCompat.setBackground(mSubmitArea, paramAttributeSet.getDrawable(R.styleable.SearchView_submitBackground));
    mSearchButton.setImageDrawable(paramAttributeSet.getDrawable(R.styleable.SearchView_searchIcon));
    mGoButton.setImageDrawable(paramAttributeSet.getDrawable(R.styleable.SearchView_goIcon));
    mCloseButton.setImageDrawable(paramAttributeSet.getDrawable(R.styleable.SearchView_closeIcon));
    mVoiceButton.setImageDrawable(paramAttributeSet.getDrawable(R.styleable.SearchView_voiceIcon));
    mCollapsedIcon.setImageDrawable(paramAttributeSet.getDrawable(R.styleable.SearchView_searchIcon));
    mSearchHintIcon = paramAttributeSet.getDrawable(R.styleable.SearchView_searchHintIcon);
    MenuItemImpl.add(mSearchButton, getResources().getString(R.string.abc_searchview_description_search));
    mSuggestionRowLayout = paramAttributeSet.getResourceId(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
    mSuggestionCommitIconResId = paramAttributeSet.getResourceId(R.styleable.SearchView_commitIcon, 0);
    mSearchButton.setOnClickListener(mOnClickListener);
    mCloseButton.setOnClickListener(mOnClickListener);
    mGoButton.setOnClickListener(mOnClickListener);
    mVoiceButton.setOnClickListener(mOnClickListener);
    mSearchSrcTextView.setOnClickListener(mOnClickListener);
    mSearchSrcTextView.addTextChangedListener(mTextWatcher);
    mSearchSrcTextView.setOnEditorActionListener(mOnEditorActionListener);
    mSearchSrcTextView.setOnItemClickListener(mOnItemClickListener);
    mSearchSrcTextView.setOnItemSelectedListener(mOnItemSelectedListener);
    mSearchSrcTextView.setOnKeyListener(mTextKeyListener);
    mSearchSrcTextView.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        paramAnonymousView = SearchView.this;
        View.OnFocusChangeListener localOnFocusChangeListener = mOnQueryTextFocusChangeListener;
        if (localOnFocusChangeListener != null) {
          localOnFocusChangeListener.onFocusChange(paramAnonymousView, paramAnonymousBoolean);
        }
      }
    });
    setIconifiedByDefault(paramAttributeSet.getBoolean(R.styleable.SearchView_iconifiedByDefault, true));
    paramInt = paramAttributeSet.getDimensionPixelSize(R.styleable.SearchView_android_maxWidth, -1);
    if (paramInt != -1) {
      setMaxWidth(paramInt);
    }
    mDefaultQueryHint = paramAttributeSet.getText(R.styleable.SearchView_defaultQueryHint);
    mQueryHint = paramAttributeSet.getText(R.styleable.SearchView_queryHint);
    paramInt = paramAttributeSet.getInt(R.styleable.SearchView_android_imeOptions, -1);
    if (paramInt != -1) {
      setImeOptions(paramInt);
    }
    paramInt = paramAttributeSet.getInt(R.styleable.SearchView_android_inputType, -1);
    if (paramInt != -1) {
      setInputType(paramInt);
    }
    setFocusable(paramAttributeSet.getBoolean(R.styleable.SearchView_android_focusable, true));
    paramAttributeSet.recycle();
    mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
    mVoiceWebSearchIntent.addFlags(268435456);
    mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
    mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    mVoiceAppSearchIntent.addFlags(268435456);
    mDropDownAnchor = findViewById(mSearchSrcTextView.getDropDownAnchor());
    paramContext = mDropDownAnchor;
    if (paramContext != null) {
      paramContext.addOnLayoutChangeListener(new View.OnLayoutChangeListener()
      {
        public void onLayoutChange(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6, int paramAnonymousInt7, int paramAnonymousInt8)
        {
          adjustDropDownSizeAndPosition();
        }
      });
    }
    updateViewsVisibility(mIconifiedByDefault);
    updateQueryHint();
  }
  
  private Intent createIntent(String paramString1, Uri paramUri, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    paramString1 = new Intent(paramString1);
    paramString1.addFlags(268435456);
    if (paramUri != null) {
      paramString1.setData(paramUri);
    }
    paramString1.putExtra("user_query", mUserQuery);
    if (paramString3 != null) {
      paramString1.putExtra("query", paramString3);
    }
    if (paramString2 != null) {
      paramString1.putExtra("intent_extra_data_key", paramString2);
    }
    paramUri = mAppSearchData;
    if (paramUri != null) {
      paramString1.putExtra("app_data", paramUri);
    }
    if (paramInt != 0)
    {
      paramString1.putExtra("action_key", paramInt);
      paramString1.putExtra("action_msg", paramString4);
    }
    paramString1.setComponent(mSearchable.getSearchActivity());
    return paramString1;
  }
  
  private Intent createIntentFromSuggestion(Cursor paramCursor, int paramInt, String paramString)
  {
    try
    {
      Object localObject2 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_action");
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = mSearchable.getSuggestIntentAction();
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "android.intent.action.SEARCH";
      }
      Object localObject3 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_data");
      localObject1 = localObject3;
      if (localObject3 == null) {
        localObject1 = mSearchable.getSuggestIntentData();
      }
      localObject3 = localObject1;
      if (localObject1 != null)
      {
        String str = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_data_id");
        localObject3 = localObject1;
        if (str != null)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append("/");
          ((StringBuilder)localObject3).append(Uri.encode(str));
          localObject3 = ((StringBuilder)localObject3).toString();
        }
      }
      if (localObject3 == null) {
        localObject1 = null;
      } else {
        localObject1 = Uri.parse((String)localObject3);
      }
      localObject3 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_query");
      paramString = createIntent((String)localObject2, (Uri)localObject1, SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_extra_data"), (String)localObject3, paramInt, paramString);
      return paramString;
    }
    catch (RuntimeException paramString) {}
    try
    {
      paramInt = paramCursor.getPosition();
    }
    catch (RuntimeException paramCursor)
    {
      for (;;) {}
    }
    paramInt = -1;
    paramCursor = new StringBuilder();
    paramCursor.append("Search suggestions cursor at row ");
    paramCursor.append(paramInt);
    paramCursor.append(" returned exception.");
    Log.w("SearchView", paramCursor.toString(), paramString);
    return null;
  }
  
  private Intent createVoiceAppSearchIntent(Intent paramIntent, SearchableInfo paramSearchableInfo)
  {
    ComponentName localComponentName = paramSearchableInfo.getSearchActivity();
    Object localObject1 = new Intent("android.intent.action.SEARCH");
    ((Intent)localObject1).setComponent(localComponentName);
    PendingIntent localPendingIntent = PendingIntent.getActivity(getContext(), 0, (Intent)localObject1, 1073741824);
    Bundle localBundle = new Bundle();
    localObject1 = mAppSearchData;
    if (localObject1 != null) {
      localBundle.putParcelable("app_data", (Parcelable)localObject1);
    }
    Intent localIntent = new Intent(paramIntent);
    int i = 1;
    Object localObject2 = getResources();
    if (paramSearchableInfo.getVoiceLanguageModeId() != 0) {
      paramIntent = ((Resources)localObject2).getString(paramSearchableInfo.getVoiceLanguageModeId());
    } else {
      paramIntent = "free_form";
    }
    int j = paramSearchableInfo.getVoicePromptTextId();
    Object localObject3 = null;
    if (j != 0) {
      localObject1 = ((Resources)localObject2).getString(paramSearchableInfo.getVoicePromptTextId());
    } else {
      localObject1 = null;
    }
    if (paramSearchableInfo.getVoiceLanguageId() != 0) {
      localObject2 = ((Resources)localObject2).getString(paramSearchableInfo.getVoiceLanguageId());
    } else {
      localObject2 = null;
    }
    if (paramSearchableInfo.getVoiceMaxResults() != 0) {
      i = paramSearchableInfo.getVoiceMaxResults();
    }
    localIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", paramIntent);
    localIntent.putExtra("android.speech.extra.PROMPT", (String)localObject1);
    localIntent.putExtra("android.speech.extra.LANGUAGE", (String)localObject2);
    localIntent.putExtra("android.speech.extra.MAX_RESULTS", i);
    if (localComponentName == null) {
      paramIntent = localObject3;
    } else {
      paramIntent = localComponentName.flattenToShortString();
    }
    localIntent.putExtra("calling_package", paramIntent);
    localIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", localPendingIntent);
    localIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", localBundle);
    return localIntent;
  }
  
  private Intent createVoiceWebSearchIntent(Intent paramIntent, SearchableInfo paramSearchableInfo)
  {
    Intent localIntent = new Intent(paramIntent);
    paramIntent = paramSearchableInfo.getSearchActivity();
    if (paramIntent == null) {
      paramIntent = null;
    } else {
      paramIntent = paramIntent.flattenToShortString();
    }
    localIntent.putExtra("calling_package", paramIntent);
    return localIntent;
  }
  
  private void dismissSuggestions()
  {
    mSearchSrcTextView.dismissDropDown();
  }
  
  private void draw(View paramView, Rect paramRect)
  {
    paramView.getLocationInWindow(a);
    getLocationInWindow(f);
    int[] arrayOfInt1 = a;
    int i = arrayOfInt1[1];
    int[] arrayOfInt2 = f;
    i -= arrayOfInt2[1];
    int j = arrayOfInt1[0] - arrayOfInt2[0];
    paramRect.set(j, i, paramView.getWidth() + j, paramView.getHeight() + i);
  }
  
  private CharSequence getDecoratedHint(CharSequence paramCharSequence)
  {
    if (mIconifiedByDefault)
    {
      if (mSearchHintIcon == null) {
        return paramCharSequence;
      }
      double d = mSearchSrcTextView.getTextSize();
      Double.isNaN(d);
      int i = (int)(d * 1.25D);
      mSearchHintIcon.setBounds(0, 0, i, i);
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder("   ");
      localSpannableStringBuilder.setSpan(new ImageSpan(mSearchHintIcon), 1, 2, 33);
      localSpannableStringBuilder.append(paramCharSequence);
      return localSpannableStringBuilder;
    }
    return paramCharSequence;
  }
  
  private int getPreferredHeight()
  {
    return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
  }
  
  private int getPreferredWidth()
  {
    return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
  }
  
  private boolean hasVoiceSearch()
  {
    Object localObject = mSearchable;
    if ((localObject != null) && (((SearchableInfo)localObject).getVoiceSearchEnabled()))
    {
      localObject = null;
      if (mSearchable.getVoiceSearchLaunchWebSearch()) {
        localObject = mVoiceWebSearchIntent;
      } else if (mSearchable.getVoiceSearchLaunchRecognizer()) {
        localObject = mVoiceAppSearchIntent;
      }
      if ((localObject != null) && (getContext().getPackageManager().resolveActivity((Intent)localObject, 65536) != null)) {
        return true;
      }
    }
    return false;
  }
  
  static boolean isLandscapeMode(Context paramContext)
  {
    return getResourcesgetConfigurationorientation == 2;
  }
  
  private boolean isSubmitAreaEnabled()
  {
    return ((mSubmitButtonEnabled) || (mVoiceButtonEnabled)) && (!isIconified());
  }
  
  private void launchIntent(Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    try
    {
      getContext().startActivity(paramIntent);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed launch activity: ");
      localStringBuilder.append(paramIntent);
      Log.e("SearchView", localStringBuilder.toString(), localRuntimeException);
    }
  }
  
  private boolean launchSuggestion(int paramInt1, int paramInt2, String paramString)
  {
    Cursor localCursor = mSuggestionsAdapter.getCursor();
    if ((localCursor != null) && (localCursor.moveToPosition(paramInt1)))
    {
      launchIntent(createIntentFromSuggestion(localCursor, paramInt2, paramString));
      return true;
    }
    return false;
  }
  
  private void postUpdateFocusedState()
  {
    post(mUpdateDrawableStateRunnable);
  }
  
  private void rewriteQueryFromSuggestion(int paramInt)
  {
    Editable localEditable = mSearchSrcTextView.getText();
    Object localObject = mSuggestionsAdapter.getCursor();
    if (localObject == null) {
      return;
    }
    if (((Cursor)localObject).moveToPosition(paramInt))
    {
      localObject = mSuggestionsAdapter.convertToString((Cursor)localObject);
      if (localObject != null)
      {
        setQuery((CharSequence)localObject);
        return;
      }
    }
    setQuery(localEditable);
  }
  
  private void setQuery(CharSequence paramCharSequence)
  {
    mSearchSrcTextView.setText(paramCharSequence);
    SearchAutoComplete localSearchAutoComplete = mSearchSrcTextView;
    int i;
    if (TextUtils.isEmpty(paramCharSequence)) {
      i = 0;
    } else {
      i = paramCharSequence.length();
    }
    localSearchAutoComplete.setSelection(i);
  }
  
  private void updateCloseButton()
  {
    boolean bool = TextUtils.isEmpty(mSearchSrcTextView.getText());
    int k = 1;
    int n = bool ^ true;
    int j = 0;
    int i = k;
    if (n == 0) {
      if ((mIconifiedByDefault) && (!mExpandedInActionView)) {
        i = k;
      } else {
        i = 0;
      }
    }
    Object localObject = mCloseButton;
    if (i != 0) {
      i = j;
    } else {
      i = 8;
    }
    ((ImageView)localObject).setVisibility(i);
    Drawable localDrawable = mCloseButton.getDrawable();
    if (localDrawable != null)
    {
      if (n != 0) {
        localObject = View.ENABLED_STATE_SET;
      } else {
        localObject = View.EMPTY_STATE_SET;
      }
      localDrawable.setState((int[])localObject);
    }
  }
  
  private void updateQueryHint()
  {
    CharSequence localCharSequence = getQueryHint();
    Object localObject = localCharSequence;
    SearchAutoComplete localSearchAutoComplete = mSearchSrcTextView;
    if (localCharSequence == null) {
      localObject = "";
    }
    localSearchAutoComplete.setHint(getDecoratedHint((CharSequence)localObject));
  }
  
  private void updateSearchAutoComplete()
  {
    mSearchSrcTextView.setThreshold(mSearchable.getSuggestThreshold());
    mSearchSrcTextView.setImeOptions(mSearchable.getImeOptions());
    int k = mSearchable.getInputType();
    int i = k;
    int j = 1;
    if ((k & 0xF) == 1)
    {
      k &= 0xFFFEFFFF;
      i = k;
      if (mSearchable.getSuggestAuthority() != null) {
        i = k | 0x10000 | 0x80000;
      }
    }
    mSearchSrcTextView.setInputType(i);
    Object localObject = mSuggestionsAdapter;
    if (localObject != null) {
      ((CursorAdapter)localObject).changeCursor(null);
    }
    if (mSearchable.getSuggestAuthority() != null)
    {
      mSuggestionsAdapter = new SuggestionsAdapter(getContext(), this, mSearchable, mOutsideDrawablesCache);
      mSearchSrcTextView.setAdapter(mSuggestionsAdapter);
      localObject = (SuggestionsAdapter)mSuggestionsAdapter;
      i = j;
      if (mQueryRefinement) {
        i = 2;
      }
      ((SuggestionsAdapter)localObject).setQueryRefinement(i);
    }
  }
  
  private void updateSubmitArea()
  {
    int i;
    if ((isSubmitAreaEnabled()) && ((mGoButton.getVisibility() == 0) || (mVoiceButton.getVisibility() == 0))) {
      i = 0;
    } else {
      i = 8;
    }
    mSubmitArea.setVisibility(i);
  }
  
  private void updateSubmitButton(boolean paramBoolean)
  {
    int i;
    if ((mSubmitButtonEnabled) && (isSubmitAreaEnabled()) && (hasFocus()) && ((paramBoolean) || (!mVoiceButtonEnabled))) {
      i = 0;
    } else {
      i = 8;
    }
    mGoButton.setVisibility(i);
  }
  
  private void updateViewsVisibility(boolean paramBoolean)
  {
    mIconified = paramBoolean;
    int j = 8;
    boolean bool1 = false;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 8;
    }
    boolean bool2 = TextUtils.isEmpty(mSearchSrcTextView.getText()) ^ true;
    mSearchButton.setVisibility(i);
    updateSubmitButton(bool2);
    View localView = mSearchEditFrame;
    if (paramBoolean) {
      i = 8;
    } else {
      i = 0;
    }
    localView.setVisibility(i);
    int i = j;
    if (mCollapsedIcon.getDrawable() != null) {
      if (mIconifiedByDefault) {
        i = j;
      } else {
        i = 0;
      }
    }
    mCollapsedIcon.setVisibility(i);
    updateCloseButton();
    paramBoolean = bool1;
    if (!bool2) {
      paramBoolean = true;
    }
    updateVoiceButton(paramBoolean);
    updateSubmitArea();
  }
  
  private void updateVoiceButton(boolean paramBoolean)
  {
    int i;
    if ((mVoiceButtonEnabled) && (!isIconified()) && (paramBoolean))
    {
      i = 0;
      mGoButton.setVisibility(8);
    }
    else
    {
      i = 8;
    }
    mVoiceButton.setVisibility(i);
  }
  
  void adjustDropDownSizeAndPosition()
  {
    if (mDropDownAnchor.getWidth() > 1)
    {
      Resources localResources = getContext().getResources();
      int k = mSearchPlate.getPaddingLeft();
      Rect localRect = new Rect();
      boolean bool = ViewUtils.isLayoutRtl(this);
      int i;
      if (mIconifiedByDefault) {
        i = localResources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + localResources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left);
      } else {
        i = 0;
      }
      mSearchSrcTextView.getDropDownBackground().getPadding(localRect);
      if (bool) {
        j = -left;
      } else {
        j = k - (left + i);
      }
      mSearchSrcTextView.setDropDownHorizontalOffset(j);
      int j = mDropDownAnchor.getWidth();
      int n = left;
      int i1 = right;
      mSearchSrcTextView.setDropDownWidth(j + n + i1 + i - k);
    }
  }
  
  public void clearFocus()
  {
    mClearingFocus = true;
    super.clearFocus();
    mSearchSrcTextView.clearFocus();
    mSearchSrcTextView.setImeVisibility(false);
    mClearingFocus = false;
  }
  
  void forceSuggestionQuery()
  {
    HIDDEN_METHOD_INVOKER.doBeforeTextChanged(mSearchSrcTextView);
    HIDDEN_METHOD_INVOKER.doAfterTextChanged(mSearchSrcTextView);
  }
  
  public int getImeOptions()
  {
    return mSearchSrcTextView.getImeOptions();
  }
  
  public int getInputType()
  {
    return mSearchSrcTextView.getInputType();
  }
  
  public int getMaxWidth()
  {
    return mMaxWidth;
  }
  
  public CharSequence getQuery()
  {
    return mSearchSrcTextView.getText();
  }
  
  public CharSequence getQueryHint()
  {
    Object localObject = mQueryHint;
    if (localObject != null) {
      return localObject;
    }
    localObject = mSearchable;
    if ((localObject != null) && (((SearchableInfo)localObject).getHintId() != 0)) {
      return getContext().getText(mSearchable.getHintId());
    }
    return mDefaultQueryHint;
  }
  
  int getSuggestionCommitIconResId()
  {
    return mSuggestionCommitIconResId;
  }
  
  int getSuggestionRowLayout()
  {
    return mSuggestionRowLayout;
  }
  
  public CursorAdapter getSuggestionsAdapter()
  {
    return mSuggestionsAdapter;
  }
  
  public boolean isIconified()
  {
    return mIconified;
  }
  
  void launchQuerySearch(int paramInt, String paramString1, String paramString2)
  {
    paramString1 = createIntent("android.intent.action.SEARCH", null, null, paramString2, paramInt, paramString1);
    getContext().startActivity(paramString1);
  }
  
  public void onActionViewCollapsed()
  {
    setQuery("", false);
    clearFocus();
    updateViewsVisibility(true);
    mSearchSrcTextView.setImeOptions(mCollapsedImeOptions);
    mExpandedInActionView = false;
  }
  
  public void onActionViewExpanded()
  {
    if (mExpandedInActionView) {
      return;
    }
    mExpandedInActionView = true;
    mCollapsedImeOptions = mSearchSrcTextView.getImeOptions();
    mSearchSrcTextView.setImeOptions(mCollapsedImeOptions | 0x2000000);
    mSearchSrcTextView.setText("");
    setIconified(false);
  }
  
  void onCloseClicked()
  {
    if (TextUtils.isEmpty(mSearchSrcTextView.getText()))
    {
      if (mIconifiedByDefault)
      {
        b localB = mOnCloseListener;
        if ((localB == null) || (!localB.onClose()))
        {
          clearFocus();
          updateViewsVisibility(true);
        }
      }
    }
    else
    {
      mSearchSrcTextView.setText("");
      mSearchSrcTextView.requestFocus();
      mSearchSrcTextView.setImeVisibility(true);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    removeCallbacks(mUpdateDrawableStateRunnable);
    post(mReleaseCursorRunnable);
    super.onDetachedFromWindow();
  }
  
  boolean onItemClicked(int paramInt1, int paramInt2, String paramString)
  {
    paramString = mOnSuggestionListener;
    if ((paramString != null) && (paramString.onSuggestionClick(paramInt1))) {
      return false;
    }
    launchSuggestion(paramInt1, 0, null);
    mSearchSrcTextView.setImeVisibility(false);
    dismissSuggestions();
    return true;
  }
  
  boolean onItemSelected(int paramInt)
  {
    d localD = mOnSuggestionListener;
    if ((localD != null) && (localD.onSuggestionSelect(paramInt))) {
      return false;
    }
    rewriteQueryFromSuggestion(paramInt);
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
    {
      draw(mSearchSrcTextView, left);
      Object localObject = width;
      Rect localRect = left;
      ((Rect)localObject).set(left, 0, right, paramInt4 - paramInt2);
      localObject = m;
      if (localObject == null)
      {
        m = new f(width, left, mSearchSrcTextView);
        setTouchDelegate(m);
        return;
      }
      ((f)localObject).draw(width, left);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (isIconified())
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    int j = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    paramInt1 = i;
    if (j != Integer.MIN_VALUE)
    {
      if (j != 0)
      {
        if (j != 1073741824) {
          break label111;
        }
        j = mMaxWidth;
        if (j <= 0) {
          break label111;
        }
        paramInt1 = j;
      }
      else
      {
        paramInt1 = mMaxWidth;
        if (paramInt1 > 0) {
          break label111;
        }
        paramInt1 = getPreferredWidth();
        break label111;
      }
    }
    else
    {
      paramInt1 = mMaxWidth;
      if (paramInt1 > 0) {
        break label105;
      }
    }
    paramInt1 = getPreferredWidth();
    label105:
    paramInt1 = Math.min(paramInt1, i);
    label111:
    j = View.MeasureSpec.getMode(paramInt2);
    i = View.MeasureSpec.getSize(paramInt2);
    paramInt2 = i;
    if (j != Integer.MIN_VALUE)
    {
      if (j == 0) {
        paramInt2 = getPreferredHeight();
      }
    }
    else {
      paramInt2 = Math.min(getPreferredHeight(), i);
    }
    super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
  }
  
  void onQueryRefine(CharSequence paramCharSequence)
  {
    setQuery(paramCharSequence);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof e))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (e)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getToken());
    updateViewsVisibility(isIconified);
    requestLayout();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    e localE = new e(super.onSaveInstanceState());
    isIconified = isIconified();
    return localE;
  }
  
  void onSearchClicked()
  {
    updateViewsVisibility(false);
    mSearchSrcTextView.requestFocus();
    mSearchSrcTextView.setImeVisibility(true);
    View.OnClickListener localOnClickListener = mOnSearchClickListener;
    if (localOnClickListener != null) {
      localOnClickListener.onClick(this);
    }
  }
  
  void onSubmitQuery()
  {
    Editable localEditable = mSearchSrcTextView.getText();
    if ((localEditable != null) && (TextUtils.getTrimmedLength(localEditable) > 0))
    {
      c localC = mOnQueryChangeListener;
      if ((localC == null) || (!localC.onQueryTextSubmit(localEditable.toString())))
      {
        if (mSearchable != null) {
          launchQuerySearch(0, null, localEditable.toString());
        }
        mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
      }
    }
  }
  
  boolean onSuggestionsKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (mSearchable == null) {
      return false;
    }
    if (mSuggestionsAdapter == null) {
      return false;
    }
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.hasNoModifiers())) {
      if ((paramInt != 66) && (paramInt != 84) && (paramInt != 61))
      {
        if ((paramInt != 21) && (paramInt != 22))
        {
          if ((paramInt == 19) && (mSearchSrcTextView.getListSelection() == 0)) {
            return false;
          }
        }
        else
        {
          if (paramInt == 21) {
            paramInt = 0;
          } else {
            paramInt = mSearchSrcTextView.length();
          }
          mSearchSrcTextView.setSelection(paramInt);
          mSearchSrcTextView.setListSelection(0);
          mSearchSrcTextView.clearListSelection();
          HIDDEN_METHOD_INVOKER.ensureImeVisible(mSearchSrcTextView, true);
          return true;
        }
      }
      else {
        return onItemClicked(mSearchSrcTextView.getListSelection(), 0, null);
      }
    }
    return false;
  }
  
  void onTextChanged(CharSequence paramCharSequence)
  {
    Editable localEditable = mSearchSrcTextView.getText();
    mUserQuery = localEditable;
    boolean bool2 = TextUtils.isEmpty(localEditable);
    boolean bool1 = true;
    bool2 ^= true;
    updateSubmitButton(bool2);
    if (bool2) {
      bool1 = false;
    }
    updateVoiceButton(bool1);
    updateCloseButton();
    updateSubmitArea();
    if ((mOnQueryChangeListener != null) && (!TextUtils.equals(paramCharSequence, mOldQueryText))) {
      mOnQueryChangeListener.onQueryTextChange(paramCharSequence.toString());
    }
    mOldQueryText = paramCharSequence.toString();
  }
  
  void onTextFocusChanged()
  {
    updateViewsVisibility(isIconified());
    postUpdateFocusedState();
    if (mSearchSrcTextView.hasFocus()) {
      forceSuggestionQuery();
    }
  }
  
  void onVoiceClicked()
  {
    Object localObject = mSearchable;
    if (localObject == null) {
      return;
    }
    try
    {
      boolean bool = ((SearchableInfo)localObject).getVoiceSearchLaunchWebSearch();
      Intent localIntent;
      if (bool) {
        localIntent = mVoiceWebSearchIntent;
      }
      for (localObject = createVoiceWebSearchIntent(localIntent, (SearchableInfo)localObject);; localObject = createVoiceAppSearchIntent(localIntent, (SearchableInfo)localObject))
      {
        getContext().startActivity((Intent)localObject);
        return;
        bool = ((SearchableInfo)localObject).getVoiceSearchLaunchRecognizer();
        if (!bool) {
          break;
        }
        localIntent = mVoiceAppSearchIntent;
      }
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Log.w("SearchView", "Could not find voice search activity");
    return;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    postUpdateFocusedState();
  }
  
  public boolean requestFocus(int paramInt, Rect paramRect)
  {
    if (mClearingFocus) {
      return false;
    }
    if (!isFocusable()) {
      return false;
    }
    boolean bool1;
    if (!isIconified())
    {
      boolean bool2 = mSearchSrcTextView.requestFocus(paramInt, paramRect);
      bool1 = bool2;
      if (bool2)
      {
        updateViewsVisibility(false);
        return bool2;
      }
    }
    else
    {
      bool1 = super.requestFocus(paramInt, paramRect);
    }
    return bool1;
  }
  
  public void setAppSearchData(Bundle paramBundle)
  {
    mAppSearchData = paramBundle;
  }
  
  public void setIconified(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      onCloseClicked();
      return;
    }
    onSearchClicked();
  }
  
  public void setIconifiedByDefault(boolean paramBoolean)
  {
    if (mIconifiedByDefault == paramBoolean) {
      return;
    }
    mIconifiedByDefault = paramBoolean;
    updateViewsVisibility(paramBoolean);
    updateQueryHint();
  }
  
  public void setImeOptions(int paramInt)
  {
    mSearchSrcTextView.setImeOptions(paramInt);
  }
  
  public void setInputType(int paramInt)
  {
    mSearchSrcTextView.setInputType(paramInt);
  }
  
  public void setMaxWidth(int paramInt)
  {
    mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setOnCloseListener(b paramB)
  {
    mOnCloseListener = paramB;
  }
  
  public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener paramOnFocusChangeListener)
  {
    mOnQueryTextFocusChangeListener = paramOnFocusChangeListener;
  }
  
  public void setOnQueryTextListener(c paramC)
  {
    mOnQueryChangeListener = paramC;
  }
  
  public void setOnSearchClickListener(View.OnClickListener paramOnClickListener)
  {
    mOnSearchClickListener = paramOnClickListener;
  }
  
  public void setOnSuggestionListener(d paramD)
  {
    mOnSuggestionListener = paramD;
  }
  
  public void setQuery(CharSequence paramCharSequence, boolean paramBoolean)
  {
    mSearchSrcTextView.setText(paramCharSequence);
    if (paramCharSequence != null)
    {
      SearchAutoComplete localSearchAutoComplete = mSearchSrcTextView;
      localSearchAutoComplete.setSelection(localSearchAutoComplete.length());
      mUserQuery = paramCharSequence;
    }
    if ((paramBoolean) && (!TextUtils.isEmpty(paramCharSequence))) {
      onSubmitQuery();
    }
  }
  
  public void setQueryHint(CharSequence paramCharSequence)
  {
    mQueryHint = paramCharSequence;
    updateQueryHint();
  }
  
  public void setQueryRefinementEnabled(boolean paramBoolean)
  {
    mQueryRefinement = paramBoolean;
    Object localObject = mSuggestionsAdapter;
    if ((localObject instanceof SuggestionsAdapter))
    {
      localObject = (SuggestionsAdapter)localObject;
      int i;
      if (paramBoolean) {
        i = 2;
      } else {
        i = 1;
      }
      ((SuggestionsAdapter)localObject).setQueryRefinement(i);
    }
  }
  
  public void setSearchableInfo(SearchableInfo paramSearchableInfo)
  {
    mSearchable = paramSearchableInfo;
    if (mSearchable != null)
    {
      updateSearchAutoComplete();
      updateQueryHint();
    }
    mVoiceButtonEnabled = hasVoiceSearch();
    if (mVoiceButtonEnabled) {
      mSearchSrcTextView.setPrivateImeOptions("nm");
    }
    updateViewsVisibility(isIconified());
  }
  
  public void setSubmitButtonEnabled(boolean paramBoolean)
  {
    mSubmitButtonEnabled = paramBoolean;
    updateViewsVisibility(isIconified());
  }
  
  public void setSuggestionsAdapter(CursorAdapter paramCursorAdapter)
  {
    mSuggestionsAdapter = paramCursorAdapter;
    mSearchSrcTextView.setAdapter(mSuggestionsAdapter);
  }
  
  void updateFocusedState()
  {
    int[] arrayOfInt;
    if (mSearchSrcTextView.hasFocus()) {
      arrayOfInt = View.FOCUSED_STATE_SET;
    } else {
      arrayOfInt = View.EMPTY_STATE_SET;
    }
    Drawable localDrawable = mSearchPlate.getBackground();
    if (localDrawable != null) {
      localDrawable.setState(arrayOfInt);
    }
    localDrawable = mSubmitArea.getBackground();
    if (localDrawable != null) {
      localDrawable.setState(arrayOfInt);
    }
    invalidate();
  }
  
  public static class SearchAutoComplete
    extends AppCompatAutoCompleteTextView
  {
    private boolean done;
    private SearchView mSearchView;
    final Runnable mShowImeRunnable = new EventInfoFragment.2(this);
    private int mThreshold = getThreshold();
    
    public SearchAutoComplete(Context paramContext)
    {
      this(paramContext, null);
    }
    
    public SearchAutoComplete(Context paramContext, AttributeSet paramAttributeSet)
    {
      this(paramContext, paramAttributeSet, R.attr.autoCompleteTextViewStyle);
    }
    
    public SearchAutoComplete(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
    }
    
    private int getSearchViewTextMinWidthDp()
    {
      Configuration localConfiguration = getResources().getConfiguration();
      int i = screenWidthDp;
      int j = screenHeightDp;
      if ((i >= 960) && (j >= 720) && (orientation == 2)) {
        return 256;
      }
      if ((i < 600) && ((i < 640) || (j < 480))) {
        return 160;
      }
      return 192;
    }
    
    public boolean enoughToFilter()
    {
      return (mThreshold <= 0) || (super.enoughToFilter());
    }
    
    boolean isEmpty()
    {
      return TextUtils.getTrimmedLength(getText()) == 0;
    }
    
    public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
    {
      paramEditorInfo = super.onCreateInputConnection(paramEditorInfo);
      if (done)
      {
        removeCallbacks(mShowImeRunnable);
        post(mShowImeRunnable);
      }
      return paramEditorInfo;
    }
    
    protected void onFinishInflate()
    {
      super.onFinishInflate();
      DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
      setMinWidth((int)TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), localDisplayMetrics));
    }
    
    protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
    {
      super.onFocusChanged(paramBoolean, paramInt, paramRect);
      mSearchView.onTextFocusChanged();
    }
    
    public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
    {
      if (paramInt == 4)
      {
        KeyEvent.DispatcherState localDispatcherState;
        if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
        {
          localDispatcherState = getKeyDispatcherState();
          if (localDispatcherState == null) {
            break label93;
          }
          localDispatcherState.startTracking(paramKeyEvent, this);
          return true;
        }
        if (paramKeyEvent.getAction() == 1)
        {
          localDispatcherState = getKeyDispatcherState();
          if (localDispatcherState != null) {
            localDispatcherState.handleUpEvent(paramKeyEvent);
          }
          if ((paramKeyEvent.isTracking()) && (!paramKeyEvent.isCanceled()))
          {
            mSearchView.clearFocus();
            setImeVisibility(false);
            return true;
          }
        }
      }
      return super.onKeyPreIme(paramInt, paramKeyEvent);
      label93:
      return true;
    }
    
    public void onWindowFocusChanged(boolean paramBoolean)
    {
      super.onWindowFocusChanged(paramBoolean);
      if ((paramBoolean) && (mSearchView.hasFocus()) && (getVisibility() == 0))
      {
        done = true;
        if (SearchView.isLandscapeMode(getContext())) {
          SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
        }
      }
    }
    
    public void performCompletion() {}
    
    protected void replaceText(CharSequence paramCharSequence) {}
    
    void reset()
    {
      if (done)
      {
        ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this, 0);
        done = false;
      }
    }
    
    void setImeVisibility(boolean paramBoolean)
    {
      InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
      if (!paramBoolean)
      {
        done = false;
        removeCallbacks(mShowImeRunnable);
        localInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        return;
      }
      if (localInputMethodManager.isActive(this))
      {
        done = false;
        removeCallbacks(mShowImeRunnable);
        localInputMethodManager.showSoftInput(this, 0);
        return;
      }
      done = true;
    }
    
    void setSearchView(SearchView paramSearchView)
    {
      mSearchView = paramSearchView;
    }
    
    public void setThreshold(int paramInt)
    {
      super.setThreshold(paramInt);
      mThreshold = paramInt;
    }
  }
  
  private static class a
  {
    private Method doAfterTextChanged;
    private Method doBeforeTextChanged;
    private Method ensureImeVisible;
    
    a()
    {
      try
      {
        localObject = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
        doBeforeTextChanged = ((Method)localObject);
        localObject = doBeforeTextChanged;
        ((Method)localObject).setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException1)
      {
        for (;;)
        {
          try
          {
            label58:
            Object localObject = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[] { localObject });
            ensureImeVisible = ((Method)localObject);
            localObject = ensureImeVisible;
            ((Method)localObject).setAccessible(true);
            return;
          }
          catch (NoSuchMethodException localNoSuchMethodException3) {}
          localNoSuchMethodException1 = localNoSuchMethodException1;
        }
      }
      try
      {
        localObject = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
        doAfterTextChanged = ((Method)localObject);
        localObject = doAfterTextChanged;
        ((Method)localObject).setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException2)
      {
        break label58;
      }
      localObject = Boolean.TYPE;
    }
    
    void doAfterTextChanged(AutoCompleteTextView paramAutoCompleteTextView)
    {
      Method localMethod = doAfterTextChanged;
      if (localMethod != null) {
        try
        {
          localMethod.invoke(paramAutoCompleteTextView, new Object[0]);
          return;
        }
        catch (Exception paramAutoCompleteTextView) {}
      }
    }
    
    void doBeforeTextChanged(AutoCompleteTextView paramAutoCompleteTextView)
    {
      Method localMethod = doBeforeTextChanged;
      if (localMethod != null) {
        try
        {
          localMethod.invoke(paramAutoCompleteTextView, new Object[0]);
          return;
        }
        catch (Exception paramAutoCompleteTextView) {}
      }
    }
    
    void ensureImeVisible(AutoCompleteTextView paramAutoCompleteTextView, boolean paramBoolean)
    {
      Method localMethod = ensureImeVisible;
      if (localMethod != null) {
        try
        {
          localMethod.invoke(paramAutoCompleteTextView, new Object[] { Boolean.valueOf(paramBoolean) });
          return;
        }
        catch (Exception paramAutoCompleteTextView) {}
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract boolean onClose();
  }
  
  public static abstract interface c
  {
    public abstract boolean onQueryTextChange(String paramString);
    
    public abstract boolean onQueryTextSubmit(String paramString);
  }
  
  public static abstract interface d
  {
    public abstract boolean onSuggestionClick(int paramInt);
    
    public abstract boolean onSuggestionSelect(int paramInt);
  }
  
  static class e
    extends Settings
  {
    public static final Parcelable.Creator<e> CREATOR = new VerticalProgressBar.SavedState.1();
    boolean isIconified;
    
    public e(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      isIconified = ((Boolean)paramParcel.readValue(null)).booleanValue();
    }
    
    e(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SearchView.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" isIconified=");
      localStringBuilder.append(isIconified);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeValue(Boolean.valueOf(isIconified));
    }
  }
  
  private static class f
    extends TouchDelegate
  {
    private final View a;
    private final Rect b;
    private final Rect d;
    private boolean e;
    private final Rect f;
    private final int i;
    
    public f(Rect paramRect1, Rect paramRect2, View paramView)
    {
      super(paramView);
      i = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
      b = new Rect();
      d = new Rect();
      f = new Rect();
      draw(paramRect1, paramRect2);
      a = paramView;
    }
    
    public void draw(Rect paramRect1, Rect paramRect2)
    {
      b.set(paramRect1);
      d.set(paramRect1);
      paramRect1 = d;
      int j = i;
      paramRect1.inset(-j, -j);
      f.set(paramRect2);
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      int m = (int)paramMotionEvent.getX();
      int n = (int)paramMotionEvent.getY();
      int j = paramMotionEvent.getAction();
      int k = 1;
      if (j != 0)
      {
        if ((j != 1) && (j != 2))
        {
          if (j == 3)
          {
            bool1 = e;
            e = false;
            j = k;
            break label143;
          }
        }
        else
        {
          boolean bool2 = e;
          j = k;
          bool1 = bool2;
          if (!bool2) {
            break label143;
          }
          j = k;
          bool1 = bool2;
          if (d.contains(m, n)) {
            break label143;
          }
          j = 0;
          bool1 = bool2;
          break label143;
        }
      }
      else if (b.contains(m, n))
      {
        e = true;
        bool1 = true;
        j = k;
        break label143;
      }
      boolean bool1 = false;
      j = k;
      label143:
      if (bool1)
      {
        float f1;
        if ((j != 0) && (!f.contains(m, n)))
        {
          f1 = a.getWidth() / 2;
          j = a.getHeight() / 2;
        }
        else
        {
          Rect localRect = f;
          f1 = m - left;
          j = n - top;
        }
        paramMotionEvent.setLocation(f1, j);
        return a.dispatchTouchEvent(paramMotionEvent);
      }
      return false;
    }
  }
}
