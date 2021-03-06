package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.IndustryDictionary;

public interface IndustryDictionaryMapper {
    int deleteByPrimaryKey(Integer inid);

    int insert(IndustryDictionary record);

    int insertSelective(IndustryDictionary record);

    IndustryDictionary selectByPrimaryKey(Integer inid);

    int updateByPrimaryKeySelective(IndustryDictionary record);

    int updateByPrimaryKey(IndustryDictionary record);

	List<IndustryDictionary> findAll();
}