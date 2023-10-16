package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ResourceCursorAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter
  extends ResourceCursorAdapter
  implements View.OnClickListener
{
  private boolean mClosed = false;
  private final int mCommitIconResId;
  private int mFlagsCol = -1;
  private int mIconName1Col = -1;
  private int mIconName2Col = -1;
  private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
  private final Context mProviderContext;
  private int mQueryRefinement = 1;
  private final SearchManager mSearchManager = (SearchManager)mContext.getSystemService("search");
  private final SearchView mSearchView;
  private final SearchableInfo mSearchable;
  private int mText1Col = -1;
  private int mText2Col = -1;
  private int mText2UrlCol = -1;
  private ColorStateList mUrlColor;
  
  public SuggestionsAdapter(Context paramContext, SearchView paramSearchView, SearchableInfo paramSearchableInfo, WeakHashMap paramWeakHashMap)
  {
    super(paramContext, paramSearchView.getSuggestionRowLayout(), null, true);
    mSearchView = paramSearchView;
    mSearchable = paramSearchableInfo;
    mCommitIconResId = paramSearchView.getSuggestionCommitIconResId();
    mProviderContext = paramContext;
    mOutsideDrawablesCache = paramWeakHashMap;
  }
  
  private Drawable checkIconCache(String paramString)
  {
    paramString = (Drawable.ConstantState)mOutsideDrawablesCache.get(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.newDrawable();
  }
  
  private CharSequence formatUrl(CharSequence paramCharSequence)
  {
    if (mUrlColor == null)
    {
      localObject = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, (TypedValue)localObject, true);
      mUrlColor = mContext.getResources().getColorStateList(resourceId);
    }
    Object localObject = new SpannableString(paramCharSequence);
    ((SpannableString)localObject).setSpan(new TextAppearanceSpan(null, 0, 0, mUrlColor, null), 0, paramCharSequence.length(), 33);
    return localObject;
  }
  
  private Drawable getActivityIcon(ComponentName paramComponentName)
  {
    Object localObject = mContext.getPackageManager();
    try
    {
      ActivityInfo localActivityInfo = ((PackageManager)localObject).getActivityInfo(paramComponentName, 128);
      int i = localActivityInfo.getIconResource();
      if (i == 0) {
        return null;
      }
      localObject = ((PackageManager)localObject).getDrawable(paramComponentName.getPackageName(), i, applicationInfo);
      if (localObject != null) {
        break label100;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid icon resource ");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(" for ");
      ((StringBuilder)localObject).append(paramComponentName.flattenToShortString());
      paramComponentName = ((StringBuilder)localObject).toString();
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      for (;;)
      {
        paramComponentName = paramComponentName.toString();
      }
    }
    Log.w("SuggestionsAdapter", paramComponentName);
    return null;
    label100:
    return localObject;
  }
  
  private Drawable getActivityIconWithCache(ComponentName paramComponentName)
  {
    String str = paramComponentName.flattenToShortString();
    boolean bool = mOutsideDrawablesCache.containsKey(str);
    Object localObject = null;
    if (bool)
    {
      paramComponentName = (Drawable.ConstantState)mOutsideDrawablesCache.get(str);
      if (paramComponentName == null) {
        return null;
      }
      return paramComponentName.newDrawable(mProviderContext.getResources());
    }
    Drawable localDrawable = getActivityIcon(paramComponentName);
    if (localDrawable == null) {
      paramComponentName = localObject;
    } else {
      paramComponentName = localDrawable.getConstantState();
    }
    mOutsideDrawablesCache.put(str, paramComponentName);
    return localDrawable;
  }
  
  public static String getColumnString(Cursor paramCursor, String paramString)
  {
    return getStringOrNull(paramCursor, paramCursor.getColumnIndex(paramString));
  }
  
  private Drawable getDefaultIcon1(Cursor paramCursor)
  {
    paramCursor = getActivityIconWithCache(mSearchable.getSearchActivity());
    if (paramCursor != null) {
      return paramCursor;
    }
    return mContext.getPackageManager().getDefaultActivityIcon();
  }
  
  private Drawable getDrawable(Uri paramUri)
  {
    try
    {
      localObject = paramUri.getScheme();
      boolean bool = "android.resource".equals(localObject);
      if (!bool) {}
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      Object localObject;
      label25:
      InputStream localInputStream;
      StringBuilder localStringBuilder1;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Icon not found: ");
      localStringBuilder2.append(paramUri);
      localStringBuilder2.append(", ");
      localStringBuilder2.append(((FileNotFoundException)localFileNotFoundException).getMessage());
      Log.w("SuggestionsAdapter", localStringBuilder2.toString());
      return null;
    }
    try
    {
      localObject = getDrawableFromResourceUri(paramUri);
      return localObject;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      break label25;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Resource does not exist: ");
    ((StringBuilder)localObject).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject).toString());
    localObject = mProviderContext;
    localInputStream = ((Context)localObject).getContentResolver().openInputStream(paramUri);
    if (localInputStream != null) {
      try
      {
        localObject = Drawable.createFromStream(localInputStream, null);
        StringBuilder localStringBuilder3;
        try
        {
          localInputStream.close();
          return localObject;
        }
        catch (IOException localIOException1)
        {
          localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("Error closing icon stream for ");
          localStringBuilder3.append(paramUri);
          Log.e("SuggestionsAdapter", localStringBuilder3.toString(), localIOException1);
          return localObject;
        }
        localStringBuilder1 = new StringBuilder();
      }
      catch (Throwable localThrowable)
      {
        try
        {
          localIOException1.close();
        }
        catch (IOException localIOException2)
        {
          localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("Error closing icon stream for ");
          localStringBuilder3.append(paramUri);
          Log.e("SuggestionsAdapter", localStringBuilder3.toString(), localIOException2);
        }
        throw localThrowable;
      }
    }
    localStringBuilder1.append("Failed to open ");
    localStringBuilder1.append(paramUri);
    throw new FileNotFoundException(localStringBuilder1.toString());
  }
  
  private Drawable getDrawableFromResourceValue(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      if ("0".equals(paramString)) {
        return null;
      }
      try
      {
        int i = Integer.parseInt(paramString);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("android.resource://");
        Object localObject2 = mProviderContext;
        ((StringBuilder)localObject1).append(((Context)localObject2).getPackageName());
        ((StringBuilder)localObject1).append("/");
        ((StringBuilder)localObject1).append(i);
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = checkIconCache((String)localObject1);
        if (localObject2 != null) {
          return localObject2;
        }
        localObject2 = mProviderContext;
        localObject2 = com.org.android.ui.Resources.getDrawable((Context)localObject2, i);
        storeInIconCache((String)localObject1, (Drawable)localObject2);
        return localObject2;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Object localObject1;
        for (;;) {}
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        for (;;) {}
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Icon resource not found: ");
      ((StringBuilder)localObject1).append(paramString);
      Log.w("SuggestionsAdapter", ((StringBuilder)localObject1).toString());
      return null;
      localObject1 = checkIconCache(paramString);
      if (localObject1 != null) {
        return localObject1;
      }
      localObject1 = getDrawable(Uri.parse(paramString));
      storeInIconCache(paramString, (Drawable)localObject1);
      return localObject1;
    }
    return null;
  }
  
  private Drawable getIcon1(Cursor paramCursor)
  {
    int i = mIconName1Col;
    if (i == -1) {
      return null;
    }
    Drawable localDrawable = getDrawableFromResourceValue(paramCursor.getString(i));
    if (localDrawable != null) {
      return localDrawable;
    }
    return getDefaultIcon1(paramCursor);
  }
  
  private Drawable getIcon2(Cursor paramCursor)
  {
    int i = mIconName2Col;
    if (i == -1) {
      return null;
    }
    return getDrawableFromResourceValue(paramCursor.getString(i));
  }
  
  private static String getStringOrNull(Cursor paramCursor, int paramInt)
  {
    if (paramInt == -1) {
      return null;
    }
    try
    {
      paramCursor = paramCursor.getString(paramInt);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", paramCursor);
    }
    return null;
  }
  
  private void setViewDrawable(ImageView paramImageView, Drawable paramDrawable, int paramInt)
  {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable == null)
    {
      paramImageView.setVisibility(paramInt);
      return;
    }
    paramImageView.setVisibility(0);
    paramDrawable.setVisible(false, false);
    paramDrawable.setVisible(true, false);
  }
  
  private void setViewText(TextView paramTextView, CharSequence paramCharSequence)
  {
    paramTextView.setText(paramCharSequence);
    int i;
    if (TextUtils.isEmpty(paramCharSequence)) {
      i = 8;
    } else {
      i = 0;
    }
    paramTextView.setVisibility(i);
  }
  
  private void storeInIconCache(String paramString, Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      mOutsideDrawablesCache.put(paramString, paramDrawable.getConstantState());
    }
  }
  
  private void updateSpinnerState(Cursor paramCursor)
  {
    if (paramCursor != null) {
      paramCursor = paramCursor.getExtras();
    } else {
      paramCursor = null;
    }
    if ((paramCursor != null) && (paramCursor.getBoolean("in_progress"))) {}
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    paramContext = (na.a)paramView.getTag();
    int i = mFlagsCol;
    if (i != -1) {
      i = paramCursor.getInt(i);
    } else {
      i = 0;
    }
    if (mText1 != null)
    {
      paramView = getStringOrNull(paramCursor, mText1Col);
      setViewText(mText1, paramView);
    }
    if (mText2 != null)
    {
      paramView = getStringOrNull(paramCursor, mText2UrlCol);
      if (paramView != null) {
        paramView = formatUrl(paramView);
      } else {
        paramView = getStringOrNull(paramCursor, mText2Col);
      }
      TextView localTextView;
      if (TextUtils.isEmpty((CharSequence)paramView))
      {
        localTextView = mText1;
        if (localTextView != null)
        {
          localTextView.setSingleLine(false);
          mText1.setMaxLines(2);
        }
      }
      else
      {
        localTextView = mText1;
        if (localTextView != null)
        {
          localTextView.setSingleLine(true);
          mText1.setMaxLines(1);
        }
      }
      setViewText(mText2, (CharSequence)paramView);
    }
    paramView = mIcon1;
    if (paramView != null) {
      setViewDrawable(paramView, getIcon1(paramCursor), 4);
    }
    paramView = mIcon2;
    if (paramView != null) {
      setViewDrawable(paramView, getIcon2(paramCursor), 8);
    }
    int j = mQueryRefinement;
    if ((j != 2) && ((j != 1) || ((i & 0x1) == 0)))
    {
      mIconRefine.setVisibility(8);
      return;
    }
    mIconRefine.setVisibility(0);
    mIconRefine.setTag(mText1.getText());
    mIconRefine.setOnClickListener(this);
  }
  
  public void changeCursor(Cursor paramCursor)
  {
    if (mClosed)
    {
      Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
    else
    {
      try
      {
        super.changeCursor(paramCursor);
        if (paramCursor != null)
        {
          int i = paramCursor.getColumnIndex("suggest_text_1");
          mText1Col = i;
          i = paramCursor.getColumnIndex("suggest_text_2");
          mText2Col = i;
          i = paramCursor.getColumnIndex("suggest_text_2_url");
          mText2UrlCol = i;
          i = paramCursor.getColumnIndex("suggest_icon_1");
          mIconName1Col = i;
          i = paramCursor.getColumnIndex("suggest_icon_2");
          mIconName2Col = i;
          i = paramCursor.getColumnIndex("suggest_flags");
          mFlagsCol = i;
          return;
        }
      }
      catch (Exception paramCursor)
      {
        Log.e("SuggestionsAdapter", "error changing cursor and caching columns", paramCursor);
      }
    }
  }
  
  public CharSequence convertToString(Cursor paramCursor)
  {
    if (paramCursor == null) {
      return null;
    }
    String str = getColumnString(paramCursor, "suggest_intent_query");
    if (str != null) {
      return str;
    }
    if (mSearchable.shouldRewriteQueryFromData())
    {
      str = getColumnString(paramCursor, "suggest_intent_data");
      if (str != null) {
        return str;
      }
    }
    if (mSearchable.shouldRewriteQueryFromText())
    {
      paramCursor = getColumnString(paramCursor, "suggest_text_1");
      if (paramCursor != null) {
        return paramCursor;
      }
    }
    return null;
  }
  
  Drawable getDrawableFromResourceUri(Uri paramUri)
  {
    Object localObject2 = paramUri.getAuthority();
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = mContext;
    }
    for (;;)
    {
      try
      {
        localObject1 = ((Context)localObject1).getPackageManager().getResourcesForApplication((String)localObject2);
        localList = paramUri.getPathSegments();
        if (localList != null)
        {
          i = localList.size();
          if (i != 1) {}
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        List localList;
        int i;
        continue;
      }
      try
      {
        localObject2 = localList.get(0);
        localObject2 = (String)localObject2;
        i = Integer.parseInt((String)localObject2);
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Single path segment is not a resource ID: ");
    ((StringBuilder)localObject1).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject1).toString());
    if (i == 2)
    {
      i = ((android.content.res.Resources)localObject1).getIdentifier((String)localList.get(1), (String)localList.get(0), (String)localObject2);
      if (i != 0) {
        return ((android.content.res.Resources)localObject1).getDrawable(i);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("No resource found for: ");
      ((StringBuilder)localObject1).append(paramUri);
      throw new FileNotFoundException(((StringBuilder)localObject1).toString());
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("More than two path segments: ");
    ((StringBuilder)localObject1).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("No path: ");
    ((StringBuilder)localObject1).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("No package found for authority: ");
    ((StringBuilder)localObject1).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("No authority: ");
    ((StringBuilder)localObject1).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject1).toString());
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    try
    {
      paramView = super.getDropDownView(paramInt, paramView, paramViewGroup);
      return paramView;
    }
    catch (RuntimeException paramView)
    {
      Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", paramView);
      paramViewGroup = newDropDownView(mContext, mCursor, paramViewGroup);
      if (paramViewGroup != null) {
        getTagmText1.setText(paramView.toString());
      }
    }
    return paramViewGroup;
  }
  
  Cursor getSearchManagerSuggestions(SearchableInfo paramSearchableInfo, String paramString, int paramInt)
  {
    Object localObject1 = null;
    if (paramSearchableInfo == null) {
      return null;
    }
    Object localObject2 = paramSearchableInfo.getSuggestAuthority();
    if (localObject2 == null) {
      return null;
    }
    localObject2 = new Uri.Builder().scheme("content").authority((String)localObject2).query("").fragment("");
    String str = paramSearchableInfo.getSuggestPath();
    if (str != null) {
      ((Uri.Builder)localObject2).appendEncodedPath(str);
    }
    ((Uri.Builder)localObject2).appendPath("search_suggest_query");
    str = paramSearchableInfo.getSuggestSelection();
    if (str != null)
    {
      paramSearchableInfo = new String[1];
      paramSearchableInfo[0] = paramString;
    }
    else
    {
      ((Uri.Builder)localObject2).appendPath(paramString);
      paramSearchableInfo = localObject1;
    }
    if (paramInt > 0) {
      ((Uri.Builder)localObject2).appendQueryParameter("limit", String.valueOf(paramInt));
    }
    paramString = ((Uri.Builder)localObject2).build();
    return mContext.getContentResolver().query(paramString, null, str, paramSearchableInfo, null);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    try
    {
      paramView = super.getView(paramInt, paramView, paramViewGroup);
      return paramView;
    }
    catch (RuntimeException paramView)
    {
      Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", paramView);
      paramViewGroup = newView(mContext, mCursor, paramViewGroup);
      if (paramViewGroup != null) {
        getTagmText1.setText(paramView.toString());
      }
    }
    return paramViewGroup;
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramContext = super.newView(paramContext, paramCursor, paramViewGroup);
    paramContext.setTag(new na.a(paramContext));
    ((ImageView)paramContext.findViewById(R.id.edit_query)).setImageResource(mCommitIconResId);
    return paramContext;
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    updateSpinnerState(getCursor());
  }
  
  public void notifyDataSetInvalidated()
  {
    super.notifyDataSetInvalidated();
    updateSpinnerState(getCursor());
  }
  
  public void onClick(View paramView)
  {
    paramView = paramView.getTag();
    if ((paramView instanceof CharSequence)) {
      mSearchView.onQueryRefine((CharSequence)paramView);
    }
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      paramCharSequence = "";
    } else {
      paramCharSequence = paramCharSequence.toString();
    }
    if (mSearchView.getVisibility() == 0)
    {
      if (mSearchView.getWindowVisibility() != 0) {
        return null;
      }
      try
      {
        paramCharSequence = getSearchManagerSuggestions(mSearchable, paramCharSequence, 50);
        if (paramCharSequence != null)
        {
          paramCharSequence.getCount();
          return paramCharSequence;
        }
      }
      catch (RuntimeException paramCharSequence)
      {
        Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", paramCharSequence);
      }
    }
    return null;
  }
  
  public void setQueryRefinement(int paramInt)
  {
    mQueryRefinement = paramInt;
  }
}
