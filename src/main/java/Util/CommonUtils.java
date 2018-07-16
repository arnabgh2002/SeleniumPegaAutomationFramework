package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Base.Base;


public class CommonUtils extends Base {

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
	
}
