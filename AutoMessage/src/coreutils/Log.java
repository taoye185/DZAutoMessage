package coreutils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log extends Logger {
	

	protected Log(String name) {
        super(name);
    }

    public static ArrayList<String> sessionLog = new ArrayList<String>();

    /**
     * This method is useful to get the class Name of the class that call Log.info
     * or Log.error
     * 
     **/
    public static String getCallingClassName() {
        setCurrentDateWithTime();
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement sTraceElement = stElements[i];
            if (!sTraceElement.getClassName().equals(Log.class.getName())
                    && sTraceElement.getClassName().indexOf("java.lang.Thread") != 0) {
                System.out.println(sTraceElement.getClassName());

                return sTraceElement.getClassName();
            }
        }
        return null;
    }

    /**
     * This method set the current date with time value to current.date.time as a
     * environment variable This variable is used in Log4j.properties file
     **/
    public static void setCurrentDateWithTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hhmmss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }

    /**
     * This method print the message on the console or log file, which helps for
     * debugging the error when occurred
     * 
     * @para message- message to print on the console or log file
     **/

    public static void info(String message) {
        sessionLog.add(message);
        String name = getCallingClassName();
        Logger log = Logger.getLogger(name);
        log.info(message);
    }

    /**
     * This print the message on the console or log file,when an error occurs
     * 
     * @para message- message to print on the console or log file
     **/
    public static void error(String message) {
        sessionLog.add(message);
        String name = getCallingClassName();
        Logger log = Logger.getLogger(name);
        log.error(message);
    }
}
