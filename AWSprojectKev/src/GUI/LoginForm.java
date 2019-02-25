package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;

public class LoginForm implements ActionListener {

	static JLabel userLabel = new JLabel("Mail User");
	static JTextField userText = new JTextField(20);
	static JLabel passwordLabel = new JLabel("Password");
	static JPasswordField passwordText = new JPasswordField(20);
	static JButton loginButton = new JButton("login");
	static JButton registerButton = new JButton("register");
	private String id_clien;
	String password = "";
	String mail = "";
	Read_xml_file read = new Read_xml_file();
	JFrame frame;

	public LoginForm() {
		BufferedReader b = null;
		try {
			b = new BufferedReader(new FileReader("src/data/session.txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			id_clien = b.readLine();
			b.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(id_clien);
		if(id_clien.contains("123456789")) {
		frame = new JFrame("Please login");
		frame.setSize(300, 150);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);

		frame.setVisible(true);
	}else {
		
		JOptionPane.showMessageDialog(null, "Your are already,"
				+ " connected your id is :" + id_clien, "Login",
				JOptionPane.INFORMATION_MESSAGE);

		new ProductListFrame();
	}
		}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);

		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == loginButton) {
			String mail = userText.getText();
			char[] pwd = passwordText.getPassword();
			String spwd = new String(pwd);

			int i = 0;

			List<Item> readConfig = read.readConfig("src/data/Clients.xml");
			for (Item id : readConfig) {

				if ((id.getPassword().contains(spwd)) && id.getMail().contains(mail)) {
					JOptionPane.showMessageDialog(null, "Your are connected your id is :" + id.getId(), "Login",
							JOptionPane.INFORMATION_MESSAGE);

					try {
						BufferedWriter b=new BufferedWriter(new FileWriter(new File("src/data/session.txt")));
						b.write(id.getId());
						b.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					i = 1;
					frame.dispose();
			  new ProductListFrame();
					
					
				}
			}
			if (i != 1) {
				JOptionPane.showMessageDialog(null, "Password or email incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);

			}
		}
		if (source == registerButton) {
			frame.dispose();
			new RegisterForm();
			
		}
		
	}

}
