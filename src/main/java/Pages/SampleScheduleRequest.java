package Pages;

import Base.Base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import Util.CommonUtils;
import Util.constants;
import Util.lib;

/**
 * @author Arnab
 * Description :- This Class will contain all the web elements and methods related to iAudit application 'Sample Schedule Request' page. 
 *
 */
public class SampleScheduleRequest extends Base{

	@FindBy(xpath="//label[text()='Measure']/following::select[1]")
	WebElement measureDropdown;
	
	@FindBy(id="AudFromDt1")
	WebElement auditFromDate;
	
	@FindBy(id="AudToDt1")
	WebElement auditToDate;
	
	@FindBy(id="AudRunDt1")
	WebElement auditRunDate;
	
	@FindBy(id="SamplingMethod1")
	WebElement samplingMethodDropdown;
	
	@FindBy(id="SampleCnt1")
	WebElement totalSampleCount;
	
	@FindBy(xpath="//button[text()='Schedule Request']")
	WebElement scheduleRequestButton;
	
	@FindBy(id="ModalButtonSubmit")
	WebElement createSSRSubmitButton;
	
	@FindBy(xpath="//div[text()='Scheduling ID']/following::td[1]/following::div[5]/child::span[contains(text(),'SSR')]")
	WebElement schedulingRequestID;
	
	public SampleScheduleRequest(){
		PageFactory.initElements(driver, this);
	}
	
 
//	public boolean switchtoIframe() throws Exception{
//		boolean result=false;
//		result=CommonUtils.switchToStandardObjFrame(measureDropdown);
//		return result;
//	}
	
	/**
	 * Author :- Arnab
	 * Description :- Selects the dropdown value of the field Measure
	 * @param dropdownValue
	 * @return :- true/false
	 * @throws Exception
	 */
	public boolean selectMeasureDropdown(String dropdownValue) throws Exception{
		boolean result=false;
		CommonUtils.switchToStandardObjFrame(measureDropdown);
		result=CommonUtils.selectDropdownValueByVisibleText(measureDropdown, dropdownValue);		
		return result;
	}
	

	/**
	 * Author :- Arnab
	 * Description :- This method is used for entering Data in the Schedule Request Page with Measure Dropdown value is 'Enrollment'
	 * @param testdata :- This method needs Test Data. User needs to pass data from main test case.
	 * @return :- This Method will return the Schedule Request ID as String. User can used the id for later flow.
	 * @throws Exception
	 */
	public String dataEntryForEnrollmentSampleScheduleRequest(HashMap<String , String> testdata) throws Exception{
		
		String SSRid="";
		
		//Switching to the iFrame. 
		CommonUtils.switchToStandardObjFrame(measureDropdown);
		
		//Enter Data for Enrollment 
		if(testdata.get("Audit From Date"+constants.i).isEmpty()){
			constants.test.log(LogStatus.INFO, "No data entered for 'Audit From Date' as there is no data in test data excel");
		}else{
			auditFromDate.clear();
			auditFromDate.sendKeys(testdata.get("Audit From Date"+constants.i));
			constants.test.log(LogStatus.PASS, "'Audit From Date' = '"+testdata.get("Audit From Date"+constants.i)+"'");
		}
		
		if(testdata.get("Audit To Date"+constants.i).isEmpty()){
			constants.test.log(LogStatus.INFO, "No data entered for 'Audit To Date' as there is no data in test data excel");
		}else{
			auditToDate.clear();
			auditToDate.sendKeys(testdata.get("Audit To Date"+constants.i));
			constants.test.log(LogStatus.PASS, "'Audit To Date' = '"+testdata.get("Audit To Date"+constants.i)+"'");
		}
		
		//Audit Run Date will take current date.
		auditRunDate.clear();
		String auditRunDateData=lib.getCurrentDateInMMDDYYYFormat();
		System.out.println("auditRunDateData="+auditRunDate);
		auditRunDate.sendKeys(auditRunDateData);
		constants.test.log(LogStatus.PASS, "'Audit Run Date' = '"+auditRunDateData+"'");
		
		
		if(testdata.get("Sampling Method"+constants.i).isEmpty()){
			constants.test.log(LogStatus.INFO, "No data entered for 'Sampling Method' as there is no data in test data excel");
		}else{
			
			if(CommonUtils.selectDropdownValueByVisibleText(samplingMethodDropdown, testdata.get("Sampling Method"+constants.i))){
				constants.test.log(LogStatus.PASS, "'Sampling Method' = '"+testdata.get("Sampling Method"+constants.i)+"'");
			}else{
				constants.test.log(LogStatus.FAIL, "'Sampling Method' dropdown value '"+testdata.get("Sampling Method"+constants.i)+"' cannot be selected.");
			}
			
		}
		
		if(testdata.get("Total Sample Count"+constants.i).isEmpty()){
			constants.test.log(LogStatus.INFO, "No data entered for 'Total Sample Count' as there is no data in test data excel");
		}else{
			totalSampleCount.clear();
			totalSampleCount.sendKeys(testdata.get("Total Sample Count"+constants.i));
			constants.test.log(LogStatus.PASS, "'Total Sample Count' = '"+testdata.get("Total Sample Count"+constants.i)+"'");
		}
		
		//click Schedule Request Button
		scheduleRequestButton.click();
		constants.test.log(LogStatus.PASS, "Clicked Schedule Request Button");
		
		//clicking submit (create SSR alert box)
		Thread.sleep(4000);
		createSSRSubmitButton.click();		
		constants.test.log(LogStatus.PASS, "Clicked Submit Button on the confirmation pop up for Create SSR dialog box");
		
		
		//Retrieving SSR id 
		Thread.sleep(4000);
		SSRid=schedulingRequestID.getText();
		constants.test.log(LogStatus.INFO, "Generated SSR ID='"+SSRid+"'");
		return SSRid;
		
	}
	
}
