package com.durgesh.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.model.LenovoSmartTab;

public class ExcelHelper {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = {"Id","model", "ram", "rom","size","expandableUpto","primaryCamera",
			  "battery","processor","tabletGuarantee","accessoriesGuarantee" };
	  static String SHEET = "Tutorials";
	  public static boolean hasExcelFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    
	    return true;
	  }
	  
	  // add excel record
	  public static List<LenovoSmartTab> excelToLenovoSmartTab(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);
	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();
	      List<LenovoSmartTab> lenovoSmartTabs = new ArrayList<LenovoSmartTab>();
	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();
	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }
	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        LenovoSmartTab lenovoSmartTab = new LenovoSmartTab();
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
	          switch (cellIdx) {
	          case 0:
	           // tutorial.setId((long) currentCell.getNumericCellValue());
	        	  lenovoSmartTab.setId((long)currentCell.getNumericCellValue());
	        	 
	            break;
	          case 1:
	        
	        	  lenovoSmartTab.setModel(currentCell.getStringCellValue());
	           
	            break;
	          case 2:
	        	  lenovoSmartTab.setRam(currentCell.getStringCellValue());
	            break;
	          case 3:
	        	
	        	  lenovoSmartTab.setRom(currentCell.getStringCellValue());
	            break;
	          case 4:
		        	
		        	  lenovoSmartTab.setSize(currentCell.getNumericCellValue());
		            break;
	          case 5:
		        	
		        	  lenovoSmartTab.setExpandableUpto(Integer.valueOf((int) currentCell.getNumericCellValue()));
		            break;
	          case 6:
		        	
	        	  lenovoSmartTab.setPrimaryCamera(currentCell.getStringCellValue());
		        	  
		            break;
	          case 7:
		        	
	        	  lenovoSmartTab.setBattery(currentCell.getStringCellValue());
		        	  
		            break;
	          case 8:
		        	 
		        	  lenovoSmartTab.setProcessor(currentCell.getStringCellValue());
		        	  
		            break;
	          case 9:
		        	
		        	  lenovoSmartTab.setTabletGuarantee(currentCell.getStringCellValue());
		        	  
		            break;
	          case 10:
		        
		        	  lenovoSmartTab.setAccessoriesGuarantee(currentCell.getStringCellValue());
		        	  
		            break;
	         
	          default:
	            break;
	          }
	          cellIdx++;
	        }
	        lenovoSmartTabs.add(lenovoSmartTab);
	      }
	      workbook.close();
	      return  lenovoSmartTabs;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
	  
	}