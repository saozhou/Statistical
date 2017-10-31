package com.zmst.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zmst.Domain.SubTax;

/**
 * 
 * @author Zhou
 *税收计算service
 */
public interface TaxCalculateService {

	List<SubTax> getSubTaxt(String year, String place, HttpServletResponse response);
 
}
