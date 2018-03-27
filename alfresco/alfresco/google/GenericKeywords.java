package qa.alfresco.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qa.alfresco.google.util.Constants;


public class GenericKeywords  {
	public WebDriver driver;
	public Properties prop;
	ExtentTest test;
	

	public GenericKeywords(ExtentTest test) {
		this.test = test;
		prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//Project.properties//");
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// open browser
	public String openBrowser(String BrowserType) {
		test.log(LogStatus.INFO, "opening Browser");
		System.out.println("opening Browser");

		if (BrowserType.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "BrowserFiles/geckodriver");
			driver = new FirefoxDriver();
		} else if (BrowserType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "BrowserFiles/chromedriver");
			driver = new ChromeDriver();
		}  else if (BrowserType.equals("safari")) {
			// System.setProperty("webdriver.ie.driver", "BrowserFiles/iedriver");
			driver = new SafariDriver();
		}
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Browser launched");
		test.log(LogStatus.PASS,BrowserType+" Browser launched"); 
		
		return Constants.PASS;
	}

	// navigating to website or url
	public String Navigate(String url) {
		test.log(LogStatus.INFO, "Navigating to" + prop.getProperty(url));
		driver.navigate().to(prop.getProperty(url));
		
		return Constants.PASS;
	}
	
	// navigating to previous page
		public String NavigateBack() {
			test.log(LogStatus.INFO, "Navigating to previous page");
			driver.navigate().back();
			System.out.println("Navigating Back to Previous Page");
			test.log(LogStatus.PASS, "Navigating Back to Previous Page");
			return Constants.PASS;
		}
		
		// navigating to forward page
		public String NavigateForward() {
			test.log(LogStatus.INFO, "Navigating to previous page");
			driver.navigate().forward();
			System.out.println(driver.getCurrentUrl());
			test.log(LogStatus.PASS, driver.getCurrentUrl());
			return Constants.PASS;
		}

	// clicking on url
	public String Click(String locator) {
		test.log(LogStatus.INFO, "Clicking to" + prop.getProperty(locator));
		WebElement e = getElement(locator);
		e.click();
		return Constants.PASS;
	}
	// entering element locator in dropdown or and element input
	public String Input(String locator, String data) {
		test.log(LogStatus.INFO, "Typing to" + prop.getProperty(locator));
		WebElement e = getElement(locator);
		e.sendKeys(data);
		return Constants.PASS;

		
	}

	// entering element locator in dropdown or and element input
	public String GetText(String locator) {
		test.log(LogStatus.INFO, "Getting Text" );
		WebElement e = getElement(locator);
		System.out.println("Printing the Page Header from 1st Search Results Page  - "+e.getText());
		test.log(LogStatus.PASS,"Printing the Page Header from 1st Search Results Page  - "+e.getText());
		test.log(LogStatus.PASS, "GetText");
		return Constants.PASS;

		
	}
	public String closeBrowser() {
		test.log(LogStatus.PASS, "Closing browser");
		driver.quit();
		return Constants.PASS;
	}
	// *************************************validation keywords ************

	public String VerifyText(String locator, String ExpectedText) {
		WebElement e = getElement(locator);
		String actualText = e.getText();
		if (actualText.equals(ExpectedText))
			return Constants.PASS;
		else
			return Constants.FAIL;
	}
	
	

	public String verifyElementPresent(String locatorKey) {
		boolean result = isElementPresent(locatorKey);
		if (result)
			return Constants.PASS;
		else
			return Constants.FAIL + " - Could not find Element " + locatorKey;
	}

	public String verifyElementNotPresent(String locatorKey) {
		boolean result = isElementPresent(locatorKey);
		if (!result)
			return Constants.PASS;
		else
			return Constants.FAIL + " - Could find Element " + locatorKey;
	}
	
	public String wait(String timeout) {

		try {
			Thread.sleep(Integer.parseInt(timeout));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.PASS;
	}

	// *************************************Utility FUNCTIONS ************
	public WebElement getElement(String locator) {
		WebElement e = null;
		// First - set the wait parameters
		try {if (locator.endsWith("_id"))
					e = driver.findElement(By.id(prop.getProperty(locator)));
				else if (locator.endsWith("_xpath"))
					e = driver.findElement(By.xpath(prop.getProperty(locator)));
				else if (locator.endsWith("_name"))
					e = driver.findElement(By.name(prop.getProperty(locator)));
				else if (locator.endsWith("_css"))
					e = driver.findElement(By.cssSelector(prop.getProperty(locator)));
				
			}
			// Then - declare the webElement and use a function to find it
               catch (Exception ex) {
			reportFailure("Failure in Element Extraction--" + locator);
			Assert.fail("Failure in Element Extraction--" + locator);

		}
		return e;
	}

	public boolean isElementPresent(String locatorKey) {
		List<WebElement> e = null;

		if (locatorKey.endsWith("_id"))
			e = driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_xpath"))
			e = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_name"))
			e = driver.findElements(By.name(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_css"))
			e = driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
		else 
			
			e = driver.findElements(By.cssSelector(locatorKey));
		if (e.size() == 0)
			return false;
		else
			return true;
	}

	/******************** REPORTING FUNCTIONS ********************/
	public void reportPass(String PassMessage) {
		//takeScreenShot();
		test.log(LogStatus.PASS, PassMessage);

	}

	public void reportFailure(String failureMessage) {
		takeScreenShot();
		test.log(LogStatus.FAIL, failureMessage);
	}

	public void takeScreenShot() {
		// decide name - time stamp
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String path = Constants.SCREENSHOT_PATH + screenshotFile;
		// take screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// embed
		test.log(LogStatus.INFO, test.addScreenCapture("/Users/sridhar/projects/rajiScreenshot"));

	}

}

