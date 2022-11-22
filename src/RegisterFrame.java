import javax.swing.*;

public class RegisterFrame extends JFrame{
	
	private RegisterPanel registerPanel;
	private boolean accept;



	public RegisterFrame(){
		
		super("Enter Name and PIN");
		setSize(350, 200);
		setResizable(false);
		registerPanel = new RegisterPanel();
		this.setLocation(500, 500);
		add(registerPanel);
	}
	
	public String getName(){
		
		return registerPanel.getName();
		
	}
	
	public String getPassword(){
		
		return registerPanel.getPassword();
		
	}
	
	public void setAcceptFalse(){
		registerPanel.setAcceptFalse();
	}
	
	public void setBackFalse(){
		registerPanel.setBackFalse();
	}
	
	public void setIncorrectPassword(){
		registerPanel.setIncorrectPassword();
	}

	public void setIncorrectUsername(){registerPanel.setIncorrectUsername();}
	
	public void setClear(){
		registerPanel.setClear();
	}

	public void setInfoClear(){registerPanel.setInfoClear();}

	public void setIncorrect(){registerPanel.setIncorrect();}
	
	public boolean getBack(){
		return registerPanel.getBack();
	}
	
}


