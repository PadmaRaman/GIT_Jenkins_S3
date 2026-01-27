package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHelper {

    public static ExtentReports reports;

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss");

    public static ExtentReports getReporter()
    {
        String reportPath = "./reports/"+dtf.format(LocalDateTime.now());
        ExtentSparkReporter sparkReporter =  new ExtentSparkReporter(reportPath);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Tester is","Padma Ramanathan");
        return reports;
    }

}
