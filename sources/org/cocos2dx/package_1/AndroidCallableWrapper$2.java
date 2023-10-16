package org.cocos2dx.package_1;

final class AndroidCallableWrapper$2 implements Runnable {
    final /* synthetic */ int val$result;

    AndroidCallableWrapper$2(int i) {
        this.val$result = i;
    }

    public void run() {
        Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.val$result);
    }
}
