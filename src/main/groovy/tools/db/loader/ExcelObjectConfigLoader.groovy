/**
 *
 */
package tools.db.loader;

import groovy.util.logging.Log4j

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem

import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.bean.TargetToExtract
import tools.db.loader.exception.ConfigLoaderException

/**
 *
 *
 */
@Log4j
public class ExcelObjectConfigLoader extends BaseObjectConfigLoader {

	/**
	 * コンストラクタ
	 *
	 * @param tablePrefix テーブルプレフィックス
	 * @param allowPattern 許可するテーブル名のパターン（省略可）
	 * @param denyPattern 除外するテーブル名のパターン（省略可）
	 * @param isIncludeView isIncludeView ビューを含むか？
	 * @param excelFilePath テーブル定義書（Excelファイル）のパス
	 */
	public ExcelObjectConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView, excelFilePath) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
		this.excelFilePath = excelFilePath
	}

	/**
	 * コンストラクタ
	 *
	 * @param target 読み込み対象
	 */
	public ExcelObjectConfigLoader(TargetToExtract target) {
		super(target)
	}

	protected String excelFilePath

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseObjectConfigLoader#load()
	 */
	@Override
	public List<Table> load(Map map = [:]) {

		try {
			List<Table> list = new ArrayList<Table>();

			File inFile = new File(excelFilePath);

			POIFSFileSystem excel = new POIFSFileSystem(new FileInputStream(inFile));
			HSSFWorkbook workbook = new HSSFWorkbook(excel);

			// HashMap<String, List<List<String>>> finderMap = config.getFinderConditionMap();

			final int sheetCount = workbook.getNumberOfSheets();
			for (int i = 0; i < sheetCount; i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				String tableName = getStringCellValue(sheet, 3, 0);
				if (!tableName) {
					log.warn(String.format("Table name is empty. sheet name ：%s",
							workbook.getSheetName(i)));
					continue;
				}

				if (!target.isAllow(tableName)) {
					log.debug(String.format("[%s] is not allow.", tableName));
					continue;
				}
				if (target.isDeny(tableName)) {
					log.debug(String.format("[%s] is deny.", tableName));
					continue;
				}

				Table table = createTable(sheet, tableName);
				list.add(table);
			}

			return list;
		} catch (FileNotFoundException e) {
			throw new ConfigLoaderException(e);
		} catch (IOException e) {
			throw new ConfigLoaderException(e);
		}
	}

	/**
	 * TableBeanを生成します。
	 *
	 * @param sheet
	 *            Excelシート
	 * @param tableName
	 *            テーブル名
	 * @return TableBean
	 */
	protected Table createTable(HSSFSheet sheet, String tableName) {

		Table table = createTable()
		table.setName(tableName)

		table.logicalName = getStringCellValue(sheet, 3, 2)

		//		// TODO _TBLの付くテーブルはfindByXxxメソッドを作る
		//		if (table.name ==~ /\\w+_TBL$/) {
		//			table.hasFinder = true;
		//		}

		for (int rowIdx = 6; true; rowIdx++) {
			String columnName = getStringCellValue(sheet, rowIdx, 1);
			if (!columnName) {
				break;
			}

			Column column = createColumn()
			column.columnName = columnName
			column.columnTypeName = getStringCellValue(sheet, rowIdx, 2)
			column.precision = getNumericCellValue(sheet, rowIdx, 3)
			column.scale = getNumericCellValue(sheet, rowIdx, 4)
			column.columnClassName = getFieldType(column.columnTypeName, column.precision, column.scale)
			boolean isNotNull = getStringCellValue(sheet, rowIdx, 5)
			column.nullable = !isNotNull
			// 空白以降は備考
			def logicalName = getStringCellValue(sheet, rowIdx, 7) ?: ''
			if (logicalName) {
				//			logicalName = logicalName.replaceFirst(/（.+/, '')
				logicalName = logicalName.split(/\s+/)[0]
			} else {
				logicalName = ''
			}
			column.logicalName = logicalName
			table.columnList.add(column);

			Double pkFlag = getNumericCellValue(sheet, rowIdx, 6);
			if (pkFlag != null && !pkFlag.equals(Double.valueOf(0))) {
				table.pkList.add(column);
				column.isPk = true;
			}

		}
		return table;
	}

	/**
	 * フィールドの型を取得
	 */
	public String getFieldType(String type, Integer lengthInteger, Integer lengthDecimal) {
		Class fieldClass = null
		String sqlType = type ? type.toUpperCase() : ''
		switch (sqlType) {
			case 'VARCHAR2':
				fieldClass = String
				break;
			case 'NUMBER':
				if (lengthDecimal != null && lengthDecimal > 0) {
					fieldClass = BigDecimal
				} else if (lengthInteger == null || lengthInteger <= 9) {
					fieldClass = Integer
				} else if (lengthInteger != null && lengthInteger > 9 && lengthInteger <= 18) {
					fieldClass = Long
				} else {
					fieldClass = BigDecimal
				}
				break;
			case 'LONG':
				fieldClass = Long
				break;
			case 'DATE':
				fieldClass = java.sql.Date
				break;
			case 'TIMEATAMP':
				fieldClass = java.sql.Timestamp
				break;
			case 'BLOB':
				fieldClass = InputStream
				break;
			default:
				fieldClass = String
				break;
		}
		String fieldType = fieldClass.simpleName
		assert fieldClass.simpleName

		return fieldType
	}

	/**
	 * セルの値を取得
	 *
	 * @param sheet
	 *            シート名
	 * @param rowIndex
	 *            行インデックス
	 * @param colIndex
	 *            列インデックス
	 * @return セルの値
	 */
	protected String getStringCellValue(HSSFSheet sheet, int rowIndex, int colIndex) {
		HSSFCell cell = getCell(sheet, rowIndex, colIndex);
		if (cell == null) {
			return null;
		}
		return cell.getStringCellValue();
	}

	/**
	 * セルの値を取得
	 *
	 * @param sheet
	 *            シート名
	 * @param rowIndex
	 *            行インデックス
	 * @param colIndex
	 *            列インデックス
	 * @return セルの値
	 */
	protected Double getNumericCellValue(HSSFSheet sheet, int rowIndex, int colIndex) {
		HSSFCell cell = getCell(sheet, rowIndex, colIndex);
		if (cell == null) {
			return null;
		}
		return cell.getNumericCellValue();
	}

	/**
	 * セルを取得
	 *
	 * @param sheet
	 *            シート名
	 * @param rowIndex
	 *            行インデックス
	 * @param colIndex
	 *            列インデックス
	 * @return セル
	 */
	protected HSSFCell getCell(HSSFSheet sheet, int rowIndex, int colIndex) {
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			return null;
		}
		HSSFCell cell = row.getCell(colIndex);
		if (cell == null) {
			return null;
		}
		return cell;
	}
}
