package dataStructure;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookRecord {

	private String bookName="";
	private String author="";
	private String email="";
	private String id="";
	private String authorUrl="";
	private String pmUrl="";
	private String bookUrl="";
	private String followerCount="";
	private String pageCount="";
	private String viewCount="";
	private String chaptersCount="";
	private String lastUpdateDate="";
	private String dataCollectionDate="";
	private String actionPlanned = "";
	private String[] fieldList = {"bookName", "author", "email", "id","authorUrl","pmUrl","bookUrl","followerCount","pageCount","viewCount",
			"chaptersCount","lastUpdateDate","dataCollectionDate", "actionPlanned"};
	
	public BookRecord (String book, String url, String follower, String page, String view, String chapters, String lastUpdate) {
		bookName = this.convertRawStrings("bookName", book);
		bookUrl = url;
		followerCount = this.convertRawStrings("default", follower);
		pageCount = this.convertRawStrings("default", page);
		viewCount = this.convertRawStrings("default", view);
		chaptersCount = this.convertRawStrings("default", chapters);
		lastUpdateDate = this.convertRawStrings("date", lastUpdate);
		id = this.convertRawStrings("bookId", url);
	}
	
	public BookRecord() {
		
	}
	
	private String convertRawStrings (String fieldType, String value) {
		switch (fieldType) {
		case "bookName": {
			value = value.replaceAll(",", "");
			return value;
		}
		case "author": {
			value = value.replaceAll(",", "");
			return value;
		}
		case "date": {// assumes May 17, 2020 format
			value = value.replaceAll(" ", "");
			value = value.replaceAll(",", "");
			return value;
		}
		case "bookId": {
			if (value.contains("https://www.royalroad.com/fiction/")) {
			value = value.split("https://www.royalroad.com/fiction/")[1];
			value = value.split("/")[0];
			}
			return value;
		}
		default: {
				value = value.split(" ")[0];
				value = value.replaceAll(",", "");
				return value;
		}
		}
	}
	
	public boolean update(BookRecord record) {
		/**
		 * This method will update the attributes if the corresponding record attributes are
		 * not empty and are different that what's on file. It will return true if any attribute
		 * was updated, false otherwise
		 * 
		 * @param: record - the input record containing the most up-to-date attribute info
		 **/
		boolean updateDone = false;
		for (int i=0; i<fieldList.length; i++) {//go through each attribute
			String field = fieldList[i];
			String value = record.getFieldValue(field); //get the value of the corresponding attribute
			if ( (!value.equals("")) && (!getFieldValue(field).equals(value)) ) {//check whether value is empty or identical
				setFieldValue(field,value);	
				updateDone = true;
			}
		}
		return updateDone;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String[] getFieldList() {
		return fieldList;
	}
	public String getFieldValue (String field) {
		String value = "";
		switch (field) {
		case "bookName": {
			return bookName;
		}
		case "author": {
			return author;
		}
		case "email": {
			return email;
		}
		case "id": {
			return id;
		}
		case "authorUrl": {
			return authorUrl;
		}
		case "pmUrl": {
			return pmUrl;
		}
		case "bookUrl": {
			return bookUrl;
		}
		case "followerCount": {
			return followerCount;
		}
		case "pageCount": {
			return pageCount;
		}
		case "viewCount": {
			return viewCount;
		}
		case "chaptersCount": {
			return chaptersCount;
		}
		case "lastUpdateDate": {
			return lastUpdateDate;
		}
		case "dataCollectionDate": {
			return dataCollectionDate;
		}
		case "actionPlanned": {
			return actionPlanned;
		}
		default: {
			System.out.print("no matching field name for " + field + " was found, returning empty string");
			return "";
		}
		}
	}
	
	public void setFieldValue (String field, String value) {
		Date today = Calendar.getInstance().getTime();
		dataCollectionDate = today.toGMTString();
		value = value.replaceAll(",", "");

		switch (field) {
		case "bookName": {
			bookName = this.convertRawStrings("bookName", value);
			break;
		}
		case "author": {
			author = this.convertRawStrings("author", value);
			break;
		}
		case "email": {
			email = value;
			break;
		}
		case "id": {
			id = this.convertRawStrings("bookId", value);
			break;
		}
		case "authorUrl": {
			authorUrl = value;
			break;
		}
		case "pmUrl": {
			pmUrl = value;
			break;
		}
		case "bookUrl": {
			bookUrl = value;
			break;
		}
		case "followerCount": {
			followerCount = this.convertRawStrings("default", value);
			break;
		}
		case "pageCount": {
			pageCount = this.convertRawStrings("default", value);
			break;
		}
		case "viewCount": {
			viewCount = this.convertRawStrings("default", value);
			break;
		}
		case "chaptersCount": {
			chaptersCount = this.convertRawStrings("default", value);
			break;
		}
		case "lastUpdateDate": {
			lastUpdateDate = this.convertRawStrings("date", value);
			break;
		}
		case "dataCollectionDate": {
			dataCollectionDate = value;
			break;
		}
		case "actionPlanned": {
			actionPlanned = value;
			break;
		}
		default: {
			System.out.print("no matching field name for " + field + " was found, value not updated");
		}
		}
	}
}
