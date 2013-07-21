/**
 *
 */
package tools.web.util

import java.util.Map;

/**
 *
 *
 */
class WebUtils {

	/**
	 *
	 */
	public WebUtils() {
	}

	/**
	 *
	 * @param paramsMap
	 * @param isEncodeUrl
	 * @param charset
	 * @return
	 */
	def static String toQueryString(Map<String, String> paramsMap, boolean isEncodeUrl, String charset) {
		def buff = new StringBuffer()
		paramsMap.each { key, value ->
			if (buff) {
				buff << '&'
			}
			if (value == null) {
				value = ''
			}
			if (isEncodeUrl && value.size() > 0) {
				value = URLEncoder.encode(value, charset)
			}
			buff << "${key}=${value}"
		}
		def result = buff.toString()

		return result
	}

	/**
	 *
	 * @param paramsMap
	 * @return
	 */
	def static String toQueryString(Map paramsMap) {
		return toQueryString(paramsMap, true, 'UTF-8')
	}

}
