package base;

public class AppConstants {

    //Acts as a Global properties file
    //Declare values to be provided in the mvn commands during execution
    public static final String browserName = System.getProperty("browserName", "chrome");
    public static final String platform = System.getProperty("platform","local");

}
