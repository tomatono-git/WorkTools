/**
 *
 */
package tools.db;

import java.util.List;
import java.util.Map;

import groovy.sql.Sql;
import groovy.transform.TypeChecked;
import groovy.util.logging.Log4j;

/**
 *
 *
 */
@Log4j
public class H2DB extends DB {

	/**
	 *
	 */
	public H2DB() {
		super()
	}

	/**
	 * テーブルのコメントを返します。
	 * @param tableNameList テーブル名のリスト
	 * @return テーブルのコメント
	 */
	Map<String, String> getTableComments(List tableNameList) {
		assert tableNameList
		connect { Sql sql ->
			String query = """
select
	 table_name
	,remarks as comments
from
	INFORMATION_SCHEMA.tables
where table_type = 'TABLE'
and
TABLE_NAME in(?${',?' * (tableNameList.size() - 1)})
"""
			def result = sql.rows(query, tableNameList)
			Map map = [:]
			result.each {
				map.put(it.table_name, it.comments)
			}
			return map
		}
	}

	/**
	 * テーブルのコメントを返します。
	 * @param tableName テーブル名
	 * @return テーブルのコメント
	 */
	String getTableComments(String tableName) {
		connect { Sql sql ->
			String query = """
select
	 table_name
	,remarks as comments
from
	INFORMATION_SCHEMA.tables
where
	table_type = 'TABLE'
	and
	TABLE_NAME = ?
"""
			def result = sql.firstRow(query, [tableName])
			return result.comments
		}
	}

	/**
	 * テーブルごとの列のコメントを取得します。
	 * @param tableNameList テーブル名のリスト
	 * @return テーブルごとの列のコメント
	 */
	Map<String, String> getColumnComment(List tableNameList) {
		assert tableNameList
		connect { Sql sql ->
			String query = """
select
	 t.table_name
	,c.column_name
	,c.remarks as comments
from
	INFORMATION_SCHEMA.columns c
inner join INFORMATION_SCHEMA.tables t
	on(c.table_name = t.table_name)
where
	t.table_type = 'TABLE'
	and
	t.TABLE_NAME in(?${',?' * (tableNameList.size() - 1)})
"""
			def result = sql.rows(query, tableNameList)
			Map map = [:]
			result.each {
				String tableName = it.table_name
				assert tableName
				assert it.column_name
				Map colMap = null
				if (map.containsKey(tableName)) {
					colMap = map.get(tableName)
				} else {
					colMap = [:]
					map.put(tableName, colMap);
				}
				colMap.put(it.column_name, it.comments)
			}
			return map
		}
	}

	/**
	 * 列のコメントを返します。
	 * @param tableName テーブル名
	 * @return 列のコメント
	 */
	Map<String, String> getColumnComment(String tableName) {
		connect { Sql sql ->
			String query = """
select
	 t.table_name
	,c.column_name
	,c.remarks as comments
from
	INFORMATION_SCHEMA.columns c
inner join INFORMATION_SCHEMA.tables t
	on(c.table_name = t.table_name)
where
	table_type = 'TABLE'
	and
	TABLE_NAME in(?${',?' * (tableNameList.size() - 1)})
	and
	TABLE_NAME = ?
"""
			def result = sql.rows(query, [tableName])
			Map map = [:]
			result.each {
				assert it.column_name
				map.put(it.column_name, it.comments)
			}
			return map
		}
	}
}
