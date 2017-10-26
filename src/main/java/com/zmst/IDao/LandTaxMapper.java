package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.LandTax;

public interface LandTaxMapper {
    int deleteByPrimaryKey(Integer ltid);

    int insert(LandTax record);

    int insertSelective(LandTax record);

    LandTax selectByPrimaryKey(Integer ltid);

    int updateByPrimaryKeySelective(LandTax record);

    int updateByPrimaryKey(LandTax record);

	List<LandTax> getAllLandTax(String year, String place);

	void deleteByYearPlace(String year, String place);

	void save(LandTax la);

	List<LandTax> getCityCentralTax(String year, String place);
}