package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {

public 	WebDriver driver;              //constructor 
	
	Basepage (WebDriver driver )
	{
		this.driver = driver;
		PageFactory.initElements(driver, this );
	}
	
	
	
	
}
