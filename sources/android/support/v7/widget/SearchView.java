package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.org.android.view.Settings;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$dimen;
import com.org.v4.util.R$id;
import com.org.v4.util.R$layout;
import com.org.v4.util.R$string;
import com.org.v4.util.R$styleable;
import com.org.v4.view.CollapsibleActionView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    static final a HIDDEN_METHOD_INVOKER = new a();
    private int[] a;
    private int[] f;
    private Rect left;
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
    private final View.OnClickListener mOnClickListener;
    private b mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private c mOnQueryChangeListener;
    View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private d mOnSuggestionListener;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
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
    View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;
    private Rect width;

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean done;
        private SearchView mSearchView;
        final Runnable mShowImeRunnable;
        private int mThreshold;

        public SearchAutoComplete(Context context) {
            this(context, (AttributeSet) null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mShowImeRunnable = new EventInfoFragment$2(this);
            this.mThreshold = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration $r2 = getResources().getConfiguration();
            int $i0 = $r2.screenWidthDp;
            int $i1 = $r2.screenHeightDp;
            if ($i0 >= 960 && $i1 >= 720 && $r2.orientation == 2) {
                return 256;
            }
            if ($i0 < 600) {
                return ($i0 < 640 || $i1 < 480) ? 160 : 192;
            }
            return 192;
        }

        public boolean enoughToFilter() {
            return this.mThreshold <= 0 || super.enoughToFilter();
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection $r1 = super.onCreateInputConnection(editorInfo);
            if (this.done) {
                removeCallbacks(this.mShowImeRunnable);
                post(this.mShowImeRunnable);
            }
            return $r1;
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        /* access modifiers changed from: protected */
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState $r2 = getKeyDispatcherState();
                    if ($r2 == null) {
                        return true;
                    }
                    $r2.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState $r22 = getKeyDispatcherState();
                    if ($r22 != null) {
                        $r22.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.mSearchView.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.mSearchView.hasFocus() && getVisibility() == 0) {
                this.done = true;
                if (SearchView.isLandscapeMode(getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
                }
            }
        }

        public void performCompletion() {
        }

        /* access modifiers changed from: protected */
        public void replaceText(CharSequence charSequence) {
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            if (this.done) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.done = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void setImeVisibility(boolean z) {
            InputMethodManager $r3 = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.done = false;
                removeCallbacks(this.mShowImeRunnable);
                $r3.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if ($r3.isActive(this)) {
                this.done = false;
                removeCallbacks(this.mShowImeRunnable);
                $r3.showSoftInput(this, 0);
            } else {
                this.done = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void setSearchView(SearchView searchView) {
            this.mSearchView = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.mThreshold = i;
        }
    }

    private static class a {
        private Method doAfterTextChanged;
        private Method doBeforeTextChanged;
        private Method ensureImeVisible;

        a() {
            try {
                this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.doBeforeTextChanged.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.doAfterTextChanged.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.ensureImeVisible.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
        }

        /* access modifiers changed from: package-private */
        public void doAfterTextChanged(AutoCompleteTextView autoCompleteTextView) {
            Method $r2 = this.doAfterTextChanged;
            if ($r2 != null) {
                try {
                    $r2.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void doBeforeTextChanged(AutoCompleteTextView autoCompleteTextView) {
            Method $r2 = this.doBeforeTextChanged;
            if ($r2 != null) {
                try {
                    $r2.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void ensureImeVisible(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method $r2 = this.ensureImeVisible;
            if ($r2 != null) {
                Object[] $r5 = new Object[1];
                try {
                    $r5[0] = Boolean.valueOf(z);
                    $r2.invoke(autoCompleteTextView, $r5);
                } catch (Exception e) {
                }
            }
        }
    }

    public interface b {
        boolean onClose();
    }

    public interface c {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    public interface d {
        boolean onSuggestionClick(int i);

        boolean onSuggestionSelect(int i);
    }

    static class e extends Settings {
        public static final Parcelable.Creator<e> CREATOR = new VerticalProgressBar$SavedState$1();
        boolean isIconified;

        public e(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isIconified = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
        }

        e(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.isIconified));
        }
    }

    private static class f extends TouchDelegate {
        private final View a;
        private final Rect b = new Rect();
        private final Rect d = new Rect();
        private boolean e;
        private final Rect f = new Rect();
        private final int i;

        public f(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.i = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            draw(rect, rect2);
            this.a = view;
        }

        public void draw(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            Rect $r2 = this.d;
            int $i1 = this.i;
            $r2.inset(-$i1, -$i1);
            this.f.set(rect2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x003f  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x006f A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouchEvent(android.view.MotionEvent r12) {
            /*
                r11 = this;
                float r0 = r12.getX()
                int r1 = (int) r0
                float r0 = r12.getY()
                int r2 = (int) r0
                int r3 = r12.getAction()
                r4 = 1
                if (r3 == 0) goto L_0x002f
                r5 = 1
                if (r3 == r5) goto L_0x0021
                r5 = 2
                if (r3 == r5) goto L_0x0021
                r5 = 3
                if (r3 == r5) goto L_0x001b
                goto L_0x003c
            L_0x001b:
                boolean r6 = r11.e
                r5 = 0
                r11.e = r5
                goto L_0x003d
            L_0x0021:
                boolean r6 = r11.e
                if (r6 == 0) goto L_0x003d
                android.graphics.Rect r7 = r11.d
                boolean r8 = r7.contains(r1, r2)
                if (r8 != 0) goto L_0x003d
                r4 = 0
                goto L_0x003d
            L_0x002f:
                android.graphics.Rect r7 = r11.b
                boolean r6 = r7.contains(r1, r2)
                if (r6 == 0) goto L_0x003c
                r5 = 1
                r11.e = r5
                r6 = 1
                goto L_0x003d
            L_0x003c:
                r6 = 0
            L_0x003d:
                if (r6 == 0) goto L_0x006f
                if (r4 == 0) goto L_0x005b
                android.graphics.Rect r7 = r11.f
                boolean r4 = r7.contains(r1, r2)
                if (r4 != 0) goto L_0x005b
                android.view.View r9 = r11.a
                int r2 = r9.getWidth()
                int r2 = r2 / 2
                float r0 = (float) r2
                android.view.View r9 = r11.a
                int r2 = r9.getHeight()
                int r2 = r2 / 2
                goto L_0x0064
            L_0x005b:
                android.graphics.Rect r7 = r11.f
                int r3 = r7.left
                int r1 = r1 - r3
                float r0 = (float) r1
                int r1 = r7.top
                int r2 = r2 - r1
            L_0x0064:
                float r10 = (float) r2
                r12.setLocation(r0, r10)
                android.view.View r9 = r11.a
                boolean r4 = r9.dispatchTouchEvent(r12)
                return r4
            L_0x006f:
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.f.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    public SearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.left = new Rect();
        this.width = new Rect();
        this.a = new int[2];
        this.f = new int[2];
        this.mUpdateDrawableStateRunnable = new MonthByWeekFragment$2(this);
        this.mReleaseCursorRunnable = new EventInfoFragment$1(this);
        this.mOutsideDrawablesCache = new WeakHashMap();
        this.mOnClickListener = new FilenameDialog$1(this);
        this.mTextKeyListener = new TimePickerDialog$KeyboardListener(this);
        this.mOnEditorActionListener = new LockActivity$1(this);
        this.mOnItemClickListener = new ActivityDlgActionInput$DlgAttributes$1(this);
        this.mOnItemSelectedListener = new MessageFragment$1(this);
        this.mTextWatcher = new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchView.this.onTextChanged(charSequence);
            }
        };
        TintTypedArray $r14 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.SearchView, i, 0);
        LayoutInflater.from(context).inflate($r14.getResourceId(R$styleable.SearchView_layout, R$layout.abc_search_view), this, true);
        this.mSearchSrcTextView = (SearchAutoComplete) findViewById(R$id.search_src_text);
        SearchAutoComplete $r17 = this.mSearchSrcTextView;
        SearchAutoComplete searchAutoComplete = $r17;
        $r17.setSearchView(this);
        this.mSearchEditFrame = findViewById(R$id.search_edit_frame);
        this.mSearchPlate = findViewById(R$id.search_plate);
        this.mSubmitArea = findViewById(R$id.submit_area);
        this.mSearchButton = (ImageView) findViewById(R$id.search_button);
        this.mGoButton = (ImageView) findViewById(R$id.search_go_btn);
        this.mCloseButton = (ImageView) findViewById(R$id.search_close_btn);
        this.mVoiceButton = (ImageView) findViewById(R$id.search_voice_btn);
        this.mCollapsedIcon = (ImageView) findViewById(R$id.search_mag_icon);
        ViewCompat.setBackground(this.mSearchPlate, $r14.getDrawable(R$styleable.SearchView_queryBackground));
        ViewCompat.setBackground(this.mSubmitArea, $r14.getDrawable(R$styleable.SearchView_submitBackground));
        this.mSearchButton.setImageDrawable($r14.getDrawable(R$styleable.SearchView_searchIcon));
        this.mGoButton.setImageDrawable($r14.getDrawable(R$styleable.SearchView_goIcon));
        this.mCloseButton.setImageDrawable($r14.getDrawable(R$styleable.SearchView_closeIcon));
        this.mVoiceButton.setImageDrawable($r14.getDrawable(R$styleable.SearchView_voiceIcon));
        this.mCollapsedIcon.setImageDrawable($r14.getDrawable(R$styleable.SearchView_searchIcon));
        this.mSearchHintIcon = $r14.getDrawable(R$styleable.SearchView_searchHintIcon);
        MenuItemImpl.add(this.mSearchButton, getResources().getString(R$string.abc_searchview_description_search));
        this.mSuggestionRowLayout = $r14.getResourceId(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = $r14.getResourceId(R$styleable.SearchView_commitIcon, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mGoButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
        this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
        this.mSearchSrcTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                SearchView $r3 = SearchView.this;
                View.OnFocusChangeListener $r1 = $r3.mOnQueryTextFocusChangeListener;
                if ($r1 != null) {
                    $r1.onFocusChange($r3, z);
                }
            }
        });
        setIconifiedByDefault($r14.getBoolean(R$styleable.SearchView_iconifiedByDefault, true));
        int $i0 = $r14.getDimensionPixelSize(R$styleable.SearchView_android_maxWidth, -1);
        if ($i0 != -1) {
            setMaxWidth($i0);
        }
        this.mDefaultQueryHint = $r14.getText(R$styleable.SearchView_defaultQueryHint);
        this.mQueryHint = $r14.getText(R$styleable.SearchView_queryHint);
        int $i02 = $r14.getInt(R$styleable.SearchView_android_imeOptions, -1);
        if ($i02 != -1) {
            setImeOptions($i02);
        }
        int $i03 = $r14.getInt(R$styleable.SearchView_android_inputType, -1);
        if ($i03 != -1) {
            setInputType($i03);
        }
        setFocusable($r14.getBoolean(R$styleable.SearchView_android_focusable, true));
        $r14.recycle();
        this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent.addFlags(268435456);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent.addFlags(268435456);
        SearchAutoComplete $r172 = this.mSearchSrcTextView;
        SearchAutoComplete searchAutoComplete2 = $r172;
        this.mDropDownAnchor = findViewById($r172.getDropDownAnchor());
        View $r16 = this.mDropDownAnchor;
        if ($r16 != null) {
            $r16.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    SearchView.this.adjustDropDownSizeAndPosition();
                }
            });
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
    }

    private Intent createIntent(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent $r4 = new Intent(str);
        $r4.addFlags(268435456);
        if (uri != null) {
            $r4.setData(uri);
        }
        $r4.putExtra("user_query", this.mUserQuery);
        if (str3 != null) {
            $r4.putExtra("query", str3);
        }
        if (str2 != null) {
            $r4.putExtra("intent_extra_data_key", str2);
        }
        Bundle $r9 = this.mAppSearchData;
        if ($r9 != null) {
            $r4.putExtra("app_data", $r9);
        }
        if (i != 0) {
            $r4.putExtra("action_key", i);
            $r4.putExtra("action_msg", str4);
        }
        $r4.setComponent(this.mSearchable.getSearchActivity());
        return $r4;
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i, String str) {
        int $i0;
        try {
            String $r1 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action");
            String $r5 = $r1;
            if ($r1 == null) {
                $r5 = this.mSearchable.getSuggestIntentAction();
            }
            if ($r5 == null) {
                $r5 = "android.intent.action.SEARCH";
            }
            String $r12 = $r5;
            String $r7 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data");
            String $r52 = $r7;
            if ($r7 == null) {
                $r52 = this.mSearchable.getSuggestIntentData();
            }
            if ($r52 != null) {
                String $r72 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id");
                if ($r72 != null) {
                    $r52 = $r52 + "/" + Uri.encode($r72);
                }
            }
            return createIntent($r12, $r52 == null ? null : Uri.parse($r52), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query"), i, str);
        } catch (RuntimeException $r11) {
            try {
                $i0 = cursor.getPosition();
            } catch (RuntimeException e2) {
                $i0 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + $i0 + " returned exception.", $r11);
            return null;
        }
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        ComponentName $r3 = searchableInfo.getSearchActivity();
        Intent $r4 = new Intent("android.intent.action.SEARCH");
        $r4.setComponent($r3);
        PendingIntent $r6 = PendingIntent.getActivity(getContext(), 0, $r4, 1073741824);
        Bundle $r7 = new Bundle();
        Bundle $r8 = this.mAppSearchData;
        if ($r8 != null) {
            $r7.putParcelable("app_data", $r8);
        }
        Intent $r42 = new Intent(intent);
        int $i0 = 1;
        Resources $r9 = getResources();
        String $r10 = searchableInfo.getVoiceLanguageModeId() != 0 ? $r9.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String $r11 = null;
        String $r12 = searchableInfo.getVoicePromptTextId() != 0 ? $r9.getString(searchableInfo.getVoicePromptTextId()) : null;
        String $r13 = searchableInfo.getVoiceLanguageId() != 0 ? $r9.getString(searchableInfo.getVoiceLanguageId()) : null;
        if (searchableInfo.getVoiceMaxResults() != 0) {
            $i0 = searchableInfo.getVoiceMaxResults();
        }
        $r42.putExtra("android.speech.extra.LANGUAGE_MODEL", $r10);
        $r42.putExtra("android.speech.extra.PROMPT", $r12);
        $r42.putExtra("android.speech.extra.LANGUAGE", $r13);
        $r42.putExtra("android.speech.extra.MAX_RESULTS", $i0);
        if ($r3 != null) {
            $r11 = $r3.flattenToShortString();
        }
        $r42.putExtra("calling_package", $r11);
        $r42.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", $r6);
        $r42.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", $r7);
        return $r42;
    }

    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        Intent $r1 = new Intent(intent);
        ComponentName $r4 = searchableInfo.getSearchActivity();
        $r1.putExtra("calling_package", $r4 == null ? null : $r4.flattenToShortString());
        return $r1;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    private void draw(View view, Rect rect) {
        view.getLocationInWindow(this.a);
        getLocationInWindow(this.f);
        int[] $r4 = this.a;
        int $i0 = $r4[1];
        int[] $r2 = this.f;
        int $i02 = $i0 - $r2[1];
        int $i1 = $r4[0] - $r2[0];
        rect.set($i1, $i02, view.getWidth() + $i1, view.getHeight() + $i02);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault || this.mSearchHintIcon == null) {
            return charSequence;
        }
        double $d0 = (double) this.mSearchSrcTextView.getTextSize();
        Double.isNaN($d0);
        int $i0 = (int) ($d0 * 1.25d);
        this.mSearchHintIcon.setBounds(0, 0, $i0, $i0);
        SpannableStringBuilder $r4 = new SpannableStringBuilder("   ");
        $r4.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
        $r4.append(charSequence);
        return $r4;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    private boolean hasVoiceSearch() {
        SearchableInfo $r1 = this.mSearchable;
        if ($r1 == null || !$r1.getVoiceSearchEnabled()) {
            return false;
        }
        Intent $r2 = null;
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            $r2 = this.mVoiceWebSearchIntent;
        } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            $r2 = this.mVoiceAppSearchIntent;
        }
        return ($r2 == null || getContext().getPackageManager().resolveActivity($r2, 65536) == null) ? false : true;
    }

    static boolean isLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified();
    }

    private void launchIntent(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException $r3) {
                Log.e("SearchView", "Failed launch activity: " + intent, $r3);
            }
        }
    }

    private boolean launchSuggestion(int i, int i2, String str) {
        Cursor $r3 = this.mSuggestionsAdapter.getCursor();
        if ($r3 == null || !$r3.moveToPosition(i)) {
            return false;
        }
        launchIntent(createIntentFromSuggestion($r3, i2, str));
        return true;
    }

    private void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i) {
        CharSequence $r5;
        Editable $r2 = this.mSearchSrcTextView.getText();
        Cursor $r4 = this.mSuggestionsAdapter.getCursor();
        if ($r4 != null) {
            if (!$r4.moveToPosition(i) || ($r5 = this.mSuggestionsAdapter.convertToString($r4)) == null) {
                setQuery($r2);
            } else {
                setQuery($r5);
            }
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.mSearchSrcTextView.setText(charSequence);
        this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void updateCloseButton() {
        boolean $z1 = true;
        boolean $z0 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        int $i0 = 0;
        if (!$z0 && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            $z1 = false;
        }
        ImageView $r3 = this.mCloseButton;
        if (!$z1) {
            $i0 = 8;
        }
        $r3.setVisibility($i0);
        Drawable $r4 = this.mCloseButton.getDrawable();
        if ($r4 != null) {
            $r4.setState($z0 ? View.ENABLED_STATE_SET : View.EMPTY_STATE_SET);
        }
    }

    private void updateQueryHint() {
        Object $r2 = getQueryHint();
        Object $r3 = $r2;
        SearchAutoComplete $r1 = this.mSearchSrcTextView;
        if ($r2 == null) {
            $r3 = "";
        }
        $r1.setHint(getDecoratedHint((CharSequence) $r3));
    }

    private void updateSearchAutoComplete() {
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int $i0 = this.mSearchable.getInputType();
        int $i1 = $i0;
        byte $b3 = 1;
        if (($i0 & 15) == 1) {
            $i1 = $i0 & -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                $i1 = $i1 | 65536 | 524288;
            }
        }
        this.mSearchSrcTextView.setInputType($i1);
        CursorAdapter $r5 = this.mSuggestionsAdapter;
        if ($r5 != null) {
            $r5.changeCursor((Cursor) null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            this.mSuggestionsAdapter = new SuggestionsAdapter(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
            SuggestionsAdapter $r6 = (SuggestionsAdapter) this.mSuggestionsAdapter;
            if (this.mQueryRefinement) {
                $b3 = 2;
            }
            $r6.setQueryRefinement($b3);
        }
    }

    private void updateSubmitArea() {
        this.mSubmitArea.setVisibility((!isSubmitAreaEnabled() || !(this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) ? (byte) 8 : 0);
    }

    private void updateSubmitButton(boolean z) {
        this.mGoButton.setVisibility((!this.mSubmitButtonEnabled || !isSubmitAreaEnabled() || !hasFocus() || (!z && this.mVoiceButtonEnabled)) ? (byte) 8 : 0);
    }

    private void updateViewsVisibility(boolean z) {
        this.mIconified = z;
        int $i0 = 8;
        boolean $z1 = false;
        int $i1 = z ? 0 : 8;
        boolean $z2 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility($i1);
        updateSubmitButton($z2);
        this.mSearchEditFrame.setVisibility(z ? 8 : 0);
        if (this.mCollapsedIcon.getDrawable() != null && !this.mIconifiedByDefault) {
            $i0 = 0;
        }
        this.mCollapsedIcon.setVisibility($i0);
        updateCloseButton();
        if (!$z2) {
            $z1 = true;
        }
        updateVoiceButton($z1);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean z) {
        byte $b0;
        if (!this.mVoiceButtonEnabled || isIconified() || !z) {
            $b0 = 8;
        } else {
            $b0 = 0;
            this.mGoButton.setVisibility(8);
        }
        this.mVoiceButton.setVisibility($b0);
    }

    /* access modifiers changed from: package-private */
    public void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources $r3 = getContext().getResources();
            int $i1 = this.mSearchPlate.getPaddingLeft();
            Rect $r4 = new Rect();
            boolean $z0 = ViewUtils.isLayoutRtl(this);
            int $i2 = this.mIconifiedByDefault ? $r3.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + $r3.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.mSearchSrcTextView.getDropDownBackground().getPadding($r4);
            this.mSearchSrcTextView.setDropDownHorizontalOffset($z0 ? -$r4.left : $i1 - ($r4.left + $i2));
            this.mSearchSrcTextView.setDropDownWidth((((this.mDropDownAnchor.getWidth() + $r4.left) + $r4.right) + $i2) - $i1);
        }
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    /* access modifiers changed from: package-private */
    public void forceSuggestionQuery() {
        HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
        HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence $r1 = this.mQueryHint;
        if ($r1 != null) {
            return $r1;
        }
        SearchableInfo $r2 = this.mSearchable;
        return ($r2 == null || $r2.getHintId() == 0) ? this.mDefaultQueryHint : getContext().getText(this.mSearchable.getHintId());
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    /* access modifiers changed from: package-private */
    public void launchQuerySearch(int i, String str, String str2) {
        getContext().startActivity(createIntent("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i, str));
    }

    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    public void onActionViewExpanded() {
        if (!this.mExpandedInActionView) {
            this.mExpandedInActionView = true;
            this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
            this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | 33554432);
            this.mSearchSrcTextView.setText("");
            setIconified(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            this.mSearchSrcTextView.requestFocus();
            this.mSearchSrcTextView.setImeVisibility(true);
        } else if (this.mIconifiedByDefault) {
            b $r3 = this.mOnCloseListener;
            if ($r3 == null || !$r3.onClose()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public boolean onItemClicked(int i, int i2, String str) {
        d $r2 = this.mOnSuggestionListener;
        if ($r2 != null && $r2.onSuggestionClick(i)) {
            return false;
        }
        launchSuggestion(i, 0, (String) null);
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemSelected(int i) {
        d $r1 = this.mOnSuggestionListener;
        if ($r1 != null && $r1.onSuggestionSelect(i)) {
            return false;
        }
        rewriteQueryFromSuggestion(i);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            draw(this.mSearchSrcTextView, this.left);
            Rect $r2 = this.width;
            Rect $r3 = this.left;
            $r2.set($r3.left, 0, $r3.right, i4 - i2);
            f $r4 = this.m;
            if ($r4 == null) {
                this.m = new f(this.width, this.left, this.mSearchSrcTextView);
                setTouchDelegate(this.m);
                return;
            }
            $r4.draw(this.width, this.left);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r1 <= 0) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            boolean r0 = r4.isIconified()
            if (r0 == 0) goto L_0x000a
            super.onMeasure(r5, r6)
            return
        L_0x000a:
            int r1 = android.view.View.MeasureSpec.getMode(r5)
            int r2 = android.view.View.MeasureSpec.getSize(r5)
            r5 = r2
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r3) goto L_0x002f
            if (r1 == 0) goto L_0x0025
            r3 = 1073741824(0x40000000, float:2.0)
            if (r1 == r3) goto L_0x0020
            goto L_0x003c
        L_0x0020:
            int r1 = r4.mMaxWidth
            if (r1 <= 0) goto L_0x003c
            goto L_0x0033
        L_0x0025:
            int r5 = r4.mMaxWidth
            if (r5 <= 0) goto L_0x002a
            goto L_0x003c
        L_0x002a:
            int r5 = r4.getPreferredWidth()
            goto L_0x003c
        L_0x002f:
            int r1 = r4.mMaxWidth
            if (r1 <= 0) goto L_0x0034
        L_0x0033:
            goto L_0x0038
        L_0x0034:
            int r1 = r4.getPreferredWidth()
        L_0x0038:
            int r5 = java.lang.Math.min(r1, r2)
        L_0x003c:
            int r2 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            r1 = r6
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == r3) goto L_0x0052
            if (r2 == 0) goto L_0x004d
            goto L_0x005a
        L_0x004d:
            int r1 = r4.getPreferredHeight()
            goto L_0x005a
        L_0x0052:
            int r2 = r4.getPreferredHeight()
            int r1 = java.lang.Math.min(r2, r6)
        L_0x005a:
            r3 = 1073741824(0x40000000, float:2.0)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r3)
            r3 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r3)
            super.onMeasure(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void onQueryRefine(CharSequence charSequence) {
        setQuery(charSequence);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        e $r2 = (e) parcelable;
        super.onRestoreInstanceState($r2.getToken());
        updateViewsVisibility($r2.isIconified);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        e $r1 = new e(super.onSaveInstanceState());
        $r1.isIconified = isIconified();
        return $r1;
    }

    /* access modifiers changed from: package-private */
    public void onSearchClicked() {
        updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
        View.OnClickListener $r2 = this.mOnSearchClickListener;
        if ($r2 != null) {
            $r2.onClick(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSubmitQuery() {
        Editable $r2 = this.mSearchSrcTextView.getText();
        if ($r2 != null && TextUtils.getTrimmedLength($r2) > 0) {
            c $r3 = this.mOnQueryChangeListener;
            if ($r3 == null || !$r3.onQueryTextSubmit($r2.toString())) {
                if (this.mSearchable != null) {
                    launchQuerySearch(0, (String) null, $r2.toString());
                }
                this.mSearchSrcTextView.setImeVisibility(false);
                dismissSuggestions();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onSuggestionsKey(View view, int $i0, KeyEvent keyEvent) {
        if (this.mSearchable == null || this.mSuggestionsAdapter == null || keyEvent.getAction() != 0 || !keyEvent.hasNoModifiers()) {
            return false;
        }
        if ($i0 == 66 || $i0 == 84 || $i0 == 61) {
            return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, (String) null);
        }
        if ($i0 != 21 && $i0 != 22) {
            return ($i0 == 19 && this.mSearchSrcTextView.getListSelection() == 0) ? false : false;
        }
        this.mSearchSrcTextView.setSelection($i0 == 21 ? 0 : this.mSearchSrcTextView.length());
        this.mSearchSrcTextView.setListSelection(0);
        this.mSearchSrcTextView.clearListSelection();
        HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onTextChanged(CharSequence charSequence) {
        Editable $r3 = this.mSearchSrcTextView.getText();
        this.mUserQuery = $r3;
        boolean $z1 = true;
        boolean $z0 = !TextUtils.isEmpty($r3);
        updateSubmitButton($z0);
        if ($z0) {
            $z1 = false;
        }
        updateVoiceButton($z1);
        updateCloseButton();
        updateSubmitArea();
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    /* access modifiers changed from: package-private */
    public void onTextFocusChanged() {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    /* access modifiers changed from: package-private */
    public void onVoiceClicked() {
        Intent $r3;
        SearchableInfo $r2 = this.mSearchable;
        if ($r2 != null) {
            try {
                if ($r2.getVoiceSearchLaunchWebSearch()) {
                    $r3 = createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, $r2);
                } else if ($r2.getVoiceSearchLaunchRecognizer()) {
                    $r3 = createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, $r2);
                } else {
                    return;
                }
                getContext().startActivity($r3);
            } catch (ActivityNotFoundException e2) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        postUpdateFocusedState();
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.mClearingFocus || !isFocusable()) {
            return false;
        }
        if (isIconified()) {
            return super.requestFocus(i, rect);
        }
        boolean $z0 = this.mSearchSrcTextView.requestFocus(i, rect);
        if (!$z0) {
            return $z0;
        }
        updateViewsVisibility(false);
        return $z0;
    }

    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.mIconifiedByDefault != z) {
            this.mIconifiedByDefault = z;
            updateViewsVisibility(z);
            updateQueryHint();
        }
    }

    public void setImeOptions(int i) {
        this.mSearchSrcTextView.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.mSearchSrcTextView.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        requestLayout();
    }

    public void setOnCloseListener(b bVar) {
        this.mOnCloseListener = bVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(c cVar) {
        this.mOnQueryChangeListener = cVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setOnSuggestionListener(d dVar) {
        this.mOnSuggestionListener = dVar;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete $r2 = this.mSearchSrcTextView;
            $r2.setSelection($r2.length());
            this.mUserQuery = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            onSubmitQuery();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.mQueryRefinement = z;
        CursorAdapter $r1 = this.mSuggestionsAdapter;
        if ($r1 instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter) $r1).setQueryRefinement(z ? (byte) 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (this.mSearchable != null) {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        this.mVoiceButtonEnabled = hasVoiceSearch();
        if (this.mVoiceButtonEnabled) {
            this.mSearchSrcTextView.setPrivateImeOptions("nm");
        }
        updateViewsVisibility(isIconified());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.mSubmitButtonEnabled = z;
        updateViewsVisibility(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.mSuggestionsAdapter = cursorAdapter;
        this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
    }

    /* access modifiers changed from: package-private */
    public void updateFocusedState() {
        int[] $r2 = this.mSearchSrcTextView.hasFocus() ? View.FOCUSED_STATE_SET : View.EMPTY_STATE_SET;
        Drawable $r4 = this.mSearchPlate.getBackground();
        if ($r4 != null) {
            $r4.setState($r2);
        }
        Drawable $r42 = this.mSubmitArea.getBackground();
        if ($r42 != null) {
            $r42.setState($r2);
        }
        invalidate();
    }
}
