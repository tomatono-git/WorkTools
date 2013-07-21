/**
 *
 */
package tools.db.loader;

import groovy.sql.Sql
import groovy.util.logging.Log4j

import java.sql.ResultSet
import java.sql.ResultSetMetaData

import tools.db.DB
import tools.db.JdbcConfig
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.bean.TargetToExtract
import tools.db.convertor.MetaDataConvertor

/**
 *
 *
 */
@Log4j
public abstract class BaseTableConfigLoader extends BaseObjectConfigLoader {

	/**
	 * コンストラクタ
	 *
	 * @param tablePrefix テーブルプレフィックス
	 * @param allowPattern 許可するテーブル名のパターン（省略可）
	 * @param denyPattern 除外するテーブル名のパターン（省略可）
	 * @param isIncludeView isIncludeView ビューを含むか？
	 */
	protected BaseTableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * コンストラクタ
	 *
	 * @param target 読み込み対象
	 */
	protected BaseTableConfigLoader(TargetToExtract target) {
		super(target)
	}

	protected abstract String getFindTableNamesQuery(String prefix);
	protected abstract String getFindViewNamesQuery(String prefix);
	protected abstract DB createDB();
	protected Map<String, String> getTableComments(List tableNameList) {
		return createDB().getTableComments(tableNameList);
	}

	protected Map<String, String> getColumnComment(List tableNameList) {
		return createDB().getColumnComment(tableNameList);
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseObjectConfigLoader#load()
	 */
	@Override
	public List<Table> load(Map map = [:]) {
		List<Table> tableList = []
		List tableNameList = getTableNameList(target.tablePrefix, target.allowPattern, target.denyPattern, target.isIncludeView)
		def DB db = createDB()
		// テーブルのコメントを取得
		def tableComment = getTableComments(tableNameList)
		log.debug("Table comments : $tableComment")

		// 列のコメントを取得
		def columnComments = getColumnComment(tableNameList)
		log.debug("Column comments : $columnComments")


		tableNameList.each { tableName ->

			Table table = createTable()
			table.name = tableName
			table.logicalName = tableComment[tableName]

			//			log.debug("${table.name}/${table.logicalName}")

			// メタデータを取得
			db.connect { Sql sql ->
				String query = "select * from ${tableName} where rownum = 1"
				def result = sql.query(query) { ResultSet rs ->
					ResultSetMetaData metaData = rs.metaData
					// 列情報
					(1..metaData.columnCount).each { i ->
						assert columnComments[tableName]
						Column column = createColumn()
						MetaDataConvertor.convert(metaData, i, column, columnComments[tableName])

						// 項目リストに追加
						table.columnList.add(column)
					}
				}
			}
			table.initColumnMap()

			// 主キーを取得
			log.debug("tableName=$tableName")
			List pkList = getPkList(JdbcConfig.instance.schemaName, tableName, table.columnList)
			//			assert pkList
			table.pkList = pkList
			log.debug("pkList :" + table.pkList)

			// 追加の設定
			setAdditionalOption(table)

			tableList.add(table)
		}

		//		assert tableList[0].pkList
		return tableList;
	}

	protected void setAdditionalOption(Table table) {
		// TODO ここでテーブルの設定を追加
	}

	/**
	 * テーブル名の一覧を取得
	 *
	 * @param prefix プレフィックス
	 * @param isIncludeView ビューを含むか？
	 * @param allow 許可するテーブル名のパターン（省略可）
	 * @param deny 除外するテーブル名のパターン（省略可）
	 * @return
	 */
	def List getTableNameList(String prefix, String allow = null, String deny = null, boolean isIncludeView) {
		String query = getFindTableNamesQuery(prefix);
		List tableNameList = getObjectNameList(query, 'table_name', prefix, allow, deny);

		if (isIncludeView) {
			String viewQuery = getFindViewNamesQuery(prefix);
			if (viewQuery) {
				List viewNameList = getObjectNameList(viewQuery, 'view_name', prefix, allow, deny);
				if (viewNameList) {
					tableNameList.addAll(viewNameList)
				}
			}
		}
		return tableNameList
	}

	/**
	 * オブジェクト名の一覧を取得
	 * （テーブル、ビューなど）
	 *
	 * @param query SQLクエリ
	 * @param columnName テーブル名の列名
	 * @param prefix プレフィックス
	 * @param allow 許可するテーブル名のパターン（省略可）
	 * @param deny 除外するテーブル名のパターン（省略可）
	 *
	 * @return オブジェクト名の一覧
	 */
	def List getObjectNameList(String query, String columnName, String prefix, String allow = null, String deny = null) {
		def DB db = createDB()
		def result = db.connect { Sql sql ->
			sql.rows(query)
		}
		List list = result.findAll {
			String tableName = it[columnName]
			boolean isAllow = true
			if (allow) {
				isAllow = (tableName ==~ allow)
			}
			if (deny) {
				// TODO バックアップテーブルのパターンは要検討
				isAllow = !(tableName ==~ deny)
			}
			return isAllow
		}
		return list[columnName]
	}

	/**
	 * 主キーの一覧を取得
	 *
	 * @param schema スキーマ名
	 * @param tableName テーブル名
	 * @param columnList 列情報の一覧
	 *
	 * @return 主キーの一覧
	 */
	def List<String> getPkList(String schema, String tableName, List<Column> columnList) {
		def DB db = createDB()
		List primaryKeyList = db.getPrimaryKeyList(schema, tableName)
		List pkNameList = primaryKeyList.columnName
		List pkList = pkNameList.collect { String name ->
			Column pk = columnList.find { Column column ->
				return column.columnName == name
			}
			pk.isPk = true
			return pk
		}
		return pkList
	}
}
