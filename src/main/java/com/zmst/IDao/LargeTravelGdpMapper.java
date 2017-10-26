package com.zmst.IDao;

import com.zmst.Domain.LargeTravelGdp;

public interface LargeTravelGdpMapper {
    int deleteByPrimaryKey(Integer ltgid);

    int insert(LargeTravelGdp record);

    int insertSelective(LargeTravelGdp record);

    LargeTravelGdp selectByPrimaryKey(Integer ltgid);

    int updateByPrimaryKeySelective(LargeTravelGdp record);

    int updateByPrimaryKey(LargeTravelGdp record);

	void deleteByYearPlace(String year, String place);
}