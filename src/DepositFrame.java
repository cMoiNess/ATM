import javax.swing.*;


public class DepositFrame extends JFrame{

	private DepositPanel depositPanel;
	
	public DepositFrame(){
		super("Make A Deposit");
		setSize(375, 200);
		setResizable(false);
		setIconImage(LoginFrame.icon.getImage());
		depositPanel = new DepositPanel();
		this.setLocation(500, 500);
		add(depositPanel);
	}
	

	
	public boolean getDeposit(){
		return depositPanel.getDeposit();
	}
	
	public void setDepositFalse(){
		depositPanel.setDepositFalse();
	}

	public boolean getBack(){ return depositPanel.getBack();}

	public void setBackFalse(){depositPanel.setBackFalse();}
	
	public String getDepositAmount(){
		return depositPanel.getDepositAmount();
	}

	public void setIncorrectAmount(){ depositPanel.setIncorrectAmount();}
	public void setCorrectAmount(){ depositPanel.setCorrectAmount();}
	public void setDepositClear(){
		depositPanel.setDepositClear();
	}
	public void setInfoClear(){ depositPanel.setInfoClear();}
	

}
