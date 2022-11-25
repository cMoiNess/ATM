import javax.swing.*;


public class AdminBalanceFrame extends JFrame{

    private AdminBalancePanel adminBalancePanel;

    public AdminBalanceFrame(){
        super("User balance"); // Sets title
        setSize(530, 280);
        setResizable(false);
        setIconImage(LoginFrame.icon.getImage());
        adminBalancePanel = new AdminBalancePanel();
        this.setLocation(500, 500);
        add(adminBalancePanel);
    }

    public void addToDisplay(String info){
        adminBalancePanel.addToDisplay(info);
    }

    public String getUsername(){return adminBalancePanel.getUsername();}

    public boolean getBack(){
        return adminBalancePanel.getBack();
    }

    public void setBackFalse(){
        adminBalancePanel.setBackFalse();
    }

    public boolean getShow(){
        return adminBalancePanel.getShow();
    }

    public void setShowFalse(){
        adminBalancePanel.setShowFalse();
    }

    public void setInfo(){adminBalancePanel.setInfo();}

    public void clearDisplay(){
        adminBalancePanel.clearInfo();
    }

}