package com.qa.quickstart.AutoTesting;

import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.quickstart.AutoTesting.OrangeLoginPage;
import com.qa.quickstart.AutoTesting.OrangeDashboard;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class assessment {
	
	WebDriver driver;
	static ExtentReports report;
	ExtentTest test;
	String type ="admin";
	String status ="enabled";
	String name="Linda Anderson";
	String username="linda";
	String password ="lindaPass";
	String passwordConf ="lindaPass";
	
	@Before
	public void firstFunction()
	{
		//load up basic window and maximize
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\chromedriver.exe");
		report = new ExtentReports("C:\\Users\\Admin\\Desktop\\adminReport.html",true);
		driver = new ChromeDriver();
		test = report.startTest("Given test");
		driver.manage().window().maximize();
	}
	
	@Given("^the Add Employee Tab$")
	public void setUpWeb()
	{
		try {
			//check if not null
			assertNotNull(driver);
			//proceed through the login page as admin and go to the add user page.
			driver.navigate().to("http://opensource.demo.orangehrmlive.com/index.php/dashboard");
			OrangeLoginPage oLoginPage= PageFactory.initElements(driver, OrangeLoginPage.class);    //login page
			oLoginPage.loginAdmin();
			OrangeDashboard adminPage= PageFactory.initElements(driver, OrangeDashboard.class);    //dashboard (defualt load page)
			adminPage.goToUsers();
			OrangeUsers addUser= PageFactory.initElements(driver, OrangeUsers.class);             //add user page
			addUser.addUser();
			test.log(LogStatus.PASS, "pages successfully loaded");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(Error E)
		{
			test.log(LogStatus.FAIL, "pages unsuccessfully loaded");
		}
	}
	
	@When("^I fill out the Employee Details correctly$")
	public void setUpUser()
	{
		OrangeSaveUser addUserPage= PageFactory.initElements(driver, OrangeSaveUser.class);
		addUserPage.addUserDetails(type, status, name, username, password, passwordConf, test);
	}
	
	@And("^I choose to create Login Details$")
	public void navigateToAccounts() 
	{
		OrangeSaveUser addUserPage= PageFactory.initElements(driver, OrangeSaveUser.class);
		addUserPage.addUserDetails(type, status, name, username, password, passwordConf, test);
	}
	
	@And("^I fill out the Login Details correctly$")
	public void addAccountDetails()
	{
		OrangeSaveUser addUserPage= PageFactory.initElements(driver, OrangeSaveUser.class);
		addUserPage.checkUserDetails(test);
		
	}
	
	@And("^I click the Save button$")
	public void saveAccount()
	{
		OrangeSaveUser addUserPage= PageFactory.initElements(driver, OrangeSaveUser.class);
		addUserPage.saveUser();
	}
	
	@Then("^I can see information about the user$")
	public void checkAccountDetails()
	{
		OrangeUsers addUser= PageFactory.initElements(driver, OrangeUsers.class);
		addUser.searchUser(username, name, driver, test);
		report.endTest(test);
		driver.quit();
		report.flush();
	}
	

}
