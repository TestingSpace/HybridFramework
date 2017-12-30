package com.selenium.HybridFramework.ExcelReader;

import java.io.File;
import java.io.FileInputStream; 
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static final Logger logger = Logger.getLogger(ExcelReader.class.getName());

	public String [][] getExcelData(String excelLocation, String sheetName ) {
		try {
			logger.info("Creating logger object"+excelLocation);
		String dataSets[][] = null;
		FileInputStream file = new FileInputStream(new File(excelLocation));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum() + 1;
		int totalCol = sheet.getRow(0).getLastCellNum();
		dataSets = new String[totalRow-1][totalCol];
		Iterator<Row> rowIterator = sheet.iterator();
		
		int i = 0;
		int j = 0;
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (i++ !=0) {
				int k = j;
				j++;
				Iterator<Cell> cellIterator = row.cellIterator();
				int m =0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.println(i);
						System.out.println(m);
						dataSets[k][m++]= cell.getStringCellValue();
						System.out.println(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.println(i);
						System.out.println(m);
						dataSets[k][m++]= cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
						
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println(i);
						System.out.println(m);
						dataSets[k][m++]= cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
						
					case Cell.CELL_TYPE_FORMULA:
						System.out.println(i);
						System.out.println(m);
						dataSets[k][m++]= cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
						
					}
				}
				System.out.println("");
			}
		}
			
				
					file.close();
					return dataSets;
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//return null;
		return null;
			 
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String excelLocation = "E:\\Testing-Work\\Maven\\selenium\\src\\main\\java\\com\\selenium\\HybridFrameWork\\Data\\data.xlsx";
		System.out.println(excelLocation);
		String sheetName = "loginData";
		ExcelReader excel = new ExcelReader();
			excel.getExcelData(excelLocation, sheetName);
	}

}
