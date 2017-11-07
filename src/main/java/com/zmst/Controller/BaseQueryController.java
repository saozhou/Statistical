package com.zmst.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;
import com.zmst.Service.BasedQueryService;
import com.zmst.Tools.HttpReturn;

/**
 * 
 * @author Zhou 基础查询控制类
 */
@Controller
@RequestMapping("/BaseQuery")
public class BaseQueryController {

	@Resource
	private BasedQueryService baseQueryService;

	/**
	 * 地税查询
	 * 
	 */
	@RequestMapping(value = "/landTaxGet", method = RequestMethod.POST)
	@ResponseBody
	public void landTaxGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		String year = null;
		String city = null;
		String county = null;
		String place = null;

		year = (String) session.getAttribute("year");
		city = (String) session.getAttribute("city");
		county = (String) session.getAttribute("county");

		if (county.length() > 0) {
			place = county;
		} else {
			place = city;
		}

		List<LandTax> landTax = baseQueryService.getLandTax(year, place, response);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		if (landTax != null) {
			String json = JSON.toJSONString(landTax);
			HttpReturn.reponseBody(response, json);
			;
		}
	}

	/**
	 * 国税查询
	 * 
	 */
	@RequestMapping(value = "/centralTaxGet", method = RequestMethod.POST)
	@ResponseBody
	public void centralTaxGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		String year = null;
		String city = null;
		String county = null;
		String place = null;

		year = (String) session.getAttribute("year");
		city = (String) session.getAttribute("city");
		county = (String) session.getAttribute("county");

		if (county.length() > 0) {
			place = county;
		} else {
			place = city;
		}

		List<CentralTax> centralTax = baseQueryService.getCentralTax(year, place, response);

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		if (centralTax != null) {
			String json = JSON.toJSONString(centralTax);
			HttpReturn.reponseBody(response, json);
			;
		}
	}

	/**
	 * gdp查询
	 * 
	 */
	@RequestMapping(value = "/gdpGet", method = RequestMethod.POST)
	@ResponseBody
	public void gdpGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String year = null;
		String city = null;
		String county = null;
		String place = null;

		year = (String) session.getAttribute("year");
		city = (String) session.getAttribute("city");
		county = (String) session.getAttribute("county");

		if (county.length() > 0) {
			place = county;
		} else {
			place = city;
		}

		List<Gdp> gdp = baseQueryService.getGdpTax(year, place, response);

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码

		if (gdp != null) {
			String json = JSON.toJSONString(gdp);
			HttpReturn.reponseBody(response, json);
			;
		}
	}

	/**
	 * 查询
	 * 
	 */
	@RequestMapping(value = "/gfCoefficientGet", method = RequestMethod.POST)
	@ResponseBody
	public void gfCoefficientGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		String year = null;
		String city = null;
		String county = null;
		String place = null;

		year = (String) session.getAttribute("year");
		city = (String) session.getAttribute("city");
		county = (String) session.getAttribute("county");
		System.out.println(year);
		System.out.println(city);
		System.out.println(county);
		System.out.println(4444);
		if (county.length() > 0) {
			place = county;
		} else {
			place = city;
		}

		GFCoefficient gfCoefficient = baseQueryService.getGFCoefficient(year, place, response);

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码

		if (gfCoefficient != null) {
			String json = JSON.toJSONString(gfCoefficient);
			HttpReturn.reponseBody(response, json);
		}
	}
}
