package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class UpdateExcel {
	@Test
	public void readData() throws IOException {
		//Goto file location
		FileInputStream fis = new FileInputStream(new File("./data/CreateLead.xlsx"));
		//Open workbook
		XSSFWorkbook wbook = new XSSFWorkbook(fis);
		//Goto sheet
		XSSFSheet sheet = wbook.getSheetAt(0);
		//Get row
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		//Goto the cell
		int columnCount = sheet.getRow(0).getLastCellNum();
		System.out.println(columnCount);
		//Create the cell
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.createCell(columnCount);
		cell.setCellValue("Result");
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row1 = sheet.getRow(i);
			XSSFCell cell1 = row1.createCell(columnCount);
			cell1.setCellValue("pass");
		}
		FileOutputStream fos = new FileOutputStream(new File("./data/create.xlsx"));
		wbook.write(fos);
		//close
		wbook.close();
	}
}
