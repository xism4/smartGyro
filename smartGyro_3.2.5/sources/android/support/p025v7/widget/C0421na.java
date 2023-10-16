package android.support.p025v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.p024v4.widget.C0217l;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;
import p000a.p001a.p005c.p006a.C0025a;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;

/* renamed from: android.support.v7.widget.na */
class C0421na extends C0217l implements View.OnClickListener {

    /* renamed from: l */
    private final SearchManager f1577l = ((SearchManager) this.f544d.getSystemService("search"));

    /* renamed from: m */
    private final SearchView f1578m;

    /* renamed from: n */
    private final SearchableInfo f1579n;

    /* renamed from: o */
    private final Context f1580o;

    /* renamed from: p */
    private final WeakHashMap<String, Drawable.ConstantState> f1581p;

    /* renamed from: q */
    private final int f1582q;

    /* renamed from: r */
    private boolean f1583r = false;

    /* renamed from: s */
    private int f1584s = 1;

    /* renamed from: t */
    private ColorStateList f1585t;

    /* renamed from: u */
    private int f1586u = -1;

    /* renamed from: v */
    private int f1587v = -1;

    /* renamed from: w */
    private int f1588w = -1;

    /* renamed from: x */
    private int f1589x = -1;

    /* renamed from: y */
    private int f1590y = -1;

    /* renamed from: z */
    private int f1591z = -1;

    /* renamed from: android.support.v7.widget.na$a */
    private static final class C0422a {

        /* renamed from: a */
        public final TextView f1592a;

        /* renamed from: b */
        public final TextView f1593b;

        /* renamed from: c */
        public final ImageView f1594c;

        /* renamed from: d */
        public final ImageView f1595d;

        /* renamed from: e */
        public final ImageView f1596e;

        public C0422a(View view) {
            this.f1592a = (TextView) view.findViewById(16908308);
            this.f1593b = (TextView) view.findViewById(16908309);
            this.f1594c = (ImageView) view.findViewById(16908295);
            this.f1595d = (ImageView) view.findViewById(16908296);
            this.f1596e = (ImageView) view.findViewById(C0141f.edit_query);
        }
    }

    public C0421na(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.f1578m = searchView;
        this.f1579n = searchableInfo;
        this.f1582q = searchView.getSuggestionCommitIconResId();
        this.f1580o = context;
        this.f1581p = weakHashMap;
    }

    /* renamed from: a */
    private Drawable m1823a(ComponentName componentName) {
        String nameNotFoundException;
        PackageManager packageManager = this.f544d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            nameNotFoundException = "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString();
            Log.w("SuggestionsAdapter", nameNotFoundException);
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            nameNotFoundException = e.toString();
        }
    }

    /* renamed from: a */
    private Drawable m1824a(String str) {
        Drawable.ConstantState constantState = this.f1581p.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    /* renamed from: a */
    private static String m1825a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    /* renamed from: a */
    public static String m1826a(Cursor cursor, String str) {
        return m1825a(cursor, cursor.getColumnIndex(str));
    }

    /* renamed from: a */
    private void m1827a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    /* renamed from: a */
    private void m1828a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    /* renamed from: a */
    private void m1829a(String str, Drawable drawable) {
        if (drawable != null) {
            this.f1581p.put(str, drawable.getConstantState());
        }
    }

    /* renamed from: b */
    private Drawable m1830b(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        Drawable.ConstantState constantState = null;
        if (this.f1581p.containsKey(flattenToShortString)) {
            Drawable.ConstantState constantState2 = this.f1581p.get(flattenToShortString);
            if (constantState2 == null) {
                return null;
            }
            return constantState2.newDrawable(this.f1580o.getResources());
        }
        Drawable a = m1823a(componentName);
        if (a != null) {
            constantState = a.getConstantState();
        }
        this.f1581p.put(flattenToShortString, constantState);
        return a;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
        throw new java.io.FileNotFoundException("Resource does not exist: " + r7);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0016 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable m1831b(android.net.Uri r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Error closing icon stream for "
            java.lang.String r1 = "SuggestionsAdapter"
            r2 = 0
            java.lang.String r3 = r7.getScheme()     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r4 = "android.resource"
            boolean r3 = r4.equals(r3)     // Catch:{ FileNotFoundException -> 0x0085 }
            if (r3 == 0) goto L_0x002d
            android.graphics.drawable.Drawable r7 = r6.mo2219a((android.net.Uri) r7)     // Catch:{ NotFoundException -> 0x0016 }
            return r7
        L_0x0016:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0085 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r4 = "Resource does not exist: "
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x0085 }
            r3.append(r7)     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0085 }
            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0085 }
            throw r0     // Catch:{ FileNotFoundException -> 0x0085 }
        L_0x002d:
            android.content.Context r3 = r6.f1580o     // Catch:{ FileNotFoundException -> 0x0085 }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0085 }
            java.io.InputStream r3 = r3.openInputStream(r7)     // Catch:{ FileNotFoundException -> 0x0085 }
            if (r3 == 0) goto L_0x006e
            android.graphics.drawable.Drawable r4 = android.graphics.drawable.Drawable.createFromStream(r3, r2)     // Catch:{ all -> 0x0055 }
            r3.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0054
        L_0x0041:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.append(r0)     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.append(r7)     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r0 = r5.toString()     // Catch:{ FileNotFoundException -> 0x0085 }
            android.util.Log.e(r1, r0, r3)     // Catch:{ FileNotFoundException -> 0x0085 }
        L_0x0054:
            return r4
        L_0x0055:
            r4 = move-exception
            r3.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x006d
        L_0x005a:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.append(r0)     // Catch:{ FileNotFoundException -> 0x0085 }
            r5.append(r7)     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r0 = r5.toString()     // Catch:{ FileNotFoundException -> 0x0085 }
            android.util.Log.e(r1, r0, r3)     // Catch:{ FileNotFoundException -> 0x0085 }
        L_0x006d:
            throw r4     // Catch:{ FileNotFoundException -> 0x0085 }
        L_0x006e:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0085 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r4 = "Failed to open "
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x0085 }
            r3.append(r7)     // Catch:{ FileNotFoundException -> 0x0085 }
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0085 }
            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0085 }
            throw r0     // Catch:{ FileNotFoundException -> 0x0085 }
        L_0x0085:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Icon not found: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = ", "
            r3.append(r7)
            java.lang.String r7 = r0.getMessage()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            android.util.Log.w(r1, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0421na.m1831b(android.net.Uri):android.graphics.drawable.Drawable");
    }

    /* renamed from: b */
    private Drawable m1832b(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f1580o.getPackageName() + "/" + parseInt;
            Drawable a = m1824a(str2);
            if (a != null) {
                return a;
            }
            Drawable b = C0025a.m77b(this.f1580o, parseInt);
            m1829a(str2, b);
            return b;
        } catch (NumberFormatException unused) {
            Drawable a2 = m1824a(str);
            if (a2 != null) {
                return a2;
            }
            Drawable b2 = m1831b(Uri.parse(str));
            m1829a(str, b2);
            return b2;
        } catch (Resources.NotFoundException unused2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    /* renamed from: b */
    private CharSequence m1833b(CharSequence charSequence) {
        if (this.f1585t == null) {
            TypedValue typedValue = new TypedValue();
            this.f544d.getTheme().resolveAttribute(C0136a.textColorSearchUrl, typedValue, true);
            this.f1585t = this.f544d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan((String) null, 0, 0, this.f1585t, (ColorStateList) null), 0, charSequence.length(), 33);
        return spannableString;
    }

    /* renamed from: c */
    private Drawable m1834c(Cursor cursor) {
        Drawable b = m1830b(this.f1579n.getSearchActivity());
        return b != null ? b : this.f544d.getPackageManager().getDefaultActivityIcon();
    }

    /* renamed from: d */
    private Drawable m1835d(Cursor cursor) {
        int i = this.f1589x;
        if (i == -1) {
            return null;
        }
        Drawable b = m1832b(cursor.getString(i));
        return b != null ? b : m1834c(cursor);
    }

    /* renamed from: e */
    private Drawable m1836e(Cursor cursor) {
        int i = this.f1590y;
        if (i == -1) {
            return null;
        }
        return m1832b(cursor.getString(i));
    }

    /* renamed from: f */
    private void m1837f(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Cursor mo2218a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.f544d.getContentResolver().query(fragment.build(), (String[]) null, suggestSelection, strArr2, (String) null);
    }

    /* renamed from: a */
    public Cursor mo912a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f1578m.getVisibility() == 0 && this.f1578m.getWindowVisibility() == 0) {
            try {
                Cursor a = mo2218a(this.f1579n, charSequence2, 50);
                if (a != null) {
                    a.getCount();
                    return a;
                }
            } catch (RuntimeException e) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Drawable mo2219a(Uri uri) {
        int i;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.f544d.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            i = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        i = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (i != 0) {
                        return resourcesForApplication.getDrawable(i);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    /* renamed from: a */
    public void mo2220a(int i) {
        this.f1584s = i;
    }

    /* renamed from: a */
    public void mo893a(Cursor cursor) {
        if (this.f1583r) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.mo893a(cursor);
            if (cursor != null) {
                this.f1586u = cursor.getColumnIndex("suggest_text_1");
                this.f1587v = cursor.getColumnIndex("suggest_text_2");
                this.f1588w = cursor.getColumnIndex("suggest_text_2_url");
                this.f1589x = cursor.getColumnIndex("suggest_icon_1");
                this.f1590y = cursor.getColumnIndex("suggest_icon_2");
                this.f1591z = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    /* renamed from: a */
    public void mo894a(View view, Context context, Cursor cursor) {
        C0422a aVar = (C0422a) view.getTag();
        int i = this.f1591z;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.f1592a != null) {
            m1828a(aVar.f1592a, (CharSequence) m1825a(cursor, this.f1586u));
        }
        if (aVar.f1593b != null) {
            String a = m1825a(cursor, this.f1588w);
            CharSequence b = a != null ? m1833b((CharSequence) a) : m1825a(cursor, this.f1587v);
            if (TextUtils.isEmpty(b)) {
                TextView textView = aVar.f1592a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f1592a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f1592a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f1592a.setMaxLines(1);
                }
            }
            m1828a(aVar.f1593b, b);
        }
        ImageView imageView = aVar.f1594c;
        if (imageView != null) {
            m1827a(imageView, m1835d(cursor), 4);
        }
        ImageView imageView2 = aVar.f1595d;
        if (imageView2 != null) {
            m1827a(imageView2, m1836e(cursor), 8);
        }
        int i3 = this.f1584s;
        if (i3 == 2 || (i3 == 1 && (i2 & 1) != 0)) {
            aVar.f1596e.setVisibility(0);
            aVar.f1596e.setTag(aVar.f1592a.getText());
            aVar.f1596e.setOnClickListener(this);
            return;
        }
        aVar.f1596e.setVisibility(8);
    }

    /* renamed from: b */
    public View mo896b(Context context, Cursor cursor, ViewGroup viewGroup) {
        View b = super.mo896b(context, cursor, viewGroup);
        b.setTag(new C0422a(b));
        ((ImageView) b.findViewById(C0141f.edit_query)).setImageResource(this.f1582q);
        return b;
    }

    public CharSequence convertToString(Cursor cursor) {
        String a;
        String a2;
        if (cursor == null) {
            return null;
        }
        String a3 = m1826a(cursor, "suggest_intent_query");
        if (a3 != null) {
            return a3;
        }
        if (this.f1579n.shouldRewriteQueryFromData() && (a2 = m1826a(cursor, "suggest_intent_data")) != null) {
            return a2;
        }
        if (!this.f1579n.shouldRewriteQueryFromText() || (a = m1826a(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return a;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View a = mo891a(this.f544d, this.f543c, viewGroup);
            if (a != null) {
                ((C0422a) a.getTag()).f1592a.setText(e.toString());
            }
            return a;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View b = mo896b(this.f544d, this.f543c, viewGroup);
            if (b != null) {
                ((C0422a) b.getTag()).f1592a.setText(e.toString());
            }
            return b;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m1837f(mo890a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        m1837f(mo890a());
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1578m.mo1844a((CharSequence) tag);
        }
    }
}
