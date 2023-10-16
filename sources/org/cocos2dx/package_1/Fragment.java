package org.cocos2dx.package_1;

final class Fragment implements Runnable {
    final /* synthetic */ int mIndex;

    Fragment(int i) {
        this.mIndex = i;
    }

    public void run() {
        Cocos2dxEditBoxHelper.openKeyboardOnUiThread(this.mIndex);
    }
}
