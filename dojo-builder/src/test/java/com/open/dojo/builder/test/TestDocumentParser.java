package com.open.dojo.builder.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.open.dojo.builder.XMLCatalogueBuilder;

public class TestDocumentParser {

	@Test
	public void testDocumentParser() throws ParserConfigurationException, IOException, SAXException {
		
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = documentBuilder.newDocument();
		Element catalogueElement = doc.createElement("catalogue");
		doc.appendChild(catalogueElement);
		catalogueElement.setAttribute("periode", "printemps/été");
		Element article1 = doc.createElement("article");
		catalogueElement.appendChild(article1);
		article1.setAttribute("nom", "Collier pour chien");
		article1.setAttribute("prix", "10");
		article1.setAttribute("reference", "CH1");
		
		Element article2 = doc.createElement("article");
		catalogueElement.appendChild(article2);
		article2.setAttribute("nom", "Antipuce");
		
		Element model1 = doc.createElement("modele");
		article2.appendChild(model1);
		model1.setAttribute("descriptif", "10ml");
		model1.setAttribute("prix", "20");
		model1.setAttribute("reference", "CH2A");
		
		Element model2 = doc.createElement("modele");
		article2.appendChild(model2);
		model2.setAttribute("descriptif", "20ml");
		model2.setAttribute("prix", "35");
		model2.setAttribute("reference", "CH2B");
		
		Element article3 = doc.createElement("article");
		catalogueElement.appendChild(article3);
		article3.setAttribute("nom", "Nonosse");
		article3.setAttribute("prix", "5");
		article3.setAttribute("reference", "CH3");
		
//		CatalogueParser parser = new CatalogueParser();
//		Catalogue catalogue = parser.parse(doc);
		
		XMLUnit.setIgnoreWhitespace(true);
		XMLAssert.assertXMLEqual(XMLUnit.buildControlDocument(
				new InputSource(getClass().getResourceAsStream("/test-catalogue-1.xml"))),
				doc);
		
//		Assert.assertNotNull(catalogue);
//		Assert.assertEquals(Periode.PrintempsEte, catalogue.getPeriode());
//		Assert.assertEquals(3, catalogue.getArticles().size());
//		Assert.assertEquals("Collier pour chien", catalogue.getArticles().get(0).getName());
//		Assert.assertEquals(1, catalogue.getArticles().get(0).getModels().size());
//		Assert.assertEquals("CH1", catalogue.getArticles().get(0).getModels().get(0).getReference());
//		Assert.assertEquals(2, catalogue.getArticles().get(1).getModels().size());
//		Assert.assertEquals("CH2A", catalogue.getArticles().get(1).getModels().get(0).getReference());
	}
	
	@Test
	public void testDocumentParserWithBuilder() throws ParserConfigurationException, IOException, SAXException {
		
		Document xmlCatalogueDocument = new XMLCatalogueBuilder()
			.newArticle("Collier pour chien", 5, "CH1")
			.newArticle("Antipuce")
			.newModele("10ml", 20, "CH2A")
			.newModele("20ml", 35, "CH2B")
			.newArticle("Nonosse", 5, "CH3")
			.build();
		
//		CatalogueParser parser = new CatalogueParser();
//		Catalogue catalogue = parser.parse(xmlCatalogueDocument);
		
		XMLUnit.setIgnoreWhitespace(true);
		XMLAssert.assertXMLEqual(XMLUnit.buildControlDocument(
				new InputSource(getClass().getResourceAsStream("/test-catalogue-1.xml"))),
				xmlCatalogueDocument);
		
//		Assert.assertNotNull(catalogue);
//		Assert.assertEquals(Periode.PrintempsEte, catalogue.getPeriode());
//		Assert.assertEquals(3, catalogue.getArticles().size());
//		Assert.assertEquals("Collier pour chien", catalogue.getArticles().get(0).getName());
//		Assert.assertEquals(1, catalogue.getArticles().get(0).getModels().size());
//		Assert.assertEquals("CH1", catalogue.getArticles().get(0).getModels().get(0).getReference());
//		Assert.assertEquals(2, catalogue.getArticles().get(1).getModels().size());
//		Assert.assertEquals("CH2A", catalogue.getArticles().get(1).getModels().get(0).getReference());
	}
}
