package com.zmst.IDao;

import com.zmst.Domain.TravelIndustryGdpContribute;

public interface TravelIndustryGdpContributeMapper {
    int deleteByPrimaryKey(Integer inid);

    int insert(TravelIndustryGdpContribute record);

    int insertSelective(TravelIndustryGdpContribute record);

    TravelIndustryGdpContribute selectByPrimaryKey(Integer inid);

    int updateByPrimaryKeySelective(TravelIndustryGdpContribute record);

    int updateByPrimaryKey(TravelIndustryGdpContribute record);

	void deleteByYearPlace(String year, String place);
}