package com.qa.quickstart.AutoTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrangeDashboard {
	
	@FindBy(id="menu_admin_viewAdminModule")
	private WebElement userOptions;
	
	public void goToUsers()
	{
		userOptions.click();
	}
	

}
