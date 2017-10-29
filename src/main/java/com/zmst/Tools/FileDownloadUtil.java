package com.zmst.Tools;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileDownloadUtil {
	String[] fileName = null;

	public String[] getfileName(int tableNumber) {
		return fileName;
		// TODO Auto-generated method stub

	}

	public HSSFWorkbook generateExcel() {
		// TODO Auto-generated method stub
		return new HSSFWorkbook();

	}

	public HSSFWorkbook generateSheet(HSSFWorkbook wb, String sheetname,  List<Object> objectList) {
		// TODO Auto-generated method stub
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetname);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置表头字段名
		HSSFCell cell;
		Map<String,String>codeMap = MapUtil.getMap();
		 
		Field[] fails = objectList.get(0).getClass().getDeclaredFields();
		int m = 0;
		for (Field fail : fails) {
			String varName = fail.getName();
			String reg = "(smcode|smname|cntax|clcode|clname|clgdp|cltax|ctgdp|cttax|gdpcode|gdpname|gdp|avspend|spday"
					+ "|cpaspend|lipeople|ysday|fsta|gsta|tpsum|incode|inname|incoefficient|lacode|latax|laname|lagdp|ltgdp"
					+ "|lttax|name|code|tax|travetax|travegdp|smgdp|smtax|stgdp|sttax|rate|trgdp|trtax)";
			
		    Pattern pattern = Pattern.compile(reg);
		   
		    Matcher matcher = pattern.matcher(varName);
		   
		    boolean rs = matcher.find();
		   
		    if(rs){
		    	 if(codeMap.get(varName)==null){
		    		 continue;
		    	 }
		    	 System.out.println(varName);
		    	cell = row.createCell(m);
		    	cell.setCellValue(codeMap.get(varName)); 
				cell.setCellStyle(style);
				m++;
		    }else{
		    	continue;
		    }
		    
			 
		}

		for (int i = 0; i < objectList.size(); i++) {
			Field[] fields1 = objectList.get(i).getClass().getDeclaredFields();

			int w=0;
			row = sheet.createRow(i + 1);  
			for (Field field : fields1) {
				// 对于每个属性，获取属性名
				String varName = field.getName();
				try {
					boolean access = field.isAccessible();
					if (!access)
						field.setAccessible(true);

					// 从obj中获取field变量
					Object o = field.get(objectList.get(i));
				  String ws = String.valueOf(o);
					 
				 
					if(!ws.equals("null")){
						row.createCell(w).setCellValue(ws);
					    w++;
					}

					if (!access)
						field.setAccessible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		return wb;
	}

	public void export(String sheetname, HSSFWorkbook wb, HttpServletResponse response) {
		// TODO Auto-generated method stub
		   try  
	        {  
	           
	        	response.setHeader("content-disposition", "attachment;filename="
						+ URLEncoder.encode(sheetname, "utf-8") + ".xls");
	        	OutputStream out = response.getOutputStream();
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            wb.write( baos);
	            byte[] xlsBytes = baos .toByteArray();
	            out.write( xlsBytes);
	            out.close();

	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	}

}
