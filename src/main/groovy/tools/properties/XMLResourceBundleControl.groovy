/**
 *
 */
package tools.properties;

import groovy.util.logging.Log4j

import java.security.AccessController
import java.security.PrivilegedActionException
import java.security.PrivilegedExceptionAction
import java.util.ResourceBundle.Control

/**
 * XMLResourceBundle—p‚ÌControl
 *
 */
public class XMLResourceBundleControl extends Control {

	/**
	 *
	 */
	public XMLResourceBundleControl() {
	}

	public static final String FORMAT = "xml";
	public static final List<String> FORMAT_XML = Collections.unmodifiableList(Arrays.asList(FORMAT));

	public List<String> getFormats(String baseName) {
		return FORMAT_XML
	}

	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
	throws IllegalAccessException, InstantiationException, IOException {

		assert baseName && locale && format && loader

		ResourceBundle bundle = null;
		String bundleName = toBundleName(baseName, locale);
		if (format.equals(FORMAT)) {
			final String resourceName = toResourceName(bundleName, format);
			final ClassLoader classLoader = loader;
			final boolean reloadFlag = reload;
			InputStream stream = null;
			try {
				stream = AccessController.doPrivileged(
						new PrivilegedExceptionAction<InputStream>() {
							public InputStream run() throws IOException {
								InputStream is = null;
								if (reloadFlag) {
									URL url = classLoader.getResource(resourceName);
									if (url != null) {
										URLConnection connection = url.openConnection();
										if (connection != null) {
											connection.setUseCaches(false);
											is = connection.getInputStream();
										}
									}
								} else {
									is = classLoader.getResourceAsStream(resourceName);
								}
								return is;
							}
						});
			} catch (PrivilegedActionException e) {
				throw (IOException) e.getException();
			}
			if (stream != null) {
				try {
					bundle = new XMLResourceBundle(stream);
				} finally {
					stream.close();
				}
			}
		} else {
			throw new IllegalArgumentException("unknown format: " + format);
		}
		return bundle;
	}
}
