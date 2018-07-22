package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
import Util.CommonUtils;
import Util.constants;
import Util.lib;



/**
 * @author Arnab
 * Description :- This Class will contain all the web elements and methods related to iAudit application 'Login' page.
 *
 */

public class Login extends Base{

	@FindBy(xpath="//input[@id='txtUserID']")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement password;
	
	@FindBy(id="sub")
	WebElement loginButton;
	
	
	public Login(){
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Author :- Arnab 
	 * Description :- This method is used to login to the iAudit Application
	 * @param userNm :- User Name
	 * @param userPassword :- Password
	 * @return :- It returns object of the next page
	 */
	public HomePage iAuditApplicationLogin(String userNm,String userPassword){
		
		try{
			
			Thread.sleep(3000);
			userName.clear();
			userName.sendKeys(userNm);
			System.out.println("User ID::-"+userNm);
			password.clear();
			password.sendKeys(userPassword);
			System.out.println("User Password::-"+userPassword);
			Thread.sleep(3000);
			loginButton.click();;
			constants.test.log(LogStatus.PASS, "Login is successfull");
//			lib.takeScreenshot();
		}catch(Exception e){
			e.printStackTrace();
			constants.test.log(LogStatus.FAIL, "Login is Failed");
		}
		return new HomePage();
	}
}
