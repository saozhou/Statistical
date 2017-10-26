package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.SubTravelTax;

public interface SubTravelTaxMapper {
    int deleteByPrimaryKey(Integer sttid);

    int insert(SubTravelTax record);

    int insertSelective(SubTravelTax record);

    SubTravelTax selectByPrimaryKey(Integer sttid);

    int updateByPrimaryKeySelective(SubTravelTax record);

    int updateByPrimaryKey(SubTravelTax record);

	void deleteByYearPlace(String year, String place);

	List<SubTravelTax> findSubTravelTaxByYearPlace(String year, String place);

	void save(SubTravelTax subTax);
}