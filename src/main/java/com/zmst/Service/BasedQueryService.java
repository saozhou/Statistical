package com.zmst.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;

import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;

/**
 * 
 * @author Zhou
 *基础查询service
 */
public interface BasedQueryService {

	/*
	 * 门类
	 */
	public List<ClassGdp> GetcGdp(String year ,String place);
	public List<ClassTax> GetcTax(String year ,String place);
	public List<ClassTravelGdp> GetctGdp(String year ,String place);
	public List<ClassTravelTax> GetctTax(String year ,String place);
	/*
	 * 大类
	 */
	public List<LargeGdp	> GetlGdp(String year,String place);
	public List<LargeTax> GetlTax(String year,String place);
	public List<LargeTravelGdp> GetlTravelGdp(String year,String place);
	public List<LargeTravelTax> GetlTravelTax(String year,String place);
	/*
	 * 小类
	 */
	public List<SubGdp	> GetsGdp(String year,String place);
	public List<SubTax> GetsTax(String year,String place);
	public List<SubTravelGdp> GetsTravelGdp(String year,String place);
	public List<SubTravelTax> GetsTravelTax(String year,String place);


	 

	List<LandTax> getLandTax(String year, String place, HttpServletResponse response);

	GFCoefficient getGFCoefficient(String year, String place, HttpServletResponse response);

	List<Gdp> getGdpTax(String year, String place, HttpServletResponse response);

	List<CentralTax> getCentralTax(String year, String place, HttpServletResponse response);
 


}
