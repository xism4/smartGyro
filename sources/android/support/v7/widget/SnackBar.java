package android.support.v7.widget;

class SnackBar implements Runnable {
    final /* synthetic */ ListViewCompat handler;

    SnackBar(ListViewCompat listViewCompat) {
        this.handler = listViewCompat;
    }

    public void clear() {
        this.handler.post(this);
    }

    public void onTouchEvent() {
        ListViewCompat $r1 = this.handler;
        $r1.b = null;
        $r1.removeCallbacks(this);
    }

    public void run() {
        ListViewCompat $r1 = this.handler;
        $r1.b = null;
        $r1.drawableStateChanged();
    }
}
