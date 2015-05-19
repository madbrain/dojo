package com.open.dojo.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LibraryProcessorTest {

    @Test
    public void testProcess() throws SAXException, IOException {
        LibraryProcessor processor = new LibraryProcessor();
        StringWriter statsWriter = new StringWriter();
        processor.run(getClass().getResourceAsStream("/books.xml"), getClass().getResourceAsStream("/sales.xml"),
                statsWriter);
        XMLAssert.assertXMLEqual(new InputSource(getClass().getResourceAsStream("/stats-ref.xml")),
                new InputSource(new StringReader(statsWriter.toString())));
    }
}
