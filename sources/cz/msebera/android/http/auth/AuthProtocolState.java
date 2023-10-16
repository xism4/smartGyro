package cz.msebera.android.http.auth;

public enum AuthProtocolState {
    UNCHALLENGED,
    CHALLENGED,
    HANDSHAKE,
    FAILURE,
    SUCCESS
}
