/**
 *
 */
package tools.web.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.web.util.WebUtils;

/**
 *
 *
 */
class TestWebUtils {

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
//	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#WebUtils()} のためのテスト・メソッド。
//	 */
//	@Test
//	public void testWebUtils() {
//		fail("まだ実装されていません");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#toQueryString(java.util.Map, boolean, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testToQueryStringMapBooleanString() {
		def params = [:]
		params['denshi'] = '1'
		//		params['backdoc'] = null
		params['author'] = '大竹 一世　ＰＬＭ技２'
		params['createdate'] = new Date().format('yyyy/MM/dd')
		params['remark'] = 'グループ共通認証対応の試験データ'
		def result = WebUtils.toQueryString(params, true , 'Shift_JIS')

		def before = 'グループ共通認証対応の試験データ'
		def encodeVal = URLEncoder.encode(before, 'Shift_JIS')
		def decodeVal = URLDecoder.decode(encodeVal, 'Shift_JIS')

		assert before == decodeVal
		assert decodeVal == 'グループ共通認証対応の試験データ'

		def encodeVal2 = URLEncoder.encode(before, 'UTF-8')


		fail("まだ実装されていません");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#toQueryString(java.util.Map)} のためのテスト・メソッド。
	 */
	@Test
	public void testToQueryStringMap() {
		def params = [:]
		params['denshi'] = '1'
		//		params['backdoc'] = null
		params['author'] = '大竹 一世　ＰＬＭ技２'
		params['createdate'] = new Date().format('yyyy/MM/dd')
		params['remark'] = 'グループ共通認証対応の試験データ'
		def result = WebUtils.toQueryString(params)


		fail("まだ実装されていません");
	}


}
