import javax.swing.*;


public class AdminListFrame extends JFrame{

    private AdminListPanel adminListPanel;

    public AdminListFrame(){
        super("Admin Customer Info");
        setSize(530, 280);
        setResizable(false);
        adminListPanel = new AdminListPanel();
        setIconImage(LoginFrame.icon.getImage());
        this.setLocation(500, 500);
        add(adminListPanel);
    }

    public void addToDisplay(String info){
        adminListPanel.addToDisplay(info);
    }

    public boolean getBack(){
        return adminListPanel.getBack();
    }

    public void setBackFalse(){
        adminListPanel.setBackFalse();
    }

    public void clearDisplay(){
        adminListPanel.clearDisplay();
    }

}