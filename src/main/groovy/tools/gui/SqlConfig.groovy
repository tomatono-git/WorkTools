/**
 *
 */
package tools.gui


import groovy.transform.TypeChecked
import tools.resources.PropertiesSupport

/**
 *
 *
 */
@Singleton(lazy = true)
@TypeChecked
class SqlConfig extends PropertiesSupport {

	def String logDirectory
	def String sqlLogPath
	def String errorLogPath
	def String debugLogPath

	private String getPath(String pathKey, String nameKey) {
		def path = get(pathKey)
		if (!path) {
			def name = get(nameKey)
			if (logDirectory) {
				path = new File(logDirectory, name).path
			} else {
				path = name
			}
		}
		return path
	}

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#initialize()
	 */
	@Override
	protected void initialize() {
		logDirectory = get('tools.sql.log.directory')
		sqlLogPath = getPath('tools.sql.log.filePath.sql', 'tools.sql.log.fileName.sql')
		errorLogPath = getPath('tools.sql.log.filePath.error', 'tools.sql.log.fileName.error')
		debugLogPath = getPath('tools.sql.log.filePath.debug', 'tools.sql.log.fileName.debug')
	}

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundleName()
	 */
	@Override
	protected String getBundleName() {
		return 'sql';
	}
}
