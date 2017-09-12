package com.pursuit.noteblog.office.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiDemo {
	
	public static void createExcel() throws IOException{
		
		//excel 又叫做工作簙 
		HSSFWorkbook workBook=new HSSFWorkbook();
		
		//创建sheet
		HSSFSheet sheet=workBook.createSheet("sheetTEST");
		
		//设置自定义的数据格式--比如日期
		HSSFDataFormat dataFormat=workBook.createDataFormat();
		
		//添加表的第一行
		HSSFRow row=sheet.createRow(0);
		HSSFRow row2=sheet.createRow(1);
//		HSSFRow row3=sheet.createRow(2);
//		HSSFRow row4=sheet.createRow(2);

		//设置单元格式
		HSSFCellStyle style=workBook.createCellStyle();
		
		//单元格格式居中
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		//设置单元格日期格式
		style.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
		
		//创建单元格
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("yes cell value");
		cell.setCellStyle(style);
		
		
		//创建单元格某行的那一列
		HSSFCell cell2=row.createCell(1);
		cell2.setCellValue(new Date());
		cell2.setCellStyle(style);
		
		HSSFCell cell3=row.createCell(2);
		cell3.setCellValue("no style");
		
		
		HSSFCell cell4=row2.createCell(3);
		cell4.setCellValue("row2 cell3  第2行第4列");
		
		
		
		File file=new File("D:/test.xls");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos= new FileOutputStream(file);
		
		workBook.write(fos);
		workBook.close();
		fos.close();
		
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("开始。。。。。");
		createExcel();
		System.out.println("结束。。。。。");
	}

}