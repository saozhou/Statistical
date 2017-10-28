package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.GFReference;

public interface GFReferenceMapper {
    int deleteByPrimaryKey(Integer gftid);

    int insert(GFReference record);

    int insertSelective(GFReference record);

    GFReference selectByPrimaryKey(Integer gftid);

    int updateByPrimaryKeySelective(GFReference record);

    int updateByPrimaryKey(GFReference record);

	List<GFReference> selectByYearAndPlace(String year, String place);

	void deleteByYearPlace(String year, String place);

	void save(GFReference gf);

	List<GFReference> findByYearPlace(String year, String place);

	List<Object> find(String year, String place);
}