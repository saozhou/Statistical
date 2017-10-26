package com.zmst.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Zhou
 *自定义查询控制类
 */
@Controller
@RequestMapping("/SelfDefineSearch")
public class SelfDefinedSearchController {
	
	@ResponseBody
	@RequestMapping(value="/getinfo",method=RequestMethod.POST)
	public void GetInfo(@RequestBody String json	){
		
		
	}

}
