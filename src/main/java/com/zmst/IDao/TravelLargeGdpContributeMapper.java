package com.zmst.IDao;

import com.zmst.Domain.TravelLargeGdpContribute;

public interface TravelLargeGdpContributeMapper {
    int deleteByPrimaryKey(Integer conid);

    int insert(TravelLargeGdpContribute record);

    int insertSelective(TravelLargeGdpContribute record);

    TravelLargeGdpContribute selectByPrimaryKey(Integer conid);

    int updateByPrimaryKeySelective(TravelLargeGdpContribute record);

    int updateByPrimaryKey(TravelLargeGdpContribute record);

	void deleteByYearPlace(String year, String place);
}