package com.org.v4.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.org.android.impl.cookie.SupportMenu;
import com.org.android.view.ActionProvider;
import com.org.android.view.h;
import com.org.v4.util.R$styleable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SupportMenuInflater extends MenuInflater {
    static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = {Context.class};
    final Object[] mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = {MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.mRealOwner = obj;
            Class $r5 = obj.getClass();
            try {
                this.mMethod = $r5.getMethod(str, PARAM_TYPES);
            } catch (Exception $r8) {
                InflateException $r1 = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + $r5.getName());
                $r1.initCause($r8);
                throw $r1;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem})).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem});
                return true;
            } catch (Exception $r8) {
                throw new RuntimeException($r8);
            }
        }
    }

    class MenuState {
        private PorterDuff.Mode D = null;
        private ColorStateList E = null;
        private CharSequence author;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        private int id;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private boolean itemEnabled;
        private int itemIconResId;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private boolean itemVisible;
        private Menu menu;
        private CharSequence title;
        private char value;

        public MenuState(Menu menu2) {
            this.menu = menu2;
            resetGroup();
        }

        private char getShortcut(String str) {
            if (str == null) {
                return 0;
            }
            return str.charAt(0);
        }

        private Object newInstance(String str, Class[] clsArr, Object[] objArr) {
            try {
                Constructor $r8 = SupportMenuInflater.this.mContext.getClassLoader().loadClass(str).getConstructor(clsArr);
                $r8.setAccessible(true);
                return $r8.newInstance(objArr);
            } catch (Exception $r10) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, $r10);
                return null;
            }
        }

        private void setItem(MenuItem menuItem) {
            boolean $z0 = false;
            menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            int $i0 = this.itemShowAsAction;
            if ($i0 >= 0) {
                menuItem.setShowAsAction($i0);
            }
            if (this.itemListenerMethodName != null) {
                if (!SupportMenuInflater.this.mContext.isRestricted()) {
                    menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            boolean $z1 = menuItem instanceof MenuItemImpl;
            if ($z1) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            }
            if (this.itemCheckable >= 2) {
                if ($z1) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).setExclusiveCheckable(true);
                }
            }
            String $r4 = this.itemActionViewClassName;
            if ($r4 != null) {
                menuItem.setActionView((View) newInstance($r4, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                $z0 = true;
            }
            int $i02 = this.itemActionViewLayout;
            if ($i02 > 0) {
                if (!$z0) {
                    menuItem.setActionView($i02);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            ActionProvider $r15 = this.itemActionProvider;
            if ($r15 != null) {
                h.setActionProvider(menuItem, $r15);
            }
            h.a(menuItem, this.author);
            h.setShowAsAction(menuItem, this.title);
            h.a(menuItem, this.value, this.id);
            h.setShowAsAction(menuItem, this.itemAlphabeticShortcut, this.itemNumericShortcut);
            PorterDuff.Mode $r16 = this.D;
            if ($r16 != null) {
                h.a(menuItem, $r16);
            }
            ColorStateList $r17 = this.E;
            if ($r17 != null) {
                h.a(menuItem, $r17);
            }
        }

        public void addItem() {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu $r3 = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem($r3.getItem());
            return $r3;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        public void parseMenu(AttributeSet attributeSet) {
            TypedArray $r5 = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R$styleable.MenuItem);
            this.itemId = $r5.getResourceId(R$styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = ($r5.getInt(R$styleable.MenuItem_android_menuCategory, this.groupCategory) & -65536) | ($r5.getInt(R$styleable.MenuItem_android_orderInCategory, this.groupOrder) & 65535);
            this.itemTitle = $r5.getText(R$styleable.MenuItem_android_title);
            this.itemTitleCondensed = $r5.getText(R$styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = $r5.getResourceId(R$styleable.MenuItem_android_icon, 0);
            this.value = getShortcut($r5.getString(R$styleable.MenuItem_android_alphabeticShortcut));
            this.id = $r5.getInt(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.itemAlphabeticShortcut = getShortcut($r5.getString(R$styleable.MenuItem_android_numericShortcut));
            this.itemNumericShortcut = $r5.getInt(R$styleable.MenuItem_numericModifiers, 4096);
            this.itemCheckable = $r5.hasValue(R$styleable.MenuItem_android_checkable) ? $r5.getBoolean(R$styleable.MenuItem_android_checkable, false) : this.groupCheckable;
            this.itemChecked = $r5.getBoolean(R$styleable.MenuItem_android_checked, false);
            this.itemVisible = $r5.getBoolean(R$styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = $r5.getBoolean(R$styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = $r5.getInt(R$styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = $r5.getString(R$styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = $r5.getResourceId(R$styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = $r5.getString(R$styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = $r5.getString(R$styleable.MenuItem_actionProviderClass);
            boolean $z0 = this.itemActionProviderClassName != null;
            if ($z0 && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider) newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            } else {
                if ($z0) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            this.author = $r5.getText(R$styleable.MenuItem_contentDescription);
            this.title = $r5.getText(R$styleable.MenuItem_tooltipText);
            if ($r5.hasValue(R$styleable.MenuItem_iconTintMode)) {
                int $i0 = $r5.getInt(R$styleable.MenuItem_iconTintMode, -1);
                PorterDuff.Mode $r12 = this.D;
                PorterDuff.Mode mode = $r12;
                this.D = DrawableUtils.parseTintMode($i0, $r12);
            } else {
                this.D = null;
            }
            if ($r5.hasValue(R$styleable.MenuItem_iconTint)) {
                this.E = $r5.getColorStateList(R$styleable.MenuItem_iconTint);
            } else {
                this.E = null;
            }
            $r5.recycle();
            this.itemAdded = false;
        }

        public void readGroup(AttributeSet attributeSet) {
            TypedArray $r5 = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.groupId = $r5.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.groupCategory = $r5.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = $r5.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = $r5.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = $r5.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.groupEnabled = $r5.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            $r5.recycle();
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.mContext = context;
        this.mActionViewConstructorArguments = new Object[]{context};
    }

    private Object findRealOwner(Object $r1) {
        return (!($r1 instanceof Activity) && ($r1 instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) $r1).getBaseContext()) : $r1;
    }

    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        MenuState $r4 = new MenuState(menu);
        int $i0 = xmlPullParser.getEventType();
        while (true) {
            if ($i0 != 2) {
                int $i1 = xmlPullParser.next();
                $i0 = $i1;
                if ($i1 == 1) {
                    break;
                }
            } else {
                String $r5 = xmlPullParser.getName();
                if ($r5.equals("menu")) {
                    $i0 = xmlPullParser.next();
                } else {
                    throw new RuntimeException("Expecting menu, got " + $r5);
                }
            }
        }
        String $r52 = null;
        boolean $z0 = false;
        boolean $z1 = false;
        while (!$z0) {
            if ($i0 != 1) {
                if ($i0 != 2) {
                    if ($i0 == 3) {
                        String $r8 = xmlPullParser.getName();
                        if ($z1 && $r8.equals($r52)) {
                            $r52 = null;
                            $z1 = false;
                        } else if ($r8.equals("group")) {
                            $r4.resetGroup();
                        } else if ($r8.equals("item")) {
                            if (!$r4.hasAddedItem()) {
                                ActionProvider $r9 = $r4.itemActionProvider;
                                if ($r9 == null || !$r9.hasSubMenu()) {
                                    $r4.addItem();
                                } else {
                                    $r4.addSubMenuItem();
                                }
                            }
                        } else if ($r8.equals("menu")) {
                            $z0 = true;
                        }
                    }
                } else if (!$z1) {
                    String $r82 = xmlPullParser.getName();
                    if ($r82.equals("group")) {
                        $r4.readGroup(attributeSet);
                    } else if ($r82.equals("item")) {
                        $r4.parseMenu(attributeSet);
                    } else if ($r82.equals("menu")) {
                        parseMenu(xmlPullParser, attributeSet, $r4.addSubMenuItem());
                    } else {
                        $r52 = $r82;
                        $z1 = true;
                    }
                }
                $i0 = xmlPullParser.next();
            } else {
                throw new RuntimeException("Unexpected end of document");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }

    public void inflate(int i, Menu menu) {
        if (!(menu instanceof SupportMenu)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser $r2 = null;
        try {
            XmlResourceParser $r5 = this.mContext.getResources().getLayout(i);
            $r2 = $r5;
            parseMenu($r5, Xml.asAttributeSet($r5), menu);
            if ($r5 != null) {
                $r5.close();
            }
        } catch (XmlPullParserException $r10) {
            throw new InflateException("Error inflating menu XML", $r10);
        } catch (IOException $r8) {
            throw new InflateException("Error inflating menu XML", $r8);
        } catch (Throwable $r7) {
            if ($r2 != null) {
                $r2.close();
            }
            throw $r7;
        }
    }
}
