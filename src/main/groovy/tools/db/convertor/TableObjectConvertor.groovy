/**
 *
 */
package tools.db.convertor

import tools.db.bean.Column
import tools.db.bean.Table


/**
 *
 *
 */
class TableObjectConvertor {

	private static final String CRLF = "\r\n"

	/**
	 *
	 */
	public TableObjectConvertor() {
	}

	/**
	 * TableクラスのオブジェクトをCREATE文に変換します。
	 * @param table
	 * @return
	 */
	def static String convertToCreateQuery(List<Table> tableList) {
		def query = '' < ''
		StringBuffer sb = new StringBuffer()

		tableList.each { table ->

			sb << "CREATE TABLE ${table.name}" << CRLF
			sb << "(" << CRLF
			table.columnList.eachWithIndex { Column column, i ->
				sb << "    " << (i <= 0 ? " " : ",")
				sb << "${column.columnName} ${column.columnTypeName}"
				if (column.precision) {
					boolean isNumber = (column.columnTypeName.equalsIgnoreCase("NUMBER"))
					sb <<  "(" << column.precision << (isNumber ? ',' + column.scale : '') << ")"
				}
				if (!column.nullable) {
					sb <<  " NOT NULL"
				}
				sb << CRLF
			}
			sb << ")" << CRLF
			sb << "/" << CRLF
			table.columnList.eachWithIndex { column, i ->
				sb << "COMMENT ON COLUMN ${table.historyName}.${column.columnName} IS '${column.logicalName}'" << CRLF
				sb << "/" << CRLF
			}
		}

		return sb.toString()
	}

//	private String getColumnSize(Column column) {
//		String columnSize = ''
//		if (column.precision) {
//			boolean isNumber = (column.columnTypeName.equalsIgnoreCase("NUMBER"))
//			String scala = isNumber ? ',' + column.scale : ''
//			columnSize =  "(${column.precision}${scala})"
//		}
//		return columnSize
//	}
//
//	def static String testTmpl(List<Table> tableList) {
//		// TODO test
//
//		def table = tableList[0]
//
//		def tmlpText = '''
//CREATE TABLE ${table.name}
//(
//<% table.columnList.eachWithIndex { column, i -> %>
//    ${i <= 0 ? " " : ","}${column.columnName} ${column.columnTypeName}\
//${!column.nullable ? " NOT NULL" : ""}
//<% } %>
//)
///
//COMMENT ON COLUMN ${table.historyName}.${column.columnName} IS '${column.logicalName}'
///
//'''
//
////        def tmlpText = '''
////<% ('a'..'z').each { %>\
////    $it
////<% } %>
////hoge = $hoge
////'''
////		def column = new Column()
////		column.columnName = 'COL_1'
////		column.logicalName = '列名１'
////		column.columnTypeName = 'NUMBER'
////		column.precision = 12
////		column.scale = 34
////		column.nullable = false
//
//        def binding = [ 'table' : table ]
//
//        TemplateEngine engine = new SimpleTemplateEngine()
//        def template = engine.createTemplate(tmlpText)
//        def tmpl = template.make(binding)
//        Writer writer = new StringWriter()
//        tmpl.writeTo(writer)
////        println writer.toString()
//
//		return writer.toString()
//	}

//	private static TEMPLATE = '''
//CREATE TABLE ${table.name}
//(
//<% table.columnList.eachWithIndex { Column column, i -> %>
//    ${(i <= 0 ? " " : ",")}${column.columnName} ${column.columnTypeName}${precision}${nullable}
//'''

//	/** CREATE文の列定義部分 */
//	public String getDefine() {
//		def define = "${columnName} ${columnTypeName}"
//		if (precision) {
//			boolean isNumber = (columnTypeName.equalsIgnoreCase("NUMBER"))
//			define << "(${precision}${isNumber ? ',' + scale : ''})"
//		}
//		if (!nullable) {
//			define << " NOT NULL"
//		}
//
//		return define
//	}


//	private static TEMPLATE = """
//<% tableList.each { table -> %>\
//CREATE TABLE ${table.historyName}
//(
//     DML_ID                         NUMBER(9,0) NOT NULL
//    ,DML_TYPE                       VARCHAR2(10) NOT NULL
//    ,DML_DATE                       DATE DEFAULT SYSDATE NOT NULL
//<% table.columnList.each { column -> %>\
//    ,${column.define}
//<% } %>\
//)
///
//COMMENT ON TABLE ${table.historyName} IS '${table.logicalName}（変更履歴）'
///
//COMMENT ON COLUMN ${table.historyName}.DML_ID IS '履歴ID'
///
//COMMENT ON COLUMN ${table.historyName}.DML_TYPE IS 'データ操作タイプ'
///
//COMMENT ON COLUMN ${table.historyName}.DML_DATE IS 'データ操作日時'
///
//<% table.columnList.eachWithIndex { column, i -> %>\
//COMMENT ON COLUMN ${table.historyName}.${column.columnName} IS '${column.logicalName}'
///
//<% } %>\
//
//
//<% } %>\
//"""

//	private writeSql(List tableList, String templateFileName, String outFileName) {
//
//		File templateFile = getTemplateFile(templateFileName)
//		if (!templateFile.exists()) {
//			throw new FileNotFoundException(templateFile.path);
//		}
//		log.debug("inFile :${templateFile}");
//
//		File baseOutDir = getOutFileDir();
//		File outDir = new File(baseOutDir, "CREATE");
//		if (!outDir.exists()) {
//			outDir.mkdirs();
//		}
//		File outFile = new File(outDir, outFileName);
//		log.debug("outFile :${outFile.getPath()}");
//
//		def binding = ['tableList' : tableList];
//
//		write(outFile, binding, templateFile)
//	}

}
