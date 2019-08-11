package com.actitime.features;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.actitime.pageobjects.CreateNewUserPage;
import com.actitime.pageobjects.EditUserSettings;
import com.actitime.pageobjects.EnterTimeTrackpage;
import com.actitime.pageobjects.UserListPage;



public class UserFeatures 
{
	WebDriver driver;
	EnterTimeTrackpage ettp;
	UserListPage ulp;
	CreateNewUserPage cnup;
	EditUserSettings eus;
	
	public UserFeatures(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		ettp= new EnterTimeTrackpage(driver);
		ulp= new UserListPage(driver);
		cnup=new CreateNewUserPage(driver);
		eus=new EditUserSettings(driver);
	}
	
	public void CreateNewUser(String UserName,String PassNrePass,String FrstName,String LstName,String Email )
	{
		ettp.getUserEle().click();
		ulp.getCreateNewUserBtn().click();
		cnup.getUserNameTxBx().sendKeys(UserName);
		cnup.getPasswordTxBx().sendKeys(PassNrePass);	
		cnup.getReTypPasswordTxBx().sendKeys(PassNrePass);
		cnup.getFirstNameTxBx().sendKeys(FrstName);
		cnup.getLastNameTxBx().sendKeys(LstName);
		cnup.getEmailTxBx().sendKeys(Email);
		cnup.getCreateUserBtn().click();
			
	}
	public void VerifyCreateNewUser()
	{
		String ActualMsg = ulp.getCreate_DelUsrSuccessMsg().getText();
		String ExpMsg="User account has been successfully created.";
		Assert.assertEquals(ActualMsg, ExpMsg);
		String ActualUsername=ulp.getUserNameRowElm().getText();
		String ExpUsername="Devgan, Ajay (Ajay_Dev)";
		Assert.assertEquals(ActualUsername, ExpUsername);
		Reporter.log(ExpUsername+ExpMsg +" & Verified",true);
		
	}
	public void logout()
	{
		ettp.getLogoutlink().click();;
	}
	
	public void DeleteUser()
	{
		ettp.getUserEle().click();
		ulp.getUserNameRowElm().click();
		eus.getDeleteThisUserElm().click();
		
		
	}
	public void VerifyDeleteUser()
	{
		String ActualTxt = ulp.getCreate_DelUsrSuccessMsg().getText();	
		String ExpText="User account has been successfully deleted.";
		Assert.assertEquals(ActualTxt, ExpText);
		Reporter.log(ExpText+ "& Verified ",true);
	}
	
}
