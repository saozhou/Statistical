package com.zmst.IDao;

import com.zmst.Domain.ClassDictionary;

public interface ClassDictionaryMapper {
    int deleteByPrimaryKey(Integer clid);

    int insert(ClassDictionary record);

    int insertSelective(ClassDictionary record);

    ClassDictionary selectByPrimaryKey(Integer clid);

    int updateByPrimaryKeySelective(ClassDictionary record);

    int updateByPrimaryKey(ClassDictionary record);
}