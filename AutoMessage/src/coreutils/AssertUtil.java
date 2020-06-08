package coreutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebElement;



public class AssertUtil{

	 private static List<AssertionError> exList = new ArrayList<AssertionError>();
	
	 

	 public static void assertEq(Object expected, Object actual, String message ){
			
         try
         {
             Assert.assertEquals(expected, actual);
             System.out.println("Assert Passed:  Message= " + message);
         }
         catch (AssertionError ex)
         {
        	
             exList.add(ex);
             System.out.println("Assert failed: " );
         }
     }	
	 
	 public static void AreEquals(WebElement element, String expectedText)
	    {
		
	        try
	        {
	   
	            Assert.assertEquals(element.getText(), expectedText);
	            System.out.println("Asserting Passed:  Message=" + element.getText());
	        }
	        catch (AssertionError  ex)
	        {
	        	 System.out.println("Assert failed: expected " + expectedText + " not found");
	        	exList.add(ex);
	        }
	    }
	 	 

	   public static void AggregatedResult()
       {
           if (exList.size() > 0)
           {
               AggregateExceptionList();
               Assert.fail();
           }
           else {
        	   System.out.println("All Assertions are  passed " ); 
           }
        	   
       }
	   
	   private static ErrorCollector AggregateExceptionList()
       {
           StringBuilder message = new StringBuilder();           
                   
           for (Iterator<AssertionError> iter = exList.iterator(); iter.hasNext(); ) {
        	   AssertionError element = iter.next();
               message.append(element.getMessage()+"\n");
            }
           System.out.println("Assert Failed due to below erros : \n" + message + " ");
           return new ErrorCollector();
       }
	   

	 
}
