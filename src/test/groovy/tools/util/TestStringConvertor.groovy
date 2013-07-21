/**
 *
 */
package tools.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 */
class TestStringConvertor {

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
//	 * {@link jp.co.melco.mei.fa.tools.util.StringConvertor#StringConvertor()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testStringConvertor() {
//		fail("まだ実装されていません");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.util.StringConvertor#linesToList(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testLinesToListStringBoolean() {
		def list = StringConvertor.linesToList('''
line1
line2
line3
''', false)

		//		assert list &&  list.size() == 5
		assert list &&  list.size() == 4

		assert list[0] == ''
		assert list[1] == 'line1'
		assert list[2] == 'line2'
		assert list[3] == 'line3'
		//		assert list[4] == ''
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.util.StringConvertor#linesToList(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testLinesToListString() {
		def list = StringConvertor.linesToList('''
line1
line2
line3
''')

		assert list &&  list.size() == 3
		assert list[0] == 'line1'
		assert list[1] == 'line2'
		assert list[2] == 'line3'
	}
}
