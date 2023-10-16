package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;
import android.widget.SeekBar;
import com.org.v4.util.R.attr;

public class SwitchCompat
  extends SeekBar
{
  private final AppCompatTextHelper mThumbDrawable = new AppCompatTextHelper(this);
  
  public SwitchCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.seekBarStyle);
  }
  
  public SwitchCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mThumbDrawable.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    mThumbDrawable.setState();
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    mThumbDrawable.jumpToCurrentState();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      super.onDraw(paramCanvas);
      mThumbDrawable.draw(paramCanvas);
      return;
    }
    catch (Throwable paramCanvas)
    {
      throw paramCanvas;
    }
  }
}
