/**
 *
 */
package tools.util

import java.io.File
import java.sql.Types

import org.codehaus.groovy.tools.Utilities

/**
 *
 *
 */
class SqlLogUtils {

	private SqlLogUtils() {
	}

	static final File cacheDir = new File(/.\cache/)
	static final File cacheLogFile = new File(cacheDir, 'sqlLog.txt')

	static String loadLogFile() {
		return cacheLogFile.text
	}

	static void storeLogFile(String log) {
		if (!cacheLogFile.parentFile.exists()) {
			cacheLogFile.parentFile.mkdirs();
		}
		cacheLogFile.withWriter { Writer writer ->
			writer.write(log);
		}
	}

	static boolean existsLogFile() {
		return cacheLogFile.exists();
	}

	static String expand(String value, int tabStop) {
		return value?.expand(tabStop)
	}

	static String expand(String value) {
		return value?.expand()
	}
}
