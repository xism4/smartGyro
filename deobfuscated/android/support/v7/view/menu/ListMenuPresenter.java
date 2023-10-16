package android.support.v7.view.menu;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.org.v4.util.R.layout;
import java.util.ArrayList;

public class ListMenuPresenter
  implements MenuPresenter, AdapterView.OnItemClickListener
{
  private l.a d;
  MenuAdapter mAdapter;
  Context mContext;
  int mExpandedIndex;
  LayoutInflater mInflater;
  int mItemLayoutRes;
  MenuBuilder mMenu;
  ExpandedMenuView mMenuView;
  int mThemeRes;
  
  public ListMenuPresenter(int paramInt1, int paramInt2)
  {
    mItemLayoutRes = paramInt1;
    mThemeRes = paramInt2;
  }
  
  public ListMenuPresenter(Context paramContext, int paramInt)
  {
    this(paramInt, 0);
    mContext = paramContext;
    mInflater = LayoutInflater.from(mContext);
  }
  
  public void a(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    l.a localA = d;
    if (localA != null) {
      localA.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void a(l.a paramA)
  {
    d = paramA;
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    new g(paramSubMenuBuilder).a(null);
    l.a localA = d;
    if (localA != null) {
      localA.onOpenSubMenu(paramSubMenuBuilder);
    }
    return true;
  }
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public ListAdapter getAdapter()
  {
    if (mAdapter == null) {
      mAdapter = new MenuAdapter();
    }
    return mAdapter;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (mMenuView == null)
    {
      mMenuView = ((ExpandedMenuView)mInflater.inflate(R.layout.abc_expanded_menu_layout, paramViewGroup, false));
      if (mAdapter == null) {
        mAdapter = new MenuAdapter();
      }
      mMenuView.setAdapter(mAdapter);
      mMenuView.setOnItemClickListener(this);
    }
    return mMenuView;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    int i = mThemeRes;
    if (i != 0) {
      mContext = new ContextThemeWrapper(paramContext, i);
    }
    do
    {
      mInflater = LayoutInflater.from(mContext);
      break;
      if (mContext == null) {
        break;
      }
      mContext = paramContext;
    } while (mInflater == null);
    mMenu = paramMenuBuilder;
    paramContext = mAdapter;
    if (paramContext != null) {
      paramContext.notifyDataSetChanged();
    }
  }
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    mMenu.performItemAction(mAdapter.getItem(paramInt), this, 0);
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    MenuAdapter localMenuAdapter = mAdapter;
    if (localMenuAdapter != null) {
      localMenuAdapter.notifyDataSetChanged();
    }
  }
  
  class MenuAdapter
    extends BaseAdapter
  {
    private int mExpandedIndex = -1;
    
    public MenuAdapter()
    {
      findExpandedIndex();
    }
    
    void findExpandedIndex()
    {
      MenuItemImpl localMenuItemImpl = mMenu.getExpandedItem();
      if (localMenuItemImpl != null)
      {
        ArrayList localArrayList = mMenu.getNonActionItems();
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          if ((MenuItemImpl)localArrayList.get(i) == localMenuItemImpl)
          {
            mExpandedIndex = i;
            return;
          }
          i += 1;
        }
      }
      mExpandedIndex = -1;
    }
    
    public int getCount()
    {
      int i = mMenu.getNonActionItems().size() - mExpandedIndex;
      if (mExpandedIndex < 0) {
        return i;
      }
      return i - 1;
    }
    
    public MenuItemImpl getItem(int paramInt)
    {
      ArrayList localArrayList = mMenu.getNonActionItems();
      int i = paramInt + mExpandedIndex;
      int j = mExpandedIndex;
      paramInt = i;
      if (j >= 0)
      {
        paramInt = i;
        if (i >= j) {
          paramInt = i + 1;
        }
      }
      return (MenuItemImpl)localArrayList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null)
      {
        paramView = ListMenuPresenter.this;
        localView = mInflater.inflate(mItemLayoutRes, paramViewGroup, false);
      }
      ((MenuView.ItemView)localView).initialize(getItem(paramInt), 0);
      return localView;
    }
    
    public void notifyDataSetChanged()
    {
      findExpandedIndex();
      super.notifyDataSetChanged();
    }
  }
}
