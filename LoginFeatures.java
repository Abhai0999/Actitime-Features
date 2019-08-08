package com.actitime.features;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.actitime.pageobjects.EnterTimeTrackpage;
import com.actitime.pageobjects.LoginPAge;
public class LoginFeatures 
{
 WebDriver driver;
 LoginPAge lp;
 EnterTimeTrackpage ettp;
 public LoginFeatures(WebDriver driver)
 {
	 this.driver=driver;
	 lp=new LoginPAge(driver);
	 ettp=new EnterTimeTrackpage(driver);
 }
 public void Login(String Username,String password)
 {
	lp.getUnTxBx().sendKeys(Username);
	lp.getPswTxBx().sendKeys(password);
	lp.getLoginBtn().click();		
 }
@Test (priority=1)
 public void VerifyValidLogin()
 { 
	 String actualText = ettp.getEttppageTitle().getText();
		String expText="Enter Time-Track";
		Assert.assertEquals(actualText, expText);
		String actualtitle = driver.getTitle();
		String expectedtitle="actiTIME - Enter Time-Track";
		Assert.assertEquals(actualtitle, expectedtitle);
		Reporter.log("Valid Login has been verified ",true);
 }
 @Test(priority=2)
 public void VerifyINvalidLogin()
 {
	 String actualmsg = ettp.getEttErrMsg().getText();
	String expectedmsg ="Username or Password is invalid. Please try again.";
	Assert.assertEquals(actualmsg, expectedmsg);
	Reporter.log("Invalid Login has been Verified ",true);
 }
}
