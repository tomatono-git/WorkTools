import java.nio.file.FileSystems
import java.nio.file.Files

import jp.co.melco.mei.fa.tools.io.file.EndoceFileVisitor

def dir = /E:\pleiades\4.3\workspace\WorkTools\src/

def path = FileSystems.default.getPath(dir)
Files.walkFileTree(path, new EndoceFileVisitor())

println 'END'


