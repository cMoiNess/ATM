import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel implements ActionListener{
	
	public static boolean accept = false;
	public static boolean back = false;

	private JLabel desiredName;
	private JLabel desiredPassword;
	private JLabel info;
	
	private JTextField desiredNameField;
	private JTextField desiredPasswordField;

	
	private JButton acceptButton;
	private JButton backButton;
	
	public RegisterPanel(){

		setLayout(new GridBagLayout());

		desiredName = new JLabel("Desired Name");
		desiredPassword= new JLabel("Desired Password");
		info = new JLabel("");
		
		desiredNameField = new JTextField(15);
		desiredPasswordField = new JPasswordField(15);
		
		acceptButton = new JButton("accept");
		backButton = new JButton("back");
		
		acceptButton.addActionListener(this);
		backButton.addActionListener(this);

		backButton.setPreferredSize(acceptButton.getPreferredSize());
		backButton.setMinimumSize(acceptButton.getMinimumSize());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(0,5,5,5);

		constraints.weighty = 0;

		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(desiredNameField, constraints);

		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(desiredName, constraints);

		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(desiredPasswordField, constraints);

		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(desiredPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		add(acceptButton, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		add(backButton, constraints);

		constraints.gridwidth = 3;
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(info, constraints);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		
		if (buttonPressed.equals(acceptButton)){
			
			if (getPassword().length() == 4){
				accept = true;
				setInfoClear();
			} else {
				setIncorrectPassword();
			}
			
		}
		
		if (buttonPressed.equals(backButton)){
			back = true;
		}
	}
	
	public static boolean getAccept(){
		
		return accept;
	}
	
	public static boolean getBack(){
		return back;
	}
	
	public String getName(){
		
		return desiredNameField.getText();
		
	}
	
	public String getPassword(){
		
		return desiredPasswordField.getText();
		
	}
	
	public void setAcceptFalse(){
		accept = false;
	}
	
	public void setBackFalse(){
		back = false;
	}
	
	public void setIncorrectPassword(){
		
		info.setText("That is not a valid password!");
	}

	public void setIncorrectUsername(){

		info.setText("This username is already taken!");

	}

	public void setIncorrect(){
		info.setText("Invalid Username and password");
	}

	public void setInfoClear(){
		info.setText("");
	}
	
	public void setClear(){
		desiredNameField.setText("");
		desiredPasswordField.setText("");
		
	}
	

}


