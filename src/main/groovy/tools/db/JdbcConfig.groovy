/**
 *
 */
package tools.db

import groovy.transform.AutoClone;
import groovy.transform.Immutable

@Immutable
public class JdbcConfig {
	def String driverClassName
	def String url
	def String userName
	def String password
	def String schemaName

	/**
	 * DB接続設定をプロパティファイルから取得
	 * @return
	 */
	public static JdbcConfig getInstance() {
		def config = JdbcConfigFactory.instance.config
	}
}
