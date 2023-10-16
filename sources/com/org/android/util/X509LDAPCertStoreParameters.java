package com.org.android.util;

import android.os.Build;
import java.util.Arrays;
import java.util.Objects;

public class X509LDAPCertStoreParameters {
    public static int hashCode(Object... objArr) {
        return Build.VERSION.SDK_INT >= 19 ? Objects.hash(objArr) : Arrays.hashCode(objArr);
    }
}
