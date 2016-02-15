package com.mrhosek.md4th;

import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractUnescapedTextChildModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.util.StringUtils;

public class MarkdownAttrProcessor extends
		AbstractUnescapedTextChildModifierAttrProcessor {
	
	protected MarkdownAttrProcessor() {
		super("text");
	}

	@Override
	public int getPrecedence() {
		return 12000;
	}

	@Override
	protected String getText(final Arguments arguments, final Element element, final String attributeName) {
        final Configuration configuration = arguments.getConfiguration();
        final String attributeValue = element.getAttributeValue(attributeName);
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);
        final Object result = expression.execute(configuration, arguments);
        final String text = result == null ? "" : StringUtils.escapeXml(result);
        
        
        try {
			return new Markdown4jProcessor().process(text);
		} catch (IOException e) {
			return null;
		}
	}

}
