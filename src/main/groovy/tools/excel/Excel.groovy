/**
 *
 */
package tools.excel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

/**
 *
 *
 */
class Excel {

	public enum ExcelType {
		/**
		 * OLE2形式
		 */
		HSSF,

		/**
		 * OOXML形式
		 */
		XSSF
	}

	/**
	 * コンストラクタ
	 * @param book ワークブック
	 */
	def Excel(Workbook book) {
		this.workbook = book
	}

	/**
	 * コンストラクタ
	 * @param inStream Excelファイル
	 */
	def Excel(InputStream inStream) {
		this.workbook = WorkbookFactory.create(inStream)
	}

	/**
	 * コンストラクタ
	 * @param inFile Excelファイル
	 */
	def Excel(File inFile) {
		inFile.withInputStream { InputStream inStream ->
			this.workbook = WorkbookFactory.create(inStream)
		}
	}

	/**
	 * コンストラクタ
	 */
	def Excel(ExcelType type = ExcelType.HSSF) {
		switch(type) {
			case ExcelType.HSSF:
				this.workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook()
				break;
			case ExcelType.XSSF:
				this.workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()
				break;
			default:
				throw new IllegalArgumentException("Invalid ExcelType. $type")
		}
	}

	/** ワークブック */
	def Workbook workbook

	/**
	 * ファイルに出力
	 * @param file
	 */
	def void write(File outFile) {
		outFile.withOutputStream {OutputStream outStream ->
			this.workbook.write(outStream);
		}
	}

	/**
	 * シートを取得
	 *
	 * @param sheetName シート名（省略した場合はアクティブシート）
	 * @return シート
	 */
	def Sheet getSheet(String sheetName = null) {
		sheetName ? workbook.getSheet(sheetName) : workbook.getSheetAt(workbook.activeSheetIndex)
	}
	def Sheet getSheet(Integer sheetIndex) {
		workbook.getSheetAt(sheetIndex)
	}

	/**
	 * シートのデータを読み込み
	 *
	 * @return シートのデータ
	 */
	def List load(String sheetName = null) {
		def sheet = getSheet(sheetName)
		def rowList = []
		getRowRange(sheet)?.each { rowNum ->
			def row = sheet?.getRow(rowNum)
			getColRange(row)?.each { cellNum ->
				def colList = []
				def value = getCellValue(row, cellNum)
				colList + value
				rowList + colList
			}
		}
		return rowList
	}

	/**
	 * シートのデータを読み込み<br>
	 * １行目をヘッダー行とみなして、項目名と値のMapのListを返す。
	 *
	 * @return シートのデータ
	 */
	List<Map> loadWithColumnName(String sheetName = null) {

		List rowList = []
		Sheet sheet = workbook.getSheet(sheetName)

		def headerList = []
		def headerRow = sheet.getRow(0)
		getColRange(headerRow).each { colIdx ->
			def value = getCellValue(headerRow, colIdx)
			headerList.add(value)
		}

		getRowRange().eachWithIndex { rowIdx, idx ->
			if (idx == 0) {
				return
			}
			def row = sheet.getRow(rowIdx)
			if (!row) {
				return
			}

			def colList = [:]
			getColRange(row).each { colIdx ->
				def colName = headerList[colIdx]
				def value = getCellValue(row, colIdx)
				value = value != null ? value : ''
				colList[colName] = value
			}
			rowList.add(colList)
		}
		return rowList
	}

	/**
	 * 有効な行範囲を取得
	 *
	 * @param sheet シート
	 * @return 有効な行範囲
	 */
	def Range getRowRange(Sheet sheet) {
		if (sheet == null) {
			return null
		}
		sheet.firstRowNum..sheet.lastRowNum
	}

	/**
	 * 有効な行範囲を取得
	 *
	 * @param sheetName シート名
	 * @return 有効な行範囲
	 */
	def Range getRowRange(String sheetName = null) {
		def sheet = getSheet(sheetName)
		getRowRange(sheet)
	}

	/**
	 * 有効な列範囲を取得
	 *
	 * @param row 行
	 * @return 有効な列範囲
	 */
	def Range getColRange(Row row) {
		if (row == null) {
			return null
		}
		row.firstCellNum..<row.lastCellNum
	}

	public Cell getCell(Sheet sheet, int rowIndex, int colIndex, boolean isCreate = false) {
		assert sheet

		Cell cell = null
		if (isCreate) {
			Row row = sheet.getRow(rowIndex) ?: sheet.createRow(rowIndex)
			cell = row.getCell(colIndex) ?: row.createCell(colIndex)
		} else {
			cell = sheet?.getRow(rowIndex)?.getCell(colIndex)
		}

		return cell
	}

	def CellStyle createCellStyle() {
		return workbook.createCellStyle()
	}

	/**
	 * セルの値を取得
	 *
	 * @param rowIndex 行インデックス
	 * @param colIndex 列インデックス
	 * @return セルの値
	 */
	def getCellValue(String sheetName = null, rowIndex, colIndex) {
		def sheet = getSheet(sheetName)
		def cell = getCell(sheet, rowIndex, colIndex)
		return getCellValue(cell)
	}

	/**
	 * セルの値を取得
	 *
	 * @param sheet シート
	 * @param rowIndex 行インデックス
	 * @param colIndex 列インデックス
	 * @return セルの値
	 */
	def getCellValue(Sheet sheet, rowIndex, colIndex) {
		def cell = getCell(sheet, rowIndex, colIndex)
		return getCellValue(cell)
	}

	/**
	 * セルの値を取得
	 *
	 * @param row 行
	 * @param colIndex 列インデックス
	 * @return セルの値
	 */
	def getCellValue(Row row, colIndex) {
		def cell = row?.getCell(colIndex)
		return getCellValue(cell)
	}

	/**
	 * セルの値を取得
	 *
	 * @param cell セル
	 * @return セルの値
	 */
	def getCellValue(Cell cell) {
		if (!cell) {
			return null
		}
		def value = null;
		switch (cell.cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue()
				} else {
					value = cell.getNumericCellValue()
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue()
				break;
			case Cell.CELL_TYPE_BLANK:
				value = ''
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue()
				break;
			case Cell.CELL_TYPE_ERROR:
				value = cell.getErrorCellValue()
				break;

			case Cell.CELL_TYPE_FORMULA:
			// 数式は結果の値に変換
				CreationHelper crateHelper = cell.row.sheet.workbook.getCreationHelper();
				FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
				value = getCellValue(evaluator.evaluateInCell(cell));
				break;

			default:
				// TODO 独自のExceptionを投げる 
				throw new RuntimeException("cellType=${cell.cellType}");
				break;
		}
		return value
	}

	def void setCellValue(Row row, int colIndex, value, CellStyle style = null) {
		assert row

		def cell = row.getCell(colIndex)
		if (cell == null) {
			cell = row.createCell(colIndex)
		}
		setCellValue(cell, value, style)
	}
	def void setCellValue(Sheet sheet, rowIndex, colIndex, value, CellStyle style = null) {
		assert sheet

		def cell = this.getCell(sheet, rowIndex, colIndex)
		Row row = sheet.getRow(rowIndex)
		if (row == null) {
			row = sheet.createRow(rowIndex)
		}
		setCellValue(row, colIndex, value, style)
	}
	def void setCellValue(Cell cell, value, CellStyle style = null) {
		assert cell

		cell.setCellValue(value);
		if (style) {
			cell.setCellStyle(style)
		}
	}
}
