/**
 *
 */
package tools.io.file;

import static org.junit.Assert.*

import java.nio.file.FileSystems
import java.nio.file.Files

import org.junit.After
import org.junit.Before
import org.junit.Test

import tools.io.file.EndoceFileVisitor;

/**
 *
 *
 */
class TestEndoceFileVisitor {

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
	 * {@link jp.co.melco.mei.fa.tools.io.file.EndoceFileVisitor#visitFile(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes)} のためのテスト・メソッド。
	 */
	@Test
	public void testVisitFilePathBasicFileAttributes() {
		def dir = /C:\pleiades\workspace\fdocgenTools\scripts/

		def path = FileSystems.default.getPath(dir)
//		Files.walkFileTree(path, new EndoceFileVisitor(encoding : 'UTF-8'))
		Files.walkFileTree(path, new EndoceFileVisitor(encoding : 'Shift_JIS'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.io.file.EndoceFileVisitor#visitFileFailed(java.nio.file.Path, java.io.IOException)} のためのテスト・メソッド。
	 */
	@Test
	public void testVisitFileFailedPathIOException() {
		fail("まだ実装されていません");
	}
}
