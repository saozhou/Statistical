package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.GFCoefficient;

public interface GFCoefficientMapper {
    int deleteByPrimaryKey(Integer gfid);

    int insert(GFCoefficient record);

    int insertSelective(GFCoefficient record);

    GFCoefficient selectByPrimaryKey(Integer gfid);

    int updateByPrimaryKeySelective(GFCoefficient record);

    int updateByPrimaryKey(GFCoefficient record);

	GFCoefficient selectByYearPlace(String year, String place);

	void deleteByYearPlace(String year, String place);

	void save(GFCoefficient corfficient);

	GFCoefficient findCorfficient(String year, String place);
}