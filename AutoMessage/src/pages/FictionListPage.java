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
import dataStructure.BookList;
import dataStructure.BookRecord;

public class FictionListPage extends WebPageInit {

	public  void navigateToLastPage() throws InterruptedException {
		/**
		 * This method considers which site we will go to (currently only royalroad implemented)
		 * and goes to the last page of the list
		 **/
		switch(Global.DEFAULT_SITE) {
		case "royalroad": {
			Utils.waitUntilClickable(lastPageButton);
			lastPageButton.click();
			break;
		}
		default: {
			
		}
		}
	}
	
	public int traverseAllFictionsInCurrentPage() {
		/**
		 * This method considers which site is in (currently only royalroad implemented)
		 * and reads in every book in the listing page, add them to the database
		 * This method will return the number of new books found and added in the current
		 * listing page
		 **/
		int newBookCount = 0;
		int pageSize = 20;
		
		for (int i = 1; i<=pageSize; i++) {
			String fictionName = generateXPathForIthFictionNameInList(i);
			String followerCount = generateXPathForIthFollowerInList(i);
			String pageCount = generateXPathForIthTotalPageInList(i);
			String viewCount = generateXPathForIthTotalViewInList(i);
			String chaptersCount = generateXPathForIthTotalChapterInList(i);
			String lastUpdateDate = generateXPathForIthLastUpdateTimeInList(i);
			try {
				Utils.waitUntilVisible(fictionName);
				Utils.waitUntilVisible(lastUpdateDate);
				BookRecord book = new BookRecord(Browser.getDriver().findElement(By.xpath(fictionName)).getText(),
						Browser.getDriver().findElement(By.xpath(fictionName)).getAttribute("href"),
						Browser.getDriver().findElement(By.xpath(followerCount)).getText(),
						Browser.getDriver().findElement(By.xpath(pageCount)).getText(),
						Browser.getDriver().findElement(By.xpath(viewCount)).getText(),
						Browser.getDriver().findElement(By.xpath(chaptersCount)).getText(),
						Browser.getDriver().findElement(By.xpath(lastUpdateDate)).getText());
				int newCount = BookList.updateRecordToList(book)? 1:0;
				newBookCount += newCount;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				break;
			}
					
		}
		return newBookCount;
	}
	

	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div[4]/ul/li[7]/a")
	public  WebItem lastPageButton;
	
	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div[4]/ul/li[2]/a")
	public  WebItem previousPageButton;
	
	@FindBy(xpath= "/html/body/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div[4]/ul/li[1]/a")
	public  WebItem firstPageButton;
	
	
	
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
		/**
		 * This method reads a specified number of book listing pages backward and add/update the 
		 * corresponding books in the listing to database
		 * @param: numPages - number of listing pages to be read
		 **/
		for (int i = 1; i<= numPages; i++) {
			this.traverseAllFictionsInCurrentPage();
			if (i<numPages) {
				try {
					Utils.waitUntilClickable(previousPageButton);
					if (previousPageButton.getText().equals("‹ Previous")) {
						this.previousPageButton.click();}
					else if (firstPageButton.getText().equals("« First")) {
						this.firstPageButton.click();
					}
					else {
						System.out.println("already in first page");
						break;
					}
				}
				catch (Exception e) {
				}
			}
		}
	}
	
	public int readUntilNewBooksRecorded (int numNewBooks) throws InterruptedException {
		/**
		 * This method reads through multiple listing pages until either a specified number of
		 * new books has been recorded or all listing pages has been read
		 * This method will return the number of new books added to database
		 * 		 
		 * @param: numNewBooks - the target number of new books to be added to database
		 **/
		int newBookCount = this.traverseAllFictionsInCurrentPage();
		boolean readThroughAllPages = false;
		while (newBookCount < numNewBooks && !readThroughAllPages) {
				try {
					Utils.waitUntilClickable(previousPageButton);
					if (previousPageButton.getText().equals("‹ Previous")) {
						this.previousPageButton.click();}
					else if (firstPageButton.getText().equals("« First")) {
						this.firstPageButton.click();
					}
					else {
						System.out.println("already in first page");
						readThroughAllPages = true;
					}
				}
				catch (Exception e) {
				}
				newBookCount += this.traverseAllFictionsInCurrentPage();
			}
		return newBookCount;
		}
	
	
}


