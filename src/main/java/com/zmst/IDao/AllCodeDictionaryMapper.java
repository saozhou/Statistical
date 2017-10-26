package com.zmst.IDao;

import java.util.List;

import com.zmst.Domain.AllCodeDictionary;

public interface AllCodeDictionaryMapper {
    int deleteByPrimaryKey(Integer inid);

    int insert(AllCodeDictionary record);

    int insertSelective(AllCodeDictionary record);

    AllCodeDictionary selectByPrimaryKey(Integer inid);

    int updateByPrimaryKeySelective(AllCodeDictionary record);

    int updateByPrimaryKey(AllCodeDictionary record);

	List<AllCodeDictionary> selectAll();

	void deleteAll();

	void save(AllCodeDictionary code);

	List<AllCodeDictionary> getLargeLine();

	List<AllCodeDictionary> getClassLine();
}