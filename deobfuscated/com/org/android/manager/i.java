package com.org.android.manager;

import a.a.c.d.f.c;
import a.a.c.d.k.a;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import com.org.android.asm.ByteVector;
import com.org.android.asm.c;
import com.org.android.ui.asm.Type;
import com.org.android.ui.asm.k;
import com.org.android.util.LruCache;
import com.org.android.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class i
{
  private static final Comparator<byte[]> a = new Version.BuildAwareOrder();
  static final a.a.c.f.i<String, ArrayList<k.a<f.c>>> b;
  static final Object c;
  private static final Connection s;
  static final a.a.c.f.e<String, Typeface> t = new LruCache(16);
  
  static
  {
    s = new Connection("fonts", 10, 10000);
    c = new Object();
    b = new SimpleArrayMap();
  }
  
  public static ProviderInfo a(PackageManager paramPackageManager, h paramH, Resources paramResources)
  {
    String str = paramH.c();
    int i = 0;
    ProviderInfo localProviderInfo = paramPackageManager.resolveContentProvider(str, 0);
    if (localProviderInfo != null)
    {
      if (packageName.equals(paramH.getGroupId()))
      {
        paramPackageManager = toArray(getPackageInfopackageName, 64).signatures);
        Collections.sort(paramPackageManager, a);
        paramH = a(paramH, paramResources);
        while (i < paramH.size())
        {
          paramResources = new ArrayList((Collection)paramH.get(i));
          Collections.sort(paramResources, a);
          if (equals(paramPackageManager, paramResources)) {
            return localProviderInfo;
          }
          i += 1;
        }
        return null;
      }
      paramPackageManager = new StringBuilder();
      paramPackageManager.append("Found content provider ");
      paramPackageManager.append(str);
      paramPackageManager.append(", but package was not ");
      paramPackageManager.append(paramH.getGroupId());
      throw new PackageManager.NameNotFoundException(paramPackageManager.toString());
    }
    paramPackageManager = new StringBuilder();
    paramPackageManager.append("No package found for authority: ");
    paramPackageManager.append(str);
    paramPackageManager = new PackageManager.NameNotFoundException(paramPackageManager.toString());
    throw paramPackageManager;
  }
  
  public static Typeface a(Context paramContext, h paramH, k paramK, Handler paramHandler, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramH.a());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramInt2);
    localObject = ((StringBuilder)localObject).toString();
    Typeface localTypeface = (Typeface)t.get(localObject);
    if (localTypeface != null)
    {
      if (paramK != null)
      {
        paramK.a(localTypeface);
        return localTypeface;
      }
    }
    else
    {
      if ((paramBoolean) && (paramInt1 == -1))
      {
        paramContext = a(paramContext, paramH, paramInt2);
        if (paramK != null)
        {
          paramInt1 = f;
          if (paramInt1 == 0) {
            paramK.a(t, paramHandler);
          } else {
            paramK.a(paramInt1, paramHandler);
          }
        }
        return t;
      }
      paramH = new a(paramContext, paramH, paramInt2, (String)localObject);
      if (paramBoolean) {
        paramContext = s;
      }
      try
      {
        paramContext = paramContext.get(paramH, paramInt1);
        return t;
      }
      catch (InterruptedException paramContext)
      {
        return null;
      }
      if (paramK == null) {
        paramContext = null;
      } else {
        paramContext = new d(paramK, paramHandler);
      }
      paramK = c;
      try
      {
        if (b.containsKey(localObject))
        {
          if (paramContext != null) {
            ((ArrayList)b.get(localObject)).add(paramContext);
          }
          return null;
        }
        if (paramContext != null)
        {
          paramHandler = new ArrayList();
          paramHandler.add(paramContext);
          b.put(localObject, paramHandler);
        }
        s.close(paramH, new e((String)localObject));
        return null;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    }
    return localTypeface;
  }
  
  static f a(Context paramContext, h paramH, int paramInt)
  {
    try
    {
      paramH = a(paramContext, null, paramH);
      int j = paramH.a();
      int i = -3;
      if (j == 0)
      {
        paramContext = c.a(paramContext, null, paramH.b(), paramInt);
        if (paramContext != null) {
          i = 0;
        }
        return new f(paramContext, i);
      }
      if (paramH.a() == 1) {
        i = -2;
      }
      return new f(null, i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return new f(null, -1);
  }
  
  public static g a(Context paramContext, CancellationSignal paramCancellationSignal, h paramH)
  {
    ProviderInfo localProviderInfo = a(paramContext.getPackageManager(), paramH, paramContext.getResources());
    if (localProviderInfo == null) {
      return new g(1, null);
    }
    return new g(0, load(paramContext, paramH, authority, paramCancellationSignal));
  }
  
  private static List a(h paramH, Resources paramResources)
  {
    if (paramH.getTitle() != null) {
      return paramH.getTitle();
    }
    return Type.getValue(paramResources, paramH.b());
  }
  
  public static Map a(Context paramContext, Label[] paramArrayOfLabel, CancellationSignal paramCancellationSignal)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfLabel.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayOfLabel[i];
      if (((Label)localObject).c() == 0)
      {
        localObject = ((Label)localObject).getValue();
        if (!localHashMap.containsKey(localObject)) {
          localHashMap.put(localObject, ByteVector.read(paramContext, paramCancellationSignal, (Uri)localObject));
        }
      }
      i += 1;
    }
    return Collections.unmodifiableMap(localHashMap);
  }
  
  private static boolean equals(List paramList1, List paramList2)
  {
    if (paramList1.size() != paramList2.size()) {
      return false;
    }
    int i = 0;
    while (i < paramList1.size())
    {
      if (!Arrays.equals((byte[])paramList1.get(i), (byte[])paramList2.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  static Label[] load(Context paramContext, h paramH, String paramString, CancellationSignal paramCancellationSignal)
  {
    ArrayList localArrayList = new ArrayList();
    Uri localUri1 = new Uri.Builder().scheme("content").authority(paramString).build();
    Uri localUri2 = new Uri.Builder().scheme("content").authority(paramString).appendPath("file").build();
    Object localObject = null;
    paramString = localObject;
    try
    {
      int i = Build.VERSION.SDK_INT;
      if (i > 16)
      {
        paramString = localObject;
        paramContext = paramContext.getContentResolver();
        paramString = localObject;
        paramH = paramH.e();
        paramString = localObject;
        paramContext = paramContext.query(localUri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { paramH }, null, paramCancellationSignal);
      }
      else
      {
        paramString = localObject;
        paramContext = paramContext.getContentResolver();
        paramString = localObject;
        paramH = paramH.e();
        paramString = localObject;
        paramContext = paramContext.query(localUri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { paramH }, null);
      }
      paramH = localArrayList;
      if (paramContext != null)
      {
        paramString = paramContext;
        i = paramContext.getCount();
        paramH = localArrayList;
        if (i > 0)
        {
          paramString = paramContext;
          int m = paramContext.getColumnIndex("result_code");
          paramString = paramContext;
          paramCancellationSignal = new ArrayList();
          paramString = paramContext;
          int n = paramContext.getColumnIndex("_id");
          paramString = paramContext;
          int i1 = paramContext.getColumnIndex("file_id");
          paramString = paramContext;
          int i2 = paramContext.getColumnIndex("font_ttc_index");
          paramString = paramContext;
          int i3 = paramContext.getColumnIndex("font_weight");
          paramString = paramContext;
          int i4 = paramContext.getColumnIndex("font_italic");
          for (;;)
          {
            paramString = paramContext;
            boolean bool = paramContext.moveToNext();
            paramH = paramCancellationSignal;
            if (!bool) {
              break;
            }
            if (m != -1)
            {
              paramString = paramContext;
              i = paramContext.getInt(m);
            }
            else
            {
              i = 0;
            }
            int j;
            if (i2 != -1)
            {
              paramString = paramContext;
              j = paramContext.getInt(i2);
            }
            else
            {
              j = 0;
            }
            if (i1 == -1)
            {
              paramString = paramContext;
              paramH = ContentUris.withAppendedId(localUri1, paramContext.getLong(n));
            }
            else
            {
              paramString = paramContext;
              paramH = ContentUris.withAppendedId(localUri2, paramContext.getLong(i1));
            }
            int k;
            if (i3 != -1)
            {
              paramString = paramContext;
              k = paramContext.getInt(i3);
            }
            else
            {
              k = 400;
            }
            if (i4 != -1)
            {
              paramString = paramContext;
              int i5 = paramContext.getInt(i4);
              if (i5 == 1)
              {
                bool = true;
                break label531;
              }
            }
            bool = false;
            label531:
            paramString = paramContext;
            paramCancellationSignal.add(new Label(paramH, j, k, bool, i));
          }
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
      return (Label[])paramH.toArray(new Label[0]);
    }
    catch (Throwable paramContext)
    {
      if (paramString != null) {
        paramString.close();
      }
      throw paramContext;
    }
  }
  
  private static List toArray(Signature[] paramArrayOfSignature)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfSignature.length)
    {
      localArrayList.add(paramArrayOfSignature[i].toByteArray());
      i += 1;
    }
    return localArrayList;
  }
}
