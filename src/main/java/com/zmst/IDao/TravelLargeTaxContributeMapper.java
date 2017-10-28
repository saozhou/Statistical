package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.TravelLargeTaxContribute;

public interface TravelLargeTaxContributeMapper {
    int deleteByPrimaryKey(Integer ttcid);

    int insert(TravelLargeTaxContribute record);

    int insertSelective(TravelLargeTaxContribute record);

    TravelLargeTaxContribute selectByPrimaryKey(Integer ttcid);

    int updateByPrimaryKeySelective(TravelLargeTaxContribute record);

    int updateByPrimaryKey(TravelLargeTaxContribute record);

	void deleteByYearPlace(String year, String place);

	List<TravelLargeTaxContribute> findByYearPlace(String year, String place);

	void save(TravelLargeTaxContribute travelLargeTaxContribute);
}