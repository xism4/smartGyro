package com.org.android.manager;

import android.util.Base64;
import com.org.android.util.m;
import java.util.List;

public final class h
{
  private final String b;
  private final String c;
  private final String d;
  private final List<List<byte[]>> e;
  private final String h;
  private final int p;
  
  public h(String paramString1, String paramString2, String paramString3, List paramList)
  {
    m.a(paramString1);
    c = ((String)paramString1);
    m.a(paramString2);
    b = ((String)paramString2);
    m.a(paramString3);
    d = ((String)paramString3);
    m.a(paramList);
    e = ((List)paramList);
    p = 0;
    paramString1 = new StringBuilder(c);
    paramString1.append("-");
    paramString1.append(b);
    paramString1.append("-");
    paramString1.append(d);
    h = paramString1.toString();
  }
  
  public String a()
  {
    return h;
  }
  
  public int b()
  {
    return p;
  }
  
  public String c()
  {
    return c;
  }
  
  public String e()
  {
    return d;
  }
  
  public String getGroupId()
  {
    return b;
  }
  
  public List getTitle()
  {
    return e;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("FontRequest {mProviderAuthority: ");
    ((StringBuilder)localObject).append(c);
    ((StringBuilder)localObject).append(", mProviderPackage: ");
    ((StringBuilder)localObject).append(b);
    ((StringBuilder)localObject).append(", mQuery: ");
    ((StringBuilder)localObject).append(d);
    ((StringBuilder)localObject).append(", mCertificates:");
    localStringBuilder.append(((StringBuilder)localObject).toString());
    int i = 0;
    while (i < e.size())
    {
      localStringBuilder.append(" [");
      localObject = (List)e.get(i);
      int j = 0;
      while (j < ((List)localObject).size())
      {
        localStringBuilder.append(" \"");
        localStringBuilder.append(Base64.encodeToString((byte[])((List)localObject).get(j), 0));
        localStringBuilder.append("\"");
        j += 1;
      }
      localStringBuilder.append(" ]");
      i += 1;
    }
    localStringBuilder.append("}");
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("mCertificatesArray: ");
    ((StringBuilder)localObject).append(p);
    localStringBuilder.append(((StringBuilder)localObject).toString());
    return localStringBuilder.toString();
  }
}
