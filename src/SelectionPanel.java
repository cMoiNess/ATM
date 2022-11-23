import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class SelectionPanel extends JPanel implements ActionListener{
	

	private boolean deposit = false;
	private boolean withdraw = false;
	private boolean transfer = false;

	private boolean history = false;
	private boolean disconnect = false;
	
	private String name = " ";


	private JLabel balance;



	private JLabel welcome;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JButton historicButton;
	private JButton disconnectButton;
	
	public SelectionPanel(){

		setLayout(new GridBagLayout());

	    welcome = new JLabel("Welcome, " + name + " Please Make a Selection");
		balance = new JLabel();

		depositButton = new JButton		("Deposit");
		withdrawButton = new JButton		("Withdraw");
		transferButton = new JButton	("Transfer");
		historicButton = new JButton	("Historic");
		disconnectButton = new JButton("Disconnect");



		depositButton.addActionListener(this);
		withdrawButton.addActionListener(this);
		transferButton.addActionListener(this);
		historicButton.addActionListener(this);
		disconnectButton.addActionListener(this);

		depositButton.setPreferredSize(disconnectButton.getPreferredSize());
		depositButton.setMinimumSize(disconnectButton.getMinimumSize());
		withdrawButton.setPreferredSize(disconnectButton.getPreferredSize());
		withdrawButton.setMinimumSize(disconnectButton.getMinimumSize());
		transferButton.setPreferredSize(disconnectButton.getPreferredSize());
		transferButton.setMinimumSize(disconnectButton.getMinimumSize());
		historicButton.setPreferredSize(disconnectButton.getPreferredSize());
		historicButton.setMinimumSize(disconnectButton.getMinimumSize());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,5,5,5);

		constraints.weighty = 0;

		constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 3;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(welcome,constraints);

		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;

		constraints.gridx = 0;
		constraints.gridy = 2;
		add(depositButton,constraints);


		constraints.gridx = 1;
		constraints.gridy = 2;
		add(withdrawButton,constraints);

		constraints.gridx = 2;
		constraints.gridy = 2;
		add(transferButton,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(historicButton,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(disconnectButton,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		add(balance,constraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(depositButton)){
			deposit = true;

		}

		else if (buttonPressed.equals(withdrawButton)){
			withdraw = true;
		}
		
		else if (buttonPressed.equals(transferButton)){
			transfer = true;
		}

		else if (buttonPressed.equals(historicButton)){
			history = true;
		}

		else if (buttonPressed.equals(disconnectButton)){
			disconnect = true;
		}
		
	}

	public boolean getDeposit(){
		return deposit;
	}
	
	public void setDepositFalse(){
		deposit = false;
	}
	
	public boolean getWithdraw(){
		return withdraw;
	}
	
	public void setWithdrawFalse(){
		withdraw = false;
	}
	
	public boolean getTransfer(){
		return transfer;
	}
	
	public void setTransferFalse(){
		transfer = false;
	}

	public boolean getHistory(){
		return history;
	}

	public void setHistoryFalse(){
		history = false;
	}
	
	public boolean getDisconnect(){
		return disconnect;
	}
	
	public void setDisconnectFalse(){
		disconnect = false;
	}


	public void setName(String n){
		name = n;
		welcome.setText("Welcome, " + name + " Please Make a Selection                                     ");
		
	}


	public void getBalance(String account_number, double account_balance){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String account_blc = formatter.format(account_balance);
		balance.setText("\nAccount Balance: " + account_blc+ "\n");
	}

	public void setClear(){
		balance.setText("");
	}
}
