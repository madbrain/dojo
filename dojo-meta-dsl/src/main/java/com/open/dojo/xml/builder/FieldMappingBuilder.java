package com.open.dojo.xml.builder;

public interface FieldMappingBuilder {

    /**
     * The current field will be mapped to the specified attribute <code>attrName</code> of the current element.
     * 
     * @param attrName
     * @return
     */
    MapBuilder attr(String attrName);

    /**
     * the current field will be mapped to the unique child <code>tagName</code> of the current element.
     * 
     * @param tagName
     * @return
     */
    FieldMappingElementBuilder selectUnique(String tagName);

}