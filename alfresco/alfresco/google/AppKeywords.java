package qa.alfresco.google;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qa.alfresco.google.util.Constants;

public class AppKeywords extends GenericKeywords {
	
	public AppKeywords(ExtentTest test)   {
		super(test);
	}

	public String GoogleHomePage(String GoogleUkHomePage) {
		String searchBar = "searchBar";
		String googleUKLogo = "googleUKLogo";
		String GoogleHomeSettings = "GoogleHomeSettings";

		test.log(LogStatus.INFO, "Navigating to Google Uk hompe page url - " + prop.getProperty(GoogleUkHomePage));
		System.out.println("Navigating to Google Uk hompe page url - "+ prop.getProperty(GoogleUkHomePage) );
		driver.navigate().to(prop.getProperty(GoogleUkHomePage));
		
		// Verifying the Google uk Home page 
		Boolean check1=driver.findElement(By.xpath(prop.getProperty(searchBar))).isDisplayed();
		Boolean check2=driver.findElement(By.xpath(prop.getProperty(googleUKLogo))).isDisplayed();
		Boolean check3 = driver.findElement(By.xpath(prop.getProperty(GoogleHomeSettings))).isDisplayed();;
		
		if(check1 && check2 && check3) {
		System.out.println("landing page loaded correctly with search bar displayed = "+ check1 );
		System.out.println("Google UK logo displayed = "+ check2);
		System.out.println("Search options displayed = "+ check3);
		test.log(LogStatus.INFO, "landing page loaded correctly with search bar displayed = "+ check1 );
		test.log(LogStatus.INFO, "Google UK logo displayed = "+ check2);
		test.log(LogStatus.INFO, "Search options displayed = "+ check3);
		test.log(LogStatus.PASS, "GoogleHomePage");
		}else 
		{
			test.log(LogStatus.FAIL, "Landing page did not load properly");
		}
		
		return Constants.PASS;

	}
	
public String VisitFirstSearchResult(String Firstlink_css ) {
	
	WebElement e = getElement(Firstlink_css);
	e.click();
	System.out.println("First page from the Search Result loaded succesfully, Window Title of the page is = "+"'"+ driver.getTitle()+"'" );
	test.log(LogStatus.INFO, "First page from the Search Result loaded succesfully, Window Title of the page is = "+"'"+ driver.getTitle()+"'" );
	test.log(LogStatus.PASS, "VisitFirstSearchResult");
		return Constants.PASS;

	}


public String VerifySearchResults() {
	
	//Dynamically finds how many results are shown and prints out the time line
	String SearchresultTimeline1_css = "SearchresultTimeline1_css";
	String SearchresultTimeline2_css = "SearchresultTimeline2_css";
	int i = 1;

	while (isElementPresent(prop.getProperty(SearchresultTimeline1_css)+i+prop.getProperty(SearchresultTimeline2_css))) {
		System.out.println("Time line of Search Result - "+i+" is "+" '"+driver.findElement(By.cssSelector((prop.getProperty(SearchresultTimeline1_css)+i+prop.getProperty(SearchresultTimeline2_css)))).getText()+"'"+"result");
		test.log(LogStatus.INFO,"Time line of Search Result - "+i+" is "+" '"+driver.findElement(By.cssSelector((prop.getProperty(SearchresultTimeline1_css)+i+prop.getProperty(SearchresultTimeline2_css)))).getText()+"'"+"result");
		i++;
	}
		
		System.out.println("Results are shown within past 7 days");
		test.log(LogStatus.INFO,"Results are shown within past 7 days");
		test.log(LogStatus.PASS, "VerifySearchResults");
		return Constants.PASS;

	}


public String GoogleSearch(String locator, String data) {
	WebElement e = getElement(locator);
	e.click();
	System.out.println("Searching for -" +data);
	test.log(LogStatus.INFO,"Searching for -" +data);
	e.sendKeys(data);
	e.submit();
	
	System.out.println("Search results are displayed for -"+ data);
	test.log(LogStatus.INFO,"Search results are displayed for -"+ data);
	test.log(LogStatus.PASS, "GoogleSearch");
	return Constants.PASS;

}
	
	
public String VerifyGoogleHomepage() {
		String searchBar_xpath = "searchBar_xpath";
		String searchBar = "searchBar";
		String googleUKLogo= "googleUKLogo";
		String GoogleHomeSettings= "GoogleHomeSettings";
		driver.navigate().back();
		driver.navigate().back();
		test.log(LogStatus.INFO,"Navigating Back to Google Uk Home Page");
		System.out.println("Navigating Back to Google Uk Home Page");
		WebElement e = getElement(searchBar_xpath);
		e.click();
		e.sendKeys("");
		e.submit();
		
		
		// Verifying the Google uk Home page 
		Boolean check1=driver.findElement(By.xpath(prop.getProperty(searchBar))).isDisplayed();
		Boolean check2=driver.findElement(By.xpath(prop.getProperty(googleUKLogo))).isDisplayed();
		Boolean check3 = driver.findElement(By.xpath(prop.getProperty(GoogleHomeSettings))).isDisplayed();
		
		if(check1 && check2 && check3) {
		System.out.println("Empty search bar displayed = "+ check1);
		System.out.println("Google UK logo displayed = "+ check2 );
		System.out.println("Search options displayed at the bottom right corner = "+ check3);
		test.log(LogStatus.INFO,"Empty search bar displayed = "+ check1);
		test.log(LogStatus.INFO,"Google UK logo displayed = "+ check2 );
		test.log(LogStatus.INFO,"Search options displayed at the bottom right corner = "+ check3);
		test.log(LogStatus.PASS, "VerifyGoogleHomepage");
		}else 
		{
			test.log(LogStatus.FAIL,"Landing page did not load properly");
		}
		return Constants.PASS;

	}

public String SearchFilterByTime(String locator) {
	String GSearchTools_xpath="GSearchTools_xpath";
	String AnyTime_xpath="AnyTime_xpath";
	driver.findElement(By.xpath(prop.getProperty(GSearchTools_xpath))).click();
	wait("2000");
	driver.findElement(By.xpath(prop.getProperty(AnyTime_xpath))).click();
	wait("2000");
	WebElement e = getElement(locator);
	new Actions(driver).click(e).perform();
	
	e.sendKeys("Past Week");
	wait("3000");
	test.log(LogStatus.INFO,"Resutls are filtered & sorted by -"+driver.findElement(By.xpath(prop.getProperty(AnyTime_xpath))).getText());
	System.out.println("Resutls are filtered & sorted by -"+driver.findElement(By.xpath(prop.getProperty(AnyTime_xpath))).getText());
	test.log(LogStatus.PASS, "SearchFilterByTime");
	return Constants.PASS;
}



}
