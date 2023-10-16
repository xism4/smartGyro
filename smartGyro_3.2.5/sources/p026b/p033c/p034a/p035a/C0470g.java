package p026b.p033c.p034a.p035a;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0563l;

/* renamed from: b.c.a.a.g */
public abstract class C0470g extends C0468f {

    /* renamed from: i */
    private String[] f1721i = {"application/octet-stream", "image/jpeg", "image/png", "image/gif"};

    public C0470g(String[] strArr) {
        if (strArr != null) {
            this.f1721i = strArr;
        } else {
            C0465d.f1690a.mo2419b("BinaryHttpRH", "Constructor passed allowedContentTypes was null !");
        }
    }

    /* renamed from: a */
    public final void mo2383a(C0883t tVar) {
        C0491G statusLine = tVar.getStatusLine();
        C0576e[] headers = tVar.getHeaders("Content-Type");
        if (headers.length != 1) {
            mo2379a(statusLine.getStatusCode(), tVar.getAllHeaders(), (byte[]) null, new C0563l(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        C0576e eVar = headers[0];
        boolean z = false;
        for (String str : mo2406j()) {
            try {
                if (Pattern.matches(str, eVar.getValue())) {
                    z = true;
                }
            } catch (PatternSyntaxException e) {
                C0465d.f1690a.mo2420b("BinaryHttpRH", "Given pattern is not valid: " + str, e);
            }
        }
        if (!z) {
            mo2379a(statusLine.getStatusCode(), tVar.getAllHeaders(), (byte[]) null, new C0563l(statusLine.getStatusCode(), "Content-Type (" + eVar.getValue() + ") not allowed!"));
            return;
        }
        super.mo2383a(tVar);
    }

    /* renamed from: j */
    public String[] mo2406j() {
        return this.f1721i;
    }
}
