import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TransferPanel extends JPanel implements ActionListener{

	private boolean transfer = false;

	private boolean back = false;
	
	private JLabel desired_transfer_amount;
	private JLabel desired_transfer_account;

	private JLabel info;
		

	
	private JButton makeTransferButton;

	private JButton backButton;

	private JTextField transferAccount;
	private JTextField transferAmount;
	
	public TransferPanel(){

			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
		    
		    desired_transfer_amount = new JLabel ("Desired Amount");
		    desired_transfer_account = new JLabel ("Desired Account");
			info = new JLabel();

		    		
		    makeTransferButton = new JButton("Transfer");
			backButton = new JButton("Back");

			transferAccount = new JTextField(15);
			transferAmount = new JTextField(15);
		    
		    makeTransferButton.addActionListener(this);
			backButton.addActionListener(this);

			constraints.gridwidth = 2;
			constraints.gridx = 0;
			constraints.gridy = 0;
			add(transferAmount,constraints);

			constraints.gridx = 0;
			constraints.gridy = 1;
			add(transferAccount,constraints);

			constraints.gridwidth = 1;
			
			constraints.gridx = 2;
			constraints.gridy = 0;
			add(desired_transfer_amount,constraints);
			
			constraints.gridx = 2;
			constraints.gridy = 1;
			add(desired_transfer_account,constraints);
		
			constraints.gridx = 0;
			constraints.gridy = 2;
			add(makeTransferButton,constraints);

			constraints.gridx = 2;
			constraints.gridy = 2;
			add(backButton,constraints);

			constraints.gridwidth = 3;

			constraints.gridx = 0;
			constraints.gridy = 3;
			add(info,constraints);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(makeTransferButton)){
			transfer = true;
		}
		if (buttonPressed.equals(backButton)){
			back = true;
		}
	}


	public boolean getBack(){return back;}

	public void setBackFalse(){
		back = false;
	}
	public boolean getTransfer(){
		return transfer;
	}
	
	public void setTransferFalse(){
		transfer = false;
	}
	
	public String getTransferAccount(){
		return transferAccount.getText();
	}
	
	public String getTransferAmount(){
		return transferAmount.getText();
	}

	public void setIncorrectAmount(){
		info.setText("Incorrect Amount");
	}

	public void setIncorrectAccount(){
		info.setText("Incorrect Account");
	}

	public void setIncorrect(){
		info.setText("Incorrect Amount and Account");
	}

	public void setCorrectAmount(){
		info.setText("You have transfered : " + getTransferAmount() + " to " + getTransferAccount());
	}

	public void setInfoClear(){ info.setText("");}
	
	public void setTransferClear(){
		transferAmount.setText("");
		transferAccount.setText("");
	}

}