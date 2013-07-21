import java.nio.file.FileSystems
import java.nio.file.Files

import jp.co.melco.mei.fa.tools.io.file.SubFolderFileVisitor

def dir = /\\meiws048\Kaihatsu\プロジェクト/

def path = FileSystems.default.getPath(dir)
Files.walkFileTree(path, new SubFolderFileVisitor())

println 'END'