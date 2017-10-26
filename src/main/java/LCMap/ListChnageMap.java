package LCMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.GFReference;

public class ListChnageMap {
	public static Map<String, String> codeDictionaryChange(List<AllCodeDictionary> list){  
	    
	    Map<String,Object> params = new HashMap<String, Object>();  
	      Map<String,String> map=new HashMap<String, String>();  
	      for(AllCodeDictionary  dic:list){  
	        if(dic==null){  
	            continue;  
	        }  
	        map.put(dic.getIncode(), dic.getInname());  
	      }  
	      return map;  
	  }

	public static Map<String, String> codeDictionaryChangeFirst(List<AllCodeDictionary> list) {
		// TODO Auto-generated method stub
		 Map<String,Object> params = new HashMap<String, Object>();  
		 
		 
	      Map<String,String> map=new HashMap<String, String>();  
	      for(AllCodeDictionary  dic:list){  
	        if(dic==null){  
	            continue;  
	        }  
	        map.put(dic.getIncode(), dic.getInname());  
	      }  
	      return map;  
	}

	public static Map<String, String> codeDictionaryChangeSecond(List<AllCodeDictionary> list) {
		// TODO Auto-generated method stub
		 int i=0;
	      Map<String,String> map=new HashMap<String, String>();  
	      for(;i<list.size();i++){  
	    	 
	        map.put(list.get(i).getInname(), list.get(i).getIncode());  
	      }  
	      
	      return map;  
	}

	public static  Map<String, String> gfReferenceChange(List<GFReference> list) {
		// TODO Auto-generated method stub
		  int i=0;
	      Map<String,String> map=new HashMap<String, String>();  
	      for(;i<list.size();i++){  
	    	 
	        map.put(list.get(i).getIncode(), list.get(i).getIncoefficient());  
	      }  
	      
	      return map;  
		 
	}  
}
