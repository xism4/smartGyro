package org.cocos2dx.package_1;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Cocos2dxTextInputWrapper implements TextWatcher, TextView.OnEditorActionListener {
    private static final String PAGE_KEY = "Cocos2dxTextInputWrapper";
    private final Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private String mOriginText;
    private String mText;

    public Cocos2dxTextInputWrapper(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mCocos2dxGLSurfaceView = cocos2dxGLSurfaceView;
    }

    private boolean isFullScreenEdit() {
        return ((InputMethodManager) this.mCocos2dxGLSurfaceView.getCocos2dxEditText().getContext().getSystemService("input_method")).isFullscreenMode();
    }

    public void afterTextChanged(Editable editable) {
        if (!isFullScreenEdit()) {
            int $i0 = 0;
            int $i1 = 0;
            while ($i0 < this.mText.length() && $i1 < editable.length() && this.mText.charAt($i0) == editable.charAt($i1)) {
                $i0++;
                $i1++;
            }
            while ($i0 < this.mText.length()) {
                this.mCocos2dxGLSurfaceView.deleteBackward();
                $i0++;
            }
            if (editable.length() - $i1 > 0) {
                this.mCocos2dxGLSurfaceView.insertText(editable.subSequence($i1, editable.length()).toString());
            }
            this.mText = editable.toString();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mText = charSequence.toString();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.mCocos2dxGLSurfaceView.getCocos2dxEditText() == textView && isFullScreenEdit()) {
            String $r5 = this.mOriginText;
            if ($r5 != null) {
                for (int $i1 = $r5.length(); $i1 > 0; $i1--) {
                    this.mCocos2dxGLSurfaceView.deleteBackward();
                }
            }
            String $r52 = textView.getText().toString();
            String $r7 = $r52;
            if ($r52 != null) {
                if ($r52.compareTo("") == 0) {
                    $r7 = "\n";
                }
                if (10 != $r7.charAt($r7.length() - 1)) {
                    $r7 = $r7 + 10;
                }
            }
            this.mCocos2dxGLSurfaceView.insertText($r7);
        }
        if (i != 6) {
            return false;
        }
        this.mCocos2dxGLSurfaceView.requestFocus();
        return false;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setOriginText(String str) {
        this.mOriginText = str;
    }
}
