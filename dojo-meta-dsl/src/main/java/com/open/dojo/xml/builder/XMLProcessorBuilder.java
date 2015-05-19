package com.open.dojo.xml.builder;

import org.w3c.dom.Element;

public class XMLProcessorBuilder implements XMLSourceBuilder {

    @Override
    public ElementSourceBuilder from(final Element element) {
        throw new RuntimeException("not implemented");
    }

}