

/**
 *
 */
package tools.db.loader.h2;

import tools.db.DB
import tools.db.H2DB
import tools.db.bean.TargetToExtract
import tools.db.loader.BaseTableConfigLoader



/**
 *
 *
 */
class H2TableConfigLoader extends BaseTableConfigLoader {

	/**
	 * コンストラクタ
	 *
	 * @param tablePrefix テーブルプレフィックス
	 * @param allowPattern 許可するテーブル名のパターン（省略可）
	 * @param denyPattern 除外するテーブル名のパターン（省略可）
	 * @param isIncludeView isIncludeView ビューを含むか？
	 */
	public H2TableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * コンストラクタ
	 *
	 * @param target 読み込み対象
	 */
	public H2TableConfigLoader(TargetToExtract target) {
		super(target)
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#getQuery(java.lang.String)
	 */
	@Override
	protected String getFindTableNamesQuery(String prefix) {
		return "select * from INFORMATION_SCHEMA.tables where table_type = 'TABLE' and table_name like '${prefix}%'";
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#createDB()
	 */
	@Override
	protected DB createDB() {
		return new H2DB();
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.daogen.loader.BaseTableConfigLoader#getFindViewNamesQuery(java.lang.String)
	 */
	@Override
	protected String getFindViewNamesQuery(String prefix) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
