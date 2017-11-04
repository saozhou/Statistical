package com.zmst.Controller;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;
import com.zmst.Service.ChangeToSaveService;
import com.zmst.Service.DeleteService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.Json2Map;

/**
 * 
 * @author Zhou
 *修改保存控制类
 */
@Controller
@RequestMapping("/ChangeToSave")
public class ChangToSaveController {
@Resource
private ChangeToSaveService change2saveService;
@Resource
private DeleteService deleteService;

	@RequestMapping(value="/gdp",method=RequestMethod.POST)
	@ResponseBody
	public void ChangeGdp(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		HttpSession session = request.getSession();
		Gdp gdp = new Gdp();
		String year = (String)session.getAttribute("year");
		
		gdp.setGdp(Double.valueOf(map.get("Gdp")));
		gdp.setGdpcode(map.get("Gdpcode"));
 
		gdp.setYear((String)session.getAttribute("year"));
		String place = (String) session.getAttribute("county");
		if("".equals(place) )place = (String) session.getAttribute("city");
		gdp.setPlace(place); 
		change2saveService.ChangeGdp(gdp);
		deleteService.DeleteGDP(year, place);
		deleteService.DeleteTravelGdp(year, place);
		  response.setContentType("text/html;charset=utf-8");
			HttpReturn.reponseBody(response,"保存成功");
	}
	
	@RequestMapping(value="/centralTax",method=RequestMethod.POST)
	@ResponseBody
	public void ChangeCentralTax(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		HttpSession session = request.getSession();
		String year = (String) session.getAttribute("year");
		CentralTax centralTax = new CentralTax();
		centralTax.setCntax(Double.valueOf(map.get("Cntax")));
		centralTax.setSmcode(map.get("Code"));
		centralTax.setYear((String) session.getAttribute("year"));
		String place = (String) session.getAttribute("county");
		if("".equals(place) )place = (String) session.getAttribute("city");
		centralTax.setPlace(place); 
		change2saveService.ChangeCentraTax(centralTax);
		deleteService.DeleteGDP(year, place);
		deleteService.DeleteTravelGdp(year, place);
		deleteService.DeleteTax(year, place);
		deleteService.DeleteTravelTax(year, place);
	      response.setContentType("text/html;charset=utf-8");
		HttpReturn.reponseBody(response,"保存成功");
	}
	
	@RequestMapping(value="/gfReference",method=RequestMethod.POST)
	@ResponseBody
	public void GfReference(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		HttpSession session = request.getSession();
		String year = (String) session.getAttribute("year");
		GFReference gfReference = new GFReference();
		gfReference.setIncoefficient(map.get("Incoefficient"));
		gfReference.setIncode(map.get("Incode"));
		gfReference.setYear((String) session.getAttribute("year"));
		String place = (String) session.getAttribute("county");
		if(place==null)place = (String) session.getAttribute("city");
		gfReference.setPlace(place); 
		change2saveService.ChangeGFRe(gfReference);
	 
		deleteService.DeleteTravelGdp(year, place);
	 
		deleteService.DeleteTravelTax(year, place);
		  response.setContentType("text/html;charset=utf-8");
			HttpReturn.reponseBody(response,"保存成功");
	}
	
	@RequestMapping(value="/gfCoefficient",method=RequestMethod.POST)
	@ResponseBody
	public void GFCoefficient(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		HttpSession session = request.getSession();
		String year = (String) session.getAttribute("year");
		GFCoefficient gfCoefficient = new GFCoefficient();
		gfCoefficient.setAvspend(Double.valueOf(map.get("Avspend")));
		gfCoefficient.setCpaspend(Double.valueOf(map.get("Cpaspend")));
		gfCoefficient.setFsta(Double.valueOf(map.get("Fsta")));
		gfCoefficient.setGsta(Double.valueOf(map.get("Gsta")));
		gfCoefficient.setLipeople(Double.valueOf(map.get("Lipeople")));
		gfCoefficient.setSpday(Double.valueOf(map.get("Spday")));
		gfCoefficient.setTpsum(Double.valueOf(map.get("Tpsum")));
		gfCoefficient.setYsday(Double.valueOf(map.get("Ysday")));
		gfCoefficient.setYear((String) session.getAttribute("year"));
		String place = (String) session.getAttribute("county");
		if("".equals(place) )place = (String) session.getAttribute("city");
		gfCoefficient.setPlace(place); 
		change2saveService.ChangeGFCo(gfCoefficient);
		
	 
		deleteService.DeleteTravelTax(year, place);
		deleteService.DeleteTravelGdp(year, place);
		  response.setContentType("text/html;charset=utf-8");
			HttpReturn.reponseBody(response,"保存成功");
	}
	
	@RequestMapping(value="/landTax",method=RequestMethod.POST)
	@ResponseBody
	public void LandTax(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		LandTax landTax = new LandTax();
		HttpSession session = request.getSession();
		String year = (String) session.getAttribute("year");
		landTax.setLatax(Double.valueOf(map.get("Latax")));
		landTax.setSmcode(map.get("Smcode"));
		landTax.setYear((String) session.getAttribute("year"));
		String place = (String) session.getAttribute("county");
		if("".equals(place) )place = (String) session.getAttribute("city");
		landTax.setPlace(place); 
		change2saveService.ChangeLandTax(landTax);
		deleteService.DeleteGDP(year, place);
		deleteService.DeleteTravelGdp(year, place);
		deleteService.DeleteTax(year, place);
		deleteService.DeleteTravelTax(year, place);
		  response.setContentType("text/html;charset=utf-8");
			HttpReturn.reponseBody(response,"保存成功");
	}
	
	@RequestMapping(value="/Session",method=RequestMethod.POST)
	@ResponseBody
	public void Session(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = Json2Map.JSON2Map(json);
		HttpSession session = request.getSession();
		Set<String> keys = map.keySet();
		for(String key:keys){
			if(session.getAttribute(key)!=null) session.removeAttribute(key);
			session.setAttribute(key, map.get(key));
		}
			HttpReturn.reponseBody(response,"保存成功");
	}
}
