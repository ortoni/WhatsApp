package sms;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadMasterData{

	public static int rowCount;
	public String[][] getSheet() {
		String[][] data = null ;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook("./data/masterData.xlsx");
			XSSFSheet sheet = workbook.getSheetAt(0);	

			// get the number of rows
			rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			//System.out.println(rowCount);
			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				try {
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						try {
							String cellValue = "";
							try{
								CellType cellType = row.getCell(j).getCellTypeEnum();
								if (cellType == CellType.STRING) {
									cellValue = row.getCell(j).getStringCellValue();
								}else if(cellType == CellType.NUMERIC){
									cellValue = ""+(long) row.getCell(j).getNumericCellValue();
								}else {
									throw new RuntimeException("It must contains text and number only");
								}
							}catch(NullPointerException e){
							}
							/*if ((j==0 && cellValue.equals(course)) && (j==1 &&cellValue.equals(day))) {

							}*/
							data[i-1][j] =  cellValue;
//							System.out.println(data[i-1][j]);// add to the data array
						} catch (Exception e) {
							e.printStackTrace();}				
					}
				} catch (Exception e) {
					e.printStackTrace();}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();}
		return data;
	}
}
