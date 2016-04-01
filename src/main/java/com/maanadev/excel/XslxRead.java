package com.maanadev.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Row;

import com.maanadev.configurations.Configurations;

public class XslxRead extends XlsxCore {

	

	public XslxRead(Configurations configurations) {
		super(configurations);
	}

	public Map<String, Double> read() {

		initializeInput();
		Map<String, Double> data = new HashMap<String, Double>();

		Row row = rowIterator.next();// Skip the Headers
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			String home = row.getCell(COLUMNONE).getStringCellValue();
			double units = row.getCell(COLUMNTWO).getNumericCellValue();
			data.put(home, units);
		}

		closeInputResource();
		return data;
	}

	public Map<String, Double> read(int rowNum) {
		initializeInput();
		Map<String, Double> data = new HashMap<String, Double>();

		Row row = sheet.getRow(rowNum);

		String home = row.getCell(COLUMNONE).getStringCellValue();
		double units = row.getCell(COLUMNTWO).getNumericCellValue();
		data.put(home, units);

		closeInputResource();
		return data;
	}

}
