package com.qa.quickstart.AutoTesting;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrangeSaveUser {
	
	private String Type_;
	private String Status_;
	private String name_;
	private String username_;
	private String password_;
	private Boolean succesful =false;


	//get all needed fields
	@FindBy(id="systemUser_userType")
	private WebElement userType;
	
	@FindBy(id="systemUser_employeeName_empName")
	private WebElement userRealName;
	
	@FindBy(id="systemUser_userName")
	private WebElement usernameInput;
	
	@FindBy(id="systemUser_status")
	private WebElement userStatus;
	
	@FindBy(id="systemUser_password")
	private WebElement passwordInput;
	
	@FindBy(id="systemUser_confirmPassword")
	private WebElement confPassword;
	
	@FindBy(id="btnSave")
	private WebElement saveUser;

	//takes in all user options and preforms simple checks.
	public void addUserDetails(String Type, String Status ,String name, String username, String password, String passwordConfirm,ExtentTest test)
	{
		if(Type.toLowerCase().equals("admin")||Type.toLowerCase().equals("ess"))
		{
			if(Status.toLowerCase().equals("enabled")||Status.toLowerCase().equals("disabled"))
			{
				if(password.equals(passwordConfirm))
				{
					Type_ = Type;
					Status_ = Status;
					name_ =name;
					username_= username;
					password_ =password;
					test.log(LogStatus.FAIL, "input was correct, temporarily storing user details");
					succesful =true;
				}
				else
				{
					succesful =false;
					test.log(LogStatus.FAIL, "passwords do not match");
				}
			}
			else
			{
				succesful =false;
				test.log(LogStatus.FAIL, "status not an option");
			}
		}else
		{
			succesful =false;
			test.log(LogStatus.FAIL, "type not an option");
		}
	}

	
	
	//simple method to check the success boolean
	public Boolean checkUserDetails(ExtentTest test)
	{
		test.log(LogStatus.INFO, "current details validity: "+succesful);
		return succesful;
	}
	
	

	//inputs user details to "add" screen.
	public void inputDetails(WebDriver driver)
	{
		//create action to select user type
		Actions builder = new Actions(driver);
		if(Type_.toLowerCase().equals("admin"))
		{
			try{
				Action dragNDrop = builder.click(userType).moveByOffset(50, 0).click().build();
				dragNDrop.perform();
				//should always work but catch just in case
			}catch(Exception e)
			{System.out.println(e);}
		}
		if(Type_.toLowerCase().equals("ess"))
		{
			try{
				Action dragNDrop = builder.click(userType).moveByOffset(100, 0).click().build();
				dragNDrop.perform();
				//should always work but catch just in case
			}catch(Exception e)
			{System.out.println(e);}
		}
		
		//add users real name. 
		//first add the starting letter of the name
		userRealName.sendKeys(name_.substring(0, 1));
		ArrayList<WebElement> NameListEven = new ArrayList<WebElement> (driver.findElements(By.className("ac_even")));
		ArrayList<WebElement> NameListOdd = new ArrayList<WebElement> (driver.findElements(By.className("ac_odd")));
		for (int i =0;i<NameListEven.size();i++)
		{
			if(NameListEven.get(i).getText().equals(name_))
			{
				NameListEven.get(i).click(); 
			}
		}
		for (int i =0;i<NameListOdd.size();i++)
		{
			if(NameListOdd.get(i).getText().equals(name_))
			{
				NameListOdd.get(i).click(); 
			}
		}
		
		//add userName
		usernameInput.sendKeys(username_);
		
		if(Status_.toLowerCase().equals("enabled"))
		{
			try{
				Action dragNDrop = builder.click(userStatus).moveByOffset(50, 0).click().build();
				dragNDrop.perform();
				//should always work but catch just in case
			}catch(Exception e)
			{System.out.println(e);}
		}
		if(Status_.toLowerCase().equals("disabled"))
		{
			try{
				Action dragNDrop = builder.click(userStatus).moveByOffset(100, 0).click().build();
				dragNDrop.perform();
				//should always work but catch just in case
			}catch(Exception e)
			{System.out.println(e);}
		}
		
		//add in user password
		passwordInput.sendKeys(password_);
		
		//send in password again
		confPassword.sendKeys(password_);
	}
	
	
	//saves the user to the system. will use the details input using addUserDetails()
	public void saveUser()
	{
		saveUser.click();
	}


}
