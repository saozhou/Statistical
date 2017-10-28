package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.LargeTax;

public interface LargeTaxMapper {
    int deleteByPrimaryKey(Integer ltid);

    int insert(LargeTax record);

    int insertSelective(LargeTax record);

    LargeTax selectByPrimaryKey(Integer ltid);

    int updateByPrimaryKeySelective(LargeTax record);

    int updateByPrimaryKey(LargeTax record);

	void deleteByYearPlace(String year, String place);

	void save(LargeTax largeTax);

	List<LargeTax> getByYearPlace(String year, String place);
}