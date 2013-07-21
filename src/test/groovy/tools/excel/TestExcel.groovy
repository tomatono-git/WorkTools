/**
 *
 */
package tools.excel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.excel.Excel;

/**
 *
 *
 */
class TestExcel {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel(org.apache.poi.hssf.usermodel.HSSFWorkbook)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testExcelHSSFWorkbook() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel(java.io.InputStream)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testExcelInputStream() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testExcel() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#write(java.io.File)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testWrite() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getSheet()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetSheetString() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getSheet()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetSheet() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#load()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testLoadString() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#load()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testLoad() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#loadWithColumnName()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testLoadWithColumnNameString() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#loadWithColumnName()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testLoadWithColumnName() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange(org.apache.poi.hssf.usermodel.HSSFSheet)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetRowRangeHSSFSheet() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetRowRangeString() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetRowRange() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getColRange(org.apache.poi.hssf.usermodel.HSSFRow)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetColRange() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#createCellStyle()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testCreateCellStyle() {
//		fail("まだ実装されていません");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testGetCellValueStringObjectObject() {
		def excel = new Excel(new File('data/TestExcel.xls'))
		// 値の取得
		assert excel.getCellValue(2, 4) == '2-4'
	}

//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetCellValueObjectObject() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetCellValueHSSFSheetObjectObject() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetCellValueHSSFRowObject() {
//		fail("まだ実装されていません");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFCell)} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testGetCellValueHSSFCell() {
//		fail("まだ実装されていません");
//	}
//
	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFRowObjectObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()

		String value = '2-4'
		def style = excel.createCellStyle()

		// 値の設定
		excel.setCellValue(sheet, 2, 4, value, style)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFRowObjectObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		def row = sheet.createRow(2)
		String value = '2-4'

		// 値の設定
		excel.setCellValue(row, 4, value)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFSheetObjectObjectObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'

		// 値の設定
		excel.setCellValue(sheet, 2, 4, value)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFSheetObjectObjectObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def style = excel.createCellStyle()

		// 値の設定
		excel.setCellValue(sheet, 2, 4, value, style)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFCell, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFCellObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def row = sheet.createRow(2)
		def cell = row.createCell(4)
		def style = excel.createCellStyle()

		// 値の設定
		excel.setCellValue(cell, value, style)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFCell, java.lang.Object)} のためのテスト・メソッド。
	 */
	@Test
	public void testSetCellValueHSSFCellObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def row = sheet.createRow(2)
		def cell = row.createCell(4)

		// 値の設定
		excel.setCellValue(cell, value)
		// 値の取得
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

}
