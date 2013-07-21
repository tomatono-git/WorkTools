/**
 *
 */
package tools.excel

import groovy.util.logging.Log4j



/**
 *
 *
 */
@Log4j
class ExcelDataLoader {

	/**
	 * Excelのデータを読み込み
	 *
	 * @param dir Excelファイルの存在するディレクトリ
	 * @param tableName テーブル名
	 * @return Excelのデータ
	 */
	def List<Map> loadTable(File dir, String tableName) {
		def bookName = "ORACLE.${tableName}"
		def inFile = new File (dir, "${bookName}.xls");
		return ExcelUtils.loadWithColumnName(inFile)
	}
}
