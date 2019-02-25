package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Id_generat;
import core.Inscription;
import core.Read_xml_file;
import de.vogella.xml.stax.reader.Item;

public class RegisterForm implements ActionListener {

	static JLabel userNameLabel = new JLabel("User's name");
	static JTextField userNameText = new JTextField(20);
	static JLabel userLabel = new JLabel("Mail User");
	static JTextField userText = new JTextField(20);
	static JLabel passwordLabel = new JLabel("Password");
	static JPasswordField passwordText = new JPasswordField(20);
	static JButton validateRegisterButton = new JButton("validate");
	String password = "";
	String mail = "";
	JFrame frame;

	public RegisterForm() {

		frame = new JFrame("Please register");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		validateRegisterButton.addActionListener(this);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		userNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userNameLabel);

		userNameText.setBounds(100, 10, 160, 25);
		panel.add(userNameText);

		userLabel.setBounds(10, 40, 80, 25);
		panel.add(userLabel);

		userText.setBounds(100, 40, 160, 25);
		panel.add(userText);

		passwordLabel.setBounds(10, 70, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(100, 70, 160, 25);
		panel.add(passwordText);

		validateRegisterButton.setBounds(100, 100, 100, 25);
		panel.add(validateRegisterButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == validateRegisterButton) {
			String name = userNameText.getText();
			String mail = userText.getText();
			char[] pwd = passwordText.getPassword();
			String spwd = new String(pwd);

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
					if (ins.validatepass(spwd)) {
						try {

							ins.copier();
							ins.setFile("src/data/Clients.xml");
							ins.inscription(name, mail, spwd);
							ins.copier_plus();
							ins.colle();
							JOptionPane.showMessageDialog(null, "Your are register", "Access",
									JOptionPane.INFORMATION_MESSAGE);
							new LoginForm();
							frame.dispose();

						} catch (Exception exc) {
							// TODO Auto-generated catch block
							exc.printStackTrace();
						}
					} else
						JOptionPane.showMessageDialog(null,
								"Your password is incorrect. - 4 caracteres minimum and no special caracteres",
								"Erreur", JOptionPane.ERROR_MESSAGE);

				} else
					JOptionPane.showMessageDialog(null, "Your mail is incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);

			} else
				JOptionPane.showMessageDialog(null, "Your name is incorrect ", "Erreur", JOptionPane.ERROR_MESSAGE);

		}
	}

}
