package com.mrhosek.md4th;

import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.StringUtils;

public class MarkdownAttrProcessor extends
		AbstractAttributeTagProcessor {

	private static final int PRECEDENCE = 12000;

	public MarkdownAttrProcessor(final String dialectPrefix) {

		super(
				TemplateMode.HTML,
				dialectPrefix,
				null,
				false,
				"text",
				true,
				PRECEDENCE,
				true
		);
	}

	@Override
	protected void doProcess(ITemplateContext context,
							 IProcessableElementTag tag,
							 AttributeName attributeName,
							 String attributeValue,
							 IElementTagStructureHandler structureHandler) {
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		final String result = (String) expression.execute(context);
		final String text = result == null ? "" : StringUtils.escapeXml(result);

		try {
			structureHandler.setBody(new Markdown4jProcessor().process(text), false);
		} catch (IOException e) {
			structureHandler.setBody("", false);
		}
	}
}
