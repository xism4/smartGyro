package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Cocos2dxTextInputWrapper implements TextWatcher, TextView.OnEditorActionListener {
    private static final String TAG = "Cocos2dxTextInputWrapper";
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
            int i = 0;
            int i2 = 0;
            while (i < this.mText.length() && i2 < editable.length() && this.mText.charAt(i) == editable.charAt(i2)) {
                i++;
                i2++;
            }
            while (i < this.mText.length()) {
                this.mCocos2dxGLSurfaceView.deleteBackward();
                i++;
            }
            if (editable.length() - i2 > 0) {
                this.mCocos2dxGLSurfaceView.insertText(editable.subSequence(i2, editable.length()).toString());
            }
            this.mText = editable.toString();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mText = charSequence.toString();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.mCocos2dxGLSurfaceView.getCocos2dxEditText() == textView && isFullScreenEdit()) {
            String str = this.mOriginText;
            if (str != null) {
                for (int length = str.length(); length > 0; length--) {
                    this.mCocos2dxGLSurfaceView.deleteBackward();
                }
            }
            String charSequence = textView.getText().toString();
            if (charSequence != null) {
                if (charSequence.compareTo("") == 0) {
                    charSequence = "\n";
                }
                if (10 != charSequence.charAt(charSequence.length() - 1)) {
                    charSequence = charSequence + 10;
                }
            }
            this.mCocos2dxGLSurfaceView.insertText(charSequence);
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
