package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Base.Base;
import Util.CommonUtils;


/**
 * @author Arnab Ghosh
 *	Description :- This class is used for storing all the constants.
 */
public class constants extends Base{
	
	public static final int i=1;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static DateFormat dateFormat=new SimpleDateFormat("MMddyyyyHHmm");
	public static Calendar cal=Calendar.getInstance();
	public static String testDataPath=System.getProperty("user.dir").toString().replace("\\", "\\\\")+"\\\\TestData";

}
