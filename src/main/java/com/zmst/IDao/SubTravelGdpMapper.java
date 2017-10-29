package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.SubTravelGdp;

public interface SubTravelGdpMapper {
    int deleteByPrimaryKey(Integer stgid);

    int insert(SubTravelGdp record);

    int insertSelective(SubTravelGdp record);

    SubTravelGdp selectByPrimaryKey(Integer stgid);

    int updateByPrimaryKeySelective(SubTravelGdp record);

    int updateByPrimaryKey(SubTravelGdp record);

	void deleteByYearPlace(String year, String place);

	List<SubTravelGdp> findByYearPlace(String year, String place);

	void save(SubTravelGdp subTravelGdp);

	List<Object> find(String year, String place);
}