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
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;
import com.zmst.Domain.TravelClassGdpContribute;
import com.zmst.Domain.TravelClassTaxContribute;
import com.zmst.Domain.TravelIndustryGdpContribute;
import com.zmst.Domain.TravelIndustryTaxContribute;
import com.zmst.Domain.TravelLargeGdpContribute;
import com.zmst.Domain.TravelLargeTaxContribute;
import com.zmst.Service.IntegratedQueryService;
import com.zmst.Tools.HttpReturn;
import javax.annotation.*;

/**
 * 
 * @author Zhou
 *综合查询
 */
@Controller
@RequestMapping("/IntegratedQuery")
public class IntegratedQueryController {

	@Resource
	private IntegratedQueryService integratedQueryService;
	/**
	 * gdp大类综合查询
	 */
	
	@RequestMapping(value="/largeGdpContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void largeGdpConrtibute(HttpServletRequest request,HttpServletResponse response){

		
		
		 
        HttpSession session = request.getSession();		 
		String year = "2017";//(String) session.getAttribute("year");
		String city ="张家界";
		String county=null; 
		String place = null;
		//city=(String) session.getAttribute("city");
		 
		//county= (String)session.getAttribute("county");
		 
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
         List<TravelLargeGdpContribute> travelLargeGdpContributeList = new ArrayList<TravelLargeGdpContribute>();
         
         travelLargeGdpContributeList = integratedQueryService.getTravelLargeGdpContribute(year,place);
		if(travelLargeGdpContributeList.size()==0){
			travelLargeGdpContributeList = integratedQueryService.getLargeGdpContribute(travelLargeGdpContributeList,year, place);
			 
		 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(travelLargeGdpContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	
	/**
	 * gdp贡献门类查询
	 */
	
	@RequestMapping(value="/classGdpContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void classGdpConrtibute(HttpServletRequest request,HttpServletResponse response){

		   HttpSession session = request.getSession();		 
		
		   String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		 
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
         List<TravelClassGdpContribute> classGdpContributeList = new ArrayList<TravelClassGdpContribute>();
         
         classGdpContributeList = integratedQueryService.getClassGdpContribute(year,place,classGdpContributeList);
         System.out.println(classGdpContributeList.size());
		if(classGdpContributeList.size()==0){
			classGdpContributeList = integratedQueryService.getClassGdpContributeList(classGdpContributeList,year, place);
			 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(classGdpContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	 /**
	  * 
	  * @param request
	  * @param response
	  *gdp 产业1
	  */
	@RequestMapping(value="/industryGdpContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void industryGdpConrtibute(HttpServletRequest request,HttpServletResponse response){

        HttpSession session = request.getSession();		 
        String year =null;  
		String city =null;
		String county=null; 
		String place = null;
		 
		year = (String) session.getAttribute("year");    
		city=(String) session.getAttribute("city");
		county= (String)session.getAttribute("county");
		 
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
         List<TravelIndustryGdpContribute> industryGdpContributeList = new ArrayList<TravelIndustryGdpContribute>();
         
         industryGdpContributeList = integratedQueryService.getIndustryGdpContribute(year,place,industryGdpContributeList);
         
		if(industryGdpContributeList.size()==0){
			industryGdpContributeList = integratedQueryService.getIndustryGdpContributeList(industryGdpContributeList,year, place);
			 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(industryGdpContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 税收贡献大类
	 */
	@RequestMapping(value="/largeTaxContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void largeTaxConrtibute(HttpServletRequest request,HttpServletResponse response){

        HttpSession session = request.getSession();
        String year =null;  
		String city =null;
		String county=null; 
		String place = null;
		 
		year = (String) session.getAttribute("year");    
		city=(String) session.getAttribute("city");
		county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
	    List<TravelLargeTaxContribute> travelLargeTaxContributeList = new ArrayList<TravelLargeTaxContribute>();
        
	    travelLargeTaxContributeList = integratedQueryService.getTravelLargeTaxContribute(year,place,travelLargeTaxContributeList);
		if(travelLargeTaxContributeList.size()==0){
			travelLargeTaxContributeList = integratedQueryService.getLargeTaxContribute(travelLargeTaxContributeList,year, place);
			 
		 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(travelLargeTaxContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 税收门类
	 */

	@RequestMapping(value="/classTaxContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void classTaxConrtibute(HttpServletRequest request,HttpServletResponse response){

		
		
		 
        HttpSession session = request.getSession();		 
        String year =null;  
		String city =null;
		String county=null; 
		String place = null;
		 
		year = (String) session.getAttribute("year");    
		city=(String) session.getAttribute("city");
		county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
         List<TravelClassTaxContribute> classTaxContributeList = new ArrayList<TravelClassTaxContribute>();
         
         classTaxContributeList = integratedQueryService.getClassTaxContribute(year,place,classTaxContributeList);
         
		if(classTaxContributeList.size()==0){
			classTaxContributeList = integratedQueryService.getClassTaxContributeList(classTaxContributeList,year, place);
			 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(classTaxContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	 /**
	  * 
	  * @param request
	  * @param response
	  *tax 产业1
	  */
	@RequestMapping(value="/industryTaxContributeSearch",method=RequestMethod.POST)
	@ResponseBody
	public void industryTaxConrtibute(HttpServletRequest request,HttpServletResponse response){

       HttpSession session = request.getSession();		 
       String year =null;  
		String city =null;
		String county=null; 
		String place = null;
		 
		year = (String) session.getAttribute("year");    
		city=(String) session.getAttribute("city");
		county= (String)session.getAttribute("county");
		 
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
        List<TravelIndustryTaxContribute> industryTaxContributeList = new ArrayList<TravelIndustryTaxContribute>();
        
        industryTaxContributeList = integratedQueryService.getIndustryTaxContribute(year,place,industryTaxContributeList);
        
		if(industryTaxContributeList.size()==0){
			industryTaxContributeList = integratedQueryService.getIndustryTaxContributeList(industryTaxContributeList,year, place);
			 
			}
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //这里不设置编码会有乱码
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(industryTaxContributeList);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 小类税收
	 * 
	 */
	@RequestMapping(value="/subTaxSearch",method=RequestMethod.POST)
	@ResponseBody
	public void subTaxSearch(HttpServletRequest request,HttpServletResponse response){


	       HttpSession session = request.getSession();		 
	       String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
		 List<SubTax> subTax =	integratedQueryService.getSubTaxt(year,place);
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //杩欓噷涓嶈缃紪鐮佷細鏈変贡鐮�
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(subTax);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 小磊旅游税收查询
	 * 
	 */
	@RequestMapping(value="/subTravelTaxSearch",method=RequestMethod.POST)
	@ResponseBody
	public void subTravelTaxSearch(HttpServletRequest request,HttpServletResponse response){

	       HttpSession session = request.getSession();		 
	       String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
		 List<SubTravelTax> subTravelTax =	integratedQueryService.getSubTravelTaxt(year,place);
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //杩欓噷涓嶈缃紪鐮佷細鏈変贡鐮�
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(subTravelTax);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 *小类gdp
	 * 
	 */
	@RequestMapping(value="/subGdpSearch",method=RequestMethod.POST)
	@ResponseBody
	public void subGdpSearch(HttpServletRequest request,HttpServletResponse response){


	       HttpSession session = request.getSession();		 
	       String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
		 List<SubGdp> subGdp=	integratedQueryService.getSubGdp(year,place);
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //杩欓噷涓嶈缃紪鐮佷細鏈変贡鐮�
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(subGdp);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 小类旅游gdp
	 * 
	 */
	@RequestMapping(value="/subTravelGdpSearch",method=RequestMethod.POST)
	@ResponseBody
	public void subTravelGdpSearch(HttpServletRequest request,HttpServletResponse response){


	       HttpSession session = request.getSession();		 
	       String year =null;  
			String city =null;
			String county=null; 
			String place = null;
			 
			year = (String) session.getAttribute("year");    
			city=(String) session.getAttribute("city");
			county= (String)session.getAttribute("county");
		if(county!=null){
			 place=county;
		}else{
			place=city;
		}
		
		 List<SubTravelGdp> subTravelGdp=	integratedQueryService.getSubTravelGdp(year,place);
			
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //杩欓噷涓嶈缃紪鐮佷細鏈変贡鐮�
	      response.setContentType("text/html;charset=utf-8");
		  String json = JSON.toJSONString(subTravelGdp);
		  HttpReturn.reponseBody(response, json);
		 
	}
	
}
