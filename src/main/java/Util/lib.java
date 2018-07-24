package Util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
//import ExtentReports.ExtentManager;

/**
 * @author Arnab Ghosh
 * Description :- In this class we will be writing all the Framework Related Methods.
 *
 */
public class lib extends Base{
	
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- This Method is created to read test data from Excel using an external api called Fillo.
	 * @param FileLoc
	 * @param Query
	 * @return
	 */
	public static HashMap<String,String> GetDataFromExcel(String FileLoc,String Query){
		
		HashMap<String,String> results = new HashMap<String,String>();
		String columnNames="";
		int columnCount=0;
		int rowCount=0;		
		Fillo fillo=new Fillo();
		Recordset recordset=null;
		
		try{			
			Connection connection=fillo.getConnection(FileLoc); //"C:\\Test.xlsx"
			if(null != connection){
				recordset=connection.executeQuery(Query);
				String cellValue=new String("");
				ArrayList<String> columnList=recordset.getFieldNames();
				columnCount=columnList.size();
				rowCount=0;
				
				while(recordset.next()){
					rowCount++;
					columnNames="";
					for(int i=0;i<columnCount;i++){
						String columnName=columnList.get(i);
						if(columnNames==""){
							columnNames=columnName;
						}else{
							columnNames=columnNames+";"+columnName;
						}
						cellValue=(String) recordset.getField(columnName);
						if(cellValue==null) cellValue="";
						results.put(columnName + rowCount,cellValue);
					}
				}
				
			}
			
			if(recordset!=null)recordset.close();
			
			if(null !=connection)connection.close();
			results.put("RowCount", rowCount + "");
			results.put("ColumnCount", columnCount + "");
			results.put("ColumnNames", columnNames);

		}catch(Exception e){
			e.printStackTrace();
		}		
		return results;
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- This method generates Random number.
	 * @return
	 */
	public static int random(){
		
		int number=0;
		
		Random rand= new Random();
		for(int i=1;i<=100000;i++){
			number=rand.nextInt((999999-100000)+1)+100000;
		}
		return number;
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- This method takes screenshot save in a folder. Also added capabilities to add screenshot in report using Extent Report.
	 * @throws IOException
	 */
	public static void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		File screenFile=new File(currentDir.toString().replace("\\", "/") + "/screenshots/" + System.currentTimeMillis() + ".png");
//		FileUtils.copyFile(scrFile, new File(currentDir.toString().replace("\\", "/") + "/screenshots/" + System.currentTimeMillis() + ".png"));
		FileUtils.copyFile(scrFile, screenFile);
		String imgPath=screenFile.getPath();		
		System.out.println(imgPath);
		constants.test.log(LogStatus.INFO, "Screenshot Below ::"+constants.test.addScreenCapture(imgPath));
		constants.extent.endTest(constants.test);
		constants.extent.flush();
		}

	/**
	 * Author :- Arnab Ghosh
	 * Description :- This Method is written to initiate Extent Report.
	 * @param methodName
	 */
	public static void beforeMethod(String methodName){
		constants.test=constants.extent.startTest(methodName);
		constants.test.assignAuthor("Automation Team");
		constants.test.assignCategory("Automation Test");
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Used to end test for Extent Report.
	 * @param methodName
	 */
	public static void afterMethod(String methodName){
		constants.extent.endTest(constants.test);
		constants.extent.flush();
	}
	
	/**
	 * Author :- Arnab Ghosh
	 * Description :- Will print exception incase any exception happens in any method.
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public static String getStackTrace(Exception e) throws Exception{
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		String errorMessage=e.toString();
		return errorMessage;
	}
	
	public static String getCurrentDateInMMDDYYYFormat() throws Exception{
		String currentDate="";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		currentDate=dtf.format(now).toString().trim();
		System.out.println("Current Data in MM/DD/YYYY format::- "+currentDate);
		return currentDate;
		
	}
}
