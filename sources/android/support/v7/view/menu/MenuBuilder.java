package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import com.org.android.impl.cookie.SupportMenu;
import com.org.android.view.ActionProvider;
import com.org.android.view.Type;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
    private static final int[] sCategoryToOrder = {1, 4, 5, 3, 2, 0};
    CharSequence a;
    View c;
    private ArrayList<p> mActionItems;
    private Callback mCallback;
    private boolean mCancellingAnimations = false;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private int mDefaultShowAsAction = 0;
    private MenuItemImpl mExpandedItem;
    Drawable mHeaderIcon;
    private boolean mIsActionItemsStale;
    private boolean mIsClosing = false;
    private boolean mIsVisibleItemsStale;
    private ArrayList<p> mItems;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private ArrayList<p> mNonActionItems;
    private boolean mOptionalIconsVisible = false;
    private boolean mOverrideVisibleItems;
    private CopyOnWriteArrayList<WeakReference<v>> mPresenters = new CopyOnWriteArrayList();
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private boolean mShowSelection = false;
    private ArrayList<p> mTempShortcutItemList = new ArrayList();
    private ArrayList<p> mVisibleItems;

    public interface Callback {
        boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem);

        void onMenuModeChange(MenuBuilder menuBuilder);
    }

    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mItems = new ArrayList();
        this.mVisibleItems = new ArrayList();
        this.mIsVisibleItemsStale = true;
        this.mActionItems = new ArrayList();
        this.mNonActionItems = new ArrayList();
        this.mIsActionItemsStale = true;
        setShortcutsVisibleInner(true);
    }

    private MenuItemImpl createNewMenuItem(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new MenuItemImpl(this, i, i2, i3, i4, charSequence, i5);
    }

    private void dispatchPresenterUpdate(boolean z) {
        if (!this.mPresenters.isEmpty()) {
            stopDispatchingItemsChanged();
            Iterator $r2 = this.mPresenters.iterator();
            while ($r2.hasNext()) {
                WeakReference $r4 = $r2.next();
                MenuPresenter $r5 = (MenuPresenter) $r4.get();
                if ($r5 == null) {
                    this.mPresenters.remove($r4);
                } else {
                    $r5.updateMenuView(z);
                }
            }
            startDispatchingItemsChanged();
        }
    }

    private boolean dispatchSubMenuSelected(SubMenuBuilder subMenuBuilder, MenuPresenter menuPresenter) {
        boolean $z1 = false;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        if (menuPresenter != null) {
            $z1 = menuPresenter.a(subMenuBuilder);
        }
        Iterator $r4 = this.mPresenters.iterator();
        while ($r4.hasNext()) {
            WeakReference $r6 = $r4.next();
            MenuPresenter $r2 = (MenuPresenter) $r6.get();
            if ($r2 == null) {
                this.mPresenters.remove($r6);
            } else if (!$z1) {
                $z1 = $r2.a(subMenuBuilder);
            }
        }
        return $z1;
    }

    private static int findInsertIndex(ArrayList arrayList, int i) {
        for (int $i1 = arrayList.size() - 1; $i1 >= 0; $i1--) {
            if (((MenuItemImpl) arrayList.get($i1)).getOrdering() <= i) {
                return $i1 + 1;
            }
        }
        return 0;
    }

    private static int getOrdering(int i) {
        int $i2 = (-65536 & i) >> 16;
        if ($i2 >= 0) {
            int[] $r0 = sCategoryToOrder;
            if ($i2 < $r0.length) {
                return (i & 65535) | ($r0[$i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void removeItemAtInt(int i, boolean z) {
        if (i >= 0 && i < this.mItems.size()) {
            this.mItems.remove(i);
            if (z) {
                onItemsChanged(true);
            }
        }
    }

    private void setHeaderInternal(int i, CharSequence $r1, int i2, Drawable $r2, View view) {
        Resources $r4 = getResources();
        if (view != null) {
            this.c = view;
            this.a = null;
            this.mHeaderIcon = null;
        } else {
            if (i > 0) {
                this.a = $r4.getText(i);
            } else if ($r1 != null) {
                this.a = $r1;
            }
            if (i2 > 0) {
                this.mHeaderIcon = com.org.android.ui.Resources.getDrawable(getContext(), i2);
            } else if ($r2 != null) {
                this.mHeaderIcon = $r2;
            }
            this.c = null;
        }
        onItemsChanged(false);
    }

    private void setShortcutsVisibleInner(boolean z) {
        boolean $z1 = true;
        if (!z || this.mResources.getConfiguration().keyboard == 1 || !Type.a(ViewConfiguration.get(this.mContext), this.mContext)) {
            $z1 = false;
        }
        this.mShortcutsVisible = $z1;
    }

    public MenuItem add(int i) {
        return addInternal(0, 0, 0, this.mResources.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return addInternal(i, i2, i3, this.mResources.getString(i4));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return addInternal(i, i2, i3, charSequence);
    }

    public MenuItem add(CharSequence charSequence) {
        return addInternal(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int $i3;
        PackageManager $r6 = this.mContext.getPackageManager();
        List $r7 = $r6.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int $i5 = $r7 != null ? $r7.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int $i4 = 0; $i4 < $i5; $i4++) {
            ResolveInfo $r9 = $r7.get($i4);
            int $i32 = $r9.specificIndex;
            Intent $r10 = new Intent($i32 < 0 ? intent : intentArr[$i32]);
            ActivityInfo $r12 = $r9.activityInfo;
            ActivityInfo activityInfo = $r12;
            ApplicationInfo $r13 = $r12.applicationInfo;
            ApplicationInfo applicationInfo = $r13;
            String $r14 = $r13.packageName;
            ActivityInfo $r122 = $r9.activityInfo;
            $r10.setComponent(new ComponentName($r14, $r122.name));
            MenuItem $r17 = add(i, i2, i3, $r9.loadLabel($r6)).setIcon($r9.loadIcon($r6)).setIntent($r10);
            if (menuItemArr != null && ($i3 = $r9.specificIndex) >= 0) {
                menuItemArr[$i3] = $r17;
            }
        }
        return $i5;
    }

    /* access modifiers changed from: protected */
    public MenuItem addInternal(int i, int i2, int i3, CharSequence charSequence) {
        int $i4 = getOrdering(i3);
        MenuItemImpl $r2 = createNewMenuItem(i, i2, i3, $i4, charSequence, this.mDefaultShowAsAction);
        ContextMenu.ContextMenuInfo $r3 = this.mCurrentMenuInfo;
        if ($r3 != null) {
            $r2.setMenuInfo($r3);
        }
        ArrayList $r4 = this.mItems;
        $r4.add(findInsertIndex($r4, $i4), $r2);
        onItemsChanged(true);
        return $r2;
    }

    public void addMenuPresenter(MenuPresenter menuPresenter) {
        addMenuPresenter(menuPresenter, this.mContext);
    }

    public void addMenuPresenter(MenuPresenter menuPresenter, Context context) {
        this.mPresenters.add(new WeakReference(menuPresenter));
        menuPresenter.initForMenu(context, this);
        this.mIsActionItemsStale = true;
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, (CharSequence) this.mResources.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, (CharSequence) this.mResources.getString(i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl $r3 = (MenuItemImpl) addInternal(i, i2, i3, charSequence);
        SubMenuBuilder $r4 = new SubMenuBuilder(this.mContext, this, $r3);
        $r3.setSubMenu($r4);
        return $r4;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public View b() {
        return this.c;
    }

    public void changeMenuMode() {
        Callback $r1 = this.mCallback;
        if ($r1 != null) {
            $r1.onMenuModeChange(this);
        }
    }

    public void clear() {
        MenuItemImpl $r1 = this.mExpandedItem;
        if ($r1 != null) {
            collapseItemActionView($r1);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.a = null;
        this.c = null;
        onItemsChanged(false);
    }

    public void close() {
        close(true);
    }

    public final void close(boolean z) {
        if (!this.mIsClosing) {
            this.mIsClosing = true;
            Iterator $r2 = this.mPresenters.iterator();
            while ($r2.hasNext()) {
                WeakReference $r4 = $r2.next();
                MenuPresenter $r5 = (MenuPresenter) $r4.get();
                if ($r5 == null) {
                    this.mPresenters.remove($r4);
                } else {
                    $r5.a(this, z);
                }
            }
            this.mIsClosing = false;
        }
    }

    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        boolean $z1 = false;
        if (this.mPresenters.isEmpty() || this.mExpandedItem != menuItemImpl) {
            return false;
        }
        stopDispatchingItemsChanged();
        Iterator $r4 = this.mPresenters.iterator();
        while ($r4.hasNext()) {
            WeakReference $r6 = $r4.next();
            MenuPresenter $r7 = (MenuPresenter) $r6.get();
            if ($r7 == null) {
                this.mPresenters.remove($r6);
            } else {
                boolean $z0 = $r7.collapseItemActionView(this, menuItemImpl);
                $z1 = $z0;
                if ($z0) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (!$z1) {
            return $z1;
        }
        this.mExpandedItem = null;
        return $z1;
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        Callback $r2 = this.mCallback;
        return $r2 != null && $r2.onMenuItemSelected(menuBuilder, menuItem);
    }

    public boolean expandItemActionView() {
        return this.mShowSelection;
    }

    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        boolean $z1 = false;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        stopDispatchingItemsChanged();
        Iterator $r3 = this.mPresenters.iterator();
        while ($r3.hasNext()) {
            WeakReference $r5 = $r3.next();
            MenuPresenter $r6 = (MenuPresenter) $r5.get();
            if ($r6 == null) {
                this.mPresenters.remove($r5);
            } else {
                boolean $z0 = $r6.expandItemActionView(this, menuItemImpl);
                $z1 = $z0;
                if ($z0) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if ($z1) {
            this.mExpandedItem = menuItemImpl;
        }
        return $z1;
    }

    public int findGroupIndex(int i) {
        return findGroupIndex(i, 0);
    }

    public int findGroupIndex(int i, int $i1) {
        int $i2 = size();
        if ($i1 < 0) {
            $i1 = 0;
        }
        while ($i1 < $i2) {
            if (this.mItems.get($i1).getGroupId() == i) {
                return $i1;
            }
            $i1++;
        }
        return -1;
    }

    public MenuItem findItem(int i) {
        MenuItem $r5;
        int $i1 = size();
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            MenuItemImpl $r3 = this.mItems.get($i2);
            if ($r3.getItemId() == i) {
                return $r3;
            }
            if ($r3.hasSubMenu() && ($r5 = $r3.getSubMenu().findItem(i)) != null) {
                return $r5;
            }
        }
        return null;
    }

    public int findItemIndex(int i) {
        int $i1 = size();
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            if (this.mItems.get($i2).getItemId() == i) {
                return $i2;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public MenuItemImpl findItemWithShortcutForKey(int i, KeyEvent keyEvent) {
        ArrayList $r1 = this.mTempShortcutItemList;
        $r1.clear();
        findItemsWithShortcutForKey($r1, i, keyEvent);
        if ($r1.isEmpty()) {
            return null;
        }
        int $i1 = keyEvent.getMetaState();
        KeyCharacterMap.KeyData $r2 = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData($r2);
        int $i2 = $r1.size();
        if ($i2 == 1) {
            return $r1.get(0);
        }
        boolean $z0 = isQwertyMode();
        for (int $i3 = 0; $i3 < $i2; $i3++) {
            MenuItemImpl $r5 = $r1.get($i3);
            char $c4 = $z0 ? $r5.getAlphabeticShortcut() : $r5.getNumericShortcut();
            if (($c4 == $r2.meta[0] && ($i1 & 2) == 0) || ($c4 == $r2.meta[2] && ($i1 & 2) != 0)) {
                return $r5;
            }
            if ($z0 && $c4 == 8 && i == 67) {
                return $r5;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void findItemsWithShortcutForKey(List list, int i, KeyEvent keyEvent) {
        boolean $z0 = isQwertyMode();
        int $i2 = keyEvent.getModifiers();
        KeyCharacterMap.KeyData $r3 = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData($r3) || i == 67) {
            int $i3 = this.mItems.size();
            for (int $i4 = 0; $i4 < $i3; $i4++) {
                MenuItemImpl $r6 = this.mItems.get($i4);
                if ($r6.hasSubMenu()) {
                    ((MenuBuilder) $r6.getSubMenu()).findItemsWithShortcutForKey(list, i, keyEvent);
                }
                char $c5 = $z0 ? $r6.getAlphabeticShortcut() : $r6.getNumericShortcut();
                if ((($i2 & 69647) == (($z0 ? $r6.getAlphabeticModifiers() : $r6.getNumericModifiers()) & 69647)) && $c5 != 0) {
                    char[] $r9 = $r3.meta;
                    if (($c5 == $r9[0] || $c5 == $r9[2] || ($z0 && $c5 == 8 && i == 67)) && $r6.isEnabled()) {
                        list.add($r6);
                    }
                }
            }
        }
    }

    public void flagActionItems() {
        ArrayList $r1 = getVisibleItems();
        if (this.mIsActionItemsStale) {
            Iterator $r3 = this.mPresenters.iterator();
            boolean $z0 = false;
            while ($r3.hasNext()) {
                WeakReference $r5 = $r3.next();
                MenuPresenter $r6 = (MenuPresenter) $r5.get();
                if ($r6 == null) {
                    this.mPresenters.remove($r5);
                } else {
                    $z0 |= $r6.flagActionItems();
                }
            }
            if ($z0) {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                int $i0 = $r1.size();
                for (int $i1 = 0; $i1 < $i0; $i1++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) $r1.get($i1);
                    (menuItemImpl.isActionButton() ? this.mActionItems : this.mNonActionItems).add(menuItemImpl);
                }
            } else {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                this.mNonActionItems.addAll(getVisibleItems());
            }
            this.mIsActionItemsStale = false;
        }
    }

    public ArrayList getActionItems() {
        flagActionItems();
        return this.mActionItems;
    }

    /* access modifiers changed from: protected */
    public String getActionViewStatesKey() {
        return "android:menu:actionviewstates";
    }

    public Context getContext() {
        return this.mContext;
    }

    public MenuItemImpl getExpandedItem() {
        return this.mExpandedItem;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public MenuItem getItem(int i) {
        return this.mItems.get(i);
    }

    public ArrayList getNonActionItems() {
        flagActionItems();
        return this.mNonActionItems;
    }

    /* access modifiers changed from: package-private */
    public boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    /* access modifiers changed from: package-private */
    public Resources getResources() {
        return this.mResources;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public CharSequence getValue() {
        return this.a;
    }

    public ArrayList getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int $i0 = this.mItems.size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            MenuItemImpl $r3 = this.mItems.get($i1);
            if ($r3.isVisible()) {
                this.mVisibleItems.add($r3);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int $i0 = size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            if (this.mItems.get($i1).isVisible()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return findItemWithShortcutForKey(i, keyEvent) != null;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    /* access modifiers changed from: package-private */
    public void onItemActionRequestChanged(MenuItemImpl menuItemImpl) {
        this.mIsActionItemsStale = true;
        onItemsChanged(true);
    }

    /* access modifiers changed from: package-private */
    public void onItemVisibleChanged(MenuItemImpl menuItemImpl) {
        this.mIsVisibleItemsStale = true;
        onItemsChanged(true);
    }

    public void onItemsChanged(boolean z) {
        if (!this.mPreventDispatchingItemsChanged) {
            if (z) {
                this.mIsVisibleItemsStale = true;
                this.mIsActionItemsStale = true;
            }
            dispatchPresenterUpdate(z);
            return;
        }
        this.mItemsChangedWhileDispatchPrevented = true;
        if (z) {
            this.mCancellingAnimations = true;
        }
    }

    public boolean performIdentifierAction(int i, int i2) {
        return performItemAction(findItem(i), i2);
    }

    public boolean performItemAction(MenuItem menuItem, int i) {
        return performItemAction(menuItem, (MenuPresenter) null, i);
    }

    public boolean performItemAction(MenuItem menuItem, MenuPresenter menuPresenter, int $i0) {
        MenuItemImpl $r3 = (MenuItemImpl) menuItem;
        if ($r3 == null || !$r3.isEnabled()) {
            return false;
        }
        boolean $z0 = $r3.invoke();
        boolean $z1 = $z0;
        ActionProvider $r4 = $r3.getSupportActionProvider();
        boolean $z2 = $r4 != null && $r4.hasSubMenu();
        if ($r3.hasCollapsibleActionView()) {
            $z1 = $z0 | $r3.expandActionView();
            if (!$z1) {
                return $z1;
            }
        } else if ($r3.hasSubMenu() || $z2) {
            if (($i0 & 4) == 0) {
                close(false);
            }
            if (!$r3.hasSubMenu()) {
                $r3.setSubMenu(new SubMenuBuilder(getContext(), this, $r3));
            }
            SubMenuBuilder $r5 = (SubMenuBuilder) $r3.getSubMenu();
            if ($z2) {
                $r4.onPrepareSubMenu($r5);
            }
            $z1 = $z0 | dispatchSubMenuSelected($r5, menuPresenter);
            if ($z1) {
                return $z1;
            }
        } else if (($i0 & 1) != 0) {
            return $z0;
        }
        close(true);
        return $z1;
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItemImpl $r2 = findItemWithShortcutForKey(i, keyEvent);
        boolean $z0 = $r2 != null ? performItemAction($r2, i2) : false;
        if ((i2 & 2) != 0) {
            close(true);
        }
        return $z0;
    }

    public void removeGroup(int i) {
        int $i2 = findGroupIndex(i);
        if ($i2 >= 0) {
            int $i3 = this.mItems.size() - $i2;
            int $i4 = 0;
            while (true) {
                int $i0 = $i4 + 1;
                if ($i4 >= $i3 || this.mItems.get($i2).getGroupId() != i) {
                    onItemsChanged(true);
                } else {
                    removeItemAtInt($i2, false);
                    $i4 = $i0;
                }
            }
            onItemsChanged(true);
        }
    }

    public void removeItem(int i) {
        removeItemAtInt(findItemIndex(i), true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: android.support.v7.view.menu.MenuPresenter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeMenuPresenter(android.support.v7.view.menu.MenuPresenter r9) {
        /*
            r8 = this;
            java.util.concurrent.CopyOnWriteArrayList<java.lang.ref.WeakReference<android.support.v7.view.menu.v>> r0 = r8.mPresenters
            java.util.Iterator r1 = r0.iterator()
        L_0x0006:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0026
            java.lang.Object r3 = r1.next()
            r5 = r3
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
            r4 = r5
            java.lang.Object r3 = r4.get()
            r7 = r3
            android.support.v7.view.menu.MenuPresenter r7 = (android.support.v7.view.menu.MenuPresenter) r7
            r6 = r7
            if (r6 == 0) goto L_0x0020
            if (r6 != r9) goto L_0x0006
        L_0x0020:
            java.util.concurrent.CopyOnWriteArrayList<java.lang.ref.WeakReference<android.support.v7.view.menu.v>> r0 = r8.mPresenters
            r0.remove(r4)
            goto L_0x0006
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.MenuBuilder.removeMenuPresenter(android.support.v7.view.menu.MenuPresenter):void");
    }

    public void restoreActionViewStates(Bundle bundle) {
        MenuItem $r4;
        if (bundle != null) {
            SparseArray $r3 = bundle.getSparseParcelableArray(getActionViewStatesKey());
            int $i0 = size();
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                MenuItem $r42 = getItem($i1);
                View $r5 = $r42.getActionView();
                if (!($r5 == null || $r5.getId() == -1)) {
                    $r5.restoreHierarchyState($r3);
                }
                if ($r42.hasSubMenu()) {
                    ((SubMenuBuilder) $r42.getSubMenu()).restoreActionViewStates(bundle);
                }
            }
            int $i02 = bundle.getInt("android:menu:expandedactionview");
            if ($i02 > 0 && ($r4 = findItem($i02)) != null) {
                $r4.expandActionView();
            }
        }
    }

    public void saveActionViewStates(Bundle bundle) {
        int $i0 = size();
        SparseArray $r2 = null;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            MenuItem $r3 = getItem($i1);
            View $r4 = $r3.getActionView();
            if (!($r4 == null || $r4.getId() == -1)) {
                if ($r2 == null) {
                    $r2 = new SparseArray();
                }
                $r4.saveHierarchyState($r2);
                if ($r3.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", $r3.getItemId());
                }
            }
            if ($r3.hasSubMenu()) {
                ((SubMenuBuilder) $r3.getSubMenu()).saveActionViewStates(bundle);
            }
        }
        if ($r2 != null) {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), $r2);
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public MenuBuilder setDefaultShowAsAction(int i) {
        this.mDefaultShowAsAction = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setExclusiveItemChecked(MenuItem menuItem) {
        int $i0 = menuItem.getGroupId();
        int $i1 = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            MenuItemImpl $r4 = this.mItems.get($i2);
            if ($r4.getGroupId() == $i0 && $r4.isExclusiveCheckable() && $r4.isCheckable()) {
                $r4.setCheckedInt($r4 == menuItem);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int $i1 = this.mItems.size();
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            MenuItemImpl $r3 = this.mItems.get($i2);
            if ($r3.getGroupId() == i) {
                $r3.setExclusiveCheckable(z2);
                $r3.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.mShowSelection = z;
    }

    public void setGroupEnabled(int i, boolean z) {
        int $i1 = this.mItems.size();
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            MenuItemImpl $r3 = this.mItems.get($i2);
            if ($r3.getGroupId() == i) {
                $r3.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int $i1 = this.mItems.size();
        boolean $z1 = false;
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            MenuItemImpl $r3 = this.mItems.get($i2);
            if ($r3.getGroupId() == i && $r3.setVisibleInt(z)) {
                $z1 = true;
            }
        }
        if ($z1) {
            onItemsChanged(true);
        }
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(int i) {
        setHeaderInternal(0, (CharSequence) null, i, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(Drawable drawable) {
        setHeaderInternal(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(int i) {
        setHeaderInternal(i, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(CharSequence charSequence) {
        setHeaderInternal(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderViewInt(View view) {
        setHeaderInternal(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public void setOverrideVisibleItems(boolean z) {
        this.mOverrideVisibleItems = z;
    }

    public void setQwertyMode(boolean z) {
        this.mQwertyMode = z;
        onItemsChanged(false);
    }

    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(this.mCancellingAnimations);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.mPreventDispatchingItemsChanged) {
            this.mPreventDispatchingItemsChanged = true;
            this.mItemsChangedWhileDispatchPrevented = false;
            this.mCancellingAnimations = false;
        }
    }
}
