package com.org.android.util;

import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.Objects;

public class X509LDAPCertStoreParameters
{
  public static int hashCode(Object... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return Objects.hash(paramVarArgs);
    }
    return Arrays.hashCode(paramVarArgs);
  }
}
