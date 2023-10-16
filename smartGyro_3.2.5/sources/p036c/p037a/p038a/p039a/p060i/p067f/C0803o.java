package p036c.p037a.p038a.p039a.p060i.p067f;

import java.net.Socket;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.f.o */
public class C0803o extends C0792d {
    public C0803o(Socket socket, int i, C0844g gVar) {
        C0870a.m3042a(socket, "Socket");
        i = i < 0 ? socket.getSendBufferSize() : i;
        mo3067a(socket.getOutputStream(), i < 1024 ? 1024 : i, gVar);
    }
}
