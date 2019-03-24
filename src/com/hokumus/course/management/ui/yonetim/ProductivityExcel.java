package com.hokumus.course.management.ui.yonetim;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hokumus.course.management.model.yonetim.Productivity;


public class ProductivityExcel extends ProductivityFrame {
		    
	private static final String FILE_NAME = "C:\\VerimlilikRaporu.xlsx";
	private static String[] columns = {"Kursun Adı","NO","Kar/Zarar","Kursun Verimliliği","Kursun Puanı","Öğretmenlerin Verimliliği","Öğretmenlerin Puanı","Öğrencilerin Verimliliği","Öğrencilerin Puanı","Öneriler","Öneri Yapan Kullanıcı","Tarih"};
 	
	public ProductivityExcel() {
		proExcel();
	}
    private void proExcel() {      
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("VerimlilikRaporu");
        CreationHelper createHelper = workbook.getCreationHelper();
       
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        

        int rowNum = 1;
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy HH:mm:ss"));
        
        getCourse();
       
		for (int i = 0; i < liste.size(); i++) {
			List<Productivity> productivityList=getProductivity(liste.get(i).getId());
			for (int j = 0; j < productivityList.size(); j++) {
				Row row = sheet.createRow(rowNum++);		
				if(liste.get(0).getAdi()!=null)
					row.createCell(0).setCellValue(liste.get(i).getAdi());
				else
					row.createCell(0).setCellValue("");
				
				row.createCell(1).setCellValue(productivityList.get(j).getId());
				
				if(productivityList.get(j).getKarZarar()!=null)
					row.createCell(2).setCellValue(productivityList.get(j).getKarZarar().toString());
				else
					row.createCell(2).setCellValue("");
				
				if(productivityList.get(j).getKursVerimlilik()!=null)
					row.createCell(3).setCellValue(productivityList.get(j).getKursVerimlilik());
				else
					row.createCell(3).setCellValue("");
				
				if(productivityList.get(j).getKursVNotu()!=null)
					row.createCell(4).setCellValue(productivityList.get(j).getKursVNotu());
				else
					row.createCell(4).setCellValue("");
				
				if(productivityList.get(j).getOrgetmenVerimlilik()!=null)
					row.createCell(5).setCellValue(productivityList.get(j).getOrgetmenVerimlilik());
				else
					row.createCell(5).setCellValue("");
				
				if(productivityList.get(j).getOrgetmenVNotu()!=null)
					row.createCell(6).setCellValue(productivityList.get(j).getOrgetmenVNotu());
				else
					row.createCell(6).setCellValue("");
				
				if(productivityList.get(j).getOgrenciVerimlilik()!=null)
					row.createCell(7).setCellValue(productivityList.get(j).getOgrenciVerimlilik());
				else
					row.createCell(7).setCellValue("");
				
				if(productivityList.get(j).getOgrenciVNotu()!=null)
					row.createCell(8).setCellValue(productivityList.get(j).getOgrenciVNotu());
				else
					row.createCell(8).setCellValue("");
				
				if(productivityList.get(j).getOneriler()!=null)
					row.createCell(9).setCellValue(productivityList.get(j).getOneriler());
				else
					row.createCell(9).setCellValue("");
				
				if(productivityList.get(j).getOneriler()!=null)
					row.createCell(10).setCellValue(productivityList.get(j).getEkleyen());
				else
					row.createCell(10).setCellValue("");
				
				if(productivityList.get(j).getEklemeTarihi()!=null) 
				{
					Cell dateOfCell = row.createCell(11);
			        dateOfCell.setCellValue(productivityList.get(j).getEklemeTarihi());
			        dateOfCell.setCellStyle(dateCellStyle);
				}
				else
					row.createCell(11).setCellValue("");
			
				}
	
			}
	

	 // Resize all columns to fit the content size
	    for(int i = 0; i < columns.length; i++) {
	        sheet.autoSizeColumn(i);
	    }
	
	    try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
	
}
