package com.zmst.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmst.Domain.Gdp;
import com.zmst.Tools.Json2Map;

/**
 * 
 * @author Zhou
 *修改保存控制类
 */
@Controller
@RequestMapping("/ChangeToSave")
public class ChangToSaveController {

	@RequestMapping(value="/gdp",method=RequestMethod.POST)
	@ResponseBody
	public void ChangeGdp(@RequestBody String json){
		Map<String, String> map = Json2Map.JSON2Map(json);
		Gdp gdp = new Gdp();
		gdp.setGdp(Double.valueOf(map.get("Gdp")));
		gdp.setGdpcode(map.get("Gdpcode"));
		gdp.setYear(map.get("Year"));
	}
	
}
