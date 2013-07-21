/**
 *
 */
package tools.db

import groovy.util.logging.Log4j
import tools.resources.PropertiesSupport

public enum DatabaseType {
	Oracle, H2, UNKNOWN
}

/**
 *
 *
 */
@Singleton(lazy = true)
@Log4j
class JdbcConfigFactory extends PropertiesSupport {
	/**
	 *
	 */
	public JdbcConfigFactory() {
		def driverClassName = get('jdbc.driver.class.name')
		def url =  get('jdbc.url')
		def userName = get('jdbc.user.name')
		def password = get('jdbc.password')
		def schemaName = getSchemaNameValue()

		config = new JdbcConfig(
			driverClassName : driverClassName,
			url : url,
			userName : userName,
			password : password,
			schemaName : schemaName)
	}

	private final JdbcConfig config

	public JdbcConfig getConfig() {
		return config
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundleName()
	 */
	@Override
	protected String getBundleName() {
		return 'jdbc';
	}

	private String getSchemaNameValue() {
		String schemaName = ''
		try {
			// PKの取得に使用
			schemaName = get('jdbc.schema.name')
			if (schemaName) {
				schemaName = schemaName.toUpperCase()
			}

		} catch (Exception e) {
			log.warn(e.getMessage(), e)
		}
		return schemaName
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#initialize()
	 */
	@Override
	protected void initialize() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
