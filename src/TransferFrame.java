import javax.swing.*;


public class TransferFrame extends JFrame{

	private TransferPanel transferPanel;
	
	public TransferFrame(){
		super("Make A Transfer");
		setSize(375, 200);
		transferPanel = new TransferPanel();
		setIconImage(LoginFrame.icon.getImage());
		setResizable(false);
		this.setLocation(500, 500);
		add(transferPanel);
	}

	public boolean getTransfer(){
		return transferPanel.getTransfer();
	}
	
	public void setTransferFalse(){
		transferPanel.setTransferFalse();
	}

	public boolean getBack(){ return transferPanel.getBack();}

	public void setBackFalse(){transferPanel.setBackFalse();}
	
	public String getTransferAccount(){
		return transferPanel.getTransferAccount();
	}
	
	public String getTransferAmount(){
		return transferPanel.getTransferAmount();
	}

	public void setIncorrectAccount(){transferPanel.setIncorrectAccount();}
	public void setIncorrectAmount(){ transferPanel.setIncorrectAmount();}

	public void setIncorrect(){transferPanel.setIncorrect();}
	public void setCorrectAmount(){ transferPanel.setCorrectAmount();}

	public void setTransferClear(){
		transferPanel.setTransferClear();
	}

	public void setInfoClear(){ transferPanel.setInfoClear();}
	
}