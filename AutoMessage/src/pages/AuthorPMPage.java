package pages;

import org.openqa.selenium.support.FindBy;

import coreutils.WebItem;
import coreutils.WebPageInit;

public class AuthorPMPage extends WebPageInit  {

	@FindBy(xpath="//*[@id=\"Subject\"]")
	public WebItem subjectField;

	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div[2]/div[3]/div[2]/div/form/div[3]/div/iframe")
	public WebItem bodyField;

	@FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]")
	public WebItem recaptchaCheckBox;

	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div[2]/div[3]/div[2]/div/form/div[5]/div/button[2]")
	public WebItem saveAsDraftButton;
	
}


