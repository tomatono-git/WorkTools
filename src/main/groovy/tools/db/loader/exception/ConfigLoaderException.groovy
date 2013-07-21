/**
 *
 */
package tools.db.loader.exception

/**
 *
 *
 */
class ConfigLoaderException extends RuntimeException {

	/**
	 *
	 */
	public ConfigLoaderException() {
	}

	/**
	 * @param message
	 */
	public ConfigLoaderException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConfigLoaderException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ConfigLoaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
