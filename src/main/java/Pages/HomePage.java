package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
import Util.constants;

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
			constants.test.log(LogStatus.PASS, "Closing the Welcome Screen");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
