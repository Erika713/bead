package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import higherorlower.*;


/**
 *XML dokumentumot feldolgozó osztály.
 */
public class XMLDomProcess {
	
	/**Egy XML fájlt dolgozz fel, ami egy egy pakli kártyát tartalmaz.
	 * @param xmlFileName az XML fájl neve
	 * @return egy kártyalapokat nyílvántartó lista
	 */
	public static List<Card> process(String xmlFileName){
		
		List<Card> cards = new ArrayList<Card>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(XMLDomProcess.class.getResourceAsStream(xmlFileName));
			
			NodeList nl = doc.getElementsByTagName("card");
			
			for (int i = 0; i < nl.getLength(); i++) {
				
				Element e = (Element) nl.item(i);
				
				int value = new Integer(e.getElementsByTagName("value").item(0).getTextContent());
				String path = e.getElementsByTagName("path").item(0).getTextContent();
				
				Card c = new Card(value, new ImageIcon(XMLDomProcess.class.getResource(path)));
				cards.add(c);
				
			}		
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return cards;
		
		
	}
	
}
