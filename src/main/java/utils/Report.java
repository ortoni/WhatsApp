package utils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;

public abstract class Report {
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test, suiteTest;
	public String testCaseName, testNodes, testDescription, category, authors;


	public void startResult() {
		html = new ExtentHtmlReporter("./reports/images/result.html");
		ExtentHtmlReporterConfiguration c = new ExtentHtmlReporterConfiguration(html);
		c.enableTimeline(true);
		extent = new ExtentReports();	
		extent.attachReporter(html);	
	}


	public ExtentTest startTestModule(String testCaseName, String testDescription) {
		suiteTest = extent.createTest(testCaseName, testDescription);
		return suiteTest;
	}



	public ExtentTest startTestCase(String testNodes) {
		test = 	suiteTest.createNode(testNodes);
		return test;
	}

	public abstract long takeSnap();


	public void reportStep(String desc, String status, boolean bSnap)  {

		File file = new File("reports/images");
		boolean exists = file.exists();
		System.out.println(exists);


		MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			System.out.println(snapNumber);
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						(file.getAbsolutePath()+"\\"+snapNumber+".jpg").build();
				System.out.println(file.getAbsolutePath()+"\\"+snapNumber+".jpg");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		if(status.equalsIgnoreCase("PASS")) {
			test.pass(desc, img);			
		}else if (status.equalsIgnoreCase("FAIL")) {
			test.fail(desc, img);
			throw new RuntimeException();
		}else if (status.equalsIgnoreCase("WARNING")) {
			test.warning(desc, img);
		}else if (status.equalsIgnoreCase("INFO")) {
			test.info(desc);
		}						
	}


	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}




	public void endResult() {
		extent.flush();
	}	

}
