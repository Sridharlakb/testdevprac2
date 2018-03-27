package qa.alfresco.google;


import java.util.Hashtable;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qa.alfresco.google.util.Constants;
import qa.alfresco.google.util.Xls_Reader;

public class Keywords{

	ExtentTest test;
	AppKeywords app;
	
	public Keywords(ExtentTest test) {
		this.test = test;
	}
	public void executeKeywords(String testUnderExecution, Xls_Reader xls, Hashtable<String, String> testData)   {
		app=new AppKeywords(test);
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);

		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcid = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);
			if (tcid.equals(testUnderExecution)) {
				String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rNum);
				String TestDescription = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rNum);
				String object = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rNum);
				String key = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rNum);
				String data = testData.get(key);
				test.log(LogStatus.INFO, tcid + " -- " +TestDescription+" -- " + keyword );
				String result = "result";
				if (keyword.equals("OpenBrowser"))
					result = app.openBrowser(data);
				else if (keyword.equals("GoogleHomePage"))
					result = app.GoogleHomePage(object);
				else if (keyword.equals("VerifySearchResults"))
					result = app.VerifySearchResults();
				else if (keyword.equals("NavigateBack"))
					result = app.NavigateBack();
				else if (keyword.equals("NavigateForward"))
					result = app.NavigateForward();
				else if (keyword.equals("VisitFirstSearchResult"))
					result = app.VisitFirstSearchResult(object);
				else if (keyword.equals("Click"))
					result = app.Click(object);
				else if (keyword.equals("GetText"))
					result = app.GetText(object);
				else if (keyword.equals("GoogleSearch"))
					result = app.GoogleSearch(object,data);
				else if (keyword.equals("VerifyGoogleHomePage"))
					result = app.VerifyGoogleHomepage();
				else if (keyword.equals("SearchFilterByTime"))
					result = app.SearchFilterByTime(object);
				else if (keyword.equals("CloseBrowser"))
					result = app.closeBrowser();
				

				// central place reporing failure
				if (!result.equals(Constants.PASS)) {
					app.reportFailure(result);
					Assert.fail(result);
				}
			}
		}

	}



	public GenericKeywords getGenericKeywords() {
		return app;
	}
}
