/**
 *
 */
package tools.resources;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.resources.PropertiesSupport;

/**
 *
 *
 */
class TestPropertiesSupport {

	def Test_DumyProperty prop = new Test_DumyProperty()

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
	 * {@link jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundleName()} のためのテスト・メソッド。
	 */
	@Test
	public void testGetBundleName() {
		def bundleName = prop.bundleName
		assert bundleName && bundleName == 'dummy'
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundle()} のためのテスト・メソッド。
	 */
	@Test
	public void testGetBundle() {
		assert prop.bundle && ( prop.bundle.is(prop.getBundle()) )
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.resources.PropertiesSupport#get(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testGet() {
		assert prop.get('test.dummy.a') == 'A'
		assert prop.get('test.dummy.b') == 'B'
		assert prop.get('test.dummy.c') == 'C'
		assert prop.get('test.dummy.string') == 'String'
		assert prop.get('test.dummy.string.empty') == ''
	}

	@Test
	public void testStringArray() {
		assert prop.getStringArray('test.dummy.StringArray') == ['val1', 'val2', 'val3'].toArray()

		String[] empty = prop.getStringArray('test.dummy.StringArray.empty')
		assert empty?.size() == 0 && empty == [].toArray()

		String[] withBlank = prop.getStringArray('test.dummy.StringArray.withblank')
		assert withBlank == [
			'',
			'val1',
			'val2',
			'val3'
		].toArray()
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getInt(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testGetInt() {
		assert prop.getInt('test.dummy.int') == 123456789
		assert prop.propInt == 123456789
		assert prop.getInt('test.dummy.int.empty') == 0
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getInt(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testDateInt() {
		Date date = prop.getDate('test.dummy.date')
		assert date && date == Date.parse('yyyy/MM/dd', '2013/06/24')
		assert prop.getDate('test.dummy.date.empty') == null
	}
}

class Test_DumyProperty extends PropertiesSupport {

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundleName()
	 */
	@Override
	protected String getBundleName() {
		return 'dummy';
	}

	int getPropInt() {
		return getInt('test.dummy.int')
	}
	String getPropString() {
		return get('test.dummy.string')
	}

	String getPropA() {
		return get('test.dummy.a')
	}
}
