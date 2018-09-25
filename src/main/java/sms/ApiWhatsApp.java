package sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ApiWhatsApp extends ApiWhastsAppBuilder {
	String msg="";
	String ph="";
	// ---> To find the no.of days from today <---
	long date(String firstCallDate) {
		long days = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		//		Date date = new Date();
		String currenDate = LocalDate.now().toString();
		String callDate = firstCallDate;

		try {
			Date date1 = simpleDateFormat.parse(callDate);
			Date date2 = simpleDateFormat.parse(currenDate);
			long diff = date2.getTime() - date1.getTime();
			days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			//System.out.println ("Days: " +days );

		} catch (ParseException e) {
			System.err.println("PLS check the date format in xls ---\n It should be yyyy-mm-dd");
		}
		return days;
	}
	@Test(dataProvider = "whatsAppApi")
	public void postJob(String status, String phoneNumber,String co, String date, String name) {
		// ----> Check if phone number is valid (10 digits)
		//		= phoneNumber;
		Pattern p = Pattern.compile("\\d{10}"); 
		Matcher m = p.matcher(phoneNumber); 
		if(m.matches()) {
			ph = phoneNumber;
		}
		// ----> <----
		long msgDate = date(date);
		// ----> Converting date value into String to check condition <-----
		String s = Long.toString(msgDate);
		String[][] rd = new ReadMasterData().getSheet();
		for (String[] a : rd) {
			if (a[0].equals(co) && a[1].equals(s)) {
				msg = a[2];	
				sendMsg(phoneNumber, name);
				break;
			}
		}
		//System.out.println(msgDate);
	}
	private void sendMsg(String phoneNumber, String name) {

		driver.get("https://api.whatsapp.com/send?phone=91" + ph + "&text=" + "Dear "+name+ " ."+msg);
		System.out.println(phoneNumber);

		// To navigate to next contact
		try {
			Thread.sleep(250);
			waitTillAlertDiasable();
		} catch (Exception e) {
		}
		// Click on SEND Link
		try {
			Thread.sleep(100);
			WebElement send = driver.findElementByLinkText("SEND");
			waitAndClick(send);
		} catch (Exception e1) {
		}
		// Disabled for performance 
		// If number is not available in WhatsApp!
		/*try {
			WebElement numberNotAvail = driver.findElementByXPath(
					"//div[text()='Phone number shared via url is invalid.']/following::div[1]/div");
			waitAndClick(numberNotAvail);
		} catch (Exception e2) {
		}*/
		try {
			WebElement sendButton = driver.findElementByXPath("//span[@data-icon='send']/..");
			waitAndClick(sendButton);
		} catch (Exception e) {
		}
	}
}



