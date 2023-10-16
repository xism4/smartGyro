package android.support.v4.widget;

import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.os.Build.VERSION;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode.Callback;
import android.view.View;
import android.widget.TextView;
import com.org.android.core.Label;
import com.org.android.core.Segment;
import com.org.android.core.a.a.a;
import com.org.android.util.m;

public final class LayoutManager
{
  public static ActionMode.Callback a(TextView paramTextView, ActionMode.Callback paramCallback)
  {
    int i = Build.VERSION.SDK_INT;
    if ((i >= 26) && (i <= 27))
    {
      if ((paramCallback instanceof i)) {
        return paramCallback;
      }
      return new i(paramCallback, paramTextView);
    }
    return paramCallback;
  }
  
  public static Label a(TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return new Label(paramTextView.getTextMetricsParams());
    }
    a.a.a localA = new a.a.a(new TextPaint(paramTextView.getPaint()));
    if (Build.VERSION.SDK_INT >= 23)
    {
      localA.b(paramTextView.getBreakStrategy());
      localA.a(paramTextView.getHyphenationFrequency());
    }
    if (Build.VERSION.SDK_INT >= 18) {
      localA.b(init(paramTextView));
    }
    return localA.a();
  }
  
  public static void a(TextView paramTextView, int paramInt)
  {
    m.getTitle(paramInt);
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramTextView.setFirstBaselineToTopHeight(paramInt);
      return;
    }
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding())) {
      i = ascent;
    } else {
      i = top;
    }
    if (paramInt > Math.abs(i))
    {
      i = -i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramInt - i, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
    }
  }
  
  public static void a(TextView paramTextView, Label paramLabel)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      paramTextView.setTextDirection(getItemViewType(paramLabel.a()));
    }
    if (Build.VERSION.SDK_INT < 23)
    {
      float f = paramLabel.getColor().getTextScaleX();
      paramTextView.getPaint().set(paramLabel.getColor());
      if (f == paramTextView.getTextScaleX()) {
        paramTextView.setTextScaleX(f / 2.0F + 1.0F);
      }
      paramTextView.setTextScaleX(f);
      return;
    }
    paramTextView.getPaint().set(paramLabel.getColor());
    paramTextView.setBreakStrategy(paramLabel.getOffset());
    paramTextView.setHyphenationFrequency(paramLabel.d());
  }
  
  public static void a(TextView paramTextView, Segment paramSegment)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      paramSegment = ((Segment)paramSegment).append();
    }
    while (a(paramTextView).equals(((Segment)paramSegment).getValue()))
    {
      paramTextView.setText((CharSequence)paramSegment);
      return;
    }
    paramTextView = new IllegalArgumentException("Given text can not be applied to TextView.");
    throw paramTextView;
  }
  
  private static int getItemViewType(TextDirectionHeuristic paramTextDirectionHeuristic)
  {
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
      return 1;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
      return 1;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
      return 2;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LTR) {
      return 3;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.RTL) {
      return 4;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
      return 5;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
      return 6;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
      return 7;
    }
    return 1;
  }
  
  private static TextDirectionHeuristic init(TextView paramTextView)
  {
    if ((paramTextView.getTransformationMethod() instanceof PasswordTransformationMethod)) {
      return TextDirectionHeuristics.LTR;
    }
    int j = Build.VERSION.SDK_INT;
    int i = 0;
    if ((j >= 28) && ((paramTextView.getInputType() & 0xF) == 3))
    {
      i = Character.getDirectionality(android.icu.text.DecimalFormatSymbols.getInstance(paramTextView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
      if ((i != 1) && (i != 2)) {
        return TextDirectionHeuristics.LTR;
      }
      return TextDirectionHeuristics.RTL;
    }
    if (paramTextView.getLayoutDirection() == 1) {
      i = 1;
    }
    switch (paramTextView.getTextDirection())
    {
    default: 
      if (i != 0) {
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
      }
      break;
    case 7: 
      return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    case 6: 
      return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    case 5: 
      return TextDirectionHeuristics.LOCALE;
    case 4: 
      return TextDirectionHeuristics.RTL;
    case 3: 
      return TextDirectionHeuristics.LTR;
    case 2: 
      return TextDirectionHeuristics.ANYRTL_LTR;
    }
    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
  }
  
  public static void init(TextView paramTextView, int paramInt)
  {
    m.getTitle(paramInt);
    int i = paramTextView.getPaint().getFontMetricsInt(null);
    if (paramInt != i) {
      paramTextView.setLineSpacing(paramInt - i, 1.0F);
    }
  }
  
  public static void initialize(TextView paramTextView, int paramInt)
  {
    m.getTitle(paramInt);
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding())) {
      i = descent;
    } else {
      i = bottom;
    }
    if (paramInt > Math.abs(i)) {
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramInt - i);
    }
  }
  
  public static int setText(TextView paramTextView)
  {
    return paramTextView.getPaddingBottom() + getPaintgetFontMetricsIntbottom;
  }
  
  public static int updatePadding(TextView paramTextView)
  {
    return paramTextView.getPaddingTop() - getPaintgetFontMetricsInttop;
  }
}
