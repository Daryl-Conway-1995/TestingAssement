package com.qa.quickstart.AutoTesting;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrangeUsers {

	@FindBy(id="btnAdd")
	private WebElement addUser;
	
	@FindBy(id="searchSystemUser_userName")
	private WebElement username;
	
	@FindBy(id="searchSystemUser_employeeName_empName")
	private WebElement name;
	
	@FindBy(id="searchBtn")
	private WebElement searchBtn;

	public void searchUser(String uName, String rName,WebDriver driver,ExtentTest test)
	{
		username.sendKeys(uName);
		name.sendKeys(rName);
		searchBtn.click();
		//after search look for top result in the user info 
		WebElement result = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a"));
		try {
			assertEquals(uName,result.getText());
			test.log(LogStatus.PASS, "value of "+ uName +" was a match");
		}catch(Error e)
		{
			test.log(LogStatus.FAIL, "value of "+uName+" was incorrect");
		}
	}
	
	public void addUser()
	{
		addUser.click();
	}
}
