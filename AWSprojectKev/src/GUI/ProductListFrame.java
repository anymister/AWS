package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.Produits;
import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;

public class ProductListFrame implements ActionListener {
	JList listFrame;
	JLabel productLabel;
	JFrame frame;
	JLabel listProductLabel[] = new JLabel[100];
	String[] listProduct = new String[100];
JButton[] b=new JButton [100];
JLabel[] lab=new JLabel[100];
JButton deconnexion;

	public ProductListFrame() {

		frame = new JFrame("Product list");
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deconnexion =new JButton("Déconnexion");
		deconnexion.addActionListener(this);

		JPanel panel = new JPanel();
		
		placeComponents(panel);
		panel.add(deconnexion);
		frame.add(panel);
		frame.setVisible(true);
	}

//kk@kk.com
	private void placeComponents(JPanel panel) {
		// TODO Auto-generated method stub
		Produits read = new Produits();
JLabel l=new JLabel("Les produits :");
panel.add(l);
		int i = 0;
		List<Item> readConfig = read.readConfig("src/data/Produits.xml");
		for (Item id : readConfig) {

			listProduct[i] = "Nom :"+id.getName()+"...Prix :"+id.getPrix();
b[i]=new JButton("Ajouter au panier");
b[i].setName(id.getId());
lab[i]=new  JLabel(listProduct[i]);
b[i].addActionListener(this);
panel.add(b[i]);
panel.add(lab[i]);
			i++;
		}
	/*	JList jList = new JList(listProduct);
		JList jList2 = new JList(b);
		panel.add(jList);
		panel.add(jList2);*/
		JLabel l1=new JLabel("Pour acheter il vous suffit de cliquez sur le botton à coté du produit :)");
		panel.add(l1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int j=0;
		while (b[j]!=null) {
			
			if((JButton)e.getSource()==b[j]) {
				
		new Produit(b[j].getName());
		frame.dispose();
		System.out.println(b[j].getName());
			}
		j++;
		}
    
		if((JButton)e.getSource() == deconnexion) {
		
			try {
				BufferedWriter b=new BufferedWriter(new FileWriter(new File("src/data/session.txt")));
				b.write("123456789");
				b.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new LoginForm();
			frame.dispose();
		}
	}

	
}
