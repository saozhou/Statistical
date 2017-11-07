package com.zmst.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zmst.Service.SelfDefinedSearchService;
import com.zmst.Tools.FileDownloadUtil;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.Json2Map;

/**
 * 
 * @author Zhou
 *自定义查询控制类
 */
@Controller
@RequestMapping("/SelfDefineSearch")
public class SelfDefinedSearchController {

	@Resource
	private SelfDefinedSearchService sf;
	@RequestMapping(value="/Searche",method=RequestMethod.POST)
	@ResponseBody
	public void Searche( @RequestBody String data,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> json = Json2Map.JSON2Map(data);
		HttpSession session = request.getSession();		 
		int type = Integer.parseInt(json.get("type"));
		String year = (String) session.getAttribute("year");
		String place = (String) session.getAttribute("county");
		if("".equals(place)) place = (String) session.getAttribute("city");
		JSONArray jsonArray=null;
		if((type&64)!=0){
			 jsonArray = new JSONArray(sf.GetDoor(type, year, place));
			
		}else if ((type&32)!=0){
			 jsonArray = new JSONArray(sf.GetSub(type, year, place));
					
		}else if((type&16)!=0){
			 jsonArray = new JSONArray(sf.GetLarge(type, year, place));
		}
		System.out.println();
		HttpReturn.reponseBody(response, jsonArray);			
	}
	@RequestMapping(value="/Download",method=RequestMethod.GET)
	@ResponseBody
	public void DownLoad( HttpServletRequest request,HttpServletResponse response) throws IOException{
		int type = Integer.parseInt( request.getParameter("type"));
		HttpSession session = request.getSession();	
		String year = (String) session.getAttribute("year");
		String place = (String) session.getAttribute("county");
		if("".equals(place)) place = (String) session.getAttribute("city");
		
		String sheetname = "自定义查询表";
		FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
		if((type&64)!=0){
			List<Object> objectList = sf.GetDoor(type, year, place) ;
			HSSFWorkbook wb = fileDownloadUtil.generateExcel();
			wb = fileDownloadUtil.generateSheet(wb, sheetname, objectList);
			fileDownloadUtil.export(sheetname,wb, response);
		}else if ((type&32)!=0){
			List<Object> objectList = sf.GetSub(type, year, place) ;
			HSSFWorkbook wb = fileDownloadUtil.generateExcel();
			wb = fileDownloadUtil.generateSheet(wb, sheetname, objectList);
			fileDownloadUtil.export(sheetname,wb, response);	
		}else if((type&16)!=0){
			List<Object> objectList = sf.GetLarge(type, year, place) ;
			for(Object temp:objectList){
				System.out.println(temp.getClass().getName());
			}
			HSSFWorkbook wb = fileDownloadUtil.generateExcel();
			wb = fileDownloadUtil.generateSheet(wb, sheetname, objectList);
			fileDownloadUtil.export(sheetname,wb, response);	
		}
	}
	  
}
