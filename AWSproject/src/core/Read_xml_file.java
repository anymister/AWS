package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


import de.vogella.xml.stax.reader.Item;

public class Read_xml_file {
	
	
	    static final String ITEM = "id";
	    static final String id = "id";
	    static final String name = "name";
	    static final String email = "mail";
	    static final String password = "password";
	   

	    @SuppressWarnings({ "unchecked", "null" })
	    public List<Item> readConfig(String configFile) {
	        List<Item> items = new ArrayList<Item>();
	        try {
	            // First, create a new XMLInputFactory
	            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	            // Setup a new eventReader
	            InputStream in = new FileInputStream(configFile);
	            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	            // read the XML document
	            Item item = null;

	            while (eventReader.hasNext() && eventReader.nextEvent()!=null) {
	            	
	                XMLEvent event = eventReader.nextEvent();

	                if (event.isStartElement()) {
	                    StartElement startElement = event.asStartElement();
	                    // If we have an item element, we create a new item
	                    if (startElement.getName().getLocalPart().equals(ITEM)) {
	                        item = new Item();
	                        // We read the attributes from this tag and add the date
	                        // attribute to our object
	                        Iterator<Attribute> attributes = startElement
	                                .getAttributes();
	                        while (attributes.hasNext()) {
	                            Attribute attribute = attributes.next();
	                            if (attribute.getName().toString().equals(id)) {
	                                item.setId(attribute.getValue());
	                            }

	                        }
	                    }

	                    if (event.isStartElement()) {
	                        if (event.asStartElement().getName().getLocalPart()
	                                .equals(name)) {
	                            event = eventReader.nextEvent();
	                           // System.out.println(event.asCharacters().getData());
	                        //   item.setName(event.asCharacters().getData());
	                            continue;
	                        }
	                    }
	                    if (event.asStartElement().getName().getLocalPart()
	                            .equals(email)) {
	                        event = eventReader.nextEvent();
	                        item.setMail(event.asCharacters().getData());
	                        continue;
	                    }

	                    if (event.asStartElement().getName().getLocalPart()
	                            .equals(password)) {
	                        event = eventReader.nextEvent();
	                        item.setPassword(event.asCharacters().getData());
	                        continue;
	                    }

	                   
	                }
	                // If we reach the end of an item element, we add it to the list
	                if (event.isEndElement()) {
	                    EndElement endElement = event.asEndElement();
	                    if (endElement.getName().getLocalPart().equals(ITEM)) {
	                        items.add(item);
	                    }
	                }

	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	        return items;
	    }

	
	
}