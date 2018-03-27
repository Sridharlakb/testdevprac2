package qa.alfresco.google.util;

import qa.alfresco.google.util.Constants;

import org.testng.annotations.DataProvider;

// all data providers for all tests
public class Data_Provider {
	static Xls_Reader xls = new Xls_Reader(Constants.GOOGLERESULTS_XLS);

	@DataProvider(name = "GoogleSearchTest")
	public static Object[][] getData() {
		String testName = "GoogleSearchTest";
		return DataUtil.getData(xls, testName);

	}

	

}
