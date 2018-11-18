package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readExcel(String fileName) throws IOException {
		//Open workbook
		XSSFWorkbook wbook = new XSSFWorkbook("./data/"+fileName+".xlsx");
		//go to sheet
		XSSFSheet sheet = wbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		//int actualRow = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		int columnCount = sheet.getRow(0).getLastCellNum();
		System.out.println(columnCount);
		String[][] data = new String[rowCount][columnCount];
		for (int i = 1; i <= rowCount; i++) {
			//go to row
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				//go to the column of row
				XSSFCell column = row.getCell(j);
				//read data
				 data[i-1][j] = column.getStringCellValue();
				//column.getNumericCellValue()
			}
		}
		return data;
		/*for (String[] row : data) {
			for (String column : row) {
			System.out.println(column);
		}
		}*/
	}
}






