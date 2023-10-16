package lombok.eclipse.handlers.http;

class Utils
{
  public static void asserts(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      return;
    }
    throw new AssertionError(paramString);
  }
  
  public static Object notNull(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      return paramObject;
    }
    paramObject = new StringBuilder();
    paramObject.append(paramString);
    paramObject.append(" should not be null!");
    throw new IllegalArgumentException(paramObject.toString());
  }
}
