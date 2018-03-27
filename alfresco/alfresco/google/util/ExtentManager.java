package qa.alfresco.google.util;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			// this is to make up the file name using the date & time
			Date d = new Date();
			// this line helps to remove the ":" & space " " and convert them
			// into underscore"_".
			String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";

			// Make sure to create ExtentReports Folder inside project
			extent = new ExtentReports(Constants.REPORT_PATH + fileName, true, DisplayOrder.NEWEST_FIRST);

			// Import ReportsConfig.xml
			extent.loadConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "3.8.0").addSystemInfo("Environment", "QA");
		}
		return extent;
	}
}
