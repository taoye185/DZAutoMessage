package dataStructure;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import coreutils.Global;
import coreutils.Browser;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class LoginPage extends WebPageInit {
	



	public void navigateToLoginPage() throws InterruptedException {
				Browser.open(Global.URL);		
				Thread.sleep(3000);
	}
	
	public void loginWithGoogle() throws InterruptedException {
		loginWithGoogle.click();
		Thread.sleep(3000);
		emailField.sendKeys("goodnovelrecruit@gmail.com");
		Thread.sleep(1000);
		emailNextButton.click();
		Thread.sleep(3000);
		passwordField.sendKeys("Thankyou1");
		Thread.sleep(1000);
		passwordNextButton.click();
		Thread.sleep(3000);
	}

	@FindBy(id = "__ufs__merchantAccountNumber")
	public WebItem usernameTxtBox;

	@FindBy(id = "__ufs__email")
	public WebItem emailTxtBox;	
	
	@FindBy(id = "__ufs__password")
	public WebItem passwordTxtBox;

	@FindBy(xpath= "//button[@label='Sign in']")
	public WebItem signInBtn;

	@FindBy(xpath= "/html/body/div/div[2]/div/form[1]/button[1]")
	public  WebItem loginWithGoogle;
	


	@FindBy(xpath= "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul/li[3]")
	public WebItem chooseGoogleAccount;
	
	
	@FindBy(xpath= "//*[@id=\"identifierId\"]")
	public  WebItem emailField;
	
	@FindBy(xpath= "//*[@id=\"identifierNext\"]")
	public  WebItem emailNextButton;
	
	@FindBy(xpath= "//*[@id=\"password\"]/div[1]/div/div[1]/input")
	public  WebItem passwordField;
	
	
	@FindBy(xpath= "//*[@id=\"passwordNext\"]")
	public  WebItem passwordNextButton;
	

}
