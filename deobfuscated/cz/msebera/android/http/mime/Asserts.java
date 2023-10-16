package cz.msebera.android.http.mime;

public class Asserts
{
  public static void check(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalStateException(paramString);
  }
  
  public static void notNull(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      return;
    }
    paramObject = new StringBuilder();
    paramObject.append(paramString);
    paramObject.append(" is null");
    throw new IllegalStateException(paramObject.toString());
  }
}
