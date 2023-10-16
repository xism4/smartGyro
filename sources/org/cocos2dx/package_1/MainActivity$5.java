package org.cocos2dx.package_1;

final class MainActivity$5 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;

    MainActivity$5(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.a);
        if ($r3 != null) {
            $r3.setChangedTextProgrammatically(true);
            $r3.setText(this.b);
            $r3.setSelection($r3.getText().length());
        }
    }
}
