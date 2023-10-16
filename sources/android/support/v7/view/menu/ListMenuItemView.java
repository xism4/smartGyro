package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;
import com.org.v4.util.R$layout;
import com.org.v4.util.R$styleable;

public class ListMenuItemView extends LinearLayout implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster {
    private ImageView c;
    private LinearLayout d;
    private ImageView e;
    private Drawable h;
    private boolean i;
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private boolean mForceShowIcon;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private int mMenuType;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MenuView, i2, 0);
        this.mBackground = $r5.getDrawable(R$styleable.MenuView_android_itemBackground);
        this.mTextAppearance = $r5.getResourceId(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = $r5.getBoolean(R$styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = context;
        this.h = $r5.getDrawable(R$styleable.MenuView_subMenuArrow);
        TypedArray $r8 = context.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{16843049}, R$attr.dropDownListViewStyle, 0);
        this.i = $r8.hasValue(0);
        $r5.recycle();
        $r8.recycle();
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i2) {
        LinearLayout $r2 = this.d;
        if ($r2 != null) {
            $r2.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    private LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getContext());
        }
        return this.mInflater;
    }

    private void insertCheckBox() {
        this.mCheckBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, this, false);
        a(this.mCheckBox);
    }

    private void insertIconView() {
        this.mIconView = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, this, false);
        a(this.mIconView, 0);
    }

    private void insertRadioButton() {
        this.mRadioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, this, false);
        a(this.mRadioButton);
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView $r1 = this.e;
        if ($r1 != null) {
            $r1.setVisibility(z ? (byte) 0 : 8);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView $r2 = this.c;
        if ($r2 != null && $r2.getVisibility() == 0) {
            LinearLayout.LayoutParams $r4 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            rect.top += this.c.getHeight() + $r4.topMargin + $r4.bottomMargin;
        }
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        this.mItemData = menuItemImpl;
        this.mMenuType = i2;
        setVisibility(menuItemImpl.isVisible() ? (byte) 0 : 8);
        setTitle(menuItemImpl.getTitleForItemView(this));
        setCheckable(menuItemImpl.isCheckable());
        setShortcut(menuItemImpl.shouldShowShortcut(), menuItemImpl.getShortcut());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
        setSubMenuArrowVisible(menuItemImpl.hasSubMenu());
        setContentDescription(menuItemImpl.getContentDescription());
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.setBackground(this, this.mBackground);
        this.mTitleView = (TextView) findViewById(R$id.title);
        int $i0 = this.mTextAppearance;
        if ($i0 != -1) {
            this.mTitleView.setTextAppearance(this.mTextAppearanceContext, $i0);
        }
        this.mShortcutView = (TextView) findViewById(R$id.shortcut);
        this.e = (ImageView) findViewById(R$id.submenuarrow);
        ImageView $r5 = this.e;
        if ($r5 != null) {
            $r5.setImageDrawable(this.h);
        }
        this.c = (ImageView) findViewById(R$id.group_divider);
        this.d = (LinearLayout) findViewById(R$id.content);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams $r2 = getLayoutParams();
            LinearLayout.LayoutParams $r4 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            if ($r2.height > 0 && $r4.width <= 0) {
                $r4.width = $r2.height;
            }
        }
        super.onMeasure(i2, i3);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z) {
        CompoundButton $r5;
        CompoundButton $r4;
        if (z || this.mRadioButton != null || this.mCheckBox != null) {
            if (this.mItemData.isExclusiveCheckable()) {
                if (this.mRadioButton == null) {
                    insertRadioButton();
                }
                $r4 = this.mRadioButton;
                $r5 = this.mCheckBox;
            } else {
                if (this.mCheckBox == null) {
                    insertCheckBox();
                }
                $r4 = this.mCheckBox;
                $r5 = this.mRadioButton;
            }
            if (z) {
                $r4.setChecked(this.mItemData.isChecked());
                if ($r4.getVisibility() != 0) {
                    $r4.setVisibility(0);
                }
                if ($r5 != null && $r5.getVisibility() != 8) {
                    $r5.setVisibility(8);
                    return;
                }
                return;
            }
            CheckBox $r2 = this.mCheckBox;
            if ($r2 != null) {
                $r2.setVisibility(8);
            }
            RadioButton $r1 = this.mRadioButton;
            if ($r1 != null) {
                $r1.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton $r3;
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                insertRadioButton();
            }
            $r3 = this.mRadioButton;
        } else {
            if (this.mCheckBox == null) {
                insertCheckBox();
            }
            $r3 = this.mCheckBox;
        }
        $r3.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        this.mPreserveIconSpacing = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView $r1 = this.c;
        if ($r1 != null) {
            $r1.setVisibility((this.i || !z) ? (byte) 8 : 0);
        }
    }

    public void setIcon(Drawable $r1) {
        boolean $z0 = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
        if (!$z0 && !this.mPreserveIconSpacing) {
            return;
        }
        if (this.mIconView != null || $r1 != null || this.mPreserveIconSpacing) {
            if (this.mIconView == null) {
                insertIconView();
            }
            if ($r1 != null || this.mPreserveIconSpacing) {
                ImageView $r3 = this.mIconView;
                if (!$z0) {
                    $r1 = null;
                }
                $r3.setImageDrawable($r1);
                if (this.mIconView.getVisibility() != 0) {
                    this.mIconView.setVisibility(0);
                    return;
                }
                return;
            }
            this.mIconView.setVisibility(8);
        }
    }

    public void setShortcut(boolean z, char c2) {
        byte $b1 = (!z || !this.mItemData.shouldShowShortcut()) ? (byte) 8 : 0;
        if ($b1 == 0) {
            this.mShortcutView.setText(this.mItemData.a());
        }
        if (this.mShortcutView.getVisibility() != $b1) {
            this.mShortcutView.setVisibility($b1);
        }
    }

    public void setTitle(CharSequence charSequence) {
        byte $b1;
        TextView $r2;
        if (charSequence != null) {
            this.mTitleView.setText(charSequence);
            if (this.mTitleView.getVisibility() != 0) {
                $r2 = this.mTitleView;
                $b1 = 0;
            } else {
                return;
            }
        } else {
            $b1 = 8;
            if (this.mTitleView.getVisibility() != 8) {
                $r2 = this.mTitleView;
            } else {
                return;
            }
        }
        $r2.setVisibility($b1);
    }
}
