package com.actitime.features;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.actitime.pageobjects.ActiveProjectCustPage;
import com.actitime.pageobjects.CreatenewCust;
import com.actitime.pageobjects.EnterTimeTrackpage;
import com.actitime.pageobjects.OpenTaskPage;
public class TaskFeatures 
{
	WebDriver driver;
	EnterTimeTrackpage ettp;
	OpenTaskPage otp;
	ActiveProjectCustPage apcp;
	CreatenewCust cncp;
	public TaskFeatures(WebDriver driver)
	{
		this.driver=driver;	
		ettp=new EnterTimeTrackpage(driver);
		otp=new OpenTaskPage(driver);
		apcp=new ActiveProjectCustPage(driver);
		cncp=new CreatenewCust(driver);
	}
public void CreateCustomer(String CustomerName)
{
ettp.getTaskEle().click();
otp.getPndCElm().click();
apcp.getProjndCust().click();
cncp.getCustNametxbx().sendKeys(CustomerName);
cncp.getCreateCustSubmitBtn().click();
}
public void ValidateCreatenewCust(String CustomerName )
{
	String expmsg="Customer \""+CustomerName+"\" has been successfully created.";
	String actualmsg = apcp.getSuccessMsgele().getText();
	Assert.assertEquals(expmsg, actualmsg);
	Reporter.log(expmsg,true);
}
public void logout()
{
	ettp.getLogoutlink().click();
}
}
