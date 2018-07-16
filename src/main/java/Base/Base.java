package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import Util.constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public static WebDriver driver; 
	public static Properties configData=new Properties();
	
	public void initiateWebdriver(){
		
		try{
//			String test=System.getProperty("user.dir")+"\\chromedriver.exe";
//			System.out.println(test);
			String browserName=configData.getProperty("Browser");
			if(browserName.equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");	
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(configData.getProperty("URL"));
			}else if (browserName.equalsIgnoreCase("FF")){
				System.out.println("\nBrower=FF");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");	
				driver = new FirefoxDriver(); 
				driver.manage().window().maximize();
				System.out.println("Login URL::-"+configData.getProperty("URL"));
				driver.get(configData.getProperty("URL"));
			}else{
				System.out.println("Invalid Browser");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		return driver;
	}
	
	
	public static void ReadConfigData(){
		File configFile = new File(System.getProperty("user.dir")+"\\Config.xml");
		//File configFile = new File("config.properties");
		
		try {
			FileInputStream reader = new FileInputStream(configFile);
			//Properties props = new Properties();
			configData.loadFromXML(reader);
		
			String url = configData.getProperty("URL");		
			System.out.print("\nLogin url is: " + url);
			reader.close();
		} catch (FileNotFoundException ex) {
			// file does not exist
		} catch(IOException ex){
			// I/O exception
		}
	}
	
	
	
}
