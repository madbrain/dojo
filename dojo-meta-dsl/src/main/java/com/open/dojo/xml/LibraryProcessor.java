package com.open.dojo.xml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LibraryProcessor {

    public static void main(String[] args) {
        new LibraryProcessor().run("books.xml", "sales.xml", "stats.xml");
    }

    public static class Book {

        private String id;
        private String title;
        private String genre;

        public Book(String id, String title, String genre) {
            this.id = id;
            this.title = title;
            this.genre = genre;
        }

        public String getId() {
            return id;
        }

    }

    public void run(String booksFile, String salesFile, String statsFile) {

        try {
            run(new FileInputStream(booksFile), new FileInputStream(salesFile),
                    new FileWriter(statsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(InputStream booksFile, InputStream salesFile, Writer writer) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document booksDoc = builder.parse(booksFile);
            Map<String, Book> books = parseBooks(booksDoc.getDocumentElement());

            Document salesDoc = builder.parse(salesFile);
            Document statDoc = parseSales(salesDoc.getDocumentElement(), books, builder);
            outputXML(writer, statDoc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void outputXML(Writer writer, Document doc) {
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Book> parseBooks(Element catalogElement) {

        // refactor all this class to describe the computation
        // with a DSL like the following example
        // XMLProcessorBuilder builder = new XMLProcessorBuilder();
        //
        // DataMapSource cmd = builder.from(catalogElement).select("book").map()
        // .field("id").attr("id")
        // .field("title").selectUnique("title").text()
        // .field("genre").selectUnique("genre").text()
        // .createIndex("id")
        // .build();
        //
        // return cmd.getDataMap();

        Map<String, Book> books = new HashMap<>();
        NodeList children = catalogElement.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE
                    && ((Element) n).getTagName().equals("book")) {
                parseBook((Element) n, books);
            }
        }
        return books;
    }

    private void parseBook(Element element, Map<String, Book> books) {
        String id = element.getAttribute("id");
        NodeList children = element.getChildNodes();
        String title = null;
        String genre = null;
        for (int i = 0; i < children.getLength(); ++i) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) n;
                if (child.getTagName().equals("title")) {
                    title = child.getTextContent();
                } else if (child.getTagName().equals("genre")) {
                    genre = child.getTextContent();
                }
            }
        }
        books.put(id, new Book(id, title, genre));
    }

    private Document parseSales(Element salesElement, Map<String, Book> books, DocumentBuilder builder) {
        Map<Book, Integer> result = new HashMap<>();
        NodeList children = salesElement.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE
                    && ((Element) n).getTagName().equals("sale")) {
                Book book = books.get(((Element) n).getAttribute("bookId"));
                Integer value = result.get(book);
                if (value == null) {
                    value = 1;
                } else {
                    ++value;
                }
                result.put(book, value);
            }
        }
        Document doc = builder.newDocument();
        Element root = doc.createElement("book-stats");
        doc.appendChild(root);
        for (Book book : result.keySet()) {
            Element bookStat = doc.createElement("book-stat");
            root.appendChild(bookStat);
            bookStat.setAttribute("title", book.title);
            bookStat.setAttribute("count", String.valueOf(result.get(book)));
        }
        return doc;
    }
}
