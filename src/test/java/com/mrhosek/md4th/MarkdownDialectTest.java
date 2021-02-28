package com.mrhosek.md4th;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


import static com.google.common.truth.Truth.assertThat;

public class MarkdownDialectTest {

		
		@Test
		public void testDialect()  {
			final TemplateEngine engine = initTemplateEngine();
			
			final Writer writer = new StringWriter();
			
			final Context context = new Context();
			context.setVariable("test", "*this*");
			
			engine.process("MarkdownDialectTest.html",  context, writer);
			String output = writer.toString();
			
			assertThat(output).contains("<em>this</em>");
		}
		
		@Test 
		public void testEscapeHTMLInMarkDown() {
			final TemplateEngine engine = initTemplateEngine();
			
			final Writer writer = new StringWriter();
			
			final Context context = new Context();
			context.setVariable("test", "<escapeme />");
			
			engine.process("MarkdownDialectTest.html",  context, writer);
			String output = writer.toString();
			
			assertThat(output).contains("&lt;escapeme /&gt;");
			
		}
		
		private static TemplateEngine initTemplateEngine() {
	        
	        final TemplateEngine engine = new TemplateEngine();
	        
	        final MarkdownDialect markdownDialect = new MarkdownDialect();
	        engine.addDialect(markdownDialect);
	        
	        final ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
	        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
	        engine.setTemplateResolver(classLoaderTemplateResolver);
	       
	        return engine;
	        
	    }
}
