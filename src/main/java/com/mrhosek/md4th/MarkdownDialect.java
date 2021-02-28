package com.mrhosek.md4th;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class MarkdownDialect implements IProcessorDialect {

	@Override
	public String getPrefix() {
		return "md";
	}

	@Override
	public int getDialectProcessorPrecedence() {
		return 12000;
	}


	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new MarkdownAttrProcessor(dialectPrefix));
        return processors;
	}


	@Override
	public String getName() {
		return "md4th";
	}
}
