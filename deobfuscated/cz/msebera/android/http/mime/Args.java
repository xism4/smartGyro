package cz.msebera.android.http.mime;

import java.util.Collection;

public class Args
{
  public static void check(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalArgumentException(paramString);
  }
  
  public static void check(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalArgumentException(String.format(paramString, paramVarArgs));
  }
  
  public static CharSequence containsNoBlanks(CharSequence paramCharSequence, String paramString)
  {
    if (paramCharSequence != null)
    {
      if (!TextUtils.containsBlanks(paramCharSequence)) {
        return paramCharSequence;
      }
      paramCharSequence = new StringBuilder();
      paramCharSequence.append(paramString);
      paramCharSequence.append(" may not contain blanks");
      throw new IllegalArgumentException(paramCharSequence.toString());
    }
    paramCharSequence = new StringBuilder();
    paramCharSequence.append(paramString);
    paramCharSequence.append(" may not be null");
    throw new IllegalArgumentException(paramCharSequence.toString());
  }
  
  public static CharSequence notBlank(CharSequence paramCharSequence, String paramString)
  {
    if (paramCharSequence != null)
    {
      if (!TextUtils.isBlank(paramCharSequence)) {
        return paramCharSequence;
      }
      paramCharSequence = new StringBuilder();
      paramCharSequence.append(paramString);
      paramCharSequence.append(" may not be blank");
      throw new IllegalArgumentException(paramCharSequence.toString());
    }
    paramCharSequence = new StringBuilder();
    paramCharSequence.append(paramString);
    paramCharSequence.append(" may not be null");
    throw new IllegalArgumentException(paramCharSequence.toString());
  }
  
  public static CharSequence notEmpty(CharSequence paramCharSequence, String paramString)
  {
    if (paramCharSequence != null)
    {
      if (!TextUtils.isEmpty(paramCharSequence)) {
        return paramCharSequence;
      }
      paramCharSequence = new StringBuilder();
      paramCharSequence.append(paramString);
      paramCharSequence.append(" may not be empty");
      throw new IllegalArgumentException(paramCharSequence.toString());
    }
    paramCharSequence = new StringBuilder();
    paramCharSequence.append(paramString);
    paramCharSequence.append(" may not be null");
    throw new IllegalArgumentException(paramCharSequence.toString());
  }
  
  public static Collection notEmpty(Collection paramCollection, String paramString)
  {
    if (paramCollection != null)
    {
      if (!paramCollection.isEmpty()) {
        return paramCollection;
      }
      paramCollection = new StringBuilder();
      paramCollection.append(paramString);
      paramCollection.append(" may not be empty");
      throw new IllegalArgumentException(paramCollection.toString());
    }
    paramCollection = new StringBuilder();
    paramCollection.append(paramString);
    paramCollection.append(" may not be null");
    throw new IllegalArgumentException(paramCollection.toString());
  }
  
  public static int notNegative(int paramInt, String paramString)
  {
    if (paramInt >= 0) {
      return paramInt;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" may not be negative");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public static long notNegative(long paramLong, String paramString)
  {
    if (paramLong >= 0L) {
      return paramLong;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" may not be negative");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public static Object notNull(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      return paramObject;
    }
    paramObject = new StringBuilder();
    paramObject.append(paramString);
    paramObject.append(" may not be null");
    throw new IllegalArgumentException(paramObject.toString());
  }
  
  public static int positive(int paramInt, String paramString)
  {
    if (paramInt > 0) {
      return paramInt;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" may not be negative or zero");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
}
