package com.open.dojo.xml.builder;

public interface FieldMappingElementBuilder {

    /**
     * The current field will be mapped to the text of the current element.
     * 
     * @param tagName
     * @return
     */
    MapBuilder text();

}