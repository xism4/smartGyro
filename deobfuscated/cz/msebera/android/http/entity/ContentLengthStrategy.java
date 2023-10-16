package cz.msebera.android.http.entity;

import cz.msebera.android.http.HttpMessage;

public abstract interface ContentLengthStrategy
{
  public abstract long determineLength(HttpMessage paramHttpMessage);
}
