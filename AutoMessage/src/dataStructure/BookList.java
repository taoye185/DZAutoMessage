package dataStructure;
import java.util.ArrayList;

import coreutils.Browser;

public class BookList {

	private static ArrayList<BookRecord> list = new ArrayList<BookRecord>();
	private static ArrayList<String> bookName = new ArrayList<String>();
	public BookList () {
		
	}
	
	public static void updateRecordToList(BookRecord record) {
		if (bookName.equals(null)) {
			list.add(record);
			bookName.add(record.getBookName());
			
		}else if (!bookName.contains(record.getBookName())) {
			list.add(record);
			bookName.add(record.getBookName());
		}
		else {
			list.get(findItemBy("bookName", record.getBookName())).update(record);

		}

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
	

}
