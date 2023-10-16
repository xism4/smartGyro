package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.view.menu.e;
import android.support.v7.widget.Label;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$dimen;
import com.org.v4.util.R$layout;

final class i extends d implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int p = R$layout.abc_popup_menu_item_layout;
    final Label a;
    private PopupWindow.OnDismissListener b;
    private final Context c;
    private boolean d;
    private final boolean e;
    private final MenuBuilder f;
    private final e.a g;
    private final int h;
    private boolean i;
    private int j;
    private l$a k;
    private boolean l;
    ViewTreeObserver mTreeObserver;
    private final View.OnAttachStateChangeListener q = new PopupMenuHelper(this);
    private final int r;
    private final int t;
    private View u;
    View v;
    final ViewTreeObserver.OnGlobalLayoutListener w = new a(this);
    private int x = 0;

    public i(Context context, MenuBuilder menuBuilder, View view, int i2, int i3, boolean z) {
        this.c = context;
        this.f = menuBuilder;
        this.e = z;
        this.g = new e.a(menuBuilder, LayoutInflater.from(context), this.e, p);
        this.t = i2;
        this.h = i3;
        Resources $r8 = context.getResources();
        this.r = Math.max($r8.getDisplayMetrics().widthPixels / 2, $r8.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.u = view;
        this.a = new Label(this.c, (AttributeSet) null, this.t, this.h);
        menuBuilder.addMenuPresenter(this, context);
    }

    private boolean a() {
        View $r1;
        if (isShowing()) {
            return true;
        }
        if (this.i || ($r1 = this.u) == null) {
            return false;
        }
        this.v = $r1;
        this.a.setOnDismissListener(this);
        this.a.setOnItemClickListener(this);
        this.a.dismiss(true);
        View $r12 = this.v;
        boolean $z0 = this.mTreeObserver == null;
        this.mTreeObserver = $r12.getViewTreeObserver();
        if ($z0) {
            this.mTreeObserver.addOnGlobalLayoutListener(this.w);
        }
        $r12.addOnAttachStateChangeListener(this.q);
        this.a.a($r12);
        this.a.a(this.x);
        if (!this.l) {
            this.j = d.measureContentWidth(this.g, (ViewGroup) null, this.c, this.r);
            this.l = true;
        }
        this.a.setContentWidth(this.j);
        this.a.show(2);
        this.a.setAdapter(c());
        this.a.show();
        ListView $r9 = this.a.add();
        $r9.setOnKeyListener(this);
        if (this.d && this.f.getValue() != null) {
            FrameLayout $r13 = (FrameLayout) LayoutInflater.from(this.c).inflate(R$layout.abc_popup_menu_header_item_layout, $r9, false);
            TextView $r14 = (TextView) $r13.findViewById(16908310);
            if ($r14 != null) {
                $r14.setText(this.f.getValue());
            }
            $r13.setEnabled(false);
            $r9.addHeaderView($r13, (Object) null, false);
        }
        this.a.setAdapter((ListAdapter) this.g);
        this.a.show();
        return true;
    }

    public void a(int i2) {
        this.x = i2;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.f) {
            dismiss();
            l$a $r3 = this.k;
            if ($r3 != null) {
                $r3.onCloseMenu(menuBuilder, z);
            }
        }
    }

    public void a(l$a l_a) {
        this.k = l_a;
    }

    public void a(View view) {
        this.u = view;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        h hVar = new h(this.c, subMenuBuilder, this.v, this.e, this.t, this.h);
        hVar.a(this.k);
        hVar.a(d.onSubMenuSelected(subMenuBuilder));
        hVar.b(this.b);
        this.b = null;
        this.f.close(false);
        Label $r8 = this.a;
        Label label = $r8;
        int $i0 = $r8.getHorizontalOffset();
        int $i2 = $i0;
        Label $r82 = this.a;
        Label label2 = $r82;
        int $i1 = $r82.getVerticalOffset();
        if ((Gravity.getAbsoluteGravity(this.x, ViewCompat.getLayoutDirection(this.u)) & 7) == 5) {
            $i2 = $i0 + this.u.getWidth();
        }
        if (!hVar.b($i2, $i1)) {
            return false;
        }
        l$a $r5 = this.k;
        if ($r5 == null) {
            return true;
        }
        $r5.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public ListView add() {
        return this.a.add();
    }

    public void add(MenuBuilder menuBuilder) {
    }

    public void b(int i2) {
        this.a.setVerticalOffset(i2);
    }

    public void b(boolean z) {
        this.g.a(z);
    }

    public void c(int i2) {
        this.a.setAdapter(i2);
    }

    public void dismiss() {
        if (isShowing()) {
            this.a.dismiss();
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public boolean isShowing() {
        return !this.i && this.a.isShowing();
    }

    public void onDismiss() {
        this.i = true;
        this.f.close();
        ViewTreeObserver $r2 = this.mTreeObserver;
        if ($r2 != null) {
            if (!$r2.isAlive()) {
                this.mTreeObserver = this.v.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.w);
            this.mTreeObserver = null;
        }
        this.v.removeOnAttachStateChangeListener(this.q);
        PopupWindow.OnDismissListener $r6 = this.b;
        if ($r6 != null) {
            $r6.onDismiss();
        }
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public void show() {
        if (!a()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    public void updateMenuView(boolean z) {
        this.l = false;
        e.a $r1 = this.g;
        if ($r1 != null) {
            $r1.notifyDataSetChanged();
        }
    }
}
