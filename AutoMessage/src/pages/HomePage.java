package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import coreutils.Browser;
import coreutils.Global;
import coreutils.Utils;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class HomePage extends WebPageInit {

	public void navigateToLoginPage() throws InterruptedException {
		/**
		 * This method considers which site we will go to (currently only royalroad implemented)
		 * and opens the login page of that site
		 **/
		switch(Global.DEFAULT_SITE) {
		case "royalroad": {
			Browser.open(Global.ROYALROAD_LOGIN_URL);		
			break;
		}
		default: {
			
		}
		}

}

	public  void navigateToOngoingFictionsListPage() throws InterruptedException {
		/**
		 * This method considers which site we will go to (currently only royalroad implemented)
		 * and opens the book listing page of that site
		 **/
		switch(Global.DEFAULT_SITE) {
		case "royalroad": {
		Utils.waitUntilClickable(menuBarRead);
		menuBarRead.click();
		Utils.waitUntilClickable(menuBarReadOngoingFictions);
		menuBarReadOngoingFictions.click();
		break;
		}
		default: {
			
		}
		}
	}
	
	public void navigateToAuthorDetailPage(String url) throws InterruptedException {
		Browser.getDriver().get(url);
	}
	
	public void navigateToAuthorPMPage(String url) throws InterruptedException {
		Browser.getDriver().get(url);
		Thread.sleep(3000);
	}
	
	public void navigateToPMSentListPage() throws InterruptedException {
		Browser.getDriver().get("https://www.royalroad.com/private/2");
		Thread.sleep(3000);
	}

	@FindBy(id = "__ufs__merchantAccountNumber")
	public WebItem usernameTxtBox;

	@FindBy(id = "__ufs__email")
	public WebItem emailTxtBox;	
	
	@FindBy(id = "__ufs__password")
	public WebItem passwordTxtBox;

	@FindBy(xpath= "/html/body/div[2]/div[2]/div/div/ul/li[1]/a")
	public  WebItem menuBarRead;
	
	@FindBy(xpath= "/html/body/div[2]/div[2]/div/div/ul/li[1]/ul/li[2]/a")
	public  WebItem menuBarReadOngoingFictions;
	

}
