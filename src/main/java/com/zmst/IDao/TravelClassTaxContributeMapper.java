package com.zmst.IDao;

import com.zmst.Domain.TravelClassTaxContribute;

public interface TravelClassTaxContributeMapper {
    int deleteByPrimaryKey(Integer cttid);

    int insert(TravelClassTaxContribute record);

    int insertSelective(TravelClassTaxContribute record);

    TravelClassTaxContribute selectByPrimaryKey(Integer cttid);

    int updateByPrimaryKeySelective(TravelClassTaxContribute record);

    int updateByPrimaryKey(TravelClassTaxContribute record);

	void deleteByYearPlace(String year, String place);

 
}