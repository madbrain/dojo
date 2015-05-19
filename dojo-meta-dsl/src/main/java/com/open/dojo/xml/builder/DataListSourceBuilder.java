package com.open.dojo.xml.builder;

public interface DataListSourceBuilder {

    /**
     * Create an index from the current data list on the specified key.
     * 
     * @param key
     * @return
     */
    DataMapSourceBuilder createIndex(String key);

}