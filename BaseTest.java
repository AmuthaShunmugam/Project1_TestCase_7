package com.ibm.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.AdminPage;
import com.ibm.pages.AdminPage1;
import com.ibm.pages.UserPage;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	PropertiesFileHandler propFIleHandler;
	HashMap<String, String> data;

	@BeforeSuite
	public void propertiesfile() throws IOException {
		String file = "./TestData/data.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		data = propFileHandler.getPropertiesAsMap(file);
	}

	@BeforeMethod
	public void BrowserInitialization() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void DeleteAndCheckProductInUserPage() throws InterruptedException, IOException {
		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String email = data.get("NewEmail");
		String number = data.get("Number");
		String Success = data.get("SuccessMessgae");
		driver.get(url);
		AdminPage1 home = new AdminPage1(driver, wait);
		home.EnetrEmailAddress(userName);
		home.takescreennshot(driver);
		home.EnetrPassword(password);
		home.ClickonLoginButton();
		home.ClickOnSystemOptions();
		home.ClickonSettings();
		home.EnterTheNewEmail(email);
		home.EnterTheNewPhone(number);
		home.ClickToGoTop();
		Thread.sleep(2000);
		home.ClickonTheSaveButton();
		Thread.sleep(2000);
		WebElement SuccessMsg = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
		String Msg = SuccessMsg.getText();
		System.out.println(Msg); 
		driver.navigate().to("https://atozgroceries.com");
		WebElement Mail1 = driver.findElement(By.xpath("//div[@class='header-mid-right-content'][2]"));
		String Mail = Mail1.getText();
		System.out.println("The mail updated in admin page:" + email);
		System.out.println("The mail updated in Top user  page:" + Mail);
		System.out.println("................................................................");
		Assert.assertEquals(Mail, email);
		WebElement Phone1 = driver.findElement(By.xpath("//div[@class='header-mid-right-content'][1]"));
		String Phone = Phone1.getText().trim().replace("+91", "").replaceAll(" ", "");
		System.out.println("The number updated in admin page:" + number);
		System.out.println("The number updates in Top user  page:" + Phone);
		System.out.println("................................................................");
		Assert.assertEquals(Phone, number);
		home.ScrollUpDownInPage();
		WebElement Phone2=driver.findElement(By.xpath("//div[@class='col-md-4 col-sm-6 has-logo']/div/p[2]"));
		String Phone3 = Phone2.getText().replace("Phone:","").replaceAll(" ","").replace("+91", "").replaceAll(" ","").replaceAll(" ","").trim();
		System.out.println("The number updated in admin page:" +number);
		System.out.println("The number updated in Bottom user page:"+Phone3);
		System.out.println("................................................................");
		Assert.assertEquals(number,Phone3);
		WebElement Mail2=driver.findElement(By.xpath("//div[@class='col-md-4 col-sm-6 has-logo']/div/p[3]"));
		String Mail3= Mail2.getText().replaceAll("Email:","").replaceAll(" ","").trim();
		System.out.println("The email updated in admin page:" +email);
		System.out.println("The email updated in Bottom user page:"+Mail3);
		System.out.println("................................................................");
		Assert.assertEquals(email, Mail3);
		
		
	}
}
