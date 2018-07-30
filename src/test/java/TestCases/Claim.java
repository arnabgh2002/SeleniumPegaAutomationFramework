package TestCases;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
import Pages.Login;
import Pages.SampleScheduleRequest;
import Util.constants;
import Util.lib;
import Pages.HomePage;


public class Claim extends Base{
	
	public Claim(){
		super();
	}
	
  @Test
  public void iAuditSampleScheduleRequestClaim() throws Exception {
	  Login login=new Login();
	  HomePage home=new HomePage();
	  SampleScheduleRequest sampleScheduleRequest = new SampleScheduleRequest();	
	  
	  String testName=new Object(){}.getClass().getEnclosingMethod().getName();
	  
	  try{
		  //reading test data from excel
		  HashMap<String, String> hm=lib.GetDataFromExcel(constants.testDataPath+"\\\\"+configData.getProperty("TestDataExcelFileName"),
				  "Select * from Claim where TestName='"+testName+"'");
		  home=login.iAuditApplicationLogin(configData.getProperty("MTMAuditorLoginID"), configData.getProperty("MTMAuditorLoginPassword"));

		  //closing the welcome screen
		  home.welocmeToIAuditCloseDialoge();
		  
		  Thread.sleep(4000);
		  //clicking Sample Schedule Request link
		  home.navigateToSampleScheduleRequest();
		  
		  //selecting the Measure dropdown value.

		  if(sampleScheduleRequest.selectMeasureDropdown(hm.get("Measure"+constants.i))){
			  constants.test.log(LogStatus.PASS, "Selected '"+hm.get("Measure"+constants.i)+"' value in Measure Dropdown.");
		  }else{
			  constants.test.log(LogStatus.FAIL, "Fail to select '"+hm.get("Measure"+constants.i)+"' value in Measure Dropdown.");
		  }
/*
		  //validate the column labels for Claim.
		  Thread.sleep(4000); 
		  int noOfColumnHeader=driver.findElements(By.xpath("//span[text()='Audit From Date']/ancestor-or-self::tr[1]/th")).size();
		  
		  String colText="";
		  for(int i=1;i<noOfColumnHeader;i++){

			  colText=driver.findElement(By.xpath("//span[text()='Audit From Date']/ancestor-or-self::tr[1]/th["+i+"]")).getAttribute("data-attribute-name");
			  if(hm.get("Enrolment Column Header"+constants.i).contains(colText)){
				  constants.test.log(LogStatus.PASS, "Column '"+colText+"' is present in the UI.");
			  }else{
				  constants.test.log(LogStatus.FAIL, "Column '"+colText+"' is not present in the UI.");
			  }
			  
		  }
*/
		  
		  //Enter data for Claim
		  sampleScheduleRequest.dataEntryForClaimSampleScheduleRequest(hm);
		 
		  
	  }catch(Exception e){
//		  e.printStackTrace();
		  constants.test.log(LogStatus.FAIL, "Verify if Test" +testName+"Pass without Exception"+lib.getStackTrace(e));
		  lib.takeScreenshot();
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
	  //Extent Report
	  constants.extent=new ExtentReports(System.getProperty("user.dir").toString().replace("\\", "/")+
			  "/test-output/reports/testreport"+constants.dateFormat.format(constants.cal.getTime())+".html", true);
  }
  

}
