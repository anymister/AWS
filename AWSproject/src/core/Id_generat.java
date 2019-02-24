package core;

import java.util.List;

import de.vogella.xml.stax.reader.Item;

public class Id_generat {
	private int id;
	
	public Id_generat(int id) {
		this.id=id;
	}
	//read the client file and return the last id of the last client
	public void read_last_id()
	{
		 Read_xml_file read = new Read_xml_file();
	       
	     int i=0;
	        List<Item> readConfig = read.readConfig("src/data/Clients.xml");
	        for (Item item : readConfig) {
	           // System.out.println(item.getId());
	        	i=Integer.parseInt(item.getId());
	        	
	        }
            setId(i);
            

	}
	public int getId() {
		return id+1;
	}
	public void setId(int id) {
		this.id = id;
	}
}
