package com.ibm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage 
{

	//To click on 'Shop by Category'
	@FindBy(xpath="//*[@id='categories-menu']/ul/li/span")
	WebElement ShopCategoryEle;
	
	WebDriverWait wait;
	WebDriver driver;
	
	public UserPage(WebDriver driver, WebDriverWait wait) 
	{
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickonShopByCategory()
	{
		ShopCategoryEle.click();
	}
	

}
