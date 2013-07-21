package tools.web

import groovy.util.logging.Log4j

@Log4j
class BasicHttpConnector extends BaseHttpConnector {

	/**
	 * コンストラクタ
	 * @param options オプション
	 **/
	BasicHttpConnector(Map options) {
		super(options)
		assert options.authenticator
		this.authenticator = options.authenticator
//		init(options)
	}

//	def connect(String url, String method = null, Map params = [:], String charset = null, Closure<BasicHttpConnector> clos) {
//		connect(new URL(url), method, params, charset, clos)
//	}
//	def connect(URL url, String method = null, Map params = [:], String charset = null, Closure<BasicHttpConnector> clos) {
//		try {
//			assert url
//			this.url = url
//			method = method ?: this.method
//			charset = this.charset = charset ?: this.charset
//			assert this.charset && charset
//
//			if (authenticator)  {
//				Authenticator.setDefault(authenticator)
//			}
//			this.connection = url.openConnection() as HttpURLConnection
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
