package com.maanadev.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.maanadev.configurations.Configurations;

public class XlsxCore implements XlsxConst {

	FileInputStream file;
	FileOutputStream outFile;
	// Get the workbook instance for XLS file
	XSSFWorkbook workbook;

	// Get first sheet from the workbook
	XSSFSheet sheet;

	// Get iterator to all the rows in current sheet
	Iterator<Row> rowIterator;
	// Configuration
	Configurations configurations;

	public XlsxCore(Configurations configurations) {
		this.configurations = configurations;
	}

	protected void initializeInput() {

		try {
			file = new FileInputStream(new File(configurations.getPATH()));

			workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			System.out.println("file not found :" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Io :" + e.getMessage());
		}

		sheet = workbook.getSheetAt(0);
		rowIterator = sheet.iterator();

	}

	protected void initializeOutput() {

		try {
			outFile = new FileOutputStream(new File(configurations.getPATH()));

		} catch (FileNotFoundException e) {
			System.out.println("file not found :" + e.getMessage());
		}

	}

	protected void closeInputResource() {

		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void closeOutputResource() {
		try {
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
