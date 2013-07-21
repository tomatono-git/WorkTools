/**
 *
 */
package tools.db.convertor;

import java.sql.ResultSetMetaData
import java.sql.Timestamp

import tools.db.bean.Column

/**
 *
 *
 */
final class MetaDataConvertor {

	/**
	 *
	 */
	private MetaDataConvertor() {
	}

	/**
	 * 日付型の変換方法
	 */
	public enum DateConvertMethod {
		/**
		 * なし（変換しない）
		 */
		None
		,

		/**
		 * Date -> Timestamp
		 */
		DateToTimestamp
		,

		/**
		 * Timestamp -> Date
		 */
		TimestampToDate
	}

	public static void convert(
			ResultSetMetaData metaData,
			int index,
			Column column,
			Map columnComments = null,
			DateConvertMethod typeConvertMethod = DateConvertMethod.DateToTimestamp) {

		column.columnName = metaData.getColumnName(index)
		// Javaの型名
		column.columnClassName = metaData.getColumnClassName(index)

		column.columnType = metaData.getColumnType(index)
		// DBの型名
		column.columnTypeName = metaData.getColumnTypeName(index)
		column.columnDisplaySize = metaData.getColumnDisplaySize(index)
		// 項目別名
		column.columnLabel = metaData.getColumnLabel(index)
		// 項目サイズ
		column.precision = metaData.getPrecision(index)
		// 小数点以下の桁数
		column.scale = metaData.getScale(index)
		column.autoIncrement = metaData.isAutoIncrement(index)
		column.caseSensitive = metaData.isCaseSensitive(index)
		column.currency = metaData.isCurrency(index)
		column.definitelyWritable = metaData.isDefinitelyWritable(index)
		// NULL可否
		column.nullable = metaData.isNullable(index)
		column.readOnly = metaData.isReadOnly(index)
		column.searchable = metaData.isSearchable(index)
		column.signed = metaData.isSigned(index)
		column.writable = metaData.isWritable(index)
		// カタログ名
		column.catalogName = metaData.getCatalogName(index)
		// スキーマー名
		column.schemaName = metaData.getSchemaName(index)
		// テーブル名
		column.tableName = metaData.getTableName(index)
		// 論理名
		column.logicalName = columnComments ? columnComments[column.columnName] : null

		// Javaの型を調整
		if (column.columnClassName == BigDecimal.class.name) {
			if (!column.scale) {
				if (column.precision < Integer.MAX_VALUE.toString().length()) {
					// Integer
					column.columnClassName = Integer.class.name
				} else if (column.precision < Long.MAX_VALUE.toString().length()) {
					// Long
					column.columnClassName = Long.class.name
				}
			}
		} else if(column.columnClassName == Date.class.name) {
			if (typeConvertMethod == DateConvertMethod.DateToTimestamp) {
				// Date は Timestamp にする
				column.columnClassName = Timestamp.class.name

			}
		} else if(column.columnClassName == Timestamp.class.name) {
			if (typeConvertMethod == DateConvertMethod.TimestampToDate) {
				// Timestamp は Date にする
				column.columnClassName = Date.class.name
			}
		}
		column.columnClassName = column.columnClassName.replaceFirst(/^java\.lang\./, '')

//		return column
	}
}
