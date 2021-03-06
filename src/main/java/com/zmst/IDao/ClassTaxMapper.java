package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.ClassTax;

public interface ClassTaxMapper {
    int deleteByPrimaryKey(Integer ctid);

    int insert(ClassTax record);

    int insertSelective(ClassTax record);

    ClassTax selectByPrimaryKey(Integer ctid);

    int updateByPrimaryKeySelective(ClassTax record);

    int updateByPrimaryKey(ClassTax record);

	void deleteByYearPlace(String year, String place);

	void save(ClassTax classTax);

	List<ClassTax> findByYearPlace(String year, String place);
}