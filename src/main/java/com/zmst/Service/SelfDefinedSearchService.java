package com.zmst.Service;

import java.util.List;

/**
 * 
 * @author Zhou
 *自定义查询service
 */
public interface SelfDefinedSearchService {

	List<Object> GetDoor(int type, String year, String place);

	List<Object> GetSub(int type, String year, String place);

	List<Object> GetLarge(int type, String year, String place);

}
