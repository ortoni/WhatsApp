package sms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


public class ApiWhastsAppBuilder{
	Actions act;
	RemoteWebDriver driver;
	WebDriverWait wait;
	public String dataSheetName = "msgData";



	@BeforeClass
	public void scan() {
		String phoneNumber = "9962251412";
		driver.get("https://api.whatsapp.com/send?phone=91" + phoneNumber + "&text=**HI**");
		try {
			Thread.sleep(100);
			WebElement send = driver.findElementByLinkText("SEND");
			waitAndClick(send);
		} catch (Exception e1) {
		}
		// To use WhatsApp on your computer: Scanning Required
		try {
			WebElement scan = driver.findElementByXPath("//div[text()='To use WhatsApp on your computer:']");
			waitTillDisappear(scan);
		} catch (Exception e) {
		}
	}

	@AfterSuite
	public void quit() {
		// logout and quit
		try {
			WebElement menuIcon = driver.findElementByXPath("(//div[@role='button' and @title='Menu'])[1]");
			WebElement logOut = driver.findElementByXPath("//div[text()='Log out']");
			mouseClick(menuIcon);
			mouseClick(logOut);
		} catch (Exception e) {
			System.out.println("Not Logged out, pls do manually log out in your application");
		}
		finally {
			driver.quit();
		}
	}

	@BeforeSuite
	public void launch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void waitAndClick(WebElement element) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void waitTillDisappear(WebElement element) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitUnitiVisible(WebElement element) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void mouseClick(WebElement ele) {
		act  = new Actions(driver);
		act.moveToElement(ele).pause(100).click().perform();
	}

	public void waitTillAlertDiasable() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	@DataProvider(name="whatsAppApi")
	public  Object[][] getData(){
		System.out.println("im running");
		return new DataInputProvider().getSheet(dataSheetName);
	}	

}
