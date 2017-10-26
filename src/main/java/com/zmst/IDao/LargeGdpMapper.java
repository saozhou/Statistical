package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.LargeGdp;

public interface LargeGdpMapper {
    int deleteByPrimaryKey(Integer lcid);

    int insert(LargeGdp record);

    int insertSelective(LargeGdp record);

    LargeGdp selectByPrimaryKey(Integer lcid);

    int updateByPrimaryKeySelective(LargeGdp record);

    int updateByPrimaryKey(LargeGdp record);

	void deleteByYearPlace(String year, String place);

	void save(LargeGdp largegdp);

	List<LargeGdp> findByYearPlace(String year, String place);
}