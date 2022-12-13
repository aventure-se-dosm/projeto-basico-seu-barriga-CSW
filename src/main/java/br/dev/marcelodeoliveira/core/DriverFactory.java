package br.dev.marcelodeoliveira.core;

import org.junit.After;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	private static WebDriver driver;
	private Dimension dimension;

	private DriverFactory() {
		dimension = new Dimension(1280, 720);
	}

	
	public Dimension getDimension() {
		return this.dimension;
	}
	public static WebDriver getDriver() {
		if (driver == null)
			setDriver();
			//driver.manage().window().setSize(dimension);
		return driver;
	}
	
	public static void setDriver()
	{
		
		switch(Propriedades.default_browser) {
		
		case CHROME:
			driver = new ChromeDriver(); break;
			
		case EDGE:
			driver =  new EdgeDriver();  break;
			
		 case SAFARI:
			driver = new SafariDriver(); break;
			
		case FIREFOX:
		default:
			driver =  new FirefoxDriver();
			
		}
		
		
	}



	public static void killDriver() {
			
		
		if (driver != null) {
			driver.close();
			driver = null;
		}
		
	}


}
