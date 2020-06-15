package coreutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;


import org.openqa.selenium.remote.DesiredCapabilities;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import dataStructure.BookList;
import dataStructure.BookRecord;
import io.appium.java_client.remote.MobileCapabilityType;

public class CustomizedExcelIO {





 /*	This class is used to handle all the file input from configuration file
 * 
 */
	private static String filePath = "result.csv";
	
	public CustomizedExcelIO () throws IOException {
	}
	
	public static void readRecords () throws IOException, CsvValidationException {
/*	Pre: path is a valid relative filepath that the corresponding configuration file would be read
 * 	Post: The configuration settings are read and stored in the "paring" DesiredCapabilities
 */
		FileReader fileReader = new FileReader(filePath); 
	    CSVReader csvReader = new CSVReader(fileReader); 
	    String[] nextRecord; 
	    int i = 0;
	    ArrayList<String> fields = new ArrayList<String> ();
	    while ((nextRecord = csvReader.readNext()) != null) { 
    		BookRecord br = new BookRecord();
    		for (int j=0; j<nextRecord.length;j++) {
/*    			if (i==0) {
    				fields.add(nextRecord[j]);
	    			}
	    		else */
    			if (i>0) {		//ignore title row(i==0) and inactive row (nextRecord[0]!=1)
	    			br.setFieldValue(br.getFieldList()[j], nextRecord[j]);
	    		}//if
    		}
    		if (i>0) {
	    	BookList.updateRecordToList(br);
	    	System.out.println("BookList updated, list size is: " + BookList.getlist().size());
    		}
	        i++;
	    }
	    csvReader.close();
	}
	

	public static boolean writeRecords() throws IOException {
		File statText = new File(filePath);
        FileOutputStream is = new FileOutputStream(statText);
        OutputStreamWriter osw = new OutputStreamWriter(is);    
        BufferedWriter wr = new BufferedWriter(osw);
        String output =  BookList.displayAll();
		wr.write(output);
		wr.flush();
		Log.info(filePath + "has been written.");
		wr.close();
		return true;
	}
	
/*	
	public void writeElementFile (String filePath, ElementList page) throws IOException {
		log.logConsole("writeElement is called");
		DesiredCapabilities elements = new DesiredCapabilities();
//		DesiredCapabilities page = new DesiredCapabilities();
		
        FileReader filereader = new FileReader(filePath);  
        CSVReader csvReader = new CSVReader(filereader); 
        String output = "";
        String[] nextRecord; 
        int i = 0;
        while ((nextRecord = csvReader.readNext()) != null) { 
        	if (i>0) {
        		output += "\r\n";
        	}
        	nextRecord = this.padArray(nextRecord, 8);
        	output += this.writeArrayAsCSV(nextRecord,7);
        	if(i>0 && (nextRecord[5].equals("yes"))) {
        		int weight=0;
 /*       		
        		if(nextRecord.length>=8) {
        			if (!nextRecord[7].equals(null)) {
        				if(!nextRecord[7].equals("")) {
        					weight = Integer.parseInt(nextRecord[7]);
        				}
        			}
        		}
*/
	/*
        		mobilePage tempPage = (mobilePage) page.getElement(nextRecord[2]);
        		if (!tempPage.equals(null)) {
        			weight = tempPage.returnCount();
        		}
//        		weight += ((mobilePage) cap.getCapability(nextRecord[2])).returnCount();
        		log.logConsole("weight is: " + weight);
        		output = output  + weight;
 
        	}
            i++;
        } 
        csvReader.close();
        log.logConsole(filePath + "has been read.");

		File statText = new File(filePath);
        FileOutputStream is = new FileOutputStream(statText);
        OutputStreamWriter osw = new OutputStreamWriter(is);    
        BufferedWriter wr = new BufferedWriter(osw);
		wr.write(output);
		wr.flush();
		log.logConsole(filePath + "has been written.");
		wr.close();
	}
	
	public String writeArrayAsCSV (String[] array, int desiredLength) {
		String arrayString = "";
		for (int i=0; i<desiredLength; i++) {
			String elementString = "";
			if (i>=array.length) {
				elementString = "";
			}
			else if (array[i].equals(null)) {
				elementString = "";
			}
			else {
				elementString = array[i];
			}
			arrayString = arrayString +elementString+",";
		}
		return arrayString;
	}
	
	public String[] padArray (String[] array, int desiredLength) {
		String[] newArray = new String [desiredLength];
		for (int i=0; i<desiredLength; i++) {
			if (i<array.length) {
				newArray[i] = array [i];
			}
			else {
				newArray[i] = "";
			}
		}
		return newArray;
	}
}
*/
}

