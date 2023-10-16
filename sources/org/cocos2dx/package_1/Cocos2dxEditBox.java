package org.cocos2dx.package_1;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.FrameLayout;

public class Cocos2dxEditBox extends EditText {
    public static final int kEndActionNext = 1;
    public static final int kEndActionReturn = 3;
    public static final int kEndActionUnknown = 0;
    private static final int kTextHorizontalAlignmentCenter = 1;
    private static final int kTextHorizontalAlignmentLeft = 0;
    private static final int kTextHorizontalAlignmentRight = 2;
    private static final int kTextVerticalAlignmentBottom = 2;
    private static final int kTextVerticalAlignmentCenter = 1;
    private static final int kTextVerticalAlignmentTop = 0;
    private Boolean changedTextProgrammatically = false;
    int endAction = 0;
    private final int kEditBoxInputFlagInitialCapsAllCharacters = 4;
    private final int kEditBoxInputFlagInitialCapsSentence = 3;
    private final int kEditBoxInputFlagInitialCapsWord = 2;
    private final int kEditBoxInputFlagLowercaseAllCharacters = 5;
    private final int kEditBoxInputFlagPassword = 0;
    private final int kEditBoxInputFlagSensitive = 1;
    private final int kEditBoxInputModeAny = 0;
    private final int kEditBoxInputModeDecimal = 5;
    private final int kEditBoxInputModeEmailAddr = 1;
    private final int kEditBoxInputModeNumeric = 2;
    private final int kEditBoxInputModePhoneNumber = 3;
    private final int kEditBoxInputModeSingleLine = 6;
    private final int kEditBoxInputModeUrl = 4;
    private final int kKeyboardReturnTypeDefault = 0;
    private final int kKeyboardReturnTypeDone = 1;
    private final int kKeyboardReturnTypeGo = 4;
    private final int kKeyboardReturnTypeNext = 5;
    private final int kKeyboardReturnTypeSearch = 3;
    private final int kKeyboardReturnTypeSend = 2;
    private int mInputFlagConstraints;
    private int mInputModeConstraints;
    private int mMaxLength;
    private float mScaleX;

    public Cocos2dxEditBox(Context context) {
        super(context);
    }

    public Boolean getChangedTextProgrammatically() {
        return this.changedTextProgrammatically;
    }

    public float getOpenGLViewScaleX() {
        return this.mScaleX;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        ((Cocos2dxActivity) getContext()).getGLSurfaceView().requestFocus();
        return true;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return super.onKeyPreIme(i, keyEvent);
    }

    public void setChangedTextProgrammatically(Boolean bool) {
        this.changedTextProgrammatically = bool;
    }

    public void setEditBoxViewRect(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams $r1 = new FrameLayout.LayoutParams(-2, -2);
        $r1.leftMargin = i;
        $r1.topMargin = i2;
        $r1.width = i3;
        $r1.height = i4;
        $r1.gravity = 51;
        setLayoutParams($r1);
    }

    public void setInputFlag(int i) {
        int $i0;
        if (i != 0) {
            if (i == 1) {
                $i0 = 524288;
            } else if (i == 2) {
                $i0 = 8192;
            } else if (i == 3) {
                $i0 = 16384;
            } else if (i == 4) {
                $i0 = 4096;
            } else if (i == 5) {
                this.mInputFlagConstraints = 1;
            }
            this.mInputFlagConstraints = $i0;
        } else {
            this.mInputFlagConstraints = 129;
            setTypeface(Typeface.DEFAULT);
            setTransformationMethod(new PasswordTransformationMethod());
        }
        setInputType(this.mInputFlagConstraints | this.mInputModeConstraints);
    }

    public void setInputMode(int i) {
        int $i0;
        setTextHorizontalAlignment(0);
        setTextVerticalAlignment(1);
        switch (i) {
            case 0:
                setTextVerticalAlignment(0);
                $i0 = 131073;
                break;
            case 1:
                $i0 = 33;
                break;
            case 2:
                $i0 = 4098;
                break;
            case 3:
                $i0 = 3;
                break;
            case 4:
                $i0 = 17;
                break;
            case 5:
                $i0 = 12290;
                break;
            case 6:
                this.mInputModeConstraints = 1;
                break;
        }
        this.mInputModeConstraints = $i0;
        setInputType(this.mInputModeConstraints | this.mInputFlagConstraints);
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mMaxLength)});
    }

    public void setMultilineEnabled(boolean z) {
        this.mInputModeConstraints |= 131072;
    }

    public void setOpenGLViewScaleX(float f) {
        this.mScaleX = f;
    }

    public void setReturnType(int i) {
        int $i0;
        if (i != 0) {
            if (i == 1) {
                $i0 = 268435462;
            } else if (i == 2) {
                $i0 = 268435460;
            } else if (i == 3) {
                $i0 = 268435459;
            } else if (i == 4) {
                $i0 = 268435458;
            } else if (i == 5) {
                $i0 = 268435461;
            }
            setImeOptions($i0);
            return;
        }
        setImeOptions(268435457);
    }

    public void setTextHorizontalAlignment(int i) {
        int $i1;
        int $i0 = getGravity();
        if (i != 0) {
            if (i == 1) {
                $i1 = ($i0 & -6 & -4) | 1;
            } else if (i == 2) {
                $i1 = ($i0 & -4) | 5;
            }
            setGravity($i1);
        }
        $i1 = ($i0 & -6) | 3;
        setGravity($i1);
    }

    public void setTextVerticalAlignment(int i) {
        int $i0;
        int $i1 = getGravity();
        int $i2 = Cocos2dxEditBoxHelper.getPadding(this.mScaleX);
        if (i == 0) {
            setPadding($i2, ($i2 * 3) / 4, 0, 0);
            $i0 = ($i1 & -81) | 48;
        } else if (i == 1 || i != 2) {
            setPadding($i2, 0, 0, $i2 / 2);
            $i0 = ($i1 & -49 & -81) | 16;
        } else {
            $i0 = ($i1 & -49) | 80;
        }
        setGravity($i0);
    }
}
