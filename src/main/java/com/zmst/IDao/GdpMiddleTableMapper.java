package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.GdpMiddleTable;

public interface GdpMiddleTableMapper {
    int deleteByPrimaryKey(Integer gmid);

    int insert(GdpMiddleTable record);

    int insertSelective(GdpMiddleTable record);

    GdpMiddleTable selectByPrimaryKey(Integer gmid);

    int updateByPrimaryKeySelective(GdpMiddleTable record);

    int updateByPrimaryKey(GdpMiddleTable record);

	void deleteByYearPlace(String year, String place);

	void save(GdpMiddleTable gdpMiddle);

	List<GdpMiddleTable> findByYearPlace(String year, String place);
}