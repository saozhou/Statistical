package com.zmst.Controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zmst.Service.SelfDefinedSearchService;
import com.zmst.ServiceImpl.SelfDefinedSearchServiceImpl;
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
	@RequestMapping(value="/ClassSearche",method=RequestMethod.POST)
	@ResponseBody
	public void ClassSearche( @RequestBody String data,HttpServletResponse response){
		Map<String, String> json = Json2Map.JSON2Map(data);
		int type = Integer.parseInt(json.get("type"));
		String year = json.get("year");
		String place = json.get("place");
		if((type&64)!=0){
			HttpReturn.reponseBody(response, sf.GetDoor(type, year, place));			
		}else if ((type&32)!=0){
			HttpReturn.reponseBody(response, sf.GetSub(type, year, place));			
		}else if((type&16)!=0){
			HttpReturn.reponseBody(response, sf.GetLarge(type, year, place));			
		}
	}
	
}
