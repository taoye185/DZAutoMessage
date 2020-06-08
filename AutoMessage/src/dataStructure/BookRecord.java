package dataStructure;
import java.util.ArrayList;

public class BookRecord {

	private String bookName="";
	private String author="";
	private String email="";
	private String id="";
	private String pmUrl="";
	private String bookUrl="";
	private String followerCount="";
	private String pageCount="";
	private String viewCount="";
	private String chaptersCount="";
	private String lastUpdateDate="";
	private String[] fieldList = {"bookName", "author", "email", "id","pmUrl","bookUrl","followerCount","pageCount","viewCount",
			"chaptersCount","lastUpdateDate"};
	
	public BookRecord (String book, String url, String follower, String page, String view, String chapters, String lastUpdate) {
		bookName = book;
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
		case "date": {// assumes May 17, 2020 format
			value = value.replaceAll(" ", "");
			value = value.replaceAll(",", "");
			return value;
		}
		case "bookId": {
			value = value.split("https://www.royalroad.com/fiction/")[0];
			value = value.split("/")[0];
			return value;
		}
		default: {
				value = value.split(" ")[0];
				value = value.replaceAll(",", "");
				return value;
		}
		}
	}
	
	public void update(BookRecord record) {
		for (int i=0; i<fieldList.length; i++) {
			String field = fieldList[i];
			String value = record.getFieldValue(field);
			if (!value.equals("")) {
				setFieldValue(field,value);
			}
		}
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
		default: {
			System.out.print("no matching field name for " + field + " was found, returning empty string");
			return "";
		}
		}
	}
	
	public void setFieldValue (String field, String value) {
		switch (field) {
		case "bookName": {
			bookName = value;
			break;
		}
		case "author": {
			author = value;
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
		default: {
			System.out.print("no matching field name for " + field + " was found, value not updated");
		}
		}
	}
}
