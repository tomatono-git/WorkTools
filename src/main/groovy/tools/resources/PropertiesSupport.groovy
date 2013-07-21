/**
 *
 */
package tools.resources

import groovy.transform.TypeChecked

/**
 * プロパティファイルのヘルパークラス
 *
 * 継承先では、@Singleton(lazy = true) をつけると良い。
 */
@TypeChecked
abstract class PropertiesSupport {

	/**
	 * String配列で取得する場合の区切り文字
	 */
	protected String delimiterStringArray = /\s*,\s*/

	/**
	 * プロパティファイルの名前
	 * @return
	 */
	abstract protected String getBundleName()

	/**
	 * ResourceBundleを取得
	 * @return
	 */
	protected ResourceBundle getBundle() {
		ResourceBundle.getBundle(bundleName)
	}

	/**
	 * プロパティの情報を取得
	 *
	 * @param key プロパティーのキー名
	 * @return プロパティの値
	 */
	protected String get(String key) {
		bundle.getString(key)
	}

	/**
	 * プロパティの情報を取得
	 *
	 * @param key プロパティーのキー名
	 * @return プロパティの値
	 */
	protected int getInt(String key) {
		Object value = bundle.getObject(key)
		int result = 0
		switch(value) {
			case int :
				result = (int)value
				break
			case String :
				result = value ? Integer.valueOf((String)value) : 0
				break
			default:
			// Stringで再取得
				String str = bundle.getString(key)
				result = value ? Integer.valueOf(str) : 0
		}
		return result
	}

	/**
	 * プロパティの値をString配列で取得
	 *
	 * @param key プロパティーのキー名
	 * @return プロパティの値
	 */
	protected String[] getStringArray(String key) {
		String value = bundle.getString(key)
		String[] values = value ? value?.split(delimiterStringArray) : [].toArray()
		return values
	}

	/**
	 * プロパティの値をListで取得
	 *
	 * @param key プロパティーのキー名
	 * @return プロパティの値
	 */
	protected List<?> getList(String key) {
		def values = this.getStringArray(key)
		def result = values.collect { it }
		return result
	}

	protected Date getDate(String key) {
		String value = bundle.getString(key)
		Date date
		if (value) {
			// GDKのDate#parse(String format, String input) を呼び出している。
			// JDKのDate#parse(String source)ではない。
			// @Deprecated が付いているのはJDKの方。Eclipseが正しく認識できていない。
			date = Date.parse('yyyy/MM/dd', value)
		}
		return date
	}
}
