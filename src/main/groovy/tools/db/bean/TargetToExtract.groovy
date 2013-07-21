/**
 *
 */
package tools.db.bean

import groovy.transform.ToString

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
class TargetToExtract {

	/** テーブルのプレフィックス */
	def String tablePrefix
	/**対象とするテーブルのパターン  */
	def String allowPattern
	/** 除外するテーブルのパターン */
	def String denyPattern
	/** VIEWを含めるか */
	def boolean isIncludeView

	/**
	 * 除外するテーブル名か判定する。
	 *
	 * @param tableName
	 * @return
	 */
	def boolean isDeny(String tableName) {
		String pattern = denyPattern
		if (!pattern) {
			return false
		}
		return (tableName ==~ pattern)
	}

	/**
	 * 対象とするテーブル名か判定する。
	 *
	 * @param tableName
	 * @return
	 */
	def boolean isAllow(String tableName) {
		String pattern = allowPattern
		if (!pattern) {
			return true
		}
		return (tableName ==~ pattern)
	}
}
