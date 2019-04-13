package com.N11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestBase {

	static WebDriver driver;
	Properties pro;

	public void pro() throws IOException {

		File src = new File(".OR.properties");

		// Create  FileInputStream object
		FileInputStream fis = new FileInputStream(src);

		// Create Properties class object to read properties file
		pro = new Properties();

		// Load file so we can use into our script
		pro.load(fis);
	}

	@BeforeTest
	public static void launchBrowser() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/apple/Desktop/Keytorc_Project/chromedrivere/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.n11.com");
	}

	 @AfterTest
	 public void terminateBrowser(){
	 driver.close();
	 }

	public void sendKeyId(WebDriver driver, String xpath, String value) {

		driver.findElement(By.id(xpath)).sendKeys(value);
	}

	public WebElement getWebElement(WebDriver driver, String xpath, String type) {

		WebElement ele =  null;
		switch (type) {
		case "id":
			ele = driver.findElement(By.id(xpath));
			break;
		case "xpath":
			ele = driver.findElement(By.xpath(xpath));
			break;
		case "className":
			ele = driver.findElement(By.className(xpath));
			break;
		}
		return ele;
	}

	public Boolean checkElementIsDisableClassName(WebDriver driver, String xpath) {

		if (driver.findElement(By.className(xpath)).isDisplayed())
			return true;
		else
			return false;
	}
	
	public void waitUntilElementClickable(WebDriver driver, int duraction, String xpath, String type) {

		WebDriverWait wait = new WebDriverWait(driver, duraction);
		switch (type) {
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			break;
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(xpath)));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(xpath)));
			break;
		}
		
	}

	public void waitLoad() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void waitUntilElementVisable(WebDriver driver, int duraction, String xpath, String type) {
		
		WebDriverWait wait = new WebDriverWait(driver, duraction);
		switch (type) {
		case "xpath":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			break;
		case "id":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(xpath)));
			break;
		case "className":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(xpath)));
			break;
		}
	}
}
