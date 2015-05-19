package com.open.dojo.xml.builder;

import com.open.dojo.xml.metamodel.DataMapSource;

public interface DataMapSourceBuilder {

    /**
     * Build the DataMapSource from the specification.
     * 
     * @return
     */
    DataMapSource build();

}