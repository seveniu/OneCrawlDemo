package com.seveniu.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by seveniu on 6/1/16.
 * Export
 */
public class TableExport {

    public static void toExcel(List<Map<String, Object>> data, OutputStream outputStream) throws IOException {
        Workbook wb = new XSSFWorkbook();


        Sheet sheet = wb.createSheet("new sheet");

        int rowNum = 0;
        int cellNum = 0;
        Row firstRow = sheet.createRow(rowNum++);
        Map<String, Object> first = data.get(0);
        for (String s : first.keySet()) {
            Cell cell = firstRow.createCell(cellNum++);
            cell.setCellValue(s);
        }
        cellNum = 0;
        for (Map<String, Object> map : data) {
            Row row = sheet.createRow(rowNum++);

            for (Object value : map.values()) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(String.valueOf(value));
            }
            cellNum = 0;
        }
        // Create a cell and put a value in it.
        wb.write(outputStream);
    }
}
