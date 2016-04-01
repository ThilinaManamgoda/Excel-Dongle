package com.maanadev.excel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.maanadev.configurations.Configurations;

public class XlsxWrite extends XlsxCore {

	public XlsxWrite(Configurations configurations) {
		super(configurations);
	}

	public void write(String data []) {

		initializeInput();
		int rowNumber = sheet.getLastRowNum() + 1;

		// creating an empty row
		createRow(rowNumber);
		Row row = sheet.getRow(rowNumber);
		// Fill the cells with the data
		Cell cell;
		cell =row.getCell(COLUMNONE);
		cell.setCellValue(Integer.parseInt(data[COLUMNONE]));
		
		cell = row.getCell(COLUMNTWO);
		cell.setCellValue(Integer.parseInt(data[COLUMNTWO]));
		
		cell = row.getCell(COLUMNTHREE);
		cell.setCellValue(Integer.parseInt(data[COLUMNTHREE]));
		
		cell = row.getCell(COLUMNFOUR);
		cell.setCellValue(Integer.parseInt(data[COLUMNFOUR]));

		// clearing resources
		closeInputResource();

		// get out put file resources
		initializeOutput();

		try {
			// Writing the modified file back
			workbook.write(outFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// clearing resources
		closeOutputResource();
	}

	private void createRow(int rowNumber) {
		XSSFRow row = sheet.createRow(rowNumber);
		for (int i = 0; i < COLUMNCOUNT; i++) {

			row.createCell(i);

		}
	}

}
