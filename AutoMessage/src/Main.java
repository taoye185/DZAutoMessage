import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import coreutils.Browser;
import coreutils.CustomizedExcelIO;
import coreutils.Global;
import dataStructure.AuthorDetailPage;
import dataStructure.BookList;
import dataStructure.BookRecord;
import dataStructure.HomePage;
import dataStructure.ListPage;
import dataStructure.LoginPage;

public class Main {

	static LoginPage lp = new LoginPage();
	static HomePage hp = new HomePage();
	static ListPage listp = new ListPage();
	static AuthorDetailPage ap = new AuthorDetailPage();
//	static BookList bl = new BookList();
	public static void main(String[] args) throws InterruptedException, CsvValidationException, IOException {
		// TODO Auto-generated method stub

		initialize();
//		CustomizedExcelIO.readRecords();
//		lp.navigateToLoginPage();
		
		lp.loginWithGoogle();
		hp.navigateToOngoingFictionsListPage();
		listp.navigateToLastPage();
		listp.readMultiplePages(1);
		determineAllAuthor();
//		listp.traverseAllFictionsInCurrentPage();
//		BookList.displayAll();
		CustomizedExcelIO.writeRecords();
	}

	private static void initialize() throws InterruptedException, CsvValidationException, IOException {
		CustomizedExcelIO.readRecords();
		Browser.open(Global.URL);		
		Thread.sleep(3000);
	}
	
	
	public static void determineAuthor(BookRecord record) throws InterruptedException {
		hp.navigateToAuthorDetailPage(record.getFieldValue("bookUrl"));
		record.setFieldValue("pmUrl", ap.getAuthorID());
		record.setFieldValue("author", ap.getAuthorName());
	}
	
	public static void determineAllAuthor() throws InterruptedException {
		for (int i = 0; i<BookList.getlist().size(); i++) {
			if (BookList.getlist().get(i).getFieldValue("author").equals("")) {
				determineAuthor(BookList.getlist().get(i));
			}
		}
	}
}
