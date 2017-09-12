package com.pursuit.noteblog.office.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class WordDemo {
	public static void main(String[] args) {
		System.out.println("写出word开始");
		System.out.println(DateFormatUtils.format(new Date(),"yyyyMMdd"));
		try {
			XWPFDocument doc = new XWPFDocument();
			XWPFTable table = doc.createTable();
			//table.setWidth(200);
			table.setCellMargins(20, 20, 20, 20);
			FileOutputStream stream = new FileOutputStream(new File("D:/testword.doc"));
			doc.write(stream);
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("写出word结束");
	}
	
}
