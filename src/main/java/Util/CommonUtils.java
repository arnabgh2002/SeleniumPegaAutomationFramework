package Util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.Base;


/**
 * @author Arnab Ghosh
 * Description :- This Class contains all reusable common WebElement methods.
 *
 */
public class CommonUtils extends Base {

	public CommonUtils(){
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Selects a dropdown value using the text.
	 * @param webElement
	 * @param valueByVisibleText
	 * @return
	 */
	public static boolean selectDropdownValueByVisibleText(WebElement webElement,String valueByVisibleText){
		boolean result=false;		
		
		try{
//			WebElement webElement=getWebElement(sObject);
			webElement.click();
			Select oSelect= new Select(webElement);
			oSelect.selectByVisibleText(valueByVisibleText);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Selects a dropdown value using the index number.
	 * @param webElement
	 * @param indexNumber
	 * @return
	 */
	public static boolean selectDropdownValueByIndex(WebElement webElement,int indexNumber){
		boolean result=false;		
		
		try{
//			WebElement webElement=getWebElement(sObject);
			Select oSelect= new Select(webElement);
			oSelect.selectByIndex(indexNumber);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Selects multiple options in and multiselect picklist (dropdown)
	 * @param webElement
	 * @param value
	 * @return
	 */
	public static boolean dropDownMultiSelectAdd(WebElement webElement,String value){
		
		boolean result=false;
		
		try{
			if(value!=""){
//				WebElement webElement=getWebElement(sObject);
				java.util.List<WebElement> list=webElement.findElements(By.tagName("option"));
				String data[]=value.split(",");
				
				for(int k=0;k<=data.length;k++){
					
					int j=0;
					for(int i=0;i<=list.size();i++){
						
						j++;
						String str=list.get(i).getText();
						System.out.println("Value is List (str) is"+str);
						System.out.println("Value stored in data[] "+data[k]);
						if(str.equalsIgnoreCase(data[k])){
							j--;
							webElement.sendKeys(Keys.CONTROL);
							list.get(i).click();
							
							System.out.println(str+" is selected");
							break;
						}
						if(j==list.size()){
							org.testng.Assert.fail(data[k]+" is not present");
						}
					}
				}
			}else{
				System.out.println("Please give input values to be selected from dropdown");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Switch to desired iframe by passing the WebElement.
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public static boolean switchToStandardObjFrame(WebElement element) throws Exception {
		boolean found=false;
		driver.switchTo().defaultContent();
		//getting the total number of iFrame.
//		int size=driver.findElements(By.tagName("iframe")).size();
		List<WebElement> iFrameList=driver.findElements(By.tagName("iframe"));
		
		for(WebElement iframe:iFrameList){
			driver.switchTo().defaultContent();
			driver.switchTo().frame(iframe);
			try{
				//checking the element is present in the current iframe.
				if(element.isDisplayed()){
					driver.switchTo().defaultContent();
					driver.switchTo().frame(iframe);
					found=true;
					break;
				}
			}catch(NoSuchElementException e){
				System.out.println("No found in current iframe::-"+iframe);
//				e.printStackTrace();
			}
		}
		
		return found;
	}
	
}
