package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.LargeTravelTax;

public interface LargeTravelTaxMapper {
    int deleteByPrimaryKey(Integer lttid);

    int insert(LargeTravelTax record);

    int insertSelective(LargeTravelTax record);

    LargeTravelTax selectByPrimaryKey(Integer lttid);

    int updateByPrimaryKeySelective(LargeTravelTax record);

    int updateByPrimaryKey(LargeTravelTax record);

	void deleteByYearPlace(String year, String place);

	void save(LargeTravelTax largeTax);

	List<LargeTravelTax> findByYearPlace(String year, String place);

 
}