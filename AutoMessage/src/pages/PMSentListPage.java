package pages;

import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import coreutils.Browser;
import coreutils.WebItem;
import coreutils.WebPageInit;
import dataStructure.BookList;
import dataStructure.BookRecord;

public class PMSentListPage extends WebPageInit {

//	@FindBy(xpath="/html/body/div[3]/div/div/div/div/div[2]/div[3]/div/div[2]/div/ul/li[6]/a")
//	public WebItem nextPageButton;
	


	public  boolean traverseAllSentPMsInCurrentPage() {
		int pageSize = 20;
		
		for (int i = 1; i<=pageSize; i++) {
			String authorName = generateXPathForIthAuthorInList(i);
	//		String contactDate = generateXPathForIthContactDateInList(i);
			try {
				ArrayList<BookRecord> bookByCorrespondingAuthor = BookList.getBookFromAuthor(Browser.getDriver().findElement(By.xpath(authorName)).getText());
				for (int j=0; j< bookByCorrespondingAuthor.size(); j++) {
					bookByCorrespondingAuthor.get(j).setFieldValue("dataCollectionDate", Calendar.getInstance().getTime().toGMTString());
					if (bookByCorrespondingAuthor.get(j).getFieldValue("actionPlanned").equals("PM") ||
							bookByCorrespondingAuthor.get(j).getFieldValue("actionPlanned").equals("")) {
						bookByCorrespondingAuthor.get(j).setFieldValue("actionPlanned","PM Sent");
					}
				}

			}
			catch(Exception e) {
				System.out.println(e);
				return false;
			}
					
		}
		return true;
	}
	
	public void readMultiplePages (int numPages) throws InterruptedException {
		for (int i = 1; i<= numPages; i++) {
			if (!this.traverseAllSentPMsInCurrentPage()) {
				break;
			}
			if ((i+1)<numPages) {
				try {
					Browser.getDriver().get("https://www.royalroad.com/private/2?page="+(i+1));
				}
				catch (Exception e) {
					System.out.println(e);
					break;
				}
			}
			Thread.sleep(3000);
		}
	}
	
	
	
	public  String generateXPathForIthAuthorInList(int i) {
		return "//*[@id=\"moveForm\"]/div/table/tbody/tr["+i+"]/td[3]/a";
	}
	
	public  String generateXPathForIthContactDateInList(int i) {
		return "//*[@id=\"moveForm\"]/div/table/tbody/tr["+i+"]/td[4]/time";
	}
}



