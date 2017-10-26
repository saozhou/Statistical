package LCMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.TravelClassGdpContribute;
import com.zmst.Domain.TravelClassTaxContribute;
import com.zmst.Domain.TravelLargeGdpContribute;
import com.zmst.Domain.TravelLargeTaxContribute;

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

	public static void GdpContributeChange(List<TravelLargeGdpContribute> largeGdpList,
			List<TravelClassGdpContribute> classGdpList, Map<String, TravelLargeGdpContribute> largeGdpContributeMap,
			Map<String, TravelClassGdpContribute> classGdpContributeMap) {
		// TODO Auto-generated method stub
		
		  int i=0;
	    
	      for(;i<classGdpList.size();i++){  
	    	 
	    	  TravelLargeGdpContribute travelLargeGdpContribute = new TravelLargeGdpContribute();
	    	  travelLargeGdpContribute.setGdp(classGdpList.get(i).getGdp());
	    	  travelLargeGdpContribute.setTrgdp(classGdpList.get(i).getTrgdp());
	    	  travelLargeGdpContribute.setRate(classGdpList.get(i).getRate());
	    	  travelLargeGdpContribute.setLacode(classGdpList.get(i).getClcode());
	    	  travelLargeGdpContribute.setLaname(classGdpList.get(i).getClname());
	    	  largeGdpList.add(travelLargeGdpContribute);
	      }  
	      
	      for(i=0;i<largeGdpList.size();i++){  
		    	 
	    	  largeGdpContributeMap.put(largeGdpList.get(i).getLacode(), largeGdpList.get(i));  
	      }  
		
	}

	public static void TaxContributeChange(List<TravelLargeTaxContribute> largeTaxList,
			List<TravelClassTaxContribute> classTaxList, Map<String, TravelLargeTaxContribute> largeTaxContributeMap,
			Map<String, TravelClassTaxContribute> classTaxContributeMap) {
		// TODO Auto-generated method stub
		
		  int i=0;
		    
	      for(;i<classTaxList.size();i++){  
	    	 
	    	  TravelLargeTaxContribute travelTaxContribute = new TravelLargeTaxContribute();
	    	  travelTaxContribute.setTax(classTaxList.get(i).getTax());
	    	  travelTaxContribute.setTrtax(classTaxList.get(i).getTrtax());
	    	  travelTaxContribute.setRate(classTaxList.get(i).getRate());
	    	  travelTaxContribute.setLacode(classTaxList.get(i).getClcode());
	    	  travelTaxContribute.setLaname(classTaxList.get(i).getClname());
	    	  largeTaxList.add(travelTaxContribute);
	      }  
	      
	      for(i=0;i<largeTaxList.size();i++){  
		    	 
	    	  largeTaxContributeMap.put(largeTaxList.get(i).getLacode(), largeTaxList.get(i));  
	      }  
	}  
}
