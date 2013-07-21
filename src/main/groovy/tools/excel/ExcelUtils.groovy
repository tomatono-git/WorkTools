/**
 *
 */
package tools.excel

/**
 *
 *
 */
class ExcelUtils {

	/**
	 * シートを読み込んで処理する
	 * @param file Excelファイル
	 * @param clos 処理
	 */
	static void read(File file, Closure<Excel> clos) {
		def excel = new Excel(file)
		clos(excel)
	}

	/**
	 * シートのデータを読み込み
	 * @param file Excelファイル
	 * @param sheetName シート名
	 * @return シートのデータ
	 */
	static List load(File file, String sheetName) {
		read(file) { Excel excel ->
			excel.load(sheetName)
		}
	}

	/**
	 * シートのデータを読み込み
	 *
	 * @param file Excelファイル
	 * @return シートのデータ
	 */
	static List<Map> loadWithColumnName(File file, String sheetName = 'Sheet1') {
		file.withInputStream { InputStream inStream ->
			def excel = new Excel(inStream)
			excel.loadWithColumnName(sheetName)
		}
	}

	static void write(File file, Closure<Excel> clos) {
		file.withOutputStream {OutputStream outStream ->
			def excel = new Excel()
			clos(excel)
			excel.workbook.write(outStream);
		}
	}
}
