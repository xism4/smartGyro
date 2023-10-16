package cz.msebera.android.http;

import java.util.Locale;

public abstract interface ReasonPhraseCatalog
{
  public abstract String getReason(int paramInt, Locale paramLocale);
}
