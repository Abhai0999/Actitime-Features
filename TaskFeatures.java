package com.actitime.features;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.actitime.pageobjects.ActiveProjectCustPage;
import com.actitime.pageobjects.CreateNewProjectPage;
import com.actitime.pageobjects.CreatenewCust;
import com.actitime.pageobjects.EditCustomerInformation;
import com.actitime.pageobjects.EnterTimeTrackpage;
import com.actitime.pageobjects.OpenTaskPage;
public class TaskFeatures 
{
	WebDriver driver;
	EnterTimeTrackpage ettp;
	OpenTaskPage otp;
	ActiveProjectCustPage apcp;
	CreatenewCust cncp;
	EditCustomerInformation eci;
	CreateNewProjectPage cnpp;
	Select sp;
	
	public TaskFeatures(WebDriver driver)
	{
		this.driver=driver;	
		ettp=new EnterTimeTrackpage(driver);
		otp=new OpenTaskPage(driver);
		apcp=new ActiveProjectCustPage(driver);
		cncp=new CreatenewCust(driver);
		eci= new EditCustomerInformation(driver);
		cnpp=new CreateNewProjectPage(driver);
		sp=new Select(cnpp.getDropdown());
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

	
	  public void CreateProject(String CustomerName,String Projectname) 
	  {
	  ettp.getTaskEle().click(); 
	  otp.getPndCElm().click();
	  apcp.getCreateNewProject().click(); sp.selectByValue(CustomerName);
	  cnpp.getProjectName().sendKeys(Projectname); 
	  cnpp.getCreateProjBtn().click();
	  } 
	  
	  public void ValidateProject(String Projectname) 
	  { 
		  String ActualTxt = apcp.getProjCreatedMsgele().getText();
		  String ExpectedTxt = "Project \""+Projectname+"\" has been successfully created.";
	      Assert.assertEquals(ActualTxt, ExpectedTxt);
	      Reporter.log(Projectname+" & verified ",true);
	  
	  }
	 
public void CheckDelcustfunc()
{
	ettp.getTaskEle().click();
	otp.getPndCElm().click();
	apcp.getCustomer().click();
	eci.getDelCustBtn().click();
	eci.getDelCustCnfbtn().click();
}
public void VerifyCheckDelcust()
{
	String ActualDelMsgtext = apcp.getDeleteMsg().getText();
	//ASSystem.out.println(ActualDelMsgtext);
	String ExpDelmsgTxt ="Customer has been successfully deleted.";
	Assert.assertEquals(ActualDelMsgtext, ExpDelmsgTxt);
	Reporter.log(ExpDelmsgTxt,true);
}
}

