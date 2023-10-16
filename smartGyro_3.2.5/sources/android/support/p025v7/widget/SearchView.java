package android.support.p025v7.widget;

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
import android.support.p024v4.widget.C0206d;
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
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import p000a.p001a.p005c.p014g.C0105c;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0139d;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p018a.C0143h;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p023d.C0166c;

/* renamed from: android.support.v7.widget.SearchView */
public class SearchView extends C0357Q implements C0166c {

    /* renamed from: p */
    static final C0360a f1304p = new C0360a();

    /* renamed from: A */
    private Rect f1305A;

    /* renamed from: B */
    private Rect f1306B;

    /* renamed from: C */
    private int[] f1307C;

    /* renamed from: D */
    private int[] f1308D;

    /* renamed from: E */
    private final ImageView f1309E;

    /* renamed from: F */
    private final Drawable f1310F;

    /* renamed from: G */
    private final int f1311G;

    /* renamed from: H */
    private final int f1312H;

    /* renamed from: I */
    private final Intent f1313I;

    /* renamed from: J */
    private final Intent f1314J;

    /* renamed from: K */
    private final CharSequence f1315K;

    /* renamed from: L */
    private C0362c f1316L;

    /* renamed from: M */
    private C0361b f1317M;

    /* renamed from: N */
    View.OnFocusChangeListener f1318N;

    /* renamed from: O */
    private C0363d f1319O;

    /* renamed from: P */
    private View.OnClickListener f1320P;

    /* renamed from: Q */
    private boolean f1321Q;

    /* renamed from: R */
    private boolean f1322R;

    /* renamed from: S */
    C0206d f1323S;

    /* renamed from: T */
    private boolean f1324T;

    /* renamed from: U */
    private CharSequence f1325U;

    /* renamed from: V */
    private boolean f1326V;

    /* renamed from: W */
    private boolean f1327W;

    /* renamed from: aa */
    private int f1328aa;

    /* renamed from: ba */
    private boolean f1329ba;

    /* renamed from: ca */
    private CharSequence f1330ca;

    /* renamed from: da */
    private CharSequence f1331da;

    /* renamed from: ea */
    private boolean f1332ea;

    /* renamed from: fa */
    private int f1333fa;

    /* renamed from: ga */
    SearchableInfo f1334ga;

    /* renamed from: ha */
    private Bundle f1335ha;

    /* renamed from: ia */
    private final Runnable f1336ia;

    /* renamed from: ja */
    private Runnable f1337ja;

    /* renamed from: ka */
    private final WeakHashMap<String, Drawable.ConstantState> f1338ka;

    /* renamed from: la */
    private final View.OnClickListener f1339la;

    /* renamed from: ma */
    View.OnKeyListener f1340ma;

    /* renamed from: na */
    private final TextView.OnEditorActionListener f1341na;

    /* renamed from: oa */
    private final AdapterView.OnItemClickListener f1342oa;

    /* renamed from: pa */
    private final AdapterView.OnItemSelectedListener f1343pa;

    /* renamed from: q */
    final SearchAutoComplete f1344q;

    /* renamed from: qa */
    private TextWatcher f1345qa;

    /* renamed from: r */
    private final View f1346r;

    /* renamed from: s */
    private final View f1347s;

    /* renamed from: t */
    private final View f1348t;

    /* renamed from: u */
    final ImageView f1349u;

    /* renamed from: v */
    final ImageView f1350v;

    /* renamed from: w */
    final ImageView f1351w;

    /* renamed from: x */
    final ImageView f1352x;

    /* renamed from: y */
    private final View f1353y;

    /* renamed from: z */
    private C0365f f1354z;

    /* renamed from: android.support.v7.widget.SearchView$SearchAutoComplete */
    public static class SearchAutoComplete extends C0410i {

        /* renamed from: d */
        private int f1355d;

        /* renamed from: e */
        private SearchView f1356e;

        /* renamed from: f */
        private boolean f1357f;

        /* renamed from: g */
        final Runnable f1358g;

        public SearchAutoComplete(Context context) {
            this(context, (AttributeSet) null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, C0136a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.f1358g = new C0419ma(this);
            this.f1355d = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo1888a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo1889b() {
            if (this.f1357f) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f1357f = false;
            }
        }

        public boolean enoughToFilter() {
            return this.f1355d <= 0 || super.enoughToFilter();
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1357f) {
                removeCallbacks(this.f1358g);
                post(this.f1358g);
            }
            return onCreateInputConnection;
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        /* access modifiers changed from: protected */
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f1356e.mo1856g();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1356e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f1356e.hasFocus() && getVisibility() == 0) {
                this.f1357f = true;
                if (SearchView.m1612a(getContext())) {
                    SearchView.f1304p.mo1902a(this, true);
                }
            }
        }

        public void performCompletion() {
        }

        /* access modifiers changed from: protected */
        public void replaceText(CharSequence charSequence) {
        }

        /* access modifiers changed from: package-private */
        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f1357f = false;
                removeCallbacks(this.f1358g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.f1357f = false;
                removeCallbacks(this.f1358g);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.f1357f = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void setSearchView(SearchView searchView) {
            this.f1356e = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f1355d = i;
        }
    }

    /* renamed from: android.support.v7.widget.SearchView$a */
    private static class C0360a {

        /* renamed from: a */
        private Method f1359a;

        /* renamed from: b */
        private Method f1360b;

        /* renamed from: c */
        private Method f1361c;

        C0360a() {
            try {
                this.f1359a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f1359a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.f1360b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f1360b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            Class<AutoCompleteTextView> cls = AutoCompleteTextView.class;
            try {
                this.f1361c = cls.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.f1361c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1901a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f1360b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1902a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.f1361c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[]{Boolean.valueOf(z)});
                } catch (Exception unused) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo1903b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f1359a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.SearchView$b */
    public interface C0361b {
        boolean onClose();
    }

    /* renamed from: android.support.v7.widget.SearchView$c */
    public interface C0362c {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    /* renamed from: android.support.v7.widget.SearchView$d */
    public interface C0363d {
        boolean onSuggestionClick(int i);

        boolean onSuggestionSelect(int i);
    }

    /* renamed from: android.support.v7.widget.SearchView$e */
    static class C0364e extends C0105c {
        public static final Parcelable.Creator<C0364e> CREATOR = new C0417la();

        /* renamed from: c */
        boolean f1362c;

        public C0364e(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1362c = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
        }

        C0364e(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f1362c + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.f1362c));
        }
    }

    /* renamed from: android.support.v7.widget.SearchView$f */
    private static class C0365f extends TouchDelegate {

        /* renamed from: a */
        private final View f1363a;

        /* renamed from: b */
        private final Rect f1364b = new Rect();

        /* renamed from: c */
        private final Rect f1365c = new Rect();

        /* renamed from: d */
        private final Rect f1366d = new Rect();

        /* renamed from: e */
        private final int f1367e;

        /* renamed from: f */
        private boolean f1368f;

        public C0365f(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f1367e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            mo1910a(rect, rect2);
            this.f1363a = view;
        }

        /* renamed from: a */
        public void mo1910a(Rect rect, Rect rect2) {
            this.f1364b.set(rect);
            this.f1366d.set(rect);
            Rect rect3 = this.f1366d;
            int i = this.f1367e;
            rect3.inset(-i, -i);
            this.f1365c.set(rect2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
        /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouchEvent(android.view.MotionEvent r8) {
            /*
                r7 = this;
                float r0 = r8.getX()
                int r0 = (int) r0
                float r1 = r8.getY()
                int r1 = (int) r1
                int r2 = r8.getAction()
                r3 = 2
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L_0x002e
                if (r2 == r4) goto L_0x0020
                if (r2 == r3) goto L_0x0020
                r6 = 3
                if (r2 == r6) goto L_0x001b
                goto L_0x003a
            L_0x001b:
                boolean r2 = r7.f1368f
                r7.f1368f = r5
                goto L_0x003b
            L_0x0020:
                boolean r2 = r7.f1368f
                if (r2 == 0) goto L_0x003b
                android.graphics.Rect r6 = r7.f1366d
                boolean r6 = r6.contains(r0, r1)
                if (r6 != 0) goto L_0x003b
                r4 = 0
                goto L_0x003b
            L_0x002e:
                android.graphics.Rect r2 = r7.f1364b
                boolean r2 = r2.contains(r0, r1)
                if (r2 == 0) goto L_0x003a
                r7.f1368f = r4
                r2 = 1
                goto L_0x003b
            L_0x003a:
                r2 = 0
            L_0x003b:
                if (r2 == 0) goto L_0x006a
                if (r4 == 0) goto L_0x0057
                android.graphics.Rect r2 = r7.f1365c
                boolean r2 = r2.contains(r0, r1)
                if (r2 != 0) goto L_0x0057
                android.view.View r0 = r7.f1363a
                int r0 = r0.getWidth()
                int r0 = r0 / r3
                float r0 = (float) r0
                android.view.View r1 = r7.f1363a
                int r1 = r1.getHeight()
                int r1 = r1 / r3
                goto L_0x0060
            L_0x0057:
                android.graphics.Rect r2 = r7.f1365c
                int r3 = r2.left
                int r0 = r0 - r3
                float r0 = (float) r0
                int r2 = r2.top
                int r1 = r1 - r2
            L_0x0060:
                float r1 = (float) r1
                r8.setLocation(r0, r1)
                android.view.View r0 = r7.f1363a
                boolean r5 = r0.dispatchTouchEvent(r8)
            L_0x006a:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.SearchView.C0365f.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    public SearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1305A = new Rect();
        this.f1306B = new Rect();
        this.f1307C = new int[2];
        this.f1308D = new int[2];
        this.f1336ia = new C0393ca(this);
        this.f1337ja = new C0395da(this);
        this.f1338ka = new WeakHashMap<>();
        this.f1339la = new C0407ga(this);
        this.f1340ma = new C0409ha(this);
        this.f1341na = new C0411ia(this);
        this.f1342oa = new C0413ja(this);
        this.f1343pa = new C0415ka(this);
        this.f1345qa = new C0391ba(this);
        C0439ta a = C0439ta.m1902a(context, attributeSet, C0145j.SearchView, i, 0);
        LayoutInflater.from(context).inflate(a.mo2286g(C0145j.SearchView_layout, C0142g.abc_search_view), this, true);
        this.f1344q = (SearchAutoComplete) findViewById(C0141f.search_src_text);
        this.f1344q.setSearchView(this);
        this.f1346r = findViewById(C0141f.search_edit_frame);
        this.f1347s = findViewById(C0141f.search_plate);
        this.f1348t = findViewById(C0141f.submit_area);
        this.f1349u = (ImageView) findViewById(C0141f.search_button);
        this.f1350v = (ImageView) findViewById(C0141f.search_go_btn);
        this.f1351w = (ImageView) findViewById(C0141f.search_close_btn);
        this.f1352x = (ImageView) findViewById(C0141f.search_voice_btn);
        this.f1309E = (ImageView) findViewById(C0141f.search_mag_icon);
        C0127u.m443a(this.f1347s, a.mo2277b(C0145j.SearchView_queryBackground));
        C0127u.m443a(this.f1348t, a.mo2277b(C0145j.SearchView_submitBackground));
        this.f1349u.setImageDrawable(a.mo2277b(C0145j.SearchView_searchIcon));
        this.f1350v.setImageDrawable(a.mo2277b(C0145j.SearchView_goIcon));
        this.f1351w.setImageDrawable(a.mo2277b(C0145j.SearchView_closeIcon));
        this.f1352x.setImageDrawable(a.mo2277b(C0145j.SearchView_voiceIcon));
        this.f1309E.setImageDrawable(a.mo2277b(C0145j.SearchView_searchIcon));
        this.f1310F = a.mo2277b(C0145j.SearchView_searchHintIcon);
        C0327Ba.m1444a(this.f1349u, getResources().getString(C0143h.abc_searchview_description_search));
        this.f1311G = a.mo2286g(C0145j.SearchView_suggestionRowLayout, C0142g.abc_search_dropdown_item_icons_2line);
        this.f1312H = a.mo2286g(C0145j.SearchView_commitIcon, 0);
        this.f1349u.setOnClickListener(this.f1339la);
        this.f1351w.setOnClickListener(this.f1339la);
        this.f1350v.setOnClickListener(this.f1339la);
        this.f1352x.setOnClickListener(this.f1339la);
        this.f1344q.setOnClickListener(this.f1339la);
        this.f1344q.addTextChangedListener(this.f1345qa);
        this.f1344q.setOnEditorActionListener(this.f1341na);
        this.f1344q.setOnItemClickListener(this.f1342oa);
        this.f1344q.setOnItemSelectedListener(this.f1343pa);
        this.f1344q.setOnKeyListener(this.f1340ma);
        this.f1344q.setOnFocusChangeListener(new C0397ea(this));
        setIconifiedByDefault(a.mo2275a(C0145j.SearchView_iconifiedByDefault, true));
        int c = a.mo2278c(C0145j.SearchView_android_maxWidth, -1);
        if (c != -1) {
            setMaxWidth(c);
        }
        this.f1315K = a.mo2283e(C0145j.SearchView_defaultQueryHint);
        this.f1325U = a.mo2283e(C0145j.SearchView_queryHint);
        int d = a.mo2280d(C0145j.SearchView_android_imeOptions, -1);
        if (d != -1) {
            setImeOptions(d);
        }
        int d2 = a.mo2280d(C0145j.SearchView_android_inputType, -1);
        if (d2 != -1) {
            setInputType(d2);
        }
        setFocusable(a.mo2275a(C0145j.SearchView_android_focusable, true));
        a.mo2274a();
        this.f1313I = new Intent("android.speech.action.WEB_SEARCH");
        this.f1313I.addFlags(268435456);
        this.f1313I.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.f1314J = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.f1314J.addFlags(268435456);
        this.f1353y = findViewById(this.f1344q.getDropDownAnchor());
        View view = this.f1353y;
        if (view != null) {
            view.addOnLayoutChangeListener(new C0399fa(this));
        }
        m1614b(this.f1321Q);
        m1624o();
    }

    /* renamed from: a */
    private Intent m1606a(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f1335ha;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        int i = 1;
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String str = null;
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i = searchableInfo.getVoiceMaxResults();
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i);
        if (searchActivity != null) {
            str = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    /* renamed from: a */
    private Intent m1607a(Cursor cursor, int i, String str) {
        int i2;
        String a;
        try {
            String a2 = C0421na.m1826a(cursor, "suggest_intent_action");
            if (a2 == null) {
                a2 = this.f1334ga.getSuggestIntentAction();
            }
            if (a2 == null) {
                a2 = "android.intent.action.SEARCH";
            }
            String str2 = a2;
            String a3 = C0421na.m1826a(cursor, "suggest_intent_data");
            if (a3 == null) {
                a3 = this.f1334ga.getSuggestIntentData();
            }
            if (!(a3 == null || (a = C0421na.m1826a(cursor, "suggest_intent_data_id")) == null)) {
                a3 = a3 + "/" + Uri.encode(a);
            }
            return m1608a(str2, a3 == null ? null : Uri.parse(a3), C0421na.m1826a(cursor, "suggest_intent_extra_data"), C0421na.m1826a(cursor, "suggest_intent_query"), i, str);
        } catch (RuntimeException e) {
            try {
                i2 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i2 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i2 + " returned exception.", e);
            return null;
        }
    }

    /* renamed from: a */
    private Intent m1608a(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f1331da);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f1335ha;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f1334ga.getSearchActivity());
        return intent;
    }

    /* renamed from: a */
    private void m1609a(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException e) {
                Log.e("SearchView", "Failed launch activity: " + intent, e);
            }
        }
    }

    /* renamed from: a */
    private void m1610a(View view, Rect rect) {
        view.getLocationInWindow(this.f1307C);
        getLocationInWindow(this.f1308D);
        int[] iArr = this.f1307C;
        int i = iArr[1];
        int[] iArr2 = this.f1308D;
        int i2 = i - iArr2[1];
        int i3 = iArr[0] - iArr2[0];
        rect.set(i3, i2, view.getWidth() + i3, view.getHeight() + i2);
    }

    /* renamed from: a */
    private void m1611a(boolean z) {
        this.f1350v.setVisibility((!this.f1324T || !m1621l() || !hasFocus() || (!z && this.f1329ba)) ? 8 : 0);
    }

    /* renamed from: a */
    static boolean m1612a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    /* renamed from: b */
    private Intent m1613b(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    /* renamed from: b */
    private void m1614b(boolean z) {
        this.f1322R = z;
        int i = 8;
        boolean z2 = false;
        int i2 = z ? 0 : 8;
        boolean z3 = !TextUtils.isEmpty(this.f1344q.getText());
        this.f1349u.setVisibility(i2);
        m1611a(z3);
        this.f1346r.setVisibility(z ? 8 : 0);
        if (this.f1309E.getDrawable() != null && !this.f1321Q) {
            i = 0;
        }
        this.f1309E.setVisibility(i);
        m1623n();
        if (!z3) {
            z2 = true;
        }
        m1617c(z2);
        m1626q();
    }

    /* renamed from: b */
    private boolean m1615b(int i, int i2, String str) {
        Cursor a = this.f1323S.mo890a();
        if (a == null || !a.moveToPosition(i)) {
            return false;
        }
        m1609a(m1607a(a, i2, str));
        return true;
    }

    /* renamed from: c */
    private CharSequence m1616c(CharSequence charSequence) {
        if (!this.f1321Q || this.f1310F == null) {
            return charSequence;
        }
        double textSize = (double) this.f1344q.getTextSize();
        Double.isNaN(textSize);
        int i = (int) (textSize * 1.25d);
        this.f1310F.setBounds(0, 0, i, i);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.f1310F), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    /* renamed from: c */
    private void m1617c(boolean z) {
        int i;
        if (!this.f1329ba || mo1850c() || !z) {
            i = 8;
        } else {
            i = 0;
            this.f1350v.setVisibility(8);
        }
        this.f1352x.setVisibility(i);
    }

    /* renamed from: e */
    private void m1618e(int i) {
        CharSequence convertToString;
        Editable text = this.f1344q.getText();
        Cursor a = this.f1323S.mo890a();
        if (a != null) {
            if (!a.moveToPosition(i) || (convertToString = this.f1323S.convertToString(a)) == null) {
                setQuery(text);
            } else {
                setQuery(convertToString);
            }
        }
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(C0139d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0139d.abc_search_view_preferred_width);
    }

    /* renamed from: j */
    private void m1619j() {
        this.f1344q.dismissDropDown();
    }

    /* renamed from: k */
    private boolean m1620k() {
        SearchableInfo searchableInfo = this.f1334ga;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.f1334ga.getVoiceSearchLaunchWebSearch()) {
            intent = this.f1313I;
        } else if (this.f1334ga.getVoiceSearchLaunchRecognizer()) {
            intent = this.f1314J;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    /* renamed from: l */
    private boolean m1621l() {
        return (this.f1324T || this.f1329ba) && !mo1850c();
    }

    /* renamed from: m */
    private void m1622m() {
        post(this.f1336ia);
    }

    /* renamed from: n */
    private void m1623n() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f1344q.getText());
        int i = 0;
        if (!z2 && (!this.f1321Q || this.f1332ea)) {
            z = false;
        }
        ImageView imageView = this.f1351w;
        if (!z) {
            i = 8;
        }
        imageView.setVisibility(i);
        Drawable drawable = this.f1351w.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    /* renamed from: o */
    private void m1624o() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f1344q;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(m1616c(queryHint));
    }

    /* renamed from: p */
    private void m1625p() {
        this.f1344q.setThreshold(this.f1334ga.getSuggestThreshold());
        this.f1344q.setImeOptions(this.f1334ga.getImeOptions());
        int inputType = this.f1334ga.getInputType();
        int i = 1;
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f1334ga.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.f1344q.setInputType(inputType);
        C0206d dVar = this.f1323S;
        if (dVar != null) {
            dVar.mo893a((Cursor) null);
        }
        if (this.f1334ga.getSuggestAuthority() != null) {
            this.f1323S = new C0421na(getContext(), this, this.f1334ga, this.f1338ka);
            this.f1344q.setAdapter(this.f1323S);
            C0421na naVar = (C0421na) this.f1323S;
            if (this.f1326V) {
                i = 2;
            }
            naVar.mo2220a(i);
        }
    }

    /* renamed from: q */
    private void m1626q() {
        this.f1348t.setVisibility((!m1621l() || !(this.f1350v.getVisibility() == 0 || this.f1352x.getVisibility() == 0)) ? 8 : 0);
    }

    private void setQuery(CharSequence charSequence) {
        this.f1344q.setText(charSequence);
        this.f1344q.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1842a() {
        if (this.f1353y.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f1347s.getPaddingLeft();
            Rect rect = new Rect();
            boolean a = C0342Ha.m1498a(this);
            int dimensionPixelSize = this.f1321Q ? resources.getDimensionPixelSize(C0139d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(C0139d.abc_dropdownitem_text_padding_left) : 0;
            this.f1344q.getDropDownBackground().getPadding(rect);
            this.f1344q.setDropDownHorizontalOffset(a ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.f1344q.setDropDownWidth((((this.f1353y.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1843a(int i, String str, String str2) {
        getContext().startActivity(m1608a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1844a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    /* renamed from: a */
    public void mo1845a(CharSequence charSequence, boolean z) {
        this.f1344q.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f1344q;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f1331da = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            mo1855f();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1846a(int i, int i2, String str) {
        C0363d dVar = this.f1319O;
        if (dVar != null && dVar.onSuggestionClick(i)) {
            return false;
        }
        m1615b(i, 0, (String) null);
        this.f1344q.setImeVisibility(false);
        m1619j();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1847a(View view, int i, KeyEvent keyEvent) {
        if (this.f1334ga != null && this.f1323S != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i == 66 || i == 84 || i == 61) {
                return mo1846a(this.f1344q.getListSelection(), 0, (String) null);
            }
            if (i == 21 || i == 22) {
                this.f1344q.setSelection(i == 21 ? 0 : this.f1344q.length());
                this.f1344q.setListSelection(0);
                this.f1344q.clearListSelection();
                f1304p.mo1902a(this.f1344q, true);
                return true;
            } else if (i != 19 || this.f1344q.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1848b() {
        f1304p.mo1903b(this.f1344q);
        f1304p.mo1901a(this.f1344q);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1849b(CharSequence charSequence) {
        Editable text = this.f1344q.getText();
        this.f1331da = text;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(text);
        m1611a(z2);
        if (z2) {
            z = false;
        }
        m1617c(z);
        m1623n();
        m1626q();
        if (this.f1316L != null && !TextUtils.equals(charSequence, this.f1330ca)) {
            this.f1316L.onQueryTextChange(charSequence.toString());
        }
        this.f1330ca = charSequence.toString();
    }

    /* renamed from: c */
    public boolean mo1850c() {
        return this.f1322R;
    }

    public void clearFocus() {
        this.f1327W = true;
        super.clearFocus();
        this.f1344q.clearFocus();
        this.f1344q.setImeVisibility(false);
        this.f1327W = false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1852d() {
        if (!TextUtils.isEmpty(this.f1344q.getText())) {
            this.f1344q.setText("");
            this.f1344q.requestFocus();
            this.f1344q.setImeVisibility(true);
        } else if (this.f1321Q) {
            C0361b bVar = this.f1317M;
            if (bVar == null || !bVar.onClose()) {
                clearFocus();
                m1614b(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo1853d(int i) {
        C0363d dVar = this.f1319O;
        if (dVar != null && dVar.onSuggestionSelect(i)) {
            return false;
        }
        m1618e(i);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo1854e() {
        m1614b(false);
        this.f1344q.requestFocus();
        this.f1344q.setImeVisibility(true);
        View.OnClickListener onClickListener = this.f1320P;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo1855f() {
        Editable text = this.f1344q.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            C0362c cVar = this.f1316L;
            if (cVar == null || !cVar.onQueryTextSubmit(text.toString())) {
                if (this.f1334ga != null) {
                    mo1843a(0, (String) null, text.toString());
                }
                this.f1344q.setImeVisibility(false);
                m1619j();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo1856g() {
        m1614b(mo1850c());
        m1622m();
        if (this.f1344q.hasFocus()) {
            mo1848b();
        }
    }

    public int getImeOptions() {
        return this.f1344q.getImeOptions();
    }

    public int getInputType() {
        return this.f1344q.getInputType();
    }

    public int getMaxWidth() {
        return this.f1328aa;
    }

    public CharSequence getQuery() {
        return this.f1344q.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.f1325U;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f1334ga;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.f1315K : getContext().getText(this.f1334ga.getHintId());
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.f1312H;
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.f1311G;
    }

    public C0206d getSuggestionsAdapter() {
        return this.f1323S;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo1865h() {
        Intent a;
        SearchableInfo searchableInfo = this.f1334ga;
        if (searchableInfo != null) {
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    a = m1613b(this.f1313I, searchableInfo);
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    a = m1606a(this.f1314J, searchableInfo);
                } else {
                    return;
                }
                getContext().startActivity(a);
            } catch (ActivityNotFoundException unused) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public void mo1866i() {
        int[] iArr = this.f1344q.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f1347s.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f1348t.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public void onActionViewCollapsed() {
        mo1845a((CharSequence) "", false);
        clearFocus();
        m1614b(true);
        this.f1344q.setImeOptions(this.f1333fa);
        this.f1332ea = false;
    }

    public void onActionViewExpanded() {
        if (!this.f1332ea) {
            this.f1332ea = true;
            this.f1333fa = this.f1344q.getImeOptions();
            this.f1344q.setImeOptions(this.f1333fa | 33554432);
            this.f1344q.setText("");
            setIconified(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.f1336ia);
        post(this.f1337ja);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            m1610a((View) this.f1344q, this.f1305A);
            Rect rect = this.f1306B;
            Rect rect2 = this.f1305A;
            rect.set(rect2.left, 0, rect2.right, i4 - i2);
            C0365f fVar = this.f1354z;
            if (fVar == null) {
                this.f1354z = new C0365f(this.f1306B, this.f1305A, this.f1344q);
                setTouchDelegate(this.f1354z);
                return;
            }
            fVar.mo1910a(this.f1306B, this.f1305A);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r0 <= 0) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            boolean r0 = r3.mo1850c()
            if (r0 == 0) goto L_0x000a
            super.onMeasure(r4, r5)
            return
        L_0x000a:
            int r0 = android.view.View.MeasureSpec.getMode(r4)
            int r4 = android.view.View.MeasureSpec.getSize(r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r1) goto L_0x002c
            if (r0 == 0) goto L_0x0022
            if (r0 == r2) goto L_0x001d
            goto L_0x0039
        L_0x001d:
            int r0 = r3.f1328aa
            if (r0 <= 0) goto L_0x0039
            goto L_0x0030
        L_0x0022:
            int r4 = r3.f1328aa
            if (r4 <= 0) goto L_0x0027
            goto L_0x0039
        L_0x0027:
            int r4 = r3.getPreferredWidth()
            goto L_0x0039
        L_0x002c:
            int r0 = r3.f1328aa
            if (r0 <= 0) goto L_0x0031
        L_0x0030:
            goto L_0x0035
        L_0x0031:
            int r0 = r3.getPreferredWidth()
        L_0x0035:
            int r4 = java.lang.Math.min(r0, r4)
        L_0x0039:
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 == r1) goto L_0x004b
            if (r0 == 0) goto L_0x0046
            goto L_0x0053
        L_0x0046:
            int r5 = r3.getPreferredHeight()
            goto L_0x0053
        L_0x004b:
            int r0 = r3.getPreferredHeight()
            int r5 = java.lang.Math.min(r0, r5)
        L_0x0053:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r2)
            super.onMeasure(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.SearchView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0364e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0364e eVar = (C0364e) parcelable;
        super.onRestoreInstanceState(eVar.mo430a());
        m1614b(eVar.f1362c);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        C0364e eVar = new C0364e(super.onSaveInstanceState());
        eVar.f1362c = mo1850c();
        return eVar;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        m1622m();
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.f1327W || !isFocusable()) {
            return false;
        }
        if (mo1850c()) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.f1344q.requestFocus(i, rect);
        if (requestFocus) {
            m1614b(false);
        }
        return requestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f1335ha = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            mo1852d();
        } else {
            mo1854e();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.f1321Q != z) {
            this.f1321Q = z;
            m1614b(z);
            m1624o();
        }
    }

    public void setImeOptions(int i) {
        this.f1344q.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.f1344q.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.f1328aa = i;
        requestLayout();
    }

    public void setOnCloseListener(C0361b bVar) {
        this.f1317M = bVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.f1318N = onFocusChangeListener;
    }

    public void setOnQueryTextListener(C0362c cVar) {
        this.f1316L = cVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.f1320P = onClickListener;
    }

    public void setOnSuggestionListener(C0363d dVar) {
        this.f1319O = dVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f1325U = charSequence;
        m1624o();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.f1326V = z;
        C0206d dVar = this.f1323S;
        if (dVar instanceof C0421na) {
            ((C0421na) dVar).mo2220a(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f1334ga = searchableInfo;
        if (this.f1334ga != null) {
            m1625p();
            m1624o();
        }
        this.f1329ba = m1620k();
        if (this.f1329ba) {
            this.f1344q.setPrivateImeOptions("nm");
        }
        m1614b(mo1850c());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.f1324T = z;
        m1614b(mo1850c());
    }

    public void setSuggestionsAdapter(C0206d dVar) {
        this.f1323S = dVar;
        this.f1344q.setAdapter(this.f1323S);
    }
}
