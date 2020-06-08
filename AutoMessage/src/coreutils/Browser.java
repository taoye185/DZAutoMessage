package coreutils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	private static Browser browser;
	private static WebDriver driver;
//	private static ChromeDriver cr = new ChromeDriver();
//	private static WebDriverManager wm = new WebDriverManager();
	
	public static Browser getBrowser() {
		return browser;
	}

	public static void setBrowser(Browser browser) {
		Browser.browser = browser;
	}

	private void setDriver(WebDriver driver) {
		Browser.driver = driver;
		setPageLoadTime(Global.DEFAULT_IMPLICIT_WAIT);
		Browser.driver.manage().window().maximize();

	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			setBrowser(new Browser(Global.BROWSER));
		}
		return driver;
	}

	/**
	 * Constructor used for setting the driver
	 * 
	 * @param browserType- Browser you want to run the tests
	 */
	public Browser(String browserType) {

		Log.info("Creating an instance of a " + browserType + " browser");

		switch (browserType) {
		case Global.CHROME: {
//			WebDriverManager.chromedriver().setup();
			/*
			WebDriverManager.chromedriver().version("2.46").setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			//options.addArguments("--headless");
			//options.addArguments("--window-size=1920,1080");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("disable-gpu");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			this.setDriver(new ChromeDriver(options));
			*/
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
//			this.setDriver(new ChromeDriver());
//			waitForPageLoad(this.getDriver());
		}
			break;
		case Global.FIREFOX: {
			WebDriverManager.firefoxdriver().setup();
			this.setDriver(new FirefoxDriver());
		}
			break;
		case Global.INTERNET_EXPLORER:
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			this.setDriver(new InternetExplorerDriver(options));
			break;

		}
	}
	
	/**
	 * check page is loaded
	 * @param webdriver
	 */
	static void waitForPageLoad(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

	        @Override
	        public boolean apply(WebDriver input) {
	            return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
	        }

	    };
	    //wait.until(pageLoaded);
	}

	/**
	 * Refresh the page
	 */
	public static void refresh() {
		Log.info(String.format("Refreshing the Browser url"));
		getDriver().navigate().refresh();
	}

	/**
	 * Open the browser with page load time
	 * 
	 * @param url                     - the url you want to get
	 * @param currentPageLoadWaitTime
	 */
	public static void open(String url) {
		try {
			Log.info(String.format("Opening %s url", url));
			getDriver().get(url);
			
		} catch (TimeoutException ignored) {
		}
		setPageLoadTime(Global.DEFAULT_IMPLICIT_WAIT);
	}

	/**
	 * This method is useful to for Browser top sleep
	 * 
	 * @para time- time to pause browser in millisecond
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used for slow browser load
	 * 
	 * @param pageLoadWaitTime - implicit time to load a page
	 */
	public static void setPageLoadTime(int pageLoadWaitTime) {
		Log.info(String.format("Setting page load time to %s seconds", pageLoadWaitTime));
		driver.manage().timeouts().pageLoadTimeout(pageLoadWaitTime, TimeUnit.SECONDS);
	}

	/**
	 * Waits the browser for given waitTime in Seconds
	 * 
	 * @param waitTime
	 */
	public static void setWaitTime(int waitTime) {
		Log.info(String.format("Setting implicit wait time to %s seconds", waitTime));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	/**
	 * Verifies element is located on the page or not
	 * 
	 * @param by      - locator
	 * @param seconds
	 * @return
	 */
	public static boolean dynamicElementExists(By by, int seconds) {
		Log.info(String.format("Checking if '%s' text locator exists on the page withing %s seconds", by.toString(),
				seconds));
		try {
			(new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (TimeoutException ignored) {
		}
		return false;
	}

	/**
	 * Verifies given text exists on the page or not
	 * 
	 * @param text    - text which you want to verify
	 * @param seconds
	 * @return
	 */
	public static boolean textExists(String text, int seconds) {
		Log.info(String.format("Checking if '%s' text exists on the page withing %s seconds", text, seconds));
		return dynamicElementExists(By.xpath("//*[contains(text(),'" + text + "')]"), seconds);
	}
}
