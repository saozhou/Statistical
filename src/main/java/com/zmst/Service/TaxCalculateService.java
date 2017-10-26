package com.zmst.Service;

import java.util.List;

import com.zmst.Domain.SubTax;

/**
 * 
 * @author Zhou
 *税收计算service
 */
public interface TaxCalculateService {

	List<SubTax> getSubTaxt(String year, String place);

}
