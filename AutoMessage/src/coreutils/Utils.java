package coreutils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static void waitUntilVisible(String xpath) { 
	WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Global.DEFAULT_IMPLICIT_WAIT);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public static void waitUntilClickable(String xpath) { 
	WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Global.DEFAULT_IMPLICIT_WAIT);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public static void waitUntilVisible(WebItem item) { 
	WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Global.DEFAULT_IMPLICIT_WAIT);
	wait.until(ExpectedConditions.visibilityOf(item));
	}
	
	public static void waitUntilClickable(WebItem item) { 
	WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Global.DEFAULT_IMPLICIT_WAIT);
	wait.until(ExpectedConditions.elementToBeClickable(item));
	}
	
	
}
