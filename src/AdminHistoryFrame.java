import javax.swing.*;


public class AdminHistoryFrame extends JFrame{

    private AdminHistoryPanel adminHistoryPanel;

    public AdminHistoryFrame(){
        super("User Historic");
        setSize(530, 280);
        setResizable(false);
        adminHistoryPanel = new AdminHistoryPanel();
        this.setLocation(500, 500);
        add(adminHistoryPanel);
    }

    public void addToDisplay(String info){
        adminHistoryPanel.addToDisplay(info);
    }

    public String getUsername(){return adminHistoryPanel.getUsername();}

    public boolean getBack(){
        return adminHistoryPanel.getBack();
    }

    public void setBackFalse(){
        adminHistoryPanel.setBackFalse();
    }

    public boolean getShow(){
        return adminHistoryPanel.getShow();
    }

    public void setShowFalse(){
        adminHistoryPanel.setShowFalse();
    }

    public void setInfo(){adminHistoryPanel.setInfo();}

    public void clearDisplay(){
        adminHistoryPanel.clearInfo();
    }

}