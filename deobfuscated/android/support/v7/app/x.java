package android.support.v7.app;

import android.view.View;
import com.org.android.view.Label;
import com.org.android.view.OnApplyWindowInsetsListener;
import com.org.android.view.ViewCompat;

class x
  implements OnApplyWindowInsetsListener
{
  x(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7) {}
  
  public Label a(View paramView, Label paramLabel)
  {
    int i = paramLabel.getColor();
    int j = a.access$300(i);
    Label localLabel = paramLabel;
    if (i != j) {
      localLabel = paramLabel.a(paramLabel.b(), j, paramLabel.c(), paramLabel.a());
    }
    return ViewCompat.a(paramView, localLabel);
  }
}
