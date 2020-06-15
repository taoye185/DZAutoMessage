package coreutils;

public class Global {


	// Default site setting
	public static final String DEFAULT_SITE = "royalroad";
	
    // Supported Browsers
    public static final String CHROME = "Chrome";
    public static final String FIREFOX = "FireFox";
    public static final String INTERNET_EXPLORER = "InternetExplorer";

    // Configurations
 /*   public static final String BROWSER = System.getProperty("GBL_WEB_BROWSER") != null
                    ? System.getProperty("GBL_WEB_BROWSER")
                    : CHROME;
*/
    public static final String BROWSER = "Chrome";
    public static final String CLIENTIDENTIFIER = System.getProperty("CLIENT") != null
                    ? System.getProperty("CLIENT")
                    : "GP";

    // Timers
    public static final int DEFAULT_EXPLICIT_WAIT = 5;
    public static final int DEFAULT_IMPLICIT_WAIT = 10;
    public static final int DEFAULT_AJAX_WAIT = 10;
    public static final int DEFAULT_PAGE_LOAD_TIME = 60;

    
    //Login Info
	public static final String ROYALROAD_HOMEPAGE_URL = "https://www.royalroad.com";
	public static final String ROYALROAD_LOGIN_URL = "https://www.royalroad.com/account/login";
    public static final String ROYALROAD_USER_ACCOUNT = "goodnovelrecruit@gmail.com";
    public static final String ROYALROAD_PASSWORD = "Thankyou1";
    

}
