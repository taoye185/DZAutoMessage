package dataStructure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import coreutils.Browser;
import coreutils.WebItem;
import coreutils.WebPageInit;

public class ListPage extends WebPageInit {

	public  void navigateToLastPage() throws InterruptedException {

		lastPageButton.click();
		Thread.sleep(3000);
//		this.navigateToDetailsPage();
	}
	
	public  void traverseAllFictionsInCurrentPage() {
		int pageSize = 20;
		
		for (int i = 1; i<=pageSize; i++) {
			String fictionName = generateXPathForIthFictionNameInList(i);
			String followerCount = generateXPathForIthFollowerInList(i);
			String pageCount = generateXPathForIthTotalPageInList(i);
			String viewCount = generateXPathForIthTotalViewInList(i);
			String chaptersCount = generateXPathForIthTotalChapterInList(i);
			String lastUpdateDate = generateXPathForIthLastUpdateTimeInList(i);
			
			try {
				BookRecord book = new BookRecord(Browser.getDriver().findElement(By.xpath(fictionName)).getText(),
						Browser.getDriver().findElement(By.xpath(fictionName)).getAttribute("href"),
						Browser.getDriver().findElement(By.xpath(followerCount)).getText(),
						Browser.getDriver().findElement(By.xpath(pageCount)).getText(),
						Browser.getDriver().findElement(By.xpath(viewCount)).getText(),
						Browser.getDriver().findElement(By.xpath(chaptersCount)).getText(),
						Browser.getDriver().findElement(By.xpath(lastUpdateDate)).getText());
				BookList.updateRecordToList(book);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				i=pageSize+1;
			}
					
		}
	}
	

	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div[4]/ul/li[7]/a")
	public  WebItem lastPageButton;
	
	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div[4]/ul/li[2]/a")
	public  WebItem previousPageButton;
	
	
	
	public  String generateXPathForIthFictionNameInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/h2/a";
	}

	public  String generateXPathForIthFollowerInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/div[2]/div[1]/span";
	}
	
	public  String generateXPathForIthTotalPageInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/div[2]/div[3]/span";
	}

	public  String generateXPathForIthTotalViewInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/div[2]/div[4]/span";
	}

	public  String generateXPathForIthTotalChapterInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/div[2]/div[5]/span";
	}

	public  String generateXPathForIthLastUpdateTimeInList(int i) {
		return "//*[@id=\"result\"]/div["+i+"]/div/div[2]/div[6]/span/time";
	}

	
	
	public void navigateToDetailsPage() {
		Browser.getDriver().findElement(By.xpath(this.generateXPathForIthFictionNameInList(1))).click();
	}
	
	public void readMultiplePages (int numPages) throws InterruptedException {
		for (int i = 1; i<= numPages; i++) {
			this.traverseAllFictionsInCurrentPage();
			if (i<numPages) {
			this.previousPageButton.click();
			}
			Thread.sleep(3000);
		}
	}
	
}


