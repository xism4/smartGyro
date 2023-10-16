package com.org.android.core;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.PrecomputedText.Params;
import android.text.PrecomputedText.Params.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import com.org.android.util.X509LDAPCertStoreParameters;
import java.util.Locale;

public final class Label
{
  private final TextPaint a;
  private final TextDirectionHeuristic b;
  private final int c;
  private final int f;
  final PrecomputedText.Params i;
  
  public Label(PrecomputedText.Params paramParams)
  {
    a = paramParams.getTextPaint();
    b = paramParams.getTextDirection();
    c = paramParams.getBreakStrategy();
    f = paramParams.getHyphenationFrequency();
    i = paramParams;
  }
  
  Label(TextPaint paramTextPaint, TextDirectionHeuristic paramTextDirectionHeuristic, int paramInt1, int paramInt2)
  {
    PrecomputedText.Params localParams;
    if (Build.VERSION.SDK_INT >= 28) {
      localParams = new PrecomputedText.Params.Builder(paramTextPaint).setBreakStrategy(paramInt1).setHyphenationFrequency(paramInt2).setTextDirection(paramTextDirectionHeuristic).build();
    } else {
      localParams = null;
    }
    i = localParams;
    a = paramTextPaint;
    b = paramTextDirectionHeuristic;
    c = paramInt1;
    f = paramInt2;
  }
  
  public TextDirectionHeuristic a()
  {
    return b;
  }
  
  public int d()
  {
    return f;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (paramObject != null)
    {
      if (!(paramObject instanceof Label)) {
        return false;
      }
      paramObject = (Label)paramObject;
      PrecomputedText.Params localParams = i;
      if (localParams != null) {
        return localParams.equals(i);
      }
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (c != paramObject.getOffset()) {
          return false;
        }
        if (f != paramObject.d()) {
          return false;
        }
      }
      if ((Build.VERSION.SDK_INT >= 18) && (b != paramObject.a())) {
        return false;
      }
      if (a.getTextSize() != paramObject.getColor().getTextSize()) {
        return false;
      }
      if (a.getTextScaleX() != paramObject.getColor().getTextScaleX()) {
        return false;
      }
      if (a.getTextSkewX() != paramObject.getColor().getTextSkewX()) {
        return false;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        if (a.getLetterSpacing() != paramObject.getColor().getLetterSpacing()) {
          return false;
        }
        if (!TextUtils.equals(a.getFontFeatureSettings(), paramObject.getColor().getFontFeatureSettings())) {
          return false;
        }
      }
      if (a.getFlags() != paramObject.getColor().getFlags()) {
        return false;
      }
      int j = Build.VERSION.SDK_INT;
      if (j >= 24)
      {
        if (!a.getTextLocales().equals(paramObject.getColor().getTextLocales())) {
          return false;
        }
      }
      else if ((j >= 17) && (!a.getTextLocale().equals(paramObject.getColor().getTextLocale()))) {
        return false;
      }
      if (a.getTypeface() == null)
      {
        if (paramObject.getColor().getTypeface() != null) {
          return false;
        }
      }
      else
      {
        if (a.getTypeface().equals(paramObject.getColor().getTypeface())) {
          break label335;
        }
        return false;
      }
      return true;
    }
    else
    {
      return false;
    }
    label335:
    return true;
  }
  
  public TextPaint getColor()
  {
    return a;
  }
  
  public int getOffset()
  {
    return c;
  }
  
  public int hashCode()
  {
    int j = Build.VERSION.SDK_INT;
    if (j >= 24) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Float.valueOf(a.getLetterSpacing()), Integer.valueOf(a.getFlags()), a.getTextLocales(), a.getTypeface(), Boolean.valueOf(a.isElegantTextHeight()), b, Integer.valueOf(c), Integer.valueOf(f) });
    }
    if (j >= 21) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Float.valueOf(a.getLetterSpacing()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), Boolean.valueOf(a.isElegantTextHeight()), b, Integer.valueOf(c), Integer.valueOf(f) });
    }
    if (j >= 18) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), b, Integer.valueOf(c), Integer.valueOf(f) });
    }
    if (j >= 17) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), b, Integer.valueOf(c), Integer.valueOf(f) });
    }
    return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTypeface(), b, Integer.valueOf(c), Integer.valueOf(f) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder2 = new StringBuilder("{");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("textSize=");
    localStringBuilder1.append(a.getTextSize());
    localStringBuilder2.append(localStringBuilder1.toString());
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", textScaleX=");
    localStringBuilder1.append(a.getTextScaleX());
    localStringBuilder2.append(localStringBuilder1.toString());
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", textSkewX=");
    localStringBuilder1.append(a.getTextSkewX());
    localStringBuilder2.append(localStringBuilder1.toString());
    if (Build.VERSION.SDK_INT >= 21)
    {
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(", letterSpacing=");
      localStringBuilder1.append(a.getLetterSpacing());
      localStringBuilder2.append(localStringBuilder1.toString());
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(", elegantTextHeight=");
      localStringBuilder1.append(a.isElegantTextHeight());
      localStringBuilder2.append(localStringBuilder1.toString());
    }
    int j = Build.VERSION.SDK_INT;
    if (j >= 24)
    {
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(", textLocale=");
    }
    for (Object localObject = a.getTextLocales();; localObject = a.getTextLocale())
    {
      localStringBuilder1.append(localObject);
      localStringBuilder2.append(localStringBuilder1.toString());
      break;
      if (j < 17) {
        break;
      }
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(", textLocale=");
    }
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", typeface=");
    localStringBuilder1.append(a.getTypeface());
    localStringBuilder2.append(localStringBuilder1.toString());
    if (Build.VERSION.SDK_INT >= 26)
    {
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(", variationSettings=");
      localStringBuilder1.append(a.getFontVariationSettings());
      localStringBuilder2.append(localStringBuilder1.toString());
    }
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", textDir=");
    localStringBuilder1.append(b);
    localStringBuilder2.append(localStringBuilder1.toString());
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", breakStrategy=");
    localStringBuilder1.append(c);
    localStringBuilder2.append(localStringBuilder1.toString());
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(", hyphenationFrequency=");
    localStringBuilder1.append(f);
    localStringBuilder2.append(localStringBuilder1.toString());
    localStringBuilder2.append("}");
    return localStringBuilder2.toString();
  }
}
