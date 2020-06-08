package dataStructure;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import coreutils.Browser;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class HomePage extends WebPageInit {

	public  void navigateToOngoingFictionsListPage() throws InterruptedException {

		menuBarRead.click();
		Thread.sleep(1000);
		menuBarReadOngoingFictions.click();
		Thread.sleep(3000);
	}
	
	public void navigateToAuthorDetailPage(String url) throws InterruptedException {
		Browser.getDriver().get(url);
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
