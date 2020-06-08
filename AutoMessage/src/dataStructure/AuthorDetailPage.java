package dataStructure;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import coreutils.Browser;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class AuthorDetailPage extends WebPageInit {

	@FindBy(xpath= "//html/body/div[3]/div/div/div/div/div/div[1]/div[2]/h4/span[2]/a")
	public WebItem authorName;
	
	
	public String getAuthorName () {
		return this.authorName.getText();
	}
	
	public String getAuthorID () {
		return this.authorName.getAttribute("href");
	}
}


