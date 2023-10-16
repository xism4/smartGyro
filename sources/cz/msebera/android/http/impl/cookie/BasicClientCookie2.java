package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.SetCookie2;
import java.util.Date;

public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    public Object clone() {
        BasicClientCookie2 $r2 = (BasicClientCookie2) super.clone();
        int[] $r3 = this.ports;
        if ($r3 != null) {
            $r2.ports = (int[]) $r3.clone();
        }
        return $r2;
    }

    public int[] getPorts() {
        return this.ports;
    }

    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    public void setCommentURL(String str) {
        this.commentURL = str;
    }

    public void setDiscard(boolean z) {
        this.discard = z;
    }

    public void setPorts(int[] iArr) {
        this.ports = iArr;
    }
}
