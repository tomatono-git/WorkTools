/**
 *
 */
package tools.web.exception

/**
 *
 *
 */
class HttpConnectorException extends RuntimeException {

	/**
	 *
	 */
	public HttpConnectorException() {
	}

	/**
	 * @param message
	 */
	public HttpConnectorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpConnectorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HttpConnectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
