import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WithdrawPanel extends JPanel implements ActionListener{

	private boolean withdraw = false;

	private boolean back = false;
	
	private JLabel desired_amount;

	private JLabel info;
	
	private JButton makeWithdrawButton;

	private JButton backButton;
	
	private JTextField amount;
	
	public WithdrawPanel(){

			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
		    constraints.anchor = GridBagConstraints.WEST;
		    
		    desired_amount = new JLabel ("Desired Amount");

			info = new JLabel();
		    		
		    makeWithdrawButton = new JButton("Make Withdrawal");

			backButton = new JButton("Back");
		    
		    amount = new JTextField(15);
		    amount.setText("");
		    
		    makeWithdrawButton.addActionListener(this);
			backButton.addActionListener(this);

			backButton.setPreferredSize(makeWithdrawButton.getPreferredSize());
			backButton.setMinimumSize(makeWithdrawButton.getMinimumSize());

			constraints.insets = new Insets(0,5,5,5);

			constraints.gridwidth = 2;
			constraints.gridx = 0;
			constraints.gridy = 0;
			add(amount,constraints);

		    constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 0;
			add(desired_amount,constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			add(makeWithdrawButton,constraints);

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

		if (buttonPressed.equals(makeWithdrawButton)){
			withdraw = true;
			
		}
		if (buttonPressed.equals(backButton)){
			back = true;
		}
	}

	public boolean getBack(){return back;}

	public void setBackFalse(){
		back = false;
	}
	public boolean getWithdraw(){
		return withdraw;
	}
	
	public void setWithdrawFalse(){
		withdraw = false;
	}
	
	public String getWithdrawAmount(){
		return amount.getText();
	}

	public void setIncorrectAmount(){
		info.setText("Incorrect Amount");
	}

	public void setCorrectAmount(){
		info.setText("You withdrew : " + getWithdrawAmount());
	}
	
	public void setWithdrawClear(){
		amount.setText("");
	}

	public void setInfoClear(){ info.setText("");}
}