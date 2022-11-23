import javax.swing.*;


public class AdminDepositFrame extends JFrame{

    private AdminDepositPanel adminDepositPanel;

    public AdminDepositFrame(){
        super("Make A Transfer");
        setSize(375, 200);
        adminDepositPanel = new AdminDepositPanel();
        setResizable(false);
        this.setLocation(500, 500);
        add(adminDepositPanel);
    }

    public boolean getAdminDeposit(){
        return adminDepositPanel.getAdminDeposit();
    }

    public void setAdminDepositFalse(){
        adminDepositPanel.setAdminDepositFalse();
    }

    public boolean getBack(){ return adminDepositPanel.getBack();}

    public void setBackFalse(){adminDepositPanel.setBackFalse();}

    public String getDepositAccount(){
        return adminDepositPanel.getDepositAccount();
    }

    public String getDepositAmount(){
        return adminDepositPanel.getDepositAmount();
    }

    public void setIncorrectAccount(){adminDepositPanel.setIncorrectAccount();}
    public void setIncorrectAmount(){ adminDepositPanel.setIncorrectAmount();}

    public void setIncorrect(){adminDepositPanel.setIncorrect();}
    public void setCorrectAmount(){ adminDepositPanel.setCorrectAmount();}

    public void setDepositClear(){
        adminDepositPanel.setDepositClear();
    }

    public void setInfoClear(){ adminDepositPanel.setInfoClear();}

}