/**
 *
 */
package tools.io.file

import groovy.util.logging.Log4j

import java.io.IOException;
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * ファイルの文字コードを変換するためのFileVisitorクラス。
 *
 */
@Log4j
class SubFolderFileVisitor extends SimpleFileVisitor<Path> {

	//	private String folderName

	//	/* (非 Javadoc)
	//	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	//	 */
	//	@Override
	//	public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
	////		log.debug "#visitFile(): $filePath"
	////		def folderName = 'Ｆシ部対応'
	//		def file = filePath.toFile()
	//		FileVisitResult result = FileVisitResult.CONTINUE
	//
	//		if (!(file.parentFile.name ==~ /201\d年度/)) {
	//			result = FileVisitResult.SKIP_SUBTREE
	//		} else if (file.parentFile.name == 'Ｆシ部対応') {
	//			result = FileVisitResult.SKIP_SUBTREE
	//			println file.path
	//		}
	//
	//		return result
	//	}

	/* (非 Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

		def file = dir.toFile()
		FileVisitResult result = FileVisitResult.CONTINUE

		def fPattern = /Ｆシ部?対応/

		if (file.path =~ /20\d{2}年度/ ) {
			if (file.path =~ /201\d年度/) {
				if (file.name ==~ /201\d年度/ || file.name ==~ fPattern) {
					log.debug "SKIP1 :${file.path}"
				} else if (file.path =~ fPattern) {
					if (file.parentFile.name ==~ fPattern) {
						log.info file.path
					} else {
						log.debug "SKIP_SUBTREE3 :${file.path}"
						result = FileVisitResult.SKIP_SUBTREE
					}
				} else {
					log.debug "SKIP_SUBTREE2 :${file.path}"
					result = FileVisitResult.SKIP_SUBTREE
				}
			} else {
				log.debug "SKIP_SUBTREE1 :${file.path}"
				result = FileVisitResult.SKIP_SUBTREE
			}

//			def path = file.path.replaceAll('\\\\', '/')
//			def path = file.path
//			if ( file.parentFile.name == 'Ｆシ部対応' || file.parentFile.parentFile.name ==~ /201\d年度/) {
//				println file.path
//			} else {
//				println "SKIP1 :${file.path}"
//				result = FileVisitResult.SKIP_SUBTREE
//			}
//
//			if ( file.name ==~ /20\d{2}年度/ ) {
//				if  (!(file.name ==~ /201\d年度/)) {
//					println "SKIP1 :${file.path}"
//					result = FileVisitResult.SKIP_SUBTREE
//				}
//			} else if (file.parentFile.name ==~ /20\d{2}年度/) {
//				if (file.name != 'Ｆシ部対応') {
//					println "SKIP2 :${file.path}"
//					result = FileVisitResult.SKIP_SUBTREE
//				}
//			} else if (file.parentFile.parentFile.name ==~ /20\d{2}年度/) {
//			} else {
//				println "SKIP3 :${file.path}"
//				result = FileVisitResult.SKIP_SUBTREE
//			}
		} else {
			log.debug "ROOT :${file.path}"
		}

		return result
	}

	/* (非 Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#postVisitDirectory(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		if (exc) log.error exc.message, exc

		return super.postVisitDirectory(dir, exc);
	}

	/* (非 Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		if (exc) log.error exc.message, exc

		return super.visitFileFailed(file, exc)
	}
}
