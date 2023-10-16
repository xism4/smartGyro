package lombok.eclipse.handlers.http;

class EventInfoFragment$1 implements Runnable {
    final /* synthetic */ boolean b;
    final /* synthetic */ AsyncHttpRequest c;
    final /* synthetic */ RequestHandle this$0;

    EventInfoFragment$1(RequestHandle requestHandle, AsyncHttpRequest asyncHttpRequest, boolean z) {
        this.this$0 = requestHandle;
        this.c = asyncHttpRequest;
        this.b = z;
    }

    public void run() {
        this.c.cancel(this.b);
    }
}
