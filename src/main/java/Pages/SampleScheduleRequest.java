package Pages;

import Base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import Util.CommonUtils;
import Util.constants;

public class SampleScheduleRequest extends Base{

	@FindBy(xpath="//label[text()='Measure']/following::select[1]")
	WebElement measureDropdown;
	
	public SampleScheduleRequest(){
		PageFactory.initElements(driver, this);
	}
	
 
	public boolean switchtoIframe() throws Exception{
		boolean result=false;
		result=CommonUtils.switchToStandardObjFrame(measureDropdown);
		return result;
	}
	
	public boolean selectMeasureDropdown(String dropdownValue) throws Exception{
		boolean result=false;
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("PegaGadget1Ifr");	
		CommonUtils.switchToStandardObjFrame(measureDropdown);
		result=CommonUtils.selectDropdownValueByVisibleText(measureDropdown, dropdownValue);		
//		if(result){
//			constants.test.log(LogStatus.PASS, "Selected '"+dropdownValue+"' value in Measure Dropdown.");
//		}else{
//			constants.test.log(LogStatus.FAIL, "Unable to select '"+dropdownValue+"' in the Measure dropdown.");
//		}
		
		return result;
	}
	
}
