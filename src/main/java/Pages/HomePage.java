package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
import Util.constants;
import Util.lib;

public class HomePage extends Base{
	
	@FindBy(xpath="//h3[text()='Welcome to iAudit']/following::a[1]")
	WebElement welcomeIAuditCloseIcon;
	
	@FindBy(xpath="//span[text()='Dashboard']")
	WebElement dashboard;
	
	@FindBy(xpath="//span[@class='menu-item-title' and text()='My Team']")
	WebElement myTeam;
	
	@FindBy(xpath="//span[text()='Case Search']")
	WebElement caseSearch;
	
	@FindBy(xpath="//span[text()='Sample Schedule Request']")
	WebElement sampleScheduleRequest;
	
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void welocmeToIAuditCloseDialoge(){
		try{
			Thread.sleep(4000);
			if(welcomeIAuditCloseIcon.isDisplayed()){
				welcomeIAuditCloseIcon.click();
				constants.test.log(LogStatus.PASS, "Closing the Welcome Screen");
			}else{
				constants.test.log(LogStatus.INFO, "Welcome Screen is not displayed");
				lib.takeScreenshot();				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void navigateToSampleScheduleRequest() throws Exception{
		sampleScheduleRequest.click();
		constants.test.log(LogStatus.PASS, "Clicked 'Sample Schedule Request' to Navigate to Sample Schedule Request page.");
	}

}
