/**
 *
 */
package tools.db

import groovy.sql.Sql
import groovy.util.logging.Log4j

import java.sql.DatabaseMetaData
import java.sql.ResultSet


/**
 *
 *
 */
@Log4j
abstract class DB {

	public DB() {
		config = JdbcConfig.instance
	}

	public DB(JdbcConfig config) {
		this.config = config
	}

	def JdbcConfig config

	abstract Map<String, String> getTableComments(List tableNameList)
	abstract String getTableComments(String tableName)
	abstract Map<String, String> getColumnComment(List tableNameList)
	abstract Map<String, String> getColumnComment(String tableName)

	def connect(Closure<Sql> closure) {
		Sql sql = null
		try {
			sql = newSql(false)
			closure.call(sql)
		} catch (e) {
			sql?.rollback()
			throw e
		} finally {
			sql?.close()
		}
	}

	def Sql connect(boolean autoCommit = false) {
		return newSql(autoCommit)
	}

	private Sql newSql(boolean autoCommit = true) {
		assert config.url && config.userName && config.driverClassName

		def sql = Sql.newInstance(config.url, config.userName, config.password, config.driverClassName)
		sql.connection.autoCommit = autoCommit
		return sql
	}

	/**
	 * 項目のリストを取得
	 * @param schema スキーマー名
	 * @param tableName テーブル名
	 * @return 項目のリスト
	 */
	List getColumns(String schema, String tableName) {
		List list = connect { Sql sql ->
			DatabaseMetaData dmd = sql.getConnection().getMetaData();
			ResultSet rs = dmd.getColumns(null, schema, tableName, "%");
			def result = []
			try {
				while (rs.next()) {
					String name = rs.getString("COLUMN_NAME");
					String type = rs.getString("TYPE_NAME");
					result << ['name' : name, 'type' : type]
				}
				return result
			} finally {
				rs.close();
			}
		}
		return list
	}

	/**
	 * 主キーのリストを取得
	 * @param schema スキーマー名
	 * @param tableName テーブル名
	 * @return 主キーのリスト
	 */
	List getPrimaryKeyList(String schema, String tableName) {
		List pkList = connect { Sql sql ->
			DatabaseMetaData dmd = sql.getConnection().getMetaData();
			ResultSet rs = dmd.getPrimaryKeys(null, schema, tableName);
			def result = []
			try {
				while (rs.next()) {
					String pkName = rs.getString("PK_NAME");
					String columnName = rs.getString("COLUMN_NAME");
					short seq = rs.getShort("KEY_SEQ");
					result << ['pkName' : pkName, 'columnName' : columnName, 'keySeq' : seq]
				}
				return result
			} finally {
				rs.close();
			}
		}
		return pkList
	}

	/**
	 * レコード一意識別項目のリストを取得
	 * @param schema スキーマー名
	 * @param tableName テーブル名
	 * @return レコード一意識別項目のリスト
	 */
	List getBestRowIdentifier(String schema, String tableName) {
		List list = connect { Sql sql ->
			DatabaseMetaData dmd = sql.getConnection().getMetaData();
			ResultSet rs = dmd.getBestRowIdentifier(null, schema, tableName, DatabaseMetaData.bestRowTransaction, true);
			def result = []
			try {
				while (rs.next()) {
					String columnName = rs.getString("COLUMN_NAME");
					short  pseudoColumn = rs.getShort("PSEUDO_COLUMN");
					short  scope = rs.getShort("SCOPE");
					result << ['columnName' : columnName, 'pseudoColumn' : pseudoColumn, 'scope' : scope]
				}
				return result
			} finally {
				rs.close();
			}
		}
		return list
	}
}
