/**
 *
 */
package tools.db.loader.oracle;

import groovy.util.logging.Log4j
import tools.db.DB
import tools.db.OracleDB
import tools.db.bean.TargetToExtract
import tools.db.loader.BaseTableConfigLoader


/**
 *
 *
 */
@Log4j
public class OracleTableConfigLoader extends BaseTableConfigLoader {

	/**
	 * コンストラクタ
	 *
	 * @param tablePrefix テーブルプレフィックス
	 * @param allowPattern 許可するテーブル名のパターン（省略可）
	 * @param denyPattern 除外するテーブル名のパターン（省略可）
	 * @param isIncludeView isIncludeView ビューを含むか？
	 */
	public OracleTableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * コンストラクタ
	 *
	 * @param target 読み込み対象
	 */
	public OracleTableConfigLoader(TargetToExtract target) {
		super(target)
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#getQuery()
	 */
	@Override
	protected String getFindTableNamesQuery(String prefix) {
		return "select table_name from user_tables where table_name like '${prefix}%'";
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#newDb()
	 */
	@Override
	protected DB createDB() {
		return new OracleDB();
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.daogen.loader.BaseTableConfigLoader#getFindViewNamesQuery(java.lang.String)
	 */
	@Override
	protected String getFindViewNamesQuery(String prefix) {
		return "select * from user_views where view_name like '${prefix}%'";
	}
}
