/**
 *
 */
package tools.web

import groovy.lang.Closure;
import groovy.util.logging.Log4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;


/**
 *
 *
 */
@Log4j
class GcaaHttpConnector extends BaseHttpConnector {

	/**
	 *
	 */
	public GcaaHttpConnector(Map options) {
		super(options)
	}

//	def connect(URL url, String method = null, Map params = [:], String charset = null, Map headers = [:], Closure<GcaaHttpConnector> clos) {
//		try {
//			assert url
//			method = method ?: this.method
//			charset = this.charset = charset ?: this.charset
//			assert this.charset && charset
//
//			this.connection = url.openConnection() as HttpURLConnection
//			if (headers) {
//				this.headers = headers
//				headers.each { key, value ->
//					connection.setRequestProperty(key, value)
//				}
//			}
////			connection.setRequestProperty("DIR-LOGONUID", "MA83224")
//
//			if (method) {
//				connection.setRequestMethod(method)
//			}
//			connection.setDoOutput(doOutput)
//			connection.connect()
//			sendRequest(params, charset)
//
//			clos(this)
//		} catch(Exception e) {
//			InputStream stream = connection.getErrorStream()
//			if (connection && connection.getErrorStream()) {
//				def errorMessage = getText(connection.getErrorStream())
//				log.error errorMessage
//			} else {
//				throw e
//			}
//		} finally {
//			close()
//		}
//	}
}
