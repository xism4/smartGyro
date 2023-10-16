package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p025v7.view.menu.C0312w;
import android.support.p025v7.widget.C0439ta;
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
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.view.menu.ListMenuItemView */
public class ListMenuItemView extends LinearLayout implements C0312w.C0313a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a */
    private C0299p f886a;

    /* renamed from: b */
    private ImageView f887b;

    /* renamed from: c */
    private RadioButton f888c;

    /* renamed from: d */
    private TextView f889d;

    /* renamed from: e */
    private CheckBox f890e;

    /* renamed from: f */
    private TextView f891f;

    /* renamed from: g */
    private ImageView f892g;

    /* renamed from: h */
    private ImageView f893h;

    /* renamed from: i */
    private LinearLayout f894i;

    /* renamed from: j */
    private Drawable f895j;

    /* renamed from: k */
    private int f896k;

    /* renamed from: l */
    private Context f897l;

    /* renamed from: m */
    private boolean f898m;

    /* renamed from: n */
    private Drawable f899n;

    /* renamed from: o */
    private boolean f900o;

    /* renamed from: p */
    private int f901p;

    /* renamed from: q */
    private LayoutInflater f902q;

    /* renamed from: r */
    private boolean f903r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        C0439ta a = C0439ta.m1902a(getContext(), attributeSet, C0145j.MenuView, i, 0);
        this.f895j = a.mo2277b(C0145j.MenuView_android_itemBackground);
        this.f896k = a.mo2286g(C0145j.MenuView_android_itemTextAppearance, -1);
        this.f898m = a.mo2275a(C0145j.MenuView_preserveIconSpacing, false);
        this.f897l = context;
        this.f899n = a.mo2277b(C0145j.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{16843049}, C0136a.dropDownListViewStyle, 0);
        this.f900o = obtainStyledAttributes.hasValue(0);
        a.mo2274a();
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    private void m1120a() {
        this.f890e = (CheckBox) getInflater().inflate(C0142g.abc_list_menu_item_checkbox, this, false);
        m1121a(this.f890e);
    }

    /* renamed from: a */
    private void m1121a(View view) {
        m1122a(view, -1);
    }

    /* renamed from: a */
    private void m1122a(View view, int i) {
        LinearLayout linearLayout = this.f894i;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    /* renamed from: b */
    private void m1123b() {
        this.f887b = (ImageView) getInflater().inflate(C0142g.abc_list_menu_item_icon, this, false);
        m1122a((View) this.f887b, 0);
    }

    /* renamed from: d */
    private void m1124d() {
        this.f888c = (RadioButton) getInflater().inflate(C0142g.abc_list_menu_item_radio, this, false);
        m1121a(this.f888c);
    }

    private LayoutInflater getInflater() {
        if (this.f902q == null) {
            this.f902q = LayoutInflater.from(getContext());
        }
        return this.f902q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.f892g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    /* renamed from: a */
    public void mo1101a(C0299p pVar, int i) {
        this.f886a = pVar;
        this.f901p = i;
        setVisibility(pVar.isVisible() ? 0 : 8);
        setTitle(pVar.mo1334a((C0312w.C0313a) this));
        setCheckable(pVar.isCheckable());
        mo1179a(pVar.mo1370m(), pVar.mo1342d());
        setIcon(pVar.getIcon());
        setEnabled(pVar.isEnabled());
        setSubMenuArrowVisible(pVar.hasSubMenu());
        setContentDescription(pVar.getContentDescription());
    }

    /* renamed from: a */
    public void mo1179a(boolean z, char c) {
        int i = (!z || !this.f886a.mo1370m()) ? 8 : 0;
        if (i == 0) {
            this.f891f.setText(this.f886a.mo1344e());
        }
        if (this.f891f.getVisibility() != i) {
            this.f891f.setVisibility(i);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f893h;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f893h.getLayoutParams();
            rect.top += this.f893h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    /* renamed from: c */
    public boolean mo1104c() {
        return false;
    }

    public C0299p getItemData() {
        return this.f886a;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        C0127u.m443a((View) this, this.f895j);
        this.f889d = (TextView) findViewById(C0141f.title);
        int i = this.f896k;
        if (i != -1) {
            this.f889d.setTextAppearance(this.f897l, i);
        }
        this.f891f = (TextView) findViewById(C0141f.shortcut);
        this.f892g = (ImageView) findViewById(C0141f.submenuarrow);
        ImageView imageView = this.f892g;
        if (imageView != null) {
            imageView.setImageDrawable(this.f899n);
        }
        this.f893h = (ImageView) findViewById(C0141f.group_divider);
        this.f894i = (LinearLayout) findViewById(C0141f.content);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.f887b != null && this.f898m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f887b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.f888c != null || this.f890e != null) {
            if (this.f886a.mo1362i()) {
                if (this.f888c == null) {
                    m1124d();
                }
                compoundButton2 = this.f888c;
                compoundButton = this.f890e;
            } else {
                if (this.f890e == null) {
                    m1120a();
                }
                compoundButton2 = this.f890e;
                compoundButton = this.f888c;
            }
            if (z) {
                compoundButton2.setChecked(this.f886a.isChecked());
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (compoundButton != null && compoundButton.getVisibility() != 8) {
                    compoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            CheckBox checkBox = this.f890e;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.f888c;
            if (radioButton != null) {
                radioButton.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f886a.mo1362i()) {
            if (this.f888c == null) {
                m1124d();
            }
            compoundButton = this.f888c;
        } else {
            if (this.f890e == null) {
                m1120a();
            }
            compoundButton = this.f890e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.f903r = z;
        this.f898m = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.f893h;
        if (imageView != null) {
            imageView.setVisibility((this.f900o || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f886a.mo1369l() || this.f903r;
        if (!z && !this.f898m) {
            return;
        }
        if (this.f887b != null || drawable != null || this.f898m) {
            if (this.f887b == null) {
                m1123b();
            }
            if (drawable != null || this.f898m) {
                ImageView imageView = this.f887b;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.f887b.getVisibility() != 0) {
                    this.f887b.setVisibility(0);
                    return;
                }
                return;
            }
            this.f887b.setVisibility(8);
        }
    }

    public void setTitle(CharSequence charSequence) {
        TextView textView;
        int i;
        if (charSequence != null) {
            this.f889d.setText(charSequence);
            if (this.f889d.getVisibility() != 0) {
                textView = this.f889d;
                i = 0;
            } else {
                return;
            }
        } else {
            i = 8;
            if (this.f889d.getVisibility() != 8) {
                textView = this.f889d;
            } else {
                return;
            }
        }
        textView.setVisibility(i);
    }
}
