package lombok.libs.org.objectweb.asm;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.Vector;

public class c
{
  public static ByteVector a(Context paramContext, int paramInt1, int paramInt2)
  {
    return a(export(paramContext, paramInt1, paramInt2));
  }
  
  public static ByteVector a(String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    ByteVector localByteVector = null;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (localByteVector == null) {
        localByteVector = new ByteVector(str);
      } else {
        localByteVector.read(str);
      }
      i += 1;
    }
    return localByteVector;
  }
  
  static String[] export(Context paramContext, int paramInt1, int paramInt2)
  {
    Object localObject1 = paramContext.getPackageName();
    paramContext = new Vector();
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile = Environment.getExternalStorageDirectory();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(localFile.toString());
      ((StringBuilder)localObject2).append("/Android/obb/");
      ((StringBuilder)localObject2).append((String)localObject1);
      localFile = new File(((StringBuilder)localObject2).toString());
      if (localFile.exists())
      {
        if (paramInt1 > 0)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(localFile);
          ((StringBuilder)localObject2).append(File.separator);
          ((StringBuilder)localObject2).append("main.");
          ((StringBuilder)localObject2).append(paramInt1);
          ((StringBuilder)localObject2).append(".");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(".obb");
          localObject2 = ((StringBuilder)localObject2).toString();
          if (new File((String)localObject2).isFile()) {
            paramContext.add(localObject2);
          }
        }
        if (paramInt2 > 0)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(localFile);
          ((StringBuilder)localObject2).append(File.separator);
          ((StringBuilder)localObject2).append("patch.");
          ((StringBuilder)localObject2).append(paramInt1);
          ((StringBuilder)localObject2).append(".");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(".obb");
          localObject1 = ((StringBuilder)localObject2).toString();
          if (new File((String)localObject1).isFile()) {
            paramContext.add(localObject1);
          }
        }
      }
    }
    localObject1 = new String[paramContext.size()];
    paramContext.toArray((Object[])localObject1);
    return localObject1;
  }
}
