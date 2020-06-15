package pages;


import org.openqa.selenium.support.FindBy;

import coreutils.Global;
import coreutils.Utils;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class LoginPage extends WebPageInit {
	

	
	public void loginWithGoogle() throws InterruptedException {
		/**
		 * This method considers which site we will go to (currently only royalroad implemented)
		 * and proceeds with login
		 **/
		
		switch(Global.DEFAULT_SITE) {
		case "royalroad": {
		Utils.waitUntilClickable(loginWithGoogle);
		loginWithGoogle.click();
		Utils.waitUntilVisible(emailField);
		emailField.sendKeys(Global.ROYALROAD_USER_ACCOUNT);
		Utils.waitUntilClickable(emailNextButton);
		emailNextButton.click();
		Utils.waitUntilVisible(passwordField);
		passwordField.sendKeys(Global.ROYALROAD_PASSWORD);
		Utils.waitUntilVisible(passwordNextButton);
		passwordNextButton.click();
		break;
		}
		default: {
			
		}
		}
	}

	@FindBy(xpath= "//button[@label='Sign in']")
	public WebItem signInBtn;

	@FindBy(xpath= "//*[@id=\"external-account\"]/button[1]")
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
