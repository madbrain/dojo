package com.open.dojo.xml.builder;

import org.w3c.dom.Element;

public interface XMLSourceBuilder {

    /**
     * Create an ElementSource from an existing Element
     * 
     * @param element
     * @return
     */
    ElementSourceBuilder from(Element element);

}
