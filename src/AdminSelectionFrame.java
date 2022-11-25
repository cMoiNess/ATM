import javax.swing.*;

public class AdminSelectionFrame extends JFrame{

    private AdminSelectionPanel adminSelectionPanel;
    private String selectID;



    public AdminSelectionFrame(){



        super("Admin Selection screen"); // Sets title
        setSize(550, 300);
        setResizable(false);
        setIconImage(LoginFrame.icon.getImage());
        adminSelectionPanel = new AdminSelectionPanel();
        this.setLocation(500, 500);
        add(adminSelectionPanel);

    }

    public void setName(String n){
        adminSelectionPanel.setName(n);
    }

    public String getName(){
        return adminSelectionPanel.getName();
    }



    public boolean getAdminDeposit(){
        return adminSelectionPanel.getAdminDeposit();
    }

    public void setAdminDepositFalse(){
        adminSelectionPanel.setAdminDepositFalse();
    }

    public boolean getAdminWithdraw(){
        return adminSelectionPanel.getAdminWithdraw();
    }

    public void setAdminWithdrawFalse(){
        adminSelectionPanel.setAdminWithdrawFalse();
    }

    public boolean getAdminTransfer(){
        return adminSelectionPanel.getAdminTransfer();
    }

    public void setAdminTransferFalse(){
        adminSelectionPanel.setAdminTransferFalse();
    }

    public boolean getAdminBalance(){
        return adminSelectionPanel.getAdminBalance();
    }

    public void setAdminBalanceFalse(){
        adminSelectionPanel.setAdminBalanceFalse();
    }
    public boolean getAdminHistory(){
        return adminSelectionPanel.getAdminHistory();
    }

    public void setAdminHistoryFalse(){
        adminSelectionPanel.setAdminHistoryFalse();
    }

    public boolean getAdminCreate(){
        return adminSelectionPanel.getAdminCreate();
    }

    public void setAdminCreateFalse(){
        adminSelectionPanel.setAdminCreateFalse();
    }

    public boolean getAdminDelete(){
        return adminSelectionPanel.getAdminDelete();
    }

    public void setAdminDeleteFalse(){
        adminSelectionPanel.setAdminDeleteFalse();
    }

    public boolean getAdminList(){
        return adminSelectionPanel.getAdminList();
    }

    public void setAdminListFalse(){
        adminSelectionPanel.setAdminListFalse();
    }

    public boolean getDisconnect(){
        return adminSelectionPanel.getDisconnect();
    }

    public void setDisconnectFalse(){
        adminSelectionPanel.setDisconnectFalse();
    }


    public void setID(String ID){
        selectID = ID;
    }
    public String getID(){
        return selectID;
    }

}