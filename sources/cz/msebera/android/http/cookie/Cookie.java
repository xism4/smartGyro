package cz.msebera.android.http.cookie;

import java.util.Date;

public interface Cookie {
    String getDomain();

    Date getExpiryDate();

    String getName();

    String getPath();

    int[] getPorts();

    String getValue();

    int getVersion();

    boolean isExpired(Date date);

    boolean isSecure();
}
