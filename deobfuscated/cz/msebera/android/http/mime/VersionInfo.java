package cz.msebera.android.http.mime;

import java.util.Map;

public class VersionInfo
{
  private final String infoClassloader;
  private final String infoModule;
  private final String infoPackage;
  private final String infoRelease;
  private final String infoTimestamp;
  
  protected VersionInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Args.notNull(paramString1, "Package identifier");
    infoRelease = paramString1;
    paramString1 = "UNAVAILABLE";
    if (paramString2 == null) {
      paramString2 = "UNAVAILABLE";
    }
    infoTimestamp = paramString2;
    if (paramString3 == null) {
      paramString3 = "UNAVAILABLE";
    }
    infoClassloader = paramString3;
    if (paramString4 == null) {
      paramString4 = "UNAVAILABLE";
    }
    infoPackage = paramString4;
    if (paramString5 != null) {
      paramString1 = paramString5;
    }
    infoModule = paramString1;
  }
  
  protected static VersionInfo fromMap(String paramString, Map paramMap, ClassLoader paramClassLoader)
  {
    Args.notNull(paramString, "Package identifier");
    String str1 = null;
    Object localObject2;
    Object localObject1;
    if (paramMap != null)
    {
      localObject2 = (String)paramMap.get("info.module");
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((String)localObject2).length() < 1) {
          localObject1 = null;
        }
      }
      String str2 = (String)paramMap.get("info.release");
      localObject2 = str2;
      if (str2 != null) {
        if (str2.length() >= 1)
        {
          localObject2 = str2;
          if (!str2.equals("${pom.version}")) {}
        }
        else
        {
          localObject2 = null;
        }
      }
      str2 = (String)paramMap.get("info.timestamp");
      paramMap = str2;
      if (str2 != null) {
        if (str2.length() >= 1)
        {
          paramMap = str2;
          if (!str2.equals("${mvn.timestamp}")) {}
        }
        else
        {
          paramMap = null;
        }
      }
    }
    else
    {
      localObject1 = null;
      localObject2 = null;
      paramMap = null;
    }
    if (paramClassLoader != null) {
      str1 = paramClassLoader.toString();
    }
    return new VersionInfo(paramString, localObject1, (String)localObject2, paramMap, str1);
  }
  
  public static String getUserAgent(String paramString1, String paramString2, Class paramClass)
  {
    paramString2 = loadVersionInfo(paramString2, paramClass.getClassLoader());
    if (paramString2 != null) {
      paramString2 = paramString2.getClassloader();
    } else {
      paramString2 = "UNAVAILABLE";
    }
    return String.format("%s/%s (Java/%s)", new Object[] { paramString1, paramString2, System.getProperty("java.version") });
  }
  
  /* Error */
  public static VersionInfo loadVersionInfo(String paramString, ClassLoader paramClassLoader)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 17
    //   3: invokestatic 23	cz/msebera/android/http/mime/Args:notNull	(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_1
    //   8: ifnull +6 -> 14
    //   11: goto +10 -> 21
    //   14: invokestatic 109	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   17: invokevirtual 112	java/lang/Thread:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   20: astore_1
    //   21: new 114	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   28: astore_2
    //   29: aload_2
    //   30: aload_0
    //   31: bipush 46
    //   33: bipush 47
    //   35: invokevirtual 119	java/lang/String:replace	(CC)Ljava/lang/String;
    //   38: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: aload_2
    //   43: ldc 125
    //   45: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: aload_2
    //   50: ldc 127
    //   52: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload_1
    //   57: aload_2
    //   58: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: invokevirtual 134	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull +30 -> 96
    //   69: new 136	java/util/Properties
    //   72: dup
    //   73: invokespecial 137	java/util/Properties:<init>	()V
    //   76: astore_2
    //   77: aload_2
    //   78: aload_3
    //   79: invokevirtual 141	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   82: aload_3
    //   83: invokevirtual 146	java/io/InputStream:close	()V
    //   86: goto +12 -> 98
    //   89: astore_2
    //   90: aload_3
    //   91: invokevirtual 146	java/io/InputStream:close	()V
    //   94: aload_2
    //   95: athrow
    //   96: aconst_null
    //   97: astore_2
    //   98: aload_2
    //   99: ifnull +22 -> 121
    //   102: aload_0
    //   103: aload_2
    //   104: aload_1
    //   105: invokestatic 148	cz/msebera/android/http/mime/VersionInfo:fromMap	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/ClassLoader;)Lcz/msebera/android/http/mime/VersionInfo;
    //   108: areturn
    //   109: astore_2
    //   110: goto -14 -> 96
    //   113: astore_3
    //   114: goto -16 -> 98
    //   117: astore_2
    //   118: goto -22 -> 96
    //   121: aconst_null
    //   122: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	paramString	String
    //   0	123	1	paramClassLoader	ClassLoader
    //   28	50	2	localObject	Object
    //   89	6	2	localThrowable	Throwable
    //   97	7	2	localMap	Map
    //   109	1	2	localIOException1	java.io.IOException
    //   117	1	2	localIOException2	java.io.IOException
    //   64	27	3	localInputStream	java.io.InputStream
    //   113	1	3	localIOException3	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   69	82	89	java/lang/Throwable
    //   21	65	109	java/io/IOException
    //   82	86	113	java/io/IOException
    //   90	96	117	java/io/IOException
  }
  
  public final String getClassloader()
  {
    return infoClassloader;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(infoRelease.length() + 20 + infoTimestamp.length() + infoClassloader.length() + infoPackage.length() + infoModule.length());
    localStringBuilder.append("VersionInfo(");
    localStringBuilder.append(infoRelease);
    localStringBuilder.append(':');
    localStringBuilder.append(infoTimestamp);
    if (!"UNAVAILABLE".equals(infoClassloader))
    {
      localStringBuilder.append(':');
      localStringBuilder.append(infoClassloader);
    }
    if (!"UNAVAILABLE".equals(infoPackage))
    {
      localStringBuilder.append(':');
      localStringBuilder.append(infoPackage);
    }
    localStringBuilder.append(')');
    if (!"UNAVAILABLE".equals(infoModule))
    {
      localStringBuilder.append('@');
      localStringBuilder.append(infoModule);
    }
    return localStringBuilder.toString();
  }
}
