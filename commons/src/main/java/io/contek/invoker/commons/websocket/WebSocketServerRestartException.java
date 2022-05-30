package io.contek.invoker.commons.websocket;

public final class WebSocketServerRestartException extends WebSocketRuntimeException {

  public WebSocketServerRestartException() {}

  public WebSocketServerRestartException(String message) {
    super(message);
  }

  public WebSocketServerRestartException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketServerRestartException(Throwable cause) {
    super(cause);
  }
}
