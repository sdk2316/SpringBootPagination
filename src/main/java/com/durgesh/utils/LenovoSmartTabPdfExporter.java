package com.durgesh.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.durgesh.model.LenovoSmartTab;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class LenovoSmartTabPdfExporter {

	 private List<LenovoSmartTab> listLenovoSmartTab;
     
	    public LenovoSmartTabPdfExporter(List<LenovoSmartTab> listLenovoSmartTab) {
	        this.listLenovoSmartTab = listLenovoSmartTab;
	    }
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Product ID", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Modell", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("RAM", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("ROM", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Size", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("ExpandableUptoe", font));
	        table.addCell(cell);
	        
	        
	        cell.setPhrase(new Phrase("PrimaryCamera", font));
	        table.addCell(cell);
	        
	        
	        cell.setPhrase(new Phrase("Battery", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Processor", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("TabletGuarantee", font));
	        table.addCell(cell);
	        
	        
	        cell.setPhrase(new Phrase("AccessoriesGuarantee", font));
	        table.addCell(cell);
	    }
	     
	    private void writeTableData(PdfPTable table) {
	        for (LenovoSmartTab lenovoList : listLenovoSmartTab) {
	            table.addCell(String.valueOf(lenovoList.getId()));
	            table.addCell(lenovoList.getModel());
	            table.addCell(lenovoList.getRam());
	            table.addCell(lenovoList.getRom());
	            table.addCell(lenovoList.getSize().toString());
	            table.addCell(lenovoList.getExpandableUpto().toString());
	            table.addCell(lenovoList.getPrimaryCamera());
	            table.addCell(lenovoList.getBattery());
	            table.addCell(lenovoList.getProcessor());
	            table.addCell(lenovoList.getTabletGuarantee());
	            table.addCell(lenovoList.getAccessoryGuarantee());
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of LenovoSmartTab", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(11);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }
	}