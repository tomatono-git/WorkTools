/**
 *
 */
package tools.db.loader;

import static org.junit.Assert.*
import groovy.util.logging.Log4j

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import tools.db.bean.Table
import tools.db.bean.TargetToExtract
import tools.db.loader.oracle.OracleTableConfigLoader

/**
 *
 *
 */
@Log4j
class TestOracleTableConfigLoader {

	private OracleTableConfigLoader loader;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		TargetToExtract extract = new TargetToExtract(tablePrefix : "EFR_", isIncludeView: true)
//		PropertyResourceBundle bundle = PropertyResourceBundle.getBundle('log4j')
//		bundle.properties
//		def config = new ConfigSlurper().parse(props)
//		props = config.toProperties()
		loader = new OracleTableConfigLoader(new TargetToExtract(tablePrefix : "EFR_", isIncludeView: true))
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#getFindTableNamesQuery(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetFindTableNamesQuery() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#getFindViewNamesQuery(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetFindViewNamesQuery() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#createDB()} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testCreateDB() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#OracleTableConfigLoader(java.lang.String, java.lang.String, java.lang.String, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testOracleTableConfigLoaderStringStringStringString() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#OracleTableConfigLoader(jp.co.melco.mei.fa.tools.db.bean.TargetToExtract)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testOracleTableConfigLoaderTargetToExtract() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#load()} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testLoadMap() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#load()} のためのテスト・メソッド。
	 */
	@Test
	public void testLoad() {
		assert loader
		log.debug 'loader.load()'
		def result = loader.load()
		assert result.size()

//		assert log.debugEnabled

		def logger = log
		result.each { Table table ->
			assert table.name
			assert table.logicalName
			assert table.columnList
			assert table.columnMap
//			assert table.pkList
//			assert table.remarks

			def message = """
テーブル物理名：${table.name}
テーブル論理名：${table.logicalName}
列（一覧）：${table.columnList}
列（Map）：${table.columnMap}
主キー：${table.pkList}
備考：${table.remarks}
"""

//			assert logger.debugEnabled
			logger.debug(message)
		}
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableComments(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetTableComments() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getColumnComment(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetColumnComment() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#setAdditionalOption(jp.co.melco.mei.fa.tools.db.bean.Table)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testSetAdditionalOption() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringStringStringBoolean() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringStringBoolean() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringBoolean() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringStringStringString() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringStringString() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringString() {
		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getPkList(java.lang.String, java.lang.String, java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testGetPkList() {
		fail("まだ実装されていません");
	}

}
