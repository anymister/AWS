package tests;

import java.util.Scanner;

import core.Inscription;

public class Inscription_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mail,password,name;
		
		System.out.println("Enter your name please \n");
		Scanner sc0=new Scanner(System.in);
		name=sc0.nextLine();
		
		System.out.println("Enter your mail adresse please \n");
		Scanner sc=new Scanner(System.in);
		mail=sc.nextLine();
		
		System.out.println("Enter your password please\n");
		Scanner sc1=new Scanner(System.in);
		password=sc1.nextLine();
		
		Inscription ins=new Inscription();
		try {
		ins.copier();
			ins.setFile("src/data/Clients.xml");
			ins.inscription(name,mail,password);
			ins.copier_plus();
			ins.colle();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
