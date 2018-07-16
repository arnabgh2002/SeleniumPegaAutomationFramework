package TestCases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import Base.Base;
import Pages.Login;
import Util.constants;
import Util.lib;


public class SampleTest extends Base{
	
	public SampleTest(){
		super();
	}
	
  @Test
  public void test1() {
	  Login login;
	  try{
		  login=new Login();
		  login.Login(configData.getProperty("LoginID"), configData.getProperty("LoginPassword"));
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  

  
  
  
  @BeforeMethod
  public void beforeMethod(Method method) {
	  ReadConfigData();

	  initiateWebdriver();
	  String methodName=method.getName();
	  lib.beforeMethod(methodName);
	  
  }

  @AfterMethod
  public void afterMethod(Method method) {
	  String methodName=method.getName();
	  lib.afterMethod(methodName);
//	  driver.quit();

  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }


  @BeforeSuite
  public void beforesuite(){
	  constants.extent=new ExtentReports(System.getProperty("user.dir").toString().replace("\\", "/")+
			  "/test-output/reports/testreport"+constants.dateFormat.format(constants.cal.getTime())+".html", true);
  }
  

}
