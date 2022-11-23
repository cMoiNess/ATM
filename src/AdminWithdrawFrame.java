import javax.swing.*;


public class AdminWithdrawFrame extends JFrame{

    private AdminWithdrawPanel adminWithdrawPanel;

    public AdminWithdrawFrame(){
        super("Make A Transfer");
        setSize(375, 200);
        adminWithdrawPanel = new AdminWithdrawPanel();
        setResizable(false);
        this.setLocation(500, 500);
        add(adminWithdrawPanel);
    }

    public boolean getAdminWithdraw(){
        return adminWithdrawPanel.getAdminWithdraw();
    }

    public void setAdminWithdrawFalse(){
        adminWithdrawPanel.setAdminWithdrawFalse();
    }

    public boolean getBack(){ return adminWithdrawPanel.getBack();}

    public void setBackFalse(){adminWithdrawPanel.setBackFalse();}

    public String getWithdrawAccount(){
        return adminWithdrawPanel.getWithdrawAccount();
    }

    public String getWithdrawAmount(){
        return adminWithdrawPanel.getWithdrawAmount();
    }

    public void setIncorrectAccount(){adminWithdrawPanel.setIncorrectAccount();}
    public void setIncorrectAmount(){ adminWithdrawPanel.setIncorrectAmount();}

    public void setIncorrect(){adminWithdrawPanel.setIncorrect();}
    public void setCorrectAmount(){ adminWithdrawPanel.setCorrectAmount();}

    public void setWithdrawClear(){
        adminWithdrawPanel.setWithdrawClear();
    }

    public void setInfoClear(){ adminWithdrawPanel.setInfoClear();}

}