package dataStructure;
import java.util.ArrayList;

import coreutils.Browser;

public class BookList {

	private static ArrayList<BookRecord> list = new ArrayList<BookRecord>();
	private static ArrayList<String> bookNameList = new ArrayList<String>();
	public BookList () {
		
	}
	
	public static boolean updateRecordToList(BookRecord record) {
		/**
		 * This method will update the input record to the list of BookRecords.
		 * If no records with a matching bookname is found, the new book is added to the list
		 * The method will return true if the record is a new book and list size has been incremented,
		 * It will return false otherwise
		 * 
		 * @param: record - the input record containing the most up-to-date attribute info
		 **/
		if (!record.getBookName().equals("")) {	//check input record is not an empty/error row
			if (bookNameList.equals(null)) {	//if no book is present in list, add the first book
				list.add(record);
				bookNameList.add(record.getBookName());
				return true;
				
			}else if (!bookNameList.contains(record.getBookName())) {
				list.add(record);
				bookNameList.add(record.getBookName());
				return true;
			}
			else {
				list.get(findItemBy("bookName", record.getBookName())).update(record);
				return false;
			}
		}
		return false;
	}
	
	public static int findItemBy (String field, String searchValue) {
		String actualValue="";
		for (int i = 0; i<list.size(); i++) {
			switch (field) {
			case ("bookName"): {
				actualValue =list.get(i).getBookName();
				break;
			}
				
			}
			if (actualValue.equals(searchValue)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static String displayAll() {
		String[] fieldNames = list.get(0).getFieldList();
		String output = "";
		
		for (int titleCol=0; titleCol<fieldNames.length; titleCol ++) {
		output += fieldNames[titleCol];
		if(titleCol<fieldNames.length-1) {
			output += ",";
		}
		}
		output += "\r\n";
		
		for (int i = 0; i<list.size(); i++) {
			for (int j=0; j< fieldNames.length; j++) {
				System.out.print(list.get(i).getFieldValue(fieldNames[j]) + " ,");

				output += list.get(i).getFieldValue(fieldNames[j]);
				if (j<fieldNames.length-1) {
					output += ",";
				}
			}
			System.out.println();
			output += "\r\n";
		}
		return output;
	}
	
	public static ArrayList<BookRecord> getlist() {
		return list;
	}
	
	public static ArrayList<BookRecord> getBookFromAuthor(String author) {
		ArrayList<BookRecord> bookByAuthor = new ArrayList<BookRecord> ();
		for (int i = 0; i<list.size(); i++) {
			if (list.get(i).getFieldValue("author").equals(author)) {
				bookByAuthor.add(list.get(i));
			}
		}
		return bookByAuthor;
	}

}
