package tools.util
/**
 *
 */

/**
 *
 *
 */
class Utils {

	/**
	 * アンダーバー区切りの文字列をパスカルケースに変換
	 * @param value 変換対象の文字列
	 * @return パスカルケースに変換された文字列
	 */
	static String toPascalCase(String value) {
		if (!value) return value
		def list = value.split('_').collect {
			return it.toLowerCase().capitalize()
		}
		def result = list.join('')
		return result
	}

	/**
	 * アンダーバー区切りの文字列をキャメルケースに変換
	 * @param value 変換対象の文字列
	 * @return キャメルケースに変換された文字列
	 */
	static String toCamelCase(String value) {
		if (!value) return value
		String pascal = toPascalCase(value)
		String result = Character.toLowerCase(pascal.charAt(0)) + pascal.substring(1);
		return result
	}

	static String toSnakeCase(String value) {
		if (!value) return value
		def result = value.replaceAll(/([A-Z][^A-Z])/) { all, String g1 ->
			return "_${g1.toLowerCase()}"
		}
		result = result.replaceFirst(/^_/, '').toLowerCase()

		return result
	}
}
