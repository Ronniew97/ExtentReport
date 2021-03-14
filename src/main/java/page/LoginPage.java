package page;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import util.BasePage;

public class LoginPage extends BasePage{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//input[@name='username']") WebElement USERNAME_FIELD;
	@FindBy(how = How.XPATH, using = "//input[@name='password']") WebElement PASSWORD_FIELD;
	@FindBy(how = How.XPATH, using = "//button[@name='login']") WebElement LOG_IN_BUTTON;
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),' Dashboard ')]") WebElement DASHBOARD_TEXT;
	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger fade in']") WebElement ERROR_MESSAGE;
	
	public void enterUserName(String userName) {
		USERNAME_FIELD.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		PASSWORD_FIELD.sendKeys(password);
	}
	
	public void clickLogInButton() {
		LOG_IN_BUTTON.click();
	}	
	
	public void validateDashboardPage() throws Exception {
		if(!DASHBOARD_TEXT.isDisplayed()) {
			throw new Exception("dashboard page isn't displayed");
		}
	}
	
	public void validateErrorMessage() throws Exception {
		Thread.sleep(3000);
		if(!ERROR_MESSAGE.isDisplayed()) {
			throw new Exception("error message isn't displayed");
		}
	}
	
	public void takeScreenShotAtEndOfTest(WebDriver driver, String testname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)(driver);
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File("screenshots//"+testname+System.currentTimeMillis()+".png"));
	}
}
