package cz.msebera.android.http.impl.client;

import c.a.a.a.f.c;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.cookie.BeanSerializer$1;
import cz.msebera.android.http.cookie.Cookie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class BasicCookieStore implements CookieStore, Serializable {
    private final TreeSet<c> cookies = new TreeSet(new BeanSerializer$1());

    public synchronized void addCookie(Cookie cookie) {
        if (cookie != null) {
            this.cookies.remove(cookie);
            if (!cookie.isExpired(new Date())) {
                this.cookies.add(cookie);
            }
        }
    }

    public synchronized boolean clearExpired(Date date) {
        boolean $z0 = false;
        if (date == null) {
            return false;
        }
        Iterator $r3 = this.cookies.iterator();
        while ($r3.hasNext()) {
            if ($r3.next().isExpired(date)) {
                $r3.remove();
                $z0 = true;
            }
        }
        return $z0;
    }

    public synchronized List getCookies() {
        return new ArrayList(this.cookies);
    }

    public synchronized String toString() {
        return this.cookies.toString();
    }
}
