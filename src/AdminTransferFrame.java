import javax.swing.*;


public class AdminTransferFrame extends JFrame{

    private AdminTransferPanel adminTransferPanel;

    public AdminTransferFrame(){
        super("Admin Transfer");
        setSize(375, 200);
        adminTransferPanel = new AdminTransferPanel();
        setIconImage(LoginFrame.icon.getImage());
        setResizable(false);
        this.setLocation(500, 500);
        add(adminTransferPanel);
    }

    public boolean getAdminTransfer(){
        return adminTransferPanel.getAdminTransfer();
    }

    public void setAdminTransferFalse(){
        adminTransferPanel.setAdminTransferFalse();
    }

    public boolean getBack(){ return adminTransferPanel.getBack();}

    public void setBackFalse(){adminTransferPanel.setBackFalse();}

    public String getWithdrawAccount(){
        return adminTransferPanel.getWithdrawAccount();
    }

    public String getDepositAccount(){
        return adminTransferPanel.getDepositAccount();
    }

    public String getTransferAmount(){
        return adminTransferPanel.getTransferAmount();
    }

    public void setIncorrectWithdrawAccount(){adminTransferPanel.setIncorrectWithdrawAccount();}
    public void setIncorrectDepositAccount(){adminTransferPanel.setIncorrectDepositAccount();}
    public void setIncorrectAmount(){ adminTransferPanel.setIncorrectAmount();}

    public void setIncorrect(){adminTransferPanel.setIncorrect();}
    public void setCorrectAmount(){ adminTransferPanel.setCorrectAmount();}

    public void setAdminTransferClear(){
        adminTransferPanel.setAdminTransferClear();
    }

    public void setInfoClear(){ adminTransferPanel.setInfoClear();}

}