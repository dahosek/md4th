package com.mrhosek.md4th;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class MarkdownDialect extends AbstractDialect {

	@Override
	public String getPrefix() {
		return "md";
	}

	@Override
	public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new MarkdownAttrProcessor());
        return processors;
	}

}
