import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import coreutils.Browser;
import coreutils.CustomizedExcelIO;
import coreutils.Global;
import dataStructure.BookList;
import dataStructure.BookRecord;
import pages.AuthorDetailPage;
import pages.AuthorPMPage;
import pages.FictionListPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PMSentListPage;

public class Main {

	static LoginPage lp = new LoginPage();
	static HomePage hp = new HomePage();
	static FictionListPage listp = new FictionListPage();
	static AuthorDetailPage ap = new AuthorDetailPage();
	static AuthorPMPage apmp = new AuthorPMPage();
	static PMSentListPage pmslp = new PMSentListPage();


	public static void main(String[] args) throws InterruptedException, CsvValidationException, IOException {
//		initialize();	//open browser and navigate to login page
//		lp.loginWithGoogle();
		initializeWithoutLogin();
		hp.navigateToOngoingFictionsListPage();
		listp.navigateToLastPage();
		listp.readUntilNewBooksRecorded(10);
//		listp.readMultiplePages(30);
		determineAllAuthor();
//		updatePMSentList();
		CustomizedExcelIO.writeRecords();
		
		clear();
	}

	private static void initialize() throws InterruptedException, CsvValidationException, IOException {
		CustomizedExcelIO.readRecords();
		hp.navigateToLoginPage();

	}
	private static void initializeWithoutLogin() throws InterruptedException, CsvValidationException, IOException {
		CustomizedExcelIO.readRecords();
		Browser.getDriver().get(Global.ROYALROAD_HOMEPAGE_URL);;

	}
	
	
	public static void determineAuthor(BookRecord record) throws InterruptedException {
		/**
		 * This method goes to the Author Details Page and records the author related
		 * information for the book in database
		 * @param: record - is the book that needs to have author information updated
		 **/
		switch(Global.DEFAULT_SITE) {
		case "royalroad": {
			if (!record.getFieldValue("bookUrl").equals("")) {
				hp.navigateToAuthorDetailPage(record.getFieldValue("bookUrl"));
				record.setFieldValue("authorUrl", ap.getAuthorID());
				record.setFieldValue("author", ap.getAuthorName());
				String authorID = ap.getAuthorID().split("/profile/")[1];
				record.setFieldValue("pmUrl", "https://www.royalroad.com/private/send/"+authorID);
			}
			else {
				System.out.println("No book url present, can't determine author for this book");
			}
			break;
		}
		default: {
			
		}
		}
	}
	
	public static void determineAllAuthor() throws InterruptedException {
		/**
		 * This method finds all books in the database that does not recorded 
		 * its authors yet, and updates the author information for all such books
		 * This method is site-independent
		 **/
		for (int i = 0; i<BookList.getlist().size(); i++) {
			if (BookList.getlist().get(i).getFieldValue("author").equals("")) {
				determineAuthor(BookList.getlist().get(i));
			}
		}
	}
	
	public static void clear() {
		Browser.getDriver().close();
	}
	

/*	
	public static void PMAuthor(BookRecord record) throws InterruptedException {
		Browser.getDriver().get(record.getFieldValue("pmUrl"));
		apmp.subjectField.sendKeys("hello");
		Thread.sleep(1000);
		apmp.bodyField.sendKeys("I like your novel");
		Thread.sleep(120000);
	}
	
	public static void PMSelectedAuthor() throws InterruptedException {
		for (int i = 0; i<BookList.getlist().size(); i++) {
			BookRecord br = BookList.getlist().get(i);
			if (br.getFieldValue("actionPlanned").equals("PM")) {
			PMAuthor(br);
			br.setFieldValue("actionPlanned", "draftSaved");
			}
		}
	}
	*/
	
	public static void updatePMSentList() throws InterruptedException {
		hp.navigateToPMSentListPage();
		pmslp.readMultiplePages(20);
	}
}
