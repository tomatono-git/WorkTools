/**
 *
 */
package tools.util

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine
import groovy.text.TemplateEngine;

import java.io.File;
import java.util.Map;

/**
 *
 *
 */
class TemplateUtils {

	def static Writable bindTemplate(File tmplFile, Map binding) {
		TemplateEngine engine = new SimpleTemplateEngine()
		def template = engine.createTemplate(tmplFile)
		def writable = template.make(binding)
		return writable
	}

	def static void write(File tmplFile, Map binding, File out) {
		def writable = TemplateUtils.bindTemplate(tmplFile, binding)
		assert out.parentFile.exists()
		out.withWriter { writer ->
			writable.writeTo(writer)
		}
	}

	def static String writeToString(File tmplFile, Map binding) {
		def writable = TemplateUtils.bindTemplate(tmplFile, binding)
		def writer = new StringWriter()
		writable.writeTo(writer)
		return writer.toString()
	}


	def static String make(String tmplText, Map binding) {
		TemplateEngine engine = new SimpleTemplateEngine()
		def template = engine.createTemplate(tmplText)
		Writable writable = template.make(binding)
        def writer = new StringWriter()
		writable.writeTo(writer)
		def result = writer.toString()

		return result
	}

}
