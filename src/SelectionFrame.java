import javax.swing.*;

public class SelectionFrame extends JFrame{
		
		private SelectionPanel selectionPanel;
		private String selectID;


		
		public SelectionFrame(){
			
			
			
			super("ATM Selection screen");
			setSize(375, 200);
			setResizable(false);
			selectionPanel = new SelectionPanel();
			this.setLocation(500, 500);
			add(selectionPanel);
		
		}
		
		public void setName(String n){
			selectionPanel.setName(n);
		}
		
		public String getName(){
			return selectionPanel.getName();
		}


		
		public boolean getDeposit(){
			return selectionPanel.getDeposit();
		}
		
		public void setDepositFalse(){
			selectionPanel.setDepositFalse();
		}
		
		public boolean getWithdraw(){
			return selectionPanel.getWithdraw();
		}
		
		public void setWithdrawFalse(){
			selectionPanel.setWithdrawFalse();
		}
		
		public boolean getTransfer(){
			return selectionPanel.getTransfer();
		}
		
		public void setTransferFalse(){
			selectionPanel.setTransferFalse();
		}

		public boolean getHistory(){
		return selectionPanel.getHistory();
	}

		public void setHistoryFalse(){
		selectionPanel.setHistoryFalse();
	}

		public boolean getDisconnect(){
			return selectionPanel.getDisconnect();
		}
		
		public void setDisconnectFalse(){
			selectionPanel.setDisconnectFalse();
		}

		public void setID(String ID){
			selectID = ID;
		}
		public String getID(){
			return selectID;
		}

		public void getBalance(String account_number, double account_balance){
			selectionPanel.getBalance(account_number, account_balance);
		}

		public void setClear(){
			selectionPanel.setClear();
		}
}