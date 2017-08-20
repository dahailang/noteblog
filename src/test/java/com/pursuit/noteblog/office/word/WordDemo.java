package com.pursuit.noteblog.office.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.web.util.WebUtils;

import com.pursuit.noteblog.util.DateUtils;

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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("写出word结束");
	}
	
}
