package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class AcheterProduit {
	private String ITEM = "item";
	private ArrayList<String> array;
	private String configFile = "Config2.xml";
	private Id_generat id_g;
	private int id;
	private int id_client;
	private int quantite;

	public AcheterProduit(int id,int id_client,int quantite) {
		array = new ArrayList();
		this.id_client=id_client;
		this.id = id;
this.quantite=quantite;
	}

	public void setFile(String configFile) {
		this.configFile = configFile;
	}

	public void acheter(String quantite,String id_client,String id) throws Exception {

		// create an XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(configFile));
		// create an EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();

		eventWriter.add(startDocument);

		// create config open tag
		StartElement configStartElement = eventFactory.createStartElement("", "", "config");
		eventWriter.add(configStartElement);
		eventWriter.add(end);

		// Print the id of the last client +1

		

		// Write the different nodes
		// createNode(eventWriter, "item", "");

		createNode(eventWriter, "id", "" + id);
		createNode(eventWriter, "id_client", "" + id_client);
		createNode(eventWriter, "quantite",""+ quantite);

		eventWriter.add(eventFactory.createEndElement("", "", "config"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	public void copier() {
		String line;
		array.clear();
		try {
			int n = 0;
			BufferedReader br = new BufferedReader(new FileReader(new File("src/data/Panier.xml")));
			try {
				while ((line = br.readLine()) != null) {

					array.add(line);
					n++;
					// System.out.println("read.."+line+"\n");
				}
				if (array.get(n - 1).contains("config")) {
					array.set(n - 1, "");
				} else if (array.get(n - 2).contains("config")) {
					array.set(n - 2, "");
				} else if (array.get(n - 3).contains("config")) {
					array.set(n - 3, "");
				}
				setArray(array);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void copier_plus() {
		String line;

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/data/Panier.xml")));
			try {
				array.add("\t<item>");
				while ((line = br.readLine()) != null) {

					if (line.contains("xml")) {

					} else if (line.contains("quantite")) {
						array.add("\t" + line);
						array.add("\t</item>");

					} else if (line.contains("config"))
						array.add(line);
					else
						array.add("\t" + line);
					// System.out.println("read.."+line+"\n");
				}
				// array.set(0, null);
				setArray(array);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void colle() {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter("src/data/Panier.xml"));

			for (int i = 0; i < array.size(); i++) {
				w.write(array.get(i) + "\n");
				System.out.println("write" + array.get(i) + "\n");
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}

	public ArrayList<String> getArray() {
		return array;
	}

	public void setArray(ArrayList<String> array) {
		this.array = array;
	}
}
