package cz.msebera.android.http.cookie;

import c.a.a.a.f.c;
import java.io.Serializable;
import java.util.Comparator;

public class BeanSerializer$1 implements Serializable, Comparator<c> {
    public int compare(Cookie cookie, Cookie cookie2) {
        int $i0 = cookie.getName().compareTo(cookie2.getName());
        int $i1 = $i0;
        if ($i0 == 0) {
            String $r5 = cookie.getDomain();
            String $r3 = $r5;
            String $r4 = "";
            if ($r5 == null) {
                $r3 = "";
            } else if ($r5.indexOf(46) == -1) {
                $r3 = $r5 + ".local";
            }
            String $r52 = cookie2.getDomain();
            if ($r52 != null) {
                if ($r52.indexOf(46) == -1) {
                    $r4 = $r52 + ".local";
                } else {
                    $r4 = $r52;
                }
            }
            $i1 = $r3.compareToIgnoreCase($r4);
        }
        if ($i1 != 0) {
            return $i1;
        }
        String $r42 = cookie.getPath();
        String $r32 = $r42;
        if ($r42 == null) {
            $r32 = "/";
        }
        String $r43 = cookie2.getPath();
        String $r53 = $r43;
        if ($r43 == null) {
            $r53 = "/";
        }
        return $r32.compareTo($r53);
    }
}
