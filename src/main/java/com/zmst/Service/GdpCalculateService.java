package com.zmst.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zmst.Domain.SubGdp;

/**
 * 
 * @author Zhou
 *gdp计算service
 */
public interface GdpCalculateService {

	List<SubGdp> findSubGdpByYearPlace(String year, String place);

	List<SubGdp> getSubgdp(String year, String place, HttpServletResponse response);

}
