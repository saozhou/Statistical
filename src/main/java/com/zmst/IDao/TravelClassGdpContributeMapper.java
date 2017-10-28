package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.TravelClassGdpContribute;

public interface TravelClassGdpContributeMapper {
    int deleteByPrimaryKey(Integer tccid);

    int insert(TravelClassGdpContribute record);

    int insertSelective(TravelClassGdpContribute record);

    TravelClassGdpContribute selectByPrimaryKey(Integer tccid);

    int updateByPrimaryKeySelective(TravelClassGdpContribute record);

    int updateByPrimaryKey(TravelClassGdpContribute record);

	void deleteByYearPlace(String year, String place);

	List<TravelClassGdpContribute> findByYearPlace(String year, String place);

	void save(TravelClassGdpContribute classGdpContribute);

	List<Object> find(String year, String place);
}