package com.qa.quickstart.AutoTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrangeLoginPage {
	
	@FindBy (id="txtUsername")
	private WebElement user;
	
	@FindBy (id="txtPassword")
	private WebElement pass;
	
	@FindBy (id="btnLogin")
	private WebElement login;

	
	public void loginAdmin()
	{
		user.click();
		user.sendKeys("admin");
		pass.click();
		pass.sendKeys("admin");
		login.click();
	}
	
	public void loginUser(String userName,String password)
	{
		user.click();
		user.sendKeys(userName);
		pass.click();
		pass.sendKeys(password);
		login.click();
	}

}
