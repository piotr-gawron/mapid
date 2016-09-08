package list.mapid;

public class InvalidArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  public InvalidArgumentException() {
      super();
  }

  public InvalidArgumentException(String message) {
      super(message);
  }

  public InvalidArgumentException(String message, Throwable cause) {
      super(message, cause);
  }

  public InvalidArgumentException(Throwable cause) {
      super(cause);
  }

}
