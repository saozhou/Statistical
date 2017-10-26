package com.zmst.IDao;

import com.zmst.Domain.SubTravelGdp;

public interface SubTravelGdpMapper {
    int deleteByPrimaryKey(Integer stgid);

    int insert(SubTravelGdp record);

    int insertSelective(SubTravelGdp record);

    SubTravelGdp selectByPrimaryKey(Integer stgid);

    int updateByPrimaryKeySelective(SubTravelGdp record);

    int updateByPrimaryKey(SubTravelGdp record);

	void deleteByYearPlace(String year, String place);
}