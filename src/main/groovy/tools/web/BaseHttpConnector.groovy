/**
 *
 */
package tools.web

import groovy.util.logging.Log4j
import tools.web.exception.HttpConnectorException
import tools.web.util.WebUtils

/**
 *
 *
 */
@Log4j
abstract class BaseHttpConnector {

	@Delegate
	protected HttpURLConnection connection;

	protected HttpAuthenticator authenticator

	protected URL url
	protected String charset
	protected String method
	protected boolean doOutput
	protected boolean isEncodeUrl
	protected Map<String, String> params
	protected Map<String, String> headers

	/**
	 *
	 */
	protected BaseHttpConnector(Map options) {
		if (options == null) {
			options = [:]
		}

		this.init(options)
	}
//	private BaseHttpConnector() {
//	}


	protected void init(Map options) {
		this.url = options.url
		this.method =  options.method ?: 'GET'
		this.charset = options.charset ?: 'Shift_JIS'
		this.doOutput = options.doOutput != null ? options.doOutput : true
		this.isEncodeUrl = options.isEncodeUrl != null ? options.isEncodeUrl : true
		this.headers = [:]
		if (options.headers != null) {
			this.headers.putAll(options.headers)
		}
	}

	def connect(String url, String method = null, Map params = [:], String charset = null, Map headers = [:], Closure<BaseHttpConnector> clos) {
		connect(new URL(url), method, params, charset, clos)
	}
	def connect(URL url, String method = null, Map params = [:], String charset = null, Map headers = [:], Closure<BaseHttpConnector> clos) {
		try {
			assert url
			this.url = url
			method = method ?: this.method
			charset = this.charset = charset ?: this.charset
			assert this.charset && charset
			assert method && (method == 'GET' || method == 'POST')

			if (authenticator)  {
				Authenticator.setDefault(authenticator)
			}
			this.connection = url.openConnection() as HttpURLConnection

			if (headers) {
				this.headers = headers
			}
			this.headers.each { key, value ->
//				println "setRequestProperty('$key', '$value')"
				connection.setRequestProperty(key, value)
			}

			if (method) {
				connection.setRequestMethod(method)
			}
			connection.setDoOutput(doOutput)
			connection.connect()
			sendRequest(params, charset)

			clos(this)
		} catch(Exception e) {
			if (connection && connection.getErrorStream()) {
				def errorMessage = getText(connection.getErrorStream())
				throw new HttpConnectorException(e)
			} else if (e instanceof SocketException) {
				throw new HttpConnectorException("リクエストの応答がありませんでした。URL=${url.path}${url.query ? '?' + url.query : ''}" ,e)
			} else {
				throw e
			}
		} finally {
			close()
		}
	}

	public void close() {
		if (connection) {
			connection.disconnect()
			//				connection == null
		}
	}

	public int getHttpStatusCode() {
		connection.getResponseCode()
	}


	protected void sendRequest(String params, String charset = this.charset) {
		if (!params) {
			return
		}
		assert connection
		// ポストする文字
		sendRequest(connection.getOutputStream(), params, charset)
	}

	// TODO 他のクラスに移動
	protected void sendRequest(OutputStream stream, String text, String charset = this.charset) {
		assert stream
		new PrintWriter(new OutputStreamWriter(stream, charset)).withPrintWriter { PrintWriter writer ->
			writer.write(text)
			writer.flush()
		}
	}

	protected void sendRequest(Map paramsMap, String charset = this.charset) {
		assert charset
		if (!paramsMap) {
			return
		}
		// ポストする文字
		def params = WebUtils.toQueryString(paramsMap, this.isEncodeUrl, charset)
		sendRequest(params, charset)
	}

	public String getText(String charset = this.charset) {
		assert connection
		getText(connection.getInputStream(), charset)
	}

	// TODO 他のクラスに移動
	protected String getText(InputStream stream, String charset = this.charset) {
		assert stream
		def text = null;
		new BufferedReader(new InputStreamReader(stream, charset)).withReader { BufferedReader reader ->
			text = reader.getText()
		}
		return text
	}

	public String getConnectInfo() {
		def message = '' << ''
		Map headers = connection.getHeaderFields()
		headers.each { message << " ${it.key}: ${it.value}\r\n" }
		message << """
リクエストURL       [$url]
レスポンスコード    [${connection.getResponseCode()}]
レスポンスメッセージ[${connection.getResponseMessage()}]
プロンプト(realm)   [${authenticator.getPrompt()}]
"""
		return message
	}

}
