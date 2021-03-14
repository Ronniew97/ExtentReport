package tests;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import page.LoginPage;
import util.BrowserFactory;

public class LoginTests {
	WebDriver driver;
	LoginPage login;

	static ExtentReports extent;
	
	@Before
	public static void configure(){
	String path = System.getProperty("user.dir")+"\\reports\\reports\\index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");

	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Ronnie Wainaina");
	}

	@Test()
	public void userWithValidCredentialsShouldBeAbleToLogIn() throws Exception {
		extent.createTest("Postive Login Test");
		driver = BrowserFactory.startBrowser();
		login = PageFactory.initElements(driver, LoginPage.class);
		login.enterUserName("demo@techfios.com");
		login.enterPassword("abc123");
		login.clickLogInButton();
		login.validateDashboardPage();
		login.takeScreenShotAtEndOfTest(driver, "positive_test");
		extent.flush();
	}

	@Test(dataProvider = "getData")
	public void userWithInvalidCredentialsShouldNotBeAbleToLogIn(String username, String password) throws Exception {
		extent.createTest("Negative Login Test");
		driver = BrowserFactory.startBrowser();
		login = PageFactory.initElements(driver, LoginPage.class);
		login.enterUserName(username);
		login.enterPassword(password);
		login.clickLogInButton();
		login.validateErrorMessage();
		login.takeScreenShotAtEndOfTest(driver, "negative_test");
		extent.flush();
	}


	@AfterMethod()
	public void closeBrowser() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] loginInfo = new Object[5][2];
		loginInfo[0][0] = "demo@techfios";
		loginInfo[0][1] = "abc123";
		loginInfo[1][0] = "demo@techfios.com";
		loginInfo[1][1] = "xyz";
		loginInfo[2][0] = "demo@teck";
		loginInfo[2][1] = "xds";
		loginInfo[3][0] = "demo@techfios.com";
		loginInfo[3][1] = "";
		loginInfo[4][0] = "";
		loginInfo[4][1] = "abc123";

		return loginInfo;
	}

}
