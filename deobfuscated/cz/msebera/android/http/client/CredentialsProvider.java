package cz.msebera.android.http.client;

import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;

public abstract interface CredentialsProvider
{
  public abstract Credentials getCredentials(AuthScope paramAuthScope);
}
