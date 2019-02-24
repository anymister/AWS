package tests;

import java.util.Scanner;

import core.Id_generat;
import core.Inscription;

public class Inscription_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mail, password, name;

		System.out.println("Enter your name please \n");
		Scanner sc0 = new Scanner(System.in);
		name = sc0.nextLine();

		System.out.println("Enter your mail adresse please \n");
		Scanner sc = new Scanner(System.in);
		mail = sc.nextLine();

		System.out.println("Enter your password please\n");
		Scanner sc1 = new Scanner(System.in);
		password = sc1.nextLine();
		String id;
		Id_generat n = new Id_generat("");
		// read the id of the last client
		n.read_last_id();
		// id of the last client +1
		id = n.getId();
		// Print the id of the last client +1
		int new_id = Integer.parseInt(id) + 1;
		Inscription ins = new Inscription(new_id);
		if (ins.validatename(name)) {
			if (ins.validate(mail)) {
				if (ins.validatepass(password)) {
					try {

						ins.copier();
						ins.setFile("src/data/Clients.xml");
						ins.inscription(name, mail, password);
						ins.copier_plus();
						ins.colle();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else
					System.out.println("Your password is incorrect. - 4 caracteres minimum and no special caracteres\n");
			} else
				System.out.println("Your mail is incorrect \n");

		} else
			System.out.println("Your name is incorrect \n");

	}

}
