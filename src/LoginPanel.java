import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener{
	
	private static boolean login = false;
	private static boolean register = false;

	private static boolean exit = false;
	
	private JLabel name;
	private JLabel password;
	private JLabel incorrect;
	
	private static JTextField nameField;
	private static JTextField passwordField;
	
	private JButton loginButton;
	private JButton registerButton;
	private JButton exitButton;
	
	public LoginPanel(){

		setLayout(new GridBagLayout());

		name = new JLabel("Customer ID");
		password = new JLabel("  Password ");
		incorrect = new JLabel("");

		
		nameField = new JTextField(15);
		passwordField = new JPasswordField(15);

		
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");

		exitButton = new JButton("Exit");
		
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		exitButton.addActionListener(this);

		loginButton.setPreferredSize(registerButton.getPreferredSize());
		loginButton.setMinimumSize(registerButton.getMinimumSize());
		exitButton.setPreferredSize(registerButton.getPreferredSize());
		exitButton.setMinimumSize(registerButton.getMinimumSize());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,5,5,5);
		constraints.weighty = 0;

		//add the buttons to the panel
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(nameField, constraints);

		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(name, constraints);

		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(passwordField, constraints);

		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(password, constraints);

		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(loginButton, constraints);

		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		add(registerButton, constraints);

		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		add(exitButton, constraints);

		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(incorrect, constraints);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		
		if (buttonPressed.equals(loginButton)){
			login = true;
		}
		
		else if (buttonPressed.equals(registerButton)){
			register = true;
		}
		
		else if (buttonPressed.equals(exitButton)){
			exit = true;
		} 

		
		
	}
	
	public static boolean getLogin(){
		
		return login;
	}
	
	public static void setLoginFalse(){
		
		login = false;
	}
	
	public static boolean getRegister(){
		
		return register;
	}

	public static boolean getExit(){

		return exit;

	}
	
	public static void setRegisterFalse(){
		register = false;
	}
	
	public static String get_name(){
		return nameField.getText();
	}

	public static String getPassword(){
		return passwordField.getText();
	}
	
	public void setIncorrect(){
		incorrect.setText("Incorrect ID or Password");
	}

	public void setIncorrectClear(){
		incorrect.setText("");
	}

	
	public void setClear(){
		nameField.setText("");
		passwordField.setText("");
	}

	

}