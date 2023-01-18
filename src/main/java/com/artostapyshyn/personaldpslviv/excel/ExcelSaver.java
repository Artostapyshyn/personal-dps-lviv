package com.artostapyshyn.personaldpslviv.excel;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelSaver {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<FiredEmployee> firedEmployeesList;

	public ExcelSaver(List<FiredEmployee> firedEmployeesList) {
		this.firedEmployeesList = firedEmployeesList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Fired employees");

		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		
		font.setBold(true);
		font.setFontHeight(15);
		style.setFont(font);

		createCell(row, 0, "№", style);
		createCell(row, 1, "First name", style);
		createCell(row, 2, "Last name", style);
		createCell(row, 3, "Department", style);
		createCell(row, 4, "Phone number", style);
		createCell(row, 5, "Birth date", style);
		createCell(row, 6, "Firing reason", style);
		createCell(row, 7, "Firing date", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell recordCell = row.createCell(columnCount);

		if (value == null)
			return;

		if (value instanceof Integer || value instanceof Long || value instanceof Double)
			recordCell.setCellValue(Double.parseDouble(value.toString()));
		else if (value instanceof String)
			recordCell.setCellValue((String) value);

		recordCell.setCellStyle(style);
	}

	private void writeIntoCellByType() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (FiredEmployee record : firedEmployeesList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, record.getId(), style);
			createCell(row, columnCount++, record.getFirstName(), style);
			createCell(row, columnCount++, record.getLastName(), style);
			createCell(row, columnCount++, record.getDepartment(), style);
			createCell(row, columnCount++, record.getPhoneNumber(), style);
			createCell(row, columnCount++, record.getBirthDate(), style);
			createCell(row, columnCount++, record.getFiringReason(), style);
			createCell(row, columnCount++, record.getFiringDate(), style);
		}
	}

	public void createExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		writeIntoCellByType();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
