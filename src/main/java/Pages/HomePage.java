package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class HomePage extends Base{
	
	@FindBy(xpath="//h3[text()='Welcome to iAudit']/following::a[1]")
	WebElement welcomeIAuditCloseIcon;
	
	@FindBy(xpath="//span[text()='Dashboard']")
	WebElement dashboard;
	
	@FindBy(xpath="//span[@class='menu-item-title' and text()='My Team']")
	WebElement myTeam;
	
	@FindBy(xpath="//span[text()='Case Search']")
	WebElement caseSearch;
	

	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void welocmeToIAuditCloseDialoge(){
		try{
			welcomeIAuditCloseIcon.click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
