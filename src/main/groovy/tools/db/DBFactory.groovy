/**
 *
 */
package tools.db


/**
 *
 *
 */
@Singleton(lazy = true)
class DBFactory {

	def DB create(DatabaseType databaseType) {
		DB db = null;
		switch (databaseType) {
			case DatabaseType.Oracle:
				db = new OracleDB();
				break;
			case DatabaseType.H2:
				db = new H2DB();
				break;
			default:
				throw new RuntimeException("Invalid DatabaseType. DatabaseType=$databaseType")
				break;
		}
		return db;
	}

	def DB create() {
		return create(JdbcConfigFactory.instance.databaseType)
	}
}
