package com.zmst.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.*;
import com.zmst.Service.FileDownloadService;

/**
 * 
 * @author Zhou
 *文件下载
 *数据编码
 *gdp表    1   小类税收           5      大类gdp贡献   9      大类税收贡献  12     gf系数参照表   15
 *地税          2   小类gdp    6     门类gdp贡献     10     门类税收贡献   13    代码库  16
 *国税          3   小类旅游税收          7    产业gdp贡献       11    产业税收贡献   14
 *gf系数   4   小类旅游gdp   8 
 *
 */
@Controller
@RequestMapping("/FileDownload")
public class FileDownloadController {
	@Resource
	private FileDownloadService fileDownLoadService;
	
	
	@RequestMapping(value="/fileDownLoad",method=RequestMethod.GET)  
    @ResponseBody
    public String gdpFileDown(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String city =  null;
		String county = null;
		String year = null;
		String place=null;
		int type = Integer.parseInt( request.getParameter("type"));
		city = (String) session.getAttribute("city");
	    county= (String) session.getAttribute("county");
		year=(String)session.getAttribute("year");
		 
		if(county!=null){
			 place=county;
			 
		}else{
			 
		    place=city;
		}
	    boolean result =	fileDownLoadService.export(year,place,response,type);
	    if(!result){
	    	return "fail";
	    }
	    return "yes";
		
	}

}
