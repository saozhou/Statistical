package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.TravelLargeGdpContribute;

public interface TravelLargeGdpContributeMapper {
    int deleteByPrimaryKey(Integer conid);

    int insert(TravelLargeGdpContribute record);

    int insertSelective(TravelLargeGdpContribute record);

    TravelLargeGdpContribute selectByPrimaryKey(Integer conid);

    int updateByPrimaryKeySelective(TravelLargeGdpContribute record);

    int updateByPrimaryKey(TravelLargeGdpContribute record);

	void deleteByYearPlace(String year, String place);

	List<TravelLargeGdpContribute> getByYearPlace(String year, String place);

	void save(TravelLargeGdpContribute travelLargeGdpContribute);

	List<Object> find(String year, String place);
}