/**
 *
 */
package tools.db.loader;

import groovy.sql.Sql

import org.codehaus.groovy.tools.Utilities

/**
 *
 *
 */
class DdlLoader {

	/**
	 *
	 */
	public DdlLoader(Sql sql, String demilter = ';') {
		this.sql = sql
		this.delimiter = Utilities.eol() + demilter + Utilities.eol()
	}

	private Sql sql
	private String delimiter;

	public String getDdlText(String tableName) {
		String ddl = getDdl(tableName, 'TABLE')
		String dependentDdl = getDependentDdl(tableName, 'COMMENT')

		StringBuffer sb = new StringBuffer()
		sb.append(ddl)
		sb.append(delimiter)
		sb.append(dependentDdl)
	}

	private String getDdl(String tableName, String objectType) {
		List lines = getDdlLines(objectType, tableName, 'GET_DDL')
		StringBuffer contents = new StringBuffer()
		lines.each { contents.append(it).append(Utilities.eol()) }
		String ddlText = contents.toString()

		return ddlText
	}

	private String getDependentDdl(String tableName, String objectType) {
		List lines = getDdlLines(objectType, tableName, 'GET_DEPENDENT_DDL')
		StringBuffer contents = new StringBuffer()
		lines.each { CharSequence line ->
			if (line.toString().trim().length() > 1) {
				contents.append(line).append(delimiter)
			}
		}
		String ddlText = contents.toString()

		return ddlText
	}

	private List<CharSequence> getDdlLines(String objectType, String tableName, String method) {
		String query = "select dbms_metadata.$method('$objectType', '$tableName') as ddl from dual"
		println query
		def result = sql.firstRow(query)
		def ddl = result['ddl']

		List lines = ddl.getCharacterStream().readLines()
		//	String ddlText = ddl.getAsciiStream().getText()

		return lines
	}
}
