/**
 *
 */
package tools.io.file

import groovy.util.logging.Log4j;

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * ファイルの文字コードを変換するためのFileVisitorクラス。
 *
 */
@Log4j
class EndoceFileVisitor extends SimpleFileVisitor<Path> {

	/** 変換する文字コード */
	def String encoding = 'UTF-8'

	/* (非 Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
		log.debug "#visitFile(): $filePath"

		def file = filePath.toFile()
		file.write(file.text, encoding)

		return FileVisitResult.CONTINUE
	}

	/* (非 Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		log.error exc.message, exc

		return FileVisitResult.CONTINUE;
	}
}
