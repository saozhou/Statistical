package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.Gdp;

public interface GdpMapper {
    int deleteByPrimaryKey(Integer gdpid);

    int insert(Gdp record);

    int insertSelective(Gdp record);

    Gdp selectByPrimaryKey(Integer gdpid);

    int updateByPrimaryKeySelective(Gdp record);

    int updateByPrimaryKey(Gdp record);

	List<Gdp> getAllGdp(String year, String place);

	void deleteByYearPlace(String year, String place);

	void save(Gdp ce);

	List<Gdp> findByYearPlace(String year, String place);

	List<Object> find(String year, String place);
}