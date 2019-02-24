package tests;

import core.Id_generat;

public class GeneratId_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int id;
		 Id_generat n=new Id_generat(1);
		 //read the id of the last client
	       n.read_last_id();
	       // id of the last client +1 
	       id=n.getId();
	    // Print the id of the last client +1 
	     
	       System.out.println(id);
	}

}
