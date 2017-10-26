package com.zmst.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Service.GdpCalculateService;
import com.zmst.Tools.HttpReturn;

import javax.annotation.*;
/**
 * 
 * @author Zhou
 *gdp计算控制类 
 *大类 large 
 *小类 subclass
 *门类  class
 */
@Controller
@RequestMapping("/GdpCaculate")
public class GdpCaculateController {

	@Resource
	private GdpCalculateService gdpService;
	@RequestMapping(value="/subGdpCaculate",method=RequestMethod.POST)  
    @ResponseBody
    public void subGdpCaculate(HttpServletRequest request,HttpServletResponse response){		 
		 HttpSession session = request.getSession();		 
		String year = (String) session.getAttribute("year");
		String city =null;
		city=(String) session.getAttribute("city");
		String county=null; 
		county= (String)session.getAttribute("county");
		String place = null;
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
	    List<SubGdp>subGdpList = new ArrayList<SubGdp>(); 
	    subGdpList=gdpService.findSubGdpByYearPlace(year,place);
	    if(subGdpList.size()==0){
			 
			 subGdpList = gdpService.getSubgdp(year,place);  
			
	    }		    
		 try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //这里不设置编码会有乱码
		      response.setContentType("text/html;charset=utf-8");
			  String json = JSON.toJSONString(subGdpList);
			  HttpReturn.reponseBody(response, json);
	
	}
	
}
