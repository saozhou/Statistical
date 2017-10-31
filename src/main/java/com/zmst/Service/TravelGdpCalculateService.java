package com.zmst.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zmst.Domain.SubTravelGdp;

/**
 * 
 * @author Zhou
 *旅游gdp计算service
 */
public interface TravelGdpCalculateService {

	List<SubTravelGdp> findByYearPlace(String year, String place);

	List<SubTravelGdp> getSubTravelGdp(String year, String place, HttpServletResponse response);

}
