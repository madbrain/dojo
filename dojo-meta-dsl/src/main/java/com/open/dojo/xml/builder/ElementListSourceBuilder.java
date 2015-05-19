package com.open.dojo.xml.builder;

public interface ElementListSourceBuilder {

    /**
     * Start the definition of a transformation from current Element list to a Data list.
     * 
     * @param tagName
     * @return
     */
    MapBuilder map();

}