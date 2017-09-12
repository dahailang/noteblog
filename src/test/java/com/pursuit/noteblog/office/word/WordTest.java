package com.pursuit.noteblog.office.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordTest {
	
	public static void main(String[] args){
		try {
			readAndWriterTest4();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	static String filepath = "D:\\dform@吉林项目\\大版本BOSS系统前台页面第一次检查报告.doc";
	
	public static void readAndWriterTest4() throws IOException {
        File file = new File(filepath);
        try {
            FileInputStream fis = new FileInputStream(file);
            System.out.println(file.exists()+"++"+file.getPath());
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String doc1 = extractor.getText();
            System.out.println(doc1);
            extractor.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
