import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DepositPanel extends JPanel implements ActionListener{

	private boolean deposit = false;

	private boolean back = false;
	
	private JLabel desired_amount;

	private JLabel info;

	
	private JButton makeDepositButton;

	private JButton backButton;
	
	private JTextField amount;
	
	public DepositPanel(){

			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
		    
		    desired_amount = new JLabel ("Desired Amount");
			info = new JLabel();

		    		
		    makeDepositButton = new JButton	("Make Deposit");
			backButton = new JButton("Back");
		    
		    amount = new JTextField(15);
		    
		    makeDepositButton.addActionListener(this);
			backButton.addActionListener(this);

			backButton.setPreferredSize(makeDepositButton.getPreferredSize());
			backButton.setMinimumSize(makeDepositButton.getMinimumSize());

			constraints.insets = new Insets(0,5,5,5);

			constraints.gridwidth=2;
			constraints.gridx = 0;
			constraints.gridy = 0;
			add(amount,constraints);

			constraints.gridwidth=1;
			constraints.gridx = 2;
			constraints.gridy = 0;
			add(desired_amount,constraints);

			constraints.gridx = 0;
			constraints.gridy = 1;
			add(makeDepositButton,constraints);

			constraints.gridx = 2;
			constraints.gridy = 1;
			add(backButton,constraints);

			constraints.gridwidth = 3;
			constraints.gridx = 0;
			constraints.gridy = 2;
			add(info,constraints);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(makeDepositButton)){
			deposit = true;
		}

		if (buttonPressed.equals(backButton)){
			back = true;
		}

	}

	public boolean getBack(){return back;}

	public void setBackFalse(){
		back = false;
	}
	public boolean getDeposit(){
		return deposit;
	}
	
	public void setDepositFalse(){
		deposit = false;
	}
	
	public String getDepositAmount(){
		return amount.getText();
	}

	public void setIncorrectAmount(){
		info.setText("Incorrect Amount");
	}

	public void setCorrectAmount(){
		info.setText("You have deposited : " + getDepositAmount());
	}
	
	public void setDepositClear(){
		amount.setText("");
	}

	public void setInfoClear(){ info.setText("");}
}