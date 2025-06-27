package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtilities(String path) {
        this.path = path;
    }

    // Returns the count of physical (non-empty) rows excluding the header
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        // physical number of rows including header
        int totalRows = sheet.getPhysicalNumberOfRows();
        workbook.close();
        fi.close();
        // Assuming first row is header, subtract 1 to get data rows count
        return totalRows > 0 ? totalRows - 1 : 0;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = 0;
        if (row != null) {
            cellcount = row.getLastCellNum(); // returns number of cells in the row
        }
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        if (row == null) {  // Null check to avoid NPE
            workbook.close();
            fi.close();
            return "";
        }
        cell = row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) {
            row = sheet.createRow(rownum);
        } else {
            row = sheet.getRow(rownum);
        }

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fi.close();

        FileOutputStream fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }
}
