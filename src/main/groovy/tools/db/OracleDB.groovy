/**
 *
 */
package tools.db

import java.sql.DatabaseMetaData;
import java.sql.ResultSet
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import groovy.sql.Sql
import groovy.util.logging.Log4j;

/**
 *
 *
 */
@Log4j
class OracleDB extends DB {

	public OracleDB(){
		super()
	}

	public OracleDB(JdbcConfig config){
		super(config)
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
select TABLE_NAME, COMMENTS from USER_TAB_COMMENTS
where TABLE_NAME in(?${',?' * (tableNameList.size() - 1)})
"""
			def result = sql.rows(query, tableNameList)
			Map<String, String> map = [:]
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
select TABLE_NAME, COMMENTS from USER_TAB_COMMENTS
where TABLE_NAME = ?
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
select TABLE_NAME, COLUMN_NAME, COMMENTS from USER_COL_COMMENTS
where TABLE_NAME in(?${',?' * (tableNameList.size() - 1)})
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
select TABLE_NAME, COLUMN_NAME, COMMENTS from USER_COL_COMMENTS
where TABLE_NAME = ?
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
