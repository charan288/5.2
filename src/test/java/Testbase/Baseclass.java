package Testbase;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;



public class Baseclass {

public  static WebDriver driver;
public Logger logger;
public Properties p;	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"sanity","Regression","Master"} )
	@Parameters ({"os" ,"browser"})
	public void setup(String os, String br) throws IOException
	{
		
	logger= 	LogManager.getLogger(this.getClass());
	// loading config file

	
	FileReader file = new FileReader("./src//test//resources//config.properties");
	p= new Properties ();
	p.load(file);
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
            {
	DesiredCapabilities capabilities = new DesiredCapabilities();
	//capabilities.setPlatform(Platform.WIN11);    here os and browser name shoud import from xml 
	//capabilities.setBrowserName("Chrome");
	          //os
	       	if( os.equalsIgnoreCase("window"))
	       	{
	       		capabilities.setPlatform(Platform.WIN11);
	       	}
	       	else if (os.equalsIgnoreCase("mac") )
	       	{
	       		capabilities.setPlatform(Platform.MAC);
	       	}
	       	else
	       	{
	       		System.out.println(" no matching os");
	       		return;
	       		
	       	}
	

           	//browser
	
	switch(br.toLowerCase())
	{
	
	case "chrome" : capabilities.setBrowserName("Chrome"); break;
	case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
	default  : System.out.println( " no matching browser"); return;
	}
	       	
	  driver = new RemoteWebDriver(new URL("http://192.168.151.20:4444/wd/hub"),capabilities )    ;	
	        	
             }
	
	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		switch(br.toLowerCase())
		{
		case "chrome" : driver= new ChromeDriver();break;
		case "edge" : driver= new EdgeDriver();break;
		case "firefox" : driver= new FirefoxDriver();break;	
		default : System.out.println("invalid browsername"); return;
		}	
	}
	
	
	
	
	
	
		//driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(p.getProperty("appURL"));// reading url from properties file 
		driver.manage().window().maximize();
	
		
		
		
	}
	
	
	@AfterClass(groups = {"sanity","Regression","Master"} )
	public	void teardown()
	{
		driver.quit();
	}
	public String 	randomstring ()

	{
String generatestring = 	RandomStringUtils.randomAlphabetic(7);
	
		return generatestring;
		
	}
	
	public String 	randomnumber ()

	{
	String generatenumber = 	RandomStringUtils.randomNumeric(10);

		return generatenumber;
		
	}
	public String 	randomalphanumeri ()

	{
	String generatenumber = 	RandomStringUtils.randomNumeric(3);
	String generatestring = 	RandomStringUtils.randomAlphabetic(3);
		return (generatenumber + generatestring);

		
	}


	public String captureScreen(String tname)
	{
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
	File sourcefile	 = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
	String	targetFilepath = System.getProperty("user.dir")+"\\Screenshots\\"+ tname+ timestamp +".png";
	File targetfile = new File(targetFilepath);
	sourcefile.renameTo(targetfile);
	return targetFilepath;
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
