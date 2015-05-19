package com.open.dojo.xml.builder;

public interface MapBuilder extends DataListSourceBuilder {

    /**
     * Start the definition of the mapping of the specified field
     * 
     * @param key
     * @return
     */
    FieldMappingBuilder field(String fieldName);

}