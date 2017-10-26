package com.zmst.Service;

import java.util.List;

import com.zmst.Domain.SubTravelTax;

/**
 * 
 * @author Zhou
 *旅游税收计算service
 */
public interface TravelTaxCalculateService {

	List<SubTravelTax> getSubTravelTaxt(String year, String place);

}
