/**
 *
 */
package tools.db.convertor;

import static org.junit.Assert.*
import groovy.util.logging.Log4j

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import tools.db.bean.TargetToExtract
import tools.db.loader.oracle.OracleTableConfigLoader

/**
 *
 *
 */
@Log4j
class TestTableObjectConvertor {

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

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.convertor.TableObjectConvertor#convertToCreateQuery(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	@Ignore
	public void testConvertToCreateQuery() {
		OracleTableConfigLoader loader = new OracleTableConfigLoader(new TargetToExtract(tablePrefix : /EFR_/, isIncludeView: true, denyPattern: /_V$/))
		def tableList = loader.load()
		def query = TableObjectConvertor.convertToCreateQuery(tableList)

		log.debug query
	}

//	@Test
//	public void testTest() {
//		OracleTableConfigLoader loader = new OracleTableConfigLoader(new TargetToExtract(tablePrefix : /EFR_/, isIncludeView: true, denyPattern: /_V$/))
//		def tableList = loader.load()
//		def query = TableObjectConvertor.testTmpl(tableList)
//
//		log.debug query
//	}

}
