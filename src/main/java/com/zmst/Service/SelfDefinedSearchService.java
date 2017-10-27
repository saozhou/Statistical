package com.zmst.Service;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author Zhou
 *自定义查询service
 */
public interface SelfDefinedSearchService {

	JSONArray GetDoor(int type, String year, String place);

	JSONArray GetSub(int type, String year, String place);

	JSONArray GetLarge(int type, String year, String place);

}
