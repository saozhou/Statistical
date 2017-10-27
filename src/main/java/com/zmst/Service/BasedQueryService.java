package com.zmst.Service;

import java.util.List;

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

	 

	List<LandTax> getLandTax(String year, String place);

	GFCoefficient getGFCoefficient(String year, String place);

	List<Gdp> getGdpTax(String year, String place);

	List<CentralTax> getCentralTax(String year, String place);
 

}
