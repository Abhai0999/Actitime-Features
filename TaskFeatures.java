package com.actitime.features;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.actitime.pageobjects.ActiveProjectCustPage;
import com.actitime.pageobjects.CreateNewProjectPage;
import com.actitime.pageobjects.CreateNewTaskPage;
import com.actitime.pageobjects.CreatenewCust;
import com.actitime.pageobjects.EditCustomerInformation;
import com.actitime.pageobjects.EnterTimeTrackpage;
import com.actitime.pageobjects.NewTaskPage;
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
	CreateNewTaskPage cntp;
	NewTaskPage ntp;
	
	public TaskFeatures(WebDriver driver)
	{
		this.driver=driver;	
		ettp=new EnterTimeTrackpage(driver);
		otp=new OpenTaskPage(driver);
		apcp=new ActiveProjectCustPage(driver);
		cncp=new CreatenewCust(driver);
		eci= new EditCustomerInformation(driver);
		cnpp=new CreateNewProjectPage(driver);
		cntp= new CreateNewTaskPage(driver);
		ntp=new NewTaskPage(driver);
	}
	//@Test(priority=1)
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

	//@Test(priority=2)
	  public void CreateProject(String CustomerName,String Projectname) 
	  {
	  ettp.getTaskEle().click(); 
	  otp.getPndCElm().click();
	  apcp.getCreateNewProject().click();
	  sp=new Select(cnpp.getDropdown());
	  sp.selectByVisibleText(CustomerName);
	  cnpp.getProjectName().sendKeys(Projectname); 
	  cnpp.getCreateProjBtn().click();
	  } 
	  
	  public void ValidateProject(String Projectname) 
	  { 
		  String ActualTxt = apcp.getProjCreatedMsgele().getText();
		  String ExpectedTxt = "Project \""+Projectname+"\" has been successfully created.";
	      Assert.assertEquals(ActualTxt, ExpectedTxt);
	      Reporter.log(ActualTxt+" & verified ",true);
	  }
	 //@Test(dependsOnMethods = {"CreateCustomer","CreateNewTask"})
public void CheckDelcustfunc(String CustomerName)
{
	ettp.getTaskEle().click();
	otp.getPndCElm().click();
	sp=new Select(apcp.getCustomerdrpdown());
	sp.selectByVisibleText(CustomerName);
	apcp.getCustrshowBtn().click();
	String custName = apcp.getCustrNameLink().getText();
	if (custName.equalsIgnoreCase(CustomerName)) 
	{
		apcp.getCustrNameLink().click();	
	}
	eci.getDelCustBtn().click();
	eci.getDelCustCnfbtn().click();
}
public void VerifyCheckDelcust()
{
	String ActualDelMsgtext = apcp.getDeleteMsg().getText();
	String ExpDelmsgTxt ="Customer has been successfully deleted.";
	Assert.assertEquals(ActualDelMsgtext, ExpDelmsgTxt);
	Reporter.log(ExpDelmsgTxt+"And Verified",true);
}
//@Test(priority=3)
public void CreateNewTask(String CustomerName,String Projectname,String TaskName)
{
	ettp.getTaskEle().click();
	otp.getCreateNewTasks().click();
	sp=new Select(cntp.getSelCustDrpdwn());
	sp.selectByVisibleText(CustomerName);
	sp=new Select(cntp.getSelProjDrpdwn());
	sp.selectByVisibleText(Projectname);
	cntp.getEntrTaskNameField().sendKeys(TaskName);
	Date dt=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("MMM dd,yyyy");
	String dateval=sdf.format(dt);
	cntp.getEntrDeadlineField().sendKeys(dateval);
	sp=new Select(cntp.getBillingTypDrpdwn());
	sp.selectByIndex(1);
	cntp.getMyTimeTrkCkBx().click();
	cntp.getCreateTskBtn().click();
}
public void ValidateCreateNewTask(String CustomerName,String Projectname,String TaskName)
{
	String Actualtxtmsg = otp.getTskSuccessmsg().getText();
	String ExpTextmsg="1 new task was added to the customer \""+CustomerName+"\", project \""+Projectname+"\".";
	Assert.assertEquals(Actualtxtmsg, ExpTextmsg);
	Reporter.log("Task Created And Validated ",true);	
}
//@Test(priority=4)
public void DelTask(String CustomerName,String Projectname,String TaskName )
{
	ettp.getTaskEle().click();
	otp.getTskSelDepdown().click();
	otp.getALLActivePro_CustRadioBtn().click();
	otp.getSelCustChkBx().click();
	otp.getSelTskCloseBtn().click();
	otp.getTskNameTxBx().sendKeys(TaskName);
	otp.getTskFiltrBtn().click();
	otp.getTaskSelLink().click();
	ntp.getDelTaskBtn().click();
	ntp.getCnfDelTasBtn().click();		
}
//@Test(dependsOnMethods = {"DelTask"})
public void VerifyDelTask()
{
	String ActualMsg = otp.getDelTskSuccMsg().getText();
	String expMsg = "Task has been successfully deleted.";
	Assert.assertEquals(ActualMsg, expMsg);
	Reporter.log(expMsg+ "& Verified ",true);
}
}

