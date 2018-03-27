package qa.alfresco.google.testcases;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qa.alfresco.google.Keywords;
import qa.alfresco.google.util.Constants;
import qa.alfresco.google.util.DataUtil;
import qa.alfresco.google.util.ExtentManager;
import qa.alfresco.google.util.Xls_Reader;

public class GoogleSearchTestRunner {	
	ExtentReports rep =ExtentManager.getInstance();
	String testName="GoogleSearchTest";
	ExtentTest test;
	Xls_Reader xls=new Xls_Reader(Constants.GOOGLERESULTS_XLS);
	public WebDriver driver;
	
	
		
		@Test(dataProvider = "getData")
		public void GoogleTest(Hashtable<String, String> data) {
			test= rep.startTest(testName);
			test.log(LogStatus.INFO, data.toString());

			if (DataUtil.isSkip(xls, testName) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as rumode is No");
				throw new SkipException("Skipping the test as rumode is No");
			}
			test.log(LogStatus.INFO, "Starting "+testName+" logging");

			Keywords app = new Keywords(test);
			test.log(LogStatus.INFO, "executing keywords");
			app.executeKeywords(testName, xls, data);
			test.log(LogStatus.PASS, testName +" Complete");
			app.getGenericKeywords().reportPass(testName);
			
			
		}

		@AfterTest
		public void quit() {
			
			if (rep != null) {
				rep.endTest(test);
				rep.flush();

			}
		}

		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(xls, testName);
		}

	}


