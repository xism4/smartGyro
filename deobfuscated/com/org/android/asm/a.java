package com.org.android.asm;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.File;

class a
  extends f
{
  a() {}
  
  private File close(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("/proc/self/fd/");
      ((StringBuilder)localObject).append(paramParcelFileDescriptor.getFd());
      paramParcelFileDescriptor = Os.readlink(((StringBuilder)localObject).toString());
      localObject = Os.stat(paramParcelFileDescriptor);
      int i = st_mode;
      boolean bool = OsConstants.S_ISREG(i);
      if (bool)
      {
        paramParcelFileDescriptor = new File(paramParcelFileDescriptor);
        return paramParcelFileDescriptor;
      }
      return null;
    }
    catch (ErrnoException paramParcelFileDescriptor) {}
    return null;
  }
  
  /* Error */
  public android.graphics.Typeface a(android.content.Context paramContext, android.os.CancellationSignal paramCancellationSignal, com.org.android.manager.Label[] paramArrayOfLabel, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: aload_3
    //   10: iload 4
    //   12: invokevirtual 71	com/org/android/asm/f:a	([Lcom/org/android/manager/Label;I)Lcom/org/android/manager/Label;
    //   15: astore_3
    //   16: aload_1
    //   17: invokevirtual 77	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: aload 6
    //   24: aload_3
    //   25: invokevirtual 83	com/org/android/manager/Label:getValue	()Landroid/net/Uri;
    //   28: ldc 85
    //   30: aload_2
    //   31: invokevirtual 91	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   34: astore_3
    //   35: aload_0
    //   36: aload_3
    //   37: invokespecial 93	com/org/android/asm/a:close	(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +34 -> 76
    //   45: aload_2
    //   46: invokevirtual 97	java/io/File:canRead	()Z
    //   49: istore 5
    //   51: iload 5
    //   53: ifne +6 -> 59
    //   56: goto +20 -> 76
    //   59: aload_2
    //   60: invokestatic 103	android/graphics/Typeface:createFromFile	(Ljava/io/File;)Landroid/graphics/Typeface;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_1
    //   66: aload_3
    //   67: ifnull +127 -> 194
    //   70: aload_3
    //   71: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   74: aload_2
    //   75: areturn
    //   76: new 107	java/io/FileInputStream
    //   79: dup
    //   80: aload_3
    //   81: invokevirtual 111	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   84: invokespecial 114	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   87: astore 6
    //   89: aload_0
    //   90: aload_1
    //   91: aload 6
    //   93: invokespecial 118	com/org/android/asm/f:add	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   96: astore_2
    //   97: aload 6
    //   99: invokevirtual 119	java/io/FileInputStream:close	()V
    //   102: aload_2
    //   103: astore_1
    //   104: aload_3
    //   105: ifnull +89 -> 194
    //   108: aload_3
    //   109: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   112: aload_2
    //   113: areturn
    //   114: astore_1
    //   115: aload_1
    //   116: athrow
    //   117: astore_2
    //   118: aload_1
    //   119: ifnull +22 -> 141
    //   122: aload 6
    //   124: invokevirtual 119	java/io/FileInputStream:close	()V
    //   127: goto +19 -> 146
    //   130: astore 6
    //   132: aload_1
    //   133: aload 6
    //   135: invokevirtual 123	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   138: goto +8 -> 146
    //   141: aload 6
    //   143: invokevirtual 119	java/io/FileInputStream:close	()V
    //   146: aload_2
    //   147: athrow
    //   148: astore_1
    //   149: aload_1
    //   150: athrow
    //   151: astore_2
    //   152: aload_3
    //   153: ifnull +27 -> 180
    //   156: aload_1
    //   157: ifnull +19 -> 176
    //   160: aload_3
    //   161: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   164: goto +16 -> 180
    //   167: astore_3
    //   168: aload_1
    //   169: aload_3
    //   170: invokevirtual 123	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   173: goto +7 -> 180
    //   176: aload_3
    //   177: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   180: aload_2
    //   181: athrow
    //   182: astore_1
    //   183: aconst_null
    //   184: areturn
    //   185: astore_1
    //   186: aconst_null
    //   187: areturn
    //   188: astore_1
    //   189: aconst_null
    //   190: areturn
    //   191: astore_1
    //   192: aconst_null
    //   193: areturn
    //   194: aload_1
    //   195: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	this	a
    //   0	196	1	paramContext	android.content.Context
    //   0	196	2	paramCancellationSignal	android.os.CancellationSignal
    //   0	196	3	paramArrayOfLabel	com.org.android.manager.Label[]
    //   0	196	4	paramInt	int
    //   49	3	5	bool	boolean
    //   20	103	6	localObject	Object
    //   130	12	6	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   89	97	114	java/lang/Throwable
    //   115	117	117	java/lang/Throwable
    //   122	127	130	java/lang/Throwable
    //   35	41	148	java/lang/Throwable
    //   45	51	148	java/lang/Throwable
    //   59	64	148	java/lang/Throwable
    //   76	89	148	java/lang/Throwable
    //   97	102	148	java/lang/Throwable
    //   132	138	148	java/lang/Throwable
    //   141	146	148	java/lang/Throwable
    //   146	148	148	java/lang/Throwable
    //   149	151	151	java/lang/Throwable
    //   160	164	167	java/lang/Throwable
    //   22	35	182	java/io/IOException
    //   70	74	185	java/io/IOException
    //   108	112	188	java/io/IOException
    //   168	173	191	java/io/IOException
    //   176	180	191	java/io/IOException
    //   180	182	191	java/io/IOException
  }
}
