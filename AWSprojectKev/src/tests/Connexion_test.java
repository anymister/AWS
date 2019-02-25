package tests;

import java.util.List;
import java.util.Scanner;

import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;

public class Connexion_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "";
		String mail = "";
		Read_xml_file read = new Read_xml_file();

		System.out.println("Enter your mail please\n");
		Scanner sc2 = new Scanner(System.in);
		mail = sc2.nextLine();

		System.out.println("Enter your password please\n");
		Scanner sc1 = new Scanner(System.in);
		password = sc1.nextLine();

		int i = 0;

		List<Item> readConfig = read.readConfig("src/data/Clients.xml");
		for (Item id : readConfig) {

			if ((id.getPassword().contains(password)) && id.getMail().contains(mail)) {
				System.out.println("Your are connected your id is :" + id.getId());
				i = 1;
			}
		}
		if (i != 1) {
			System.out.println("Password or email incorrect");
		}
	}
}
