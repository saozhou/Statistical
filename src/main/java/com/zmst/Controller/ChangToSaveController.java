package com.zmst.Controller;

import java.util.Map;

import javax.annotation.Resource;

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


	@RequestMapping(value="/gdp",method=RequestMethod.POST)
	@ResponseBody
	public void ChangeGdp(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		Gdp gdp = new Gdp();
		gdp.setGdp(Double.valueOf(map.get("Gdp")));
		gdp.setGdpcode(map.get("Gdpcode"));
		gdp.setYear(map.get("Year"));
		gdp.setPlace(map.get("Place")); 
		change2saveService.ChangeGdp(gdp);
	}
	
	@RequestMapping(value="/centralTax",method=RequestMethod.POST)
	@ResponseBody
	public void ChangeCentralTax(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		CentralTax centralTax = new CentralTax();
		centralTax.setCntax(Double.valueOf(map.get("Cntax")));
		centralTax.setSmcode(map.get("Code"));
		centralTax.setYear(map.get("Year"));
		centralTax.setPlace(map.get("Place")); 
		change2saveService.ChangeCentraTax(centralTax);
	}
	
	@RequestMapping(value="/gfReference",method=RequestMethod.POST)
	@ResponseBody
	public void GfReference(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		GFReference gfReference = new GFReference();
		gfReference.setIncoefficient(map.get("Incode"));
		gfReference.setIncode(map.get("Code"));
		gfReference.setYear(map.get("Year"));
		gfReference.setPlace(map.get("Place")); 
		change2saveService.ChangeGFRe(gfReference);
	}
	
	@RequestMapping(value="/gfCoefficient",method=RequestMethod.POST)
	@ResponseBody
	public void GFCoefficient(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		GFCoefficient gfCoefficient = new GFCoefficient();
		gfCoefficient.setAvspend(Double.valueOf(map.get("Avspend")));
		gfCoefficient.setCpaspend(Double.valueOf(map.get("Cpaspend")));
		gfCoefficient.setFsta(Double.valueOf(map.get("Fsta")));
		gfCoefficient.setGfid(Integer.valueOf(map.get("Gfid")));
		gfCoefficient.setGsta(Double.valueOf(map.get("Gsta")));
		gfCoefficient.setLipeople(Double.valueOf(map.get("Lipeople")));
		gfCoefficient.setSpday(Double.valueOf(map.get("Spday")));
		gfCoefficient.setTpsum(Double.valueOf(map.get("Tpsum")));
		gfCoefficient.setYsday(Double.valueOf(map.get("Ysday")));
		gfCoefficient.setYear(map.get("Year"));
		gfCoefficient.setPlace(map.get("Place")); 
		change2saveService.ChangeGFCo(gfCoefficient);
	}
	
	@RequestMapping(value="/landTax",method=RequestMethod.POST)
	@ResponseBody
	public void LandTax(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		LandTax landTax = new LandTax();
		landTax.setLatax(Double.valueOf(map.get("Latax")));
		landTax.setYear(map.get("Year"));
		landTax.setPlace(map.get("Place")); 
		change2saveService.ChangeLandTax(landTax);
	}
	

}
