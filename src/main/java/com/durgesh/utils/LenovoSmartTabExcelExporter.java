package com.durgesh.utils;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.durgesh.model.LenovoSmartTab;


public class LenovoSmartTabExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<LenovoSmartTab> listLenovoSmartTab;

	
     
	    public LenovoSmartTabExcelExporter(List<LenovoSmartTab> listLenovoSmartTab) {
	        this.listLenovoSmartTab = listLenovoSmartTab;
	        workbook = new XSSFWorkbook();
	    }
	    private void writeHeaderLine() {
	        sheet = workbook.createSheet("Users");
	         
	        Row row = sheet.createRow(0);
	         
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	         
	        createCell(row, 0, "Product ID", style);      
	        createCell(row, 1, "Modell", style);       
	        createCell(row, 2, "RAM", style);    
	        createCell(row, 3, "ROM", style);
	        createCell(row, 4, "Size", style);
	        createCell(row, 5, "ExpandableUptoe", style);      
	        createCell(row, 6, "PrimaryCamera", style);       
	        createCell(row, 7, "Battery", style);    
	        createCell(row, 8, "Processor", style);
	        createCell(row, 9, "TabletGuarantee", style);
	        createCell(row, 10, "AccessoriesGuarantee", style);
	         
	    }
	   
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	       
	      
	  
	    
	     
	    
	}