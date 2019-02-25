package core;

import java.util.List;

import de.vogella.xml.stax.reader.Item;

public class Id_generat {
	private String id;
	
	public Id_generat(String id) {
		this.id=id;
		
	}
	//read the client file and return the last id of the last client
	public void read_last_id()
	{
		
		 Read_xml_file read = new Read_xml_file();
	       
		 String i="";
	        List<Item> readConfig = read.readConfig("src/data/Clients.xml");
	        for (Item item : readConfig) {
	         //  System.out.println(item.getId());
	           i=item.getId();
	        }
            setId(i);
   }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
