package com.open.dojo.xml.builder;

public interface ElementSourceBuilder {

    /**
     * Select all children elements with tag <code>tagName</code> of the current element.
     * 
     * @param tagName
     * @return
     */
    ElementListSourceBuilder select(String tagName);

}