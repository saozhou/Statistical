package com.zmst.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtil {
	 public static List<List<String>> readXls(String path) throws IOException{  
	        //检查文件  
			InputStream ps = new FileInputStream(path);
			
			HSSFWorkbook xss = new HSSFWorkbook(ps);
			List<List<String>> lists= new ArrayList<List<String>>();
			for(Sheet shees:xss){
				 
				if(shees==null){
					continue;
				}
				for(int j=0;j<=shees.getLastRowNum();j++){
					HSSFRow row = (HSSFRow) shees.getRow(j);
					int l = row.getLastCellNum();
					int f = row.getFirstCellNum();
					 
					List<String>list = new ArrayList<String>();
					
					for(int w= f;w<l;w++){
						HSSFCell cell= row.getCell(w);
						if(cell==null){
							continue;
						}
						list.add(cell.toString());
					}
					lists.add(list);
				}
			}
			return lists;
	    }  
	 public static List<List<String>> readXlsx(String path) throws IOException{  
	        //检查文件  
			InputStream ps = new FileInputStream(path);
			
			XSSFWorkbook xss = new XSSFWorkbook(ps);
			List<List<String>> lists= new ArrayList<List<String>>();
			for(Sheet shees:xss){
				 
				if(shees==null){
					continue;
				}
				for(int j=0;j<=shees.getLastRowNum();j++){
					XSSFRow row = (XSSFRow) shees.getRow(j);
					if(row==null) continue;
					int l = row.getLastCellNum();
				 
	                int f = row.getFirstCellNum();
	                
					List<String>list = new ArrayList<String>();
					for(int w= f;w<l;w++){
						XSSFCell cell= row.getCell(w);
						if(cell==null){
							continue;
						}
						list.add(cell.toString());
					}
					lists.add(list);
				}
			}
			return lists;
	    }  
}
