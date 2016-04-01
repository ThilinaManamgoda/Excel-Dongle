package com.maanadev.excel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.maanadev.configurations.Configurations;

public class XlsxWrite extends XlsxCore {

	public XlsxWrite(Configurations configurations) {
		super(configurations);
	}

	public void write(String home, double unit) {

		initializeInput();
		int rowNumber = sheet.getLastRowNum() + 1;

		// creating an empty row
		createRow(rowNumber);

		// Fill the cells with the data
		Cell cell;
		cell = sheet.getRow(rowNumber).getCell(COLUMNONE);
		cell.setCellValue(home);
		cell = sheet.getRow(rowNumber).getCell(COLUMNTWO);
		cell.setCellValue(unit);

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
