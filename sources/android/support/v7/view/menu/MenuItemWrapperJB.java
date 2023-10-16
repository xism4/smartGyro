package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.view.ActionProvider;

class MenuItemWrapperJB extends MenuItemWrapperICS {

    class ActionProviderWrapperJB extends MenuItemWrapperICS.ActionProviderWrapper implements ActionProvider.VisibilityListener {
        ActionProvider.VisibilityListener mListener;

        public ActionProviderWrapperJB(Context context, android.view.ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        public boolean isVisible() {
            return this.mInner.isVisible();
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            ActionProvider.VisibilityListener $r1 = this.mListener;
            if ($r1 != null) {
                $r1.onActionProviderVisibilityChanged(z);
            }
        }

        public View onCreateActionView(MenuItem menuItem) {
            return this.mInner.onCreateActionView(menuItem);
        }

        public boolean overridesItemVisibility() {
            return this.mInner.overridesItemVisibility();
        }

        public void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
            this.mListener = visibilityListener;
            android.view.ActionProvider $r1 = this.mInner;
            if (visibilityListener == null) {
                this = null;
            }
            $r1.setVisibilityListener(this);
        }
    }

    MenuItemWrapperJB(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.view.menu.MenuItemWrapperJB, android.support.v7.view.menu.BaseMenuWrapper] */
    /* access modifiers changed from: package-private */
    public MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider actionProvider) {
        return new ActionProviderWrapperJB(this.mContext, actionProvider);
    }
}
