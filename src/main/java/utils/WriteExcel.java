package utils;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteExcel {
	@Test
	public void writeExcel() throws Exception {

		String data[][] = new String[2][3];
		data[0][0] = "Testleaf";
		data[0][1] = "koushik";
		data[0][2] = "chatterjee";

		//Open excel
		XSSFWorkbook wbook = new XSSFWorkbook();
		//Create a sheet
		XSSFSheet sheet = wbook.createSheet("sheet1");
		int rowNumber = 0;
		for (String[] row : data) {
			//Create row
			XSSFRow createRow = sheet.createRow(rowNumber);
			int columnCount = 0;
			for (String columnData : row) {
				//Create cell
				XSSFCell createCell = createRow.createCell(columnCount);
				//Write data
				createCell.setCellValue(columnData);
				columnCount++;
			}
			rowNumber++;
		}
		//save
		FileOutputStream fos = new FileOutputStream(new File("./data/write.xlsx"));
		wbook.write(fos);
		//close
		wbook.close();
	}

}









