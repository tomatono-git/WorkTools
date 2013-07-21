/**
 *
 */
package tools.util

import java.util.List;

/**
 *
 *
 */
final class StringConvertor {

	/**
	 *
	 */
	private StringConvertor() {
	}

	public static List<String> linesToList(String lines, boolean isTrim = true) {
		if (!lines) {
			// 引数が空なら空のリストを返す
			return []
		}

		String target
		if (isTrim) {
			target = lines.trim()
		} else {
			target = lines
		}

		if (!target) {
			// trimした結果が空なら空のリストを返す
			return []
		}

		// リストに変換
		List<String> list = []
		target.eachLine { list += it }

		return list
	}

}
