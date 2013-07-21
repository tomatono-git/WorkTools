/**
 *
 */
package tools.db.loader;

import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.bean.TargetToExtract

/**
 *
 *
 */
public abstract class BaseObjectConfigLoader {

	/**
	 * コンストラクタ
	 *
	 * @param tablePrefix テーブルプレフィックス
	 * @param allowPattern 許可するテーブル名のパターン（省略可）
	 * @param denyPattern 除外するテーブル名のパターン（省略可）
	 * @param isIncludeView isIncludeView ビューを含むか？
	 */
	protected BaseObjectConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		this.target.tablePrefix= tablePrefix
		this.target.allowPattern= allowPattern
		this.target.denyPattern= denyPattern
		this.target.isIncludeView= isIncludeView
	}

	/**
	 * コンストラクタ
	 *
	 * @param target 読み込み対象
	 */
	protected BaseObjectConfigLoader(TargetToExtract target) {
		this.target = target
	}

	protected TargetToExtract target = new TargetToExtract()

	protected Table createTable() {
		Table table = new Table()
		return table
	}

	protected Column createColumn() {
		Column column = new Column()
		return column
	}

	public abstract List<Table> load(Map map = [:]);
}
