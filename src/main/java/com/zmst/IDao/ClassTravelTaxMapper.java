package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.ClassTravelTax;

public interface ClassTravelTaxMapper {
    int deleteByPrimaryKey(Integer cttid);

    int insert(ClassTravelTax record);

    int insertSelective(ClassTravelTax record);

    ClassTravelTax selectByPrimaryKey(Integer cttid);

    int updateByPrimaryKeySelective(ClassTravelTax record);

    int updateByPrimaryKey(ClassTravelTax record);

	void deleteByYearPlace(String year, String place);

	void save(ClassTravelTax classTax);

	List<ClassTravelTax> findByYearPlace(String year, String place);
}