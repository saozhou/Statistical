package ListChnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;

import LCMap.ListChnageMap;
 

public class ListChangeUtil {

	public static List<AllCodeDictionary> ChangeCodeList(List<List<String>> list) {
		// TODO Auto-generated method stub
	 
		 List<AllCodeDictionary> codeDictionaryList = new ArrayList<AllCodeDictionary>();
			for(int i=1;i<list.size();i++){
				List<String>lists=list.get(i);
				AllCodeDictionary codeDictionary = new  AllCodeDictionary();
				 String industryCode = lists.get(0);
				 String industryName = lists.get(1);
				 codeDictionary.setIncode(industryCode);
				 codeDictionary.setInname(industryName);
				 codeDictionaryList.add(codeDictionary);
			}
			return codeDictionaryList;
		 
	}

	public static List<GFReference> changeGFReference(List<List<String>> list, String year, String place) {
		// TODO Auto-generated method stub
		 List<GFReference> gfReferenceList = new ArrayList<GFReference>();
			for(int i=1;i<list.size();i++){
				List<String>lists=list.get(i);
				GFReference gfreference = new  GFReference();
				 String industryCode = lists.get(0);
				 String industryName = lists.get(1);
				 if(lists.size()==3){
					 
				 gfreference.setIncoefficient(lists.get(2));
				
				 }else{
					 gfreference.setIncoefficient("");
				 }
				 gfreference.setIncode(industryCode);
				 gfreference.setInname(industryName);
				  
				 gfreference.setYear(year);
				 gfreference.setPlace(place);
				 gfReferenceList.add(gfreference);
			}
		 
		return gfReferenceList;
	}

	/**
	 * 
	 * @param list
	 * @param year
	 * @param place
	 * @param matchingWay
	 * @return
	 * 将地税stringlist转对象list
	 */
	public static List<LandTax> changeLandTax(List<List<String>> list, String year, String place, int matchingWay) {
		// TODO Auto-generated method stub
		 List<LandTax> landTaxList = new ArrayList<LandTax>();
			for(int i=1;i<list.size();i++){
				List<String>lists=list.get(i);
				LandTax landTax = new  LandTax();
				 if(matchingWay==1){
					 landTax.setSmcode(lists.get(0));
				 }else{
					 landTax.setSmname(lists.get(0));
				 }
				 
					 
				landTax.setLatax(Double.valueOf(lists.get(1)) );  
			 
				 landTax.setYear(year);
				 landTax.setPlace(place);
				 landTaxList.add(landTax);
			}
		 
		return landTaxList;
	}
/**
 * 
 * @param landTaxList
 * @param codeDictionaryList
 * @param matchingWay
 * 地税表选择性匹配
 */
	public static void matchingLandTax(List<LandTax> landTaxList, List<AllCodeDictionary> codeDictionaryList,
			int matchingWay) {
		// TODO Auto-generated method stub
	 
		Map<String,String>para =null;
		if(matchingWay==1){
			para = ListChnageMap.codeDictionaryChangeFirst(codeDictionaryList);
		}else{
			para = ListChnageMap.codeDictionaryChangeSecond(codeDictionaryList);
		}
		int j=0;

         
	 
		 for(int i = 0;i<landTaxList.size();i++){
			 			 
		 
				 if(matchingWay==1){
						 landTaxList.get(i).setSmname(para.get(landTaxList.get(i).getSmcode()));
						 landTaxList.get(i).setLacode(landTaxList.get(i).getSmcode().substring(0,2));
				 }else{
					 
						 landTaxList.get(i).setSmcode(para.get(landTaxList.get(i).getSmname()));
						 landTaxList.get(i).setLacode(para.get(landTaxList.get(i).getSmname()).substring(0,2));
						 
					 
				 }
				 		 
					 
	    	
	   
	}

  }

public static List<CentralTax> changeCentralTax(List<List<String>> list, String year, String place, int matchingWay) {
	// TODO Auto-generated method stub
	 List<CentralTax> CentralTaxList = new ArrayList<CentralTax>();
		for(int i=1;i<list.size();i++){
			List<String>lists=list.get(i);
			CentralTax centralTax = new  CentralTax();
			 if(matchingWay==1){
				 centralTax.setSmcode(lists.get(0));
			 }else{
				 centralTax.setSmname(lists.get(0));
			 }
			 
				 
			 centralTax.setCntax(Double.valueOf(lists.get(1)) );  
			 centralTax.setYear(year);
			 centralTax.setPlace(place);
			 CentralTaxList.add(centralTax);
		}
		return CentralTaxList;
	 
	 
    }

public static void matchingCentralTax(List<CentralTax> list, List<AllCodeDictionary> codeDictionaryList,
		int matchingWay) {
  
	    Map<String,String>para =null;
	    if(matchingWay==1){
		      para = ListChnageMap.codeDictionaryChangeFirst(codeDictionaryList);
	    }else{
		      para = ListChnageMap.codeDictionaryChangeSecond(codeDictionaryList);
	    }
	 

     
 
	      for(int i = 0;i<list.size();i++){
		 			 
			 if(matchingWay==1){
				 list.get(i).setSmname(para.get(list.get(i).getSmcode()));
				 list.get(i).setLacode(list.get(i).getSmcode().substring(0,2));
			 }else{
				 
				 list.get(i).setSmcode(para.get(list.get(i).getSmname()));
				 list.get(i).setLacode(para.get(list.get(i).getSmname()).substring(0,2));
					 
				 
			 }
			 		 
				 
    	
   
      }
   	
  }

public static List<Gdp> changeGdp(List<List<String>> list, String year, String place) {
	// TODO Auto-generated method stub
	 List<Gdp> gdpList = new ArrayList<Gdp>();
		for(int i=1;i<list.size();i++){
			List<String>lists=list.get(i);
			Gdp gdp = new  Gdp();
			gdp.setGdpname(lists.get(0));
			gdp.setGdpcode(lists.get(1));
			gdp.setGdp(Double.valueOf(lists.get(2)));
			gdp.setYear(year);
			gdp.setPlace(place);
			gdpList.add(gdp);
			System.out.println(lists.get(1));
		}
		 
	 
	
	return gdpList;
}

	
}

