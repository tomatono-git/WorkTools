/**
 *
 */
package tools.db.loader.oracle;

import groovy.sql.Sql
import groovy.util.logging.Log4j

import java.sql.ResultSet
import java.sql.ResultSetMetaData

import tools.db.OracleDB
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.convertor.MetaDataConvertor
import tools.db.loader.BaseObjectConfigLoader

/**
 *
 *
 */
@Log4j
public class OracleQueryConfigLoader extends BaseObjectConfigLoader {

	/**
	 * コンストラクタ
	 */
	public OracleQueryConfigLoader() {
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseObjectConfigLoader#load()
	 */
	@Override
	public List<Table> load(Map map = [:]) {
		assert map.name
		assert map.query
		def name = map.name
		def query = map.query

		List<Table> tableList = []
		def OracleDB db = new OracleDB()


		Table table = new Table()
		table.name = name

		// メタデータを取得
		db.connect { Sql sql ->
			//				String query = "select * from ${tableName} where rownum = 1"
			def result = sql.query(query) { ResultSet rs ->
				ResultSetMetaData metaData = rs.metaData
				// 列情報
				(1..metaData.columnCount).each { i ->
					Column column = createColumn()
					MetaDataConvertor.convert(metaData, i, column)

					// 項目リストに追加
					table.columnList.add(column)
				}
			}
		}

		tableList.add(table)

		return tableList;
	}
}
