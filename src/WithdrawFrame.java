import javax.swing.*;

public class WithdrawFrame extends JFrame{

	private WithdrawPanel withdrawPanel;
	
	public WithdrawFrame(){
		super("Make A Withdrawal"); // Sets title
		setSize(375, 200);
		setResizable(false);
		setIconImage(LoginFrame.icon.getImage());
		withdrawPanel = new WithdrawPanel();
		this.setLocation(500, 500);
		add(withdrawPanel);
	}
	
	public boolean getWithdraw(){
		return withdrawPanel.getWithdraw();
	}
	
	public void setWithdrawFalse(){
		withdrawPanel.setWithdrawFalse();
	}

	public boolean getBack(){ return withdrawPanel.getBack();}

	public void setBackFalse(){withdrawPanel.setBackFalse();}
	public String getWithdrawAmount(){
		return withdrawPanel.getWithdrawAmount();
	}

	public void setIncorrectAmount(){ withdrawPanel.setIncorrectAmount();}
	public void setCorrectAmount(){ withdrawPanel.setCorrectAmount();}
	public void setWithdrawClear(){
		withdrawPanel.setWithdrawClear();
	}

	public void setInfoClear(){ withdrawPanel.setInfoClear();}
	

}
