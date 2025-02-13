package io.contek.invoker.commons.websocket;

public final class WebSocketAuthenticationException extends WebSocketRuntimeException {

  public WebSocketAuthenticationException() {}

  public WebSocketAuthenticationException(String message) {
    super(message);
  }

  public WebSocketAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketAuthenticationException(Throwable cause) {
    super(cause);
  }
}
