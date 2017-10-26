package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.SubTax;

public interface SubTaxMapper {
    int deleteByPrimaryKey(Integer gtid);

    int insert(SubTax record);

    int insertSelective(SubTax record);

    SubTax selectByPrimaryKey(Integer gtid);

    int updateByPrimaryKeySelective(SubTax record);

    int updateByPrimaryKey(SubTax record);

	void deleteByYearPlace(String year, String place);

	List<SubTax> findSubTaxByYearPlace(String year, String place);

	void save(SubTax subTax);
}