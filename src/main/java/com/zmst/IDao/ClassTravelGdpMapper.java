package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.ClassTravelGdp;

public interface ClassTravelGdpMapper {
    int deleteByPrimaryKey(Integer ctgid);

    int insert(ClassTravelGdp record);

    int insertSelective(ClassTravelGdp record);

    ClassTravelGdp selectByPrimaryKey(Integer ctgid);

    int updateByPrimaryKeySelective(ClassTravelGdp record);

    int updateByPrimaryKey(ClassTravelGdp record);

	void deleteByYearPlace(String year, String place);

	void save(ClassTravelGdp classTravelDao);

	List<ClassTravelGdp> findByYearPlace(String year, String place);
}