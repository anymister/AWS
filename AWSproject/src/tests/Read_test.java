package tests;
import java.util.List;


import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;

public class Read_test {
    public static void main(String args[]) {
        Read_xml_file read = new Read_xml_file();
      
     
        List<Item> readConfig = read.readConfig("src/data/Clients.xml");
        for (Item id : readConfig) {
            System.out.println(id);
        }
        
    }
}