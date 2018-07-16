package Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import Base.Base;
import ExtentReports.ExtentManager;

public class lib extends Base{
	
	
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
	
	public static int random(){
		
		int number=0;
		
		Random rand= new Random();
		for(int i=1;i<=100000;i++){
			number=rand.nextInt((999999-100000)+1)+100000;
		}
		return number;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
		}

	public static void beforeMethod(String methodName){
		constants.test=constants.extent.startTest(methodName);
		constants.test.assignAuthor("Automation Team");
		constants.test.assignCategory("Automation Test");
	}
	
	public static void afterMethod(String methodName){
		constants.extent.endTest(constants.test);
		constants.extent.flush();
	}
}
