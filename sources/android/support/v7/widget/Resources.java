package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class Resources {
    static InputConnection validate(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            for (ViewParent $r4 = view.getParent(); $r4 instanceof View; $r4 = $r4.getParent()) {
                if ($r4 instanceof Field) {
                    editorInfo.hintText = ((Field) $r4).getMessage();
                    return inputConnection;
                }
            }
        }
        return inputConnection;
    }
}
