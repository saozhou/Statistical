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
import javax.annotation.*;


import com.alibaba.fastjson.JSON;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.IDao.SubTravelGdpMapper;
import com.zmst.Service.TravelGdpCalculateService;
import com.zmst.Tools.HttpReturn;

/**
 * 
 * @author Zhou
 *旅游gdp计算控制类
 */
@Controller
@RequestMapping("/TravelGdpCalculate")
public class TravelGdpCalculateController {
	@Resource
	private TravelGdpCalculateService travelGdpCalculateService;
	
	
	@RequestMapping(value="/subTravelGdpCaculate",method=RequestMethod.POST)  
    @ResponseBody
    public void subTravelGdpCaculate(HttpServletRequest request,HttpServletResponse response){		 
		 HttpSession session = request.getSession();		
		 response.setContentType("text/html;charset=utf-8");
		 String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		 System.out.println(year);
		 System.out.println(city);
		 System.out.println(county);
		if(county.length()>0){
			 place=county;
		}else{
			place=city;
		}
	    List<SubTravelGdp>subTravelGdpList = new ArrayList<SubTravelGdp>(); 
	    subTravelGdpList=travelGdpCalculateService.findByYearPlace(year,place);
	    if(subTravelGdpList.size()==0){
			 
	    	subTravelGdpList = travelGdpCalculateService.getSubTravelGdp(year,place,response);  
			
	    }		    
		 try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //这里不设置编码会有乱码
		      response.setContentType("text/html;charset=utf-8");
			  String json = JSON.toJSONString(subTravelGdpList);
			  if(subTravelGdpList!=null){
				  System.out.println(subTravelGdpList.size());
				  	System.out.println(subTravelGdpList.get(1).getStgdp());
				  HttpReturn.reponseBody(response, json);
			  }
			   
	
	}
}
