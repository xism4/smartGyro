package android.support.v7.widget;

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
import android.support.v4.widget.ResourceCursorAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    private boolean mClosed = false;
    private final int mCommitIconResId;
    private int mFlagsCol = -1;
    private int mIconName1Col = -1;
    private int mIconName2Col = -1;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement = 1;
    private final SearchManager mSearchManager = ((SearchManager) this.mContext.getSystemService("search"));
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col = -1;
    private int mText2Col = -1;
    private int mText2UrlCol = -1;
    private ColorStateList mUrlColor;

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.mSearchView = searchView;
        this.mSearchable = searchableInfo;
        this.mCommitIconResId = searchView.getSuggestionCommitIconResId();
        this.mProviderContext = context;
        this.mOutsideDrawablesCache = weakHashMap;
    }

    private Drawable checkIconCache(String str) {
        Drawable.ConstantState $r4 = this.mOutsideDrawablesCache.get(str);
        if ($r4 == null) {
            return null;
        }
        return $r4.newDrawable();
    }

    private CharSequence formatUrl(CharSequence charSequence) {
        if (this.mUrlColor == null) {
            TypedValue $r4 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R$attr.textColorSearchUrl, $r4, true);
            this.mUrlColor = this.mContext.getResources().getColorStateList($r4.resourceId);
        }
        SpannableString $r8 = new SpannableString(charSequence);
        $r8.setSpan(new TextAppearanceSpan((String) null, 0, 0, this.mUrlColor, (ColorStateList) null), 0, charSequence.length(), 33);
        return $r8;
    }

    private Drawable getActivityIcon(ComponentName componentName) {
        String $r5;
        PackageManager $r3 = this.mContext.getPackageManager();
        try {
            ActivityInfo $r4 = $r3.getActivityInfo(componentName, 128);
            int $i0 = $r4.getIconResource();
            if ($i0 == 0) {
                return null;
            }
            Drawable $r7 = $r3.getDrawable(componentName.getPackageName(), $i0, $r4.applicationInfo);
            if ($r7 != null) {
                return $r7;
            }
            $r5 = "Invalid icon resource " + $i0 + " for " + componentName.flattenToShortString();
            Log.w("SuggestionsAdapter", $r5);
            return null;
        } catch (PackageManager.NameNotFoundException $r9) {
            $r5 = $r9.toString();
        }
    }

    private Drawable getActivityIconWithCache(ComponentName componentName) {
        String $r2 = componentName.flattenToShortString();
        Drawable.ConstantState $r4 = null;
        if (this.mOutsideDrawablesCache.containsKey($r2)) {
            Drawable.ConstantState $r42 = this.mOutsideDrawablesCache.get($r2);
            if ($r42 == null) {
                return null;
            }
            return $r42.newDrawable(this.mProviderContext.getResources());
        }
        Drawable $r8 = getActivityIcon(componentName);
        if ($r8 != null) {
            $r4 = $r8.getConstantState();
        }
        this.mOutsideDrawablesCache.put($r2, $r4);
        return $r8;
    }

    public static String getColumnString(Cursor cursor, String str) {
        return getStringOrNull(cursor, cursor.getColumnIndex(str));
    }

    private Drawable getDefaultIcon1(Cursor cursor) {
        Drawable $r4 = getActivityIconWithCache(this.mSearchable.getSearchActivity());
        return $r4 != null ? $r4 : this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable getDrawable(Uri uri) {
        InputStream $r10;
        FileNotFoundException r16;
        StringBuilder r15;
        StringBuilder r152;
        StringBuilder r153;
        FileNotFoundException r162;
        StringBuilder r154;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return getDrawableFromResourceUri(uri);
            }
            $r10 = this.mProviderContext.getContentResolver().openInputStream(uri);
            if ($r10 != null) {
                Drawable $r5 = Drawable.createFromStream($r10, (String) null);
                try {
                    $r10.close();
                    return $r5;
                } catch (IOException $r11) {
                    StringBuilder $r7 = r153;
                    r153 = new StringBuilder();
                    $r7.append("Error closing icon stream for ");
                    $r7.append(uri);
                    Log.e("SuggestionsAdapter", $r7.toString(), $r11);
                    return $r5;
                }
            } else {
                FileNotFoundException $r6 = r16;
                StringBuilder $r72 = r15;
                r15 = new StringBuilder();
                $r72.append("Failed to open ");
                $r72.append(uri);
                r16 = new FileNotFoundException($r72.toString());
                throw $r6;
            }
        } catch (Resources.NotFoundException e) {
            FileNotFoundException $r62 = r162;
            StringBuilder $r73 = r154;
            r154 = new StringBuilder();
            $r73.append("Resource does not exist: ");
            $r73.append(uri);
            r162 = new FileNotFoundException($r73.toString());
            throw $r62;
        } catch (FileNotFoundException e2) {
            StringBuilder $r74 = r15;
            StringBuilder r155 = new StringBuilder();
            $r74.append("Icon not found: ");
            $r74.append(uri);
            $r74.append(", ");
            $r74.append(e2.getMessage());
            Log.w("SuggestionsAdapter", $r74.toString());
            return null;
        } catch (Throwable $r12) {
            try {
                $r10.close();
            } catch (IOException $r13) {
                StringBuilder $r75 = r152;
                r152 = new StringBuilder();
                $r75.append("Error closing icon stream for ");
                $r75.append(uri);
                Log.e("SuggestionsAdapter", $r75.toString(), $r13);
            }
            throw $r12;
        }
    }

    private Drawable getDrawableFromResourceValue(String $r3) {
        if ($r3 == null || $r3.isEmpty() || "0".equals($r3)) {
            return null;
        }
        try {
            int $i0 = Integer.parseInt($r3);
            StringBuilder $r5 = new StringBuilder();
            $r5.append("android.resource://");
            $r5.append(this.mProviderContext.getPackageName());
            $r5.append("/");
            $r5.append($i0);
            String $r4 = $r5.toString();
            Drawable $r7 = checkIconCache($r4);
            if ($r7 != null) {
                return $r7;
            }
            Drawable $r72 = com.org.android.ui.Resources.getDrawable(this.mProviderContext, $i0);
            storeInIconCache($r4, $r72);
            return $r72;
        } catch (NumberFormatException e) {
            Drawable $r73 = checkIconCache($r3);
            if ($r73 != null) {
                return $r73;
            }
            Drawable $r74 = getDrawable(Uri.parse($r3));
            storeInIconCache($r3, $r74);
            return $r74;
        } catch (Resources.NotFoundException e2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + $r3);
            return null;
        }
    }

    private Drawable getIcon1(Cursor cursor) {
        int $i0 = this.mIconName1Col;
        if ($i0 == -1) {
            return null;
        }
        Drawable $r3 = getDrawableFromResourceValue(cursor.getString($i0));
        return $r3 != null ? $r3 : getDefaultIcon1(cursor);
    }

    private Drawable getIcon2(Cursor cursor) {
        int $i0 = this.mIconName2Col;
        if ($i0 == -1) {
            return null;
        }
        return getDrawableFromResourceValue(cursor.getString($i0));
    }

    private static String getStringOrNull(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception $r2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", $r2);
            return null;
        }
    }

    private void setViewDrawable(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? (byte) 8 : 0);
    }

    private void storeInIconCache(String str, Drawable drawable) {
        if (drawable != null) {
            this.mOutsideDrawablesCache.put(str, drawable.getConstantState());
        }
    }

    private void updateSpinnerState(Cursor cursor) {
        Bundle $r2 = cursor != null ? cursor.getExtras() : null;
        if ($r2 == null || $r2.getBoolean("in_progress")) {
        }
    }

    public void bindView(View view, Context context, Cursor cursor) {
        na$a $r5 = (na$a) view.getTag();
        int $i0 = this.mFlagsCol;
        int $i02 = $i0 != -1 ? cursor.getInt($i0) : 0;
        if ($r5.mText1 != null) {
            setViewText($r5.mText1, getStringOrNull(cursor, this.mText1Col));
        }
        if ($r5.mText2 != null) {
            String $r7 = getStringOrNull(cursor, this.mText2UrlCol);
            CharSequence $r8 = $r7 != null ? formatUrl($r7) : getStringOrNull(cursor, this.mText2Col);
            if (TextUtils.isEmpty($r8)) {
                TextView $r6 = $r5.mText1;
                if ($r6 != null) {
                    $r6.setSingleLine(false);
                    $r5.mText1.setMaxLines(2);
                }
            } else {
                TextView $r62 = $r5.mText1;
                if ($r62 != null) {
                    $r62.setSingleLine(true);
                    $r5.mText1.setMaxLines(1);
                }
            }
            setViewText($r5.mText2, $r8);
        }
        ImageView $r9 = $r5.mIcon1;
        if ($r9 != null) {
            setViewDrawable($r9, getIcon1(cursor), 4);
        }
        ImageView $r92 = $r5.mIcon2;
        if ($r92 != null) {
            setViewDrawable($r92, getIcon2(cursor), 8);
        }
        int $i1 = this.mQueryRefinement;
        if ($i1 == 2 || ($i1 == 1 && ($i02 & 1) != 0)) {
            $r5.mIconRefine.setVisibility(0);
            $r5.mIconRefine.setTag($r5.mText1.getText());
            $r5.mIconRefine.setOnClickListener(this);
            return;
        }
        $r5.mIconRefine.setVisibility(8);
    }

    public void changeCursor(Cursor cursor) {
        if (this.mClosed) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.mText1Col = cursor.getColumnIndex("suggest_text_1");
                this.mText2Col = cursor.getColumnIndex("suggest_text_2");
                this.mText2UrlCol = cursor.getColumnIndex("suggest_text_2_url");
                this.mIconName1Col = cursor.getColumnIndex("suggest_icon_1");
                this.mIconName2Col = cursor.getColumnIndex("suggest_icon_2");
                this.mFlagsCol = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception $r2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", $r2);
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        String $r2;
        String $r22;
        if (cursor == null) {
            return null;
        }
        String $r23 = getColumnString(cursor, "suggest_intent_query");
        if ($r23 != null) {
            return $r23;
        }
        if (this.mSearchable.shouldRewriteQueryFromData() && ($r22 = getColumnString(cursor, "suggest_intent_data")) != null) {
            return $r22;
        }
        if (!this.mSearchable.shouldRewriteQueryFromText() || ($r2 = getColumnString(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return $r2;
    }

    /* access modifiers changed from: package-private */
    public Drawable getDrawableFromResourceUri(Uri uri) {
        int $i0;
        String $r4 = uri.getAuthority();
        if (!TextUtils.isEmpty($r4)) {
            try {
                Resources $r7 = this.mContext.getPackageManager().getResourcesForApplication($r4);
                List $r8 = uri.getPathSegments();
                if ($r8 != null) {
                    int $i02 = $r8.size();
                    if ($i02 == 1) {
                        try {
                            $i0 = Integer.parseInt($r8.get(0));
                        } catch (NumberFormatException e) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if ($i02 == 2) {
                        $i0 = $r7.getIdentifier($r8.get(1), $r8.get(0), $r4);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if ($i0 != 0) {
                        return $r7.getDrawable($i0);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException e2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException $r3) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", $r3);
            View $r1 = newDropDownView(this.mContext, this.mCursor, viewGroup);
            if ($r1 != null) {
                ((na$a) $r1.getTag()).mText1.setText($r3.toString());
            }
            return $r1;
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor getSearchManagerSuggestions(SearchableInfo searchableInfo, String str, int i) {
        String $r4;
        String[] $r1 = null;
        if (searchableInfo == null || ($r4 = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder $r5 = new Uri.Builder().scheme("content").authority($r4).query("").fragment("");
        String $r42 = searchableInfo.getSuggestPath();
        if ($r42 != null) {
            $r5.appendEncodedPath($r42);
        }
        $r5.appendPath("search_suggest_query");
        String $r43 = searchableInfo.getSuggestSelection();
        if ($r43 != null) {
            $r1 = new String[]{str};
        } else {
            $r5.appendPath(str);
        }
        if (i > 0) {
            $r5.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.mContext.getContentResolver().query($r5.build(), (String[]) null, $r43, $r1, (String) null);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException $r3) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", $r3);
            View $r1 = newView(this.mContext, this.mCursor, viewGroup);
            if ($r1 != null) {
                ((na$a) $r1.getTag()).mText1.setText($r3.toString());
            }
            return $r1;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View $r4 = super.newView(context, cursor, viewGroup);
        $r4.setTag(new na$a($r4));
        ((ImageView) $r4.findViewById(R$id.edit_query)).setImageResource(this.mCommitIconResId);
        return $r4;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateSpinnerState(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        updateSpinnerState(getCursor());
    }

    public void onClick(View view) {
        Object $r1 = view.getTag();
        if ($r1 instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence) $r1);
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String $r2 = charSequence == null ? "" : charSequence.toString();
        if (this.mSearchView.getVisibility() != 0 || this.mSearchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor $r5 = getSearchManagerSuggestions(this.mSearchable, $r2, 50);
            if ($r5 == null) {
                return null;
            }
            $r5.getCount();
            return $r5;
        } catch (RuntimeException $r6) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", $r6);
            return null;
        }
    }

    public void setQueryRefinement(int i) {
        this.mQueryRefinement = i;
    }
}
