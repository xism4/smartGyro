package android.support.v7.widget;

import android.view.View;

class SnackBar
  implements Runnable
{
  SnackBar(ListViewCompat paramListViewCompat) {}
  
  public void clear()
  {
    handler.post(this);
  }
  
  public void onTouchEvent()
  {
    ListViewCompat localListViewCompat = handler;
    b = null;
    localListViewCompat.removeCallbacks(this);
  }
  
  public void run()
  {
    ListViewCompat localListViewCompat = handler;
    b = null;
    localListViewCompat.drawableStateChanged();
  }
}
