/**
 *
 */
package tools.db.bean

import groovy.transform.ToString
import groovy.transform.TypeChecked

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
@TypeChecked
public class Column implements Serializable {

	/**
	 *
	 */
	public Column() {
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L

	def String logicalName
	def boolean isPk

	/** 項目名 */
	def String columnName
	/** Javaの型名 */
	def String columnClassName
	def columnType
	/** DBの型名 */
	def String columnTypeName
	def columnDisplaySize
	/** 項目別名 */
	def String columnLabel
	/** 項目サイズ */
	def Integer precision
	/** 小数点以下の桁数 */
	def Integer scale
	/** 自動採番有無 */
	def boolean autoIncrement
	def boolean caseSensitive
	def boolean currency
	def boolean definitelyWritable
	/** NULL可否 */
	def boolean nullable
	def boolean readOnly
	def boolean searchable
	def boolean signed
	def boolean writable
	def String catalogName
	/** スキーマ名 */
	def String schemaName
	/** テーブル名 */
	def String tableName

	def String remarks

	public String getFieldName() {
		return columnName.toLowerCase()
	}

	/**
	 * logicalNameを取得します。
	 *
	 * @return logicalName
	 */
	public String getLogicalName() {
		return logicalName ?: columnName
	}

	public void setLogicalName(String logicalName) {
		// 空白以降は備考
		if (logicalName) {
			def values = logicalName.split(/\s+/)
			this.logicalName = values[0]
			if (values.size() >= 2) {
				remarks = logicalName.replaceFirst(/^${this.logicalName}\s+/, '')
			}
		} else {
			this.logicalName = logicalName
		}
	}

	/**
	 * lengthIntegerを設定します。
	 *
	 * @param precision
	 *            precision
	 */
	public void setPrecision(Double precision) {
		this.precision = precision?.intValue()
	}

	/**
	 * lengthDecimalを設定します。
	 *
	 * @param scale
	 *            scale
	 */
	public void setScale(Double scale) {
		this.scale = scale?.intValue()
	}
}
