package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Testbase.Baseclass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports Extent;
	public ExtentTest Test;
	
	String repName;
	 public void onStart(ITestContext testContext)
	 {
		 /*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  * Dare dt= new Date();
		  * string currentdatetimestamp = df.format(dt);
		  * 
		  */
		 
		 
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report" + timestamp+".html";
		sparkReporter =new ExtentSparkReporter(".\\reports\\" +repName );// specify location of the report
		sparkReporter.config().setDocumentTitle(" opencart automation report");
		sparkReporter.config().setReportName(" opencart functional testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
	
		
		Extent  = new ExtentReports();
		Extent.attachReporter(sparkReporter);
		Extent.setSystemInfo("Application ", " opencart");
		Extent.setSystemInfo("Module", " Admin");
		Extent.setSystemInfo(" Sub module", " customers");
		Extent.setSystemInfo("username", System.getProperty("user.name"));
		Extent.setSystemInfo("Envirnoment", " QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		Extent.setSystemInfo("operating system", os);
		
		String br = testContext.getCurrentXmlTest().getParameter("browser");
		Extent.setSystemInfo("Browser", br);
		
List<String>IncludedGroups =	testContext.getCurrentXmlTest().getIncludedGroups();
if (IncludedGroups.isEmpty())
{
	Extent.setSystemInfo("groups",IncludedGroups.toString() );
}

	 }
	
	 public void onTestSuccess(ITestResult result)
	 {
		 Test = Extent.createTest(result.getMethod().getMethodName());

		Test.assignCategory(result.getMethod().getGroups());
		Test.log(Status.PASS,result.getName()+ "got successfully excuted ");
		
		 
	 }
	 
	 public void onTestFailure(ITestResult result)
	 {
		 Test = Extent.createTest(result.getMethod().getMethodName());

			Test.assignCategory(result.getMethod().getGroups());
			Test.log(Status.FAIL,result.getName()+ "got failed ");
			Test.log(Status.INFO,result.getThrowable().getMessage());
			
		try {	
		String imgpath	= new Baseclass().captureScreen(result.getName());
		Test.addScreenCaptureFromPath(imgpath);
		}catch (Exception e){
			e.printStackTrace();
		}
			
	 }
	 public void onTestSkipped(ITestResult result)
	 {
		 Test = Extent.createTest(result.getMethod().getMethodName());

			Test.assignCategory(result.getMethod().getGroups());
			Test.log(Status.SKIP,result.getName()+ "test skipped ");
			Test.log(Status.INFO,result.getThrowable().getMessage());
	 }
	 public void onFinish(ITestContext testContext)
	 {
		 Extent.flush();
String Pathofextentreport =  System.getProperty("user.dir")+"\\reports\\"+repName;
File extentreport = new File (Pathofextentreport);
try
{
	Desktop.getDesktop().browse(extentreport.toURI());
} catch (Exception e)
{
	e.printStackTrace();
}
	 }
	 

}
