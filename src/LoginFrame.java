import javax.swing.*;

public class LoginFrame extends JFrame {
	
	private LoginPanel loginPanel;

	static ImageIcon icon = new ImageIcon("atmIcon.png");

	private static boolean login;

	
	public LoginFrame(){ // Mainframe Constructor
		
	
		
		super("Login");
		setSize(375, 200);
		setResizable(false);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new LoginPanel();
		
		this.setLocation(500, 500);
		add(loginPanel);

		loginPanel.setVisible(true);

	
	}

	public static boolean isNumeric(String str)  {  
		  try  {  
			  double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  {  
		    return false;  
		  }  
		  return true;  
		}
	
	public String getName(){
		return loginPanel.get_name();
	}
	public String getPassword(){
		return loginPanel.getPassword();
	}
	
	public void setIncorrect(){
		loginPanel.setIncorrect();
	}

	public void setIncorrectClear(){loginPanel.setIncorrectClear();}
	public void setClear(){
		loginPanel.setClear();
	}
	

}