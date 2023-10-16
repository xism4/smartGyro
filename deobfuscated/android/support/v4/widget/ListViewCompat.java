package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

public final class ListViewCompat
{
  public static void scrollListBy(ListView paramListView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramListView.scrollListBy(paramInt);
      return;
    }
    int i = paramListView.getFirstVisiblePosition();
    if (i == -1) {
      return;
    }
    View localView = paramListView.getChildAt(0);
    if (localView == null) {
      return;
    }
    paramListView.setSelectionFromTop(i, localView.getTop() - paramInt);
  }
}
