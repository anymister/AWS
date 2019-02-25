package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.AcheterProduit;
import core.Produits;
import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;
public class Produit implements ActionListener {
	
	static JButton deconnexion = new JButton("Déconnexion");

private String idp;
private String id_client;

		JList listFrame;
		JLabel productLabel;
		JFrame frame;
		JLabel listProductLabel[] = new JLabel[100];
		String[] listProduct = new String[100];
private static	JButton b1;
private static	JButton b2;
	JTextField f;
		public Produit(String idp) {
this.idp=idp;
			frame = new JFrame("Product list");
			frame.setSize(1000, 500);
			frame.setLocationRelativeTo(frame);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();
b1=new JButton("Back");
b2=new JButton("Confirmer");
JLabel quantite=new JLabel("Quantité :");
f=new JTextField(10);
deconnexion.addActionListener(this);

			placeComponents(panel);
			b1.addActionListener(this);
			b2.addActionListener(this);
			panel.add(b1);
			panel.add(b2);
			panel.add(quantite);
			panel.add(f);
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
if(idp.contains(id.getId())) {
				listProduct[i] = "Nom :"+id.getName()+"...Marque :"+id.getMarque()+"...Description :"+id.getDescription()+"...Prix :"+id.getPrix();
}
				i++;
			}
			JList jList = new JList(listProduct);
			
			panel.add(jList);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BufferedReader b = null;
			try {
				b = new BufferedReader(new FileReader("src/data/session.txt"));
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				id_client = b.readLine();
				b.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				Object source = e.getSource();
				if (source == b2) {
					if(f.getText()!=null) {
			AcheterProduit ap=new AcheterProduit(0,0,0); 
			try {

				ap.copier();
				ap.setFile("src/data/Panier.xml");
				ap.acheter(f.getText(),id_client,idp);
				ap.copier_plus();
				ap.colle();
				JOptionPane.showMessageDialog(null, "La commande est passer avec succès ** Merci pour votre confiance :)", "Login",
						JOptionPane.INFORMATION_MESSAGE);
				new ProductListFrame();
				frame.dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else 
				JOptionPane.showMessageDialog(null, "Veuillez entrer une quantité de produit", "Login",
						JOptionPane.INFORMATION_MESSAGE);

			
		}else if(source == b1) {
			frame.dispose();
			new ProductListFrame();
		
			
		}else if(source == deconnexion) {
		
			try {
				BufferedWriter bi=new BufferedWriter(new FileWriter(new File("src/data/session.txt")));
				bi.write("123456789");
				bi.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new LoginForm();
			frame.dispose();
		}
				}
		
	}



