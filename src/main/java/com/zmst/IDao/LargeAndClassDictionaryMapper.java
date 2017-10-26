package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.LargeAndClassDictionary;

public interface LargeAndClassDictionaryMapper {
    int deleteByPrimaryKey(Integer clid);

    int insert(LargeAndClassDictionary record);

    int insertSelective(LargeAndClassDictionary record);

    LargeAndClassDictionary selectByPrimaryKey(Integer clid);

    int updateByPrimaryKeySelective(LargeAndClassDictionary record);

    int updateByPrimaryKey(LargeAndClassDictionary record);

	List<LargeAndClassDictionary> findAll();
}