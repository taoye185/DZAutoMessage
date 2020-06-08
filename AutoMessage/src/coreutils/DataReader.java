package coreutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	/// <summary>
	/// This method returns the element by waiting till its visible and enabled.
	/// </summary>
	/// <param name="FileURL">URL to file</param>
	/// <param name="property">property that should read from the file</param>
	/// <returns>String</returns>
	private static String excelFilePathGBL;
	private static String excelSheetNameGBL;
	private static String filepathGBL;
	public static Properties elementsProperty = new Properties();
	public static Document elementsXML;

	public static String readProperty(String FileURL, String property) {

		String result = "";
		InputStream inputStream = null;

		try {
			Properties prop = new Properties();
			File initialFile = new File(FileURL);
			inputStream = new FileInputStream(initialFile);
			if (inputStream != null) {
				prop.load(inputStream);
			}
			result = prop.getProperty(property);
			inputStream.close();
		} catch (Exception ex) {
			System.out.println("EXCEPTION  " + " File not found ...... ");
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		} finally {

		}
		return result;
	}

	public static String readExcel(String FileURL, String sheetName, int row, int col) {
		XSSFWorkbook excelBook = null;
		XSSFSheet excelSheet = null;
		try {
			File file = new File(FileURL);
			FileInputStream inputStream = new FileInputStream(file);
			excelBook = new XSSFWorkbook(inputStream);
			excelSheet = excelBook.getSheet(sheetName);

		} catch (Exception ex) {
			System.out.println("EXCEPTION  " + " File not found ...... ");
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		} finally {

		}
		return excelSheet.getRow(row).getCell(col).getStringCellValue().toString();
	}

	public static void setExcelFile(String FileURL, String sheetName) {
		excelFilePathGBL = FileURL;
		excelSheetNameGBL = sheetName;
		System.out.println(" excelFilePathGBL " + excelFilePathGBL);
		System.out.println(" excelSheetNameGBL " + excelSheetNameGBL);
	}

	public static void setFilePath(String FileURL) {
		filepathGBL = FileURL;
	}

	public static String readExcel(int row, int col) {
		return readExcel(excelFilePathGBL, excelSheetNameGBL, row, col);
	}

	public static String readProperty(String property) {
		return readProperty(filepathGBL, property);
	}

	public static void loadPageElements(String FileURL) {
		FileInputStream objfile;
		try {
			objfile = new FileInputStream(FileURL);
			elementsProperty.load(objfile);
		} catch (FileNotFoundException ex) {
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		} catch (IOException ex) {
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		}

	}

	public static void loadPageElementsXML(String FileURL) {
		File inputFile = new File(FileURL);
		SAXReader saxReader = new SAXReader();
		try {
			elementsXML = saxReader.read(inputFile);
			/**
			 * Usage String mobileTesting =
			 * elementsXML.selectSingleNode("//login/tf_username_xpath").getText();
			 */
		} 
		
		catch (DocumentException ex) {
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		} 
		
/*		catch (IOException ex) {
			System.out.println("====================    " + ex.getMessage() + "   =============================");
		}
*/
	}

}
