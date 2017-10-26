package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.CentralTax;
import com.zmst.Domain.CentralTaxKey;

public interface CentralTaxMapper {
    int deleteByPrimaryKey(CentralTaxKey key);

    int insert(CentralTax record);

    int insertSelective(CentralTax record);

    CentralTax selectByPrimaryKey(CentralTaxKey key);

    int updateByPrimaryKeySelective(CentralTax record);

    int updateByPrimaryKey(CentralTax record);

	List<CentralTax> getAllCentralTax(String year, String place);

	void deleteByYearPlace(String year, String place);

	void save(CentralTax ce);
}