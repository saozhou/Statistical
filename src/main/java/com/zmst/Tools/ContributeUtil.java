package com.zmst.Tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.IndustryDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.TravelClassGdpContribute;
import com.zmst.Domain.TravelClassTaxContribute;
import com.zmst.Domain.TravelIndustryGdpContribute;
import com.zmst.Domain.TravelIndustryTaxContribute;
import com.zmst.Domain.TravelLargeGdpContribute;
import com.zmst.Domain.TravelLargeTaxContribute;

import LCMap.ListChnageMap;

public class ContributeUtil {
/**
 * 
 * @param year
 * @param place
 * @param travelLargeGdpContributeList
 * @param largeTravelGdpList
 * @param largeGdp
 */
	public static void getLargeGdpContribute(String year, String place,
			List<TravelLargeGdpContribute> travelLargeGdpContributeList, List<LargeTravelGdp> largeTravelGdpList,
			List<LargeGdp> largeGdp) {
		// TODO Auto-generated method stub
		double rate=0;
		for(int i=0;i<largeGdp.size();i++){
			rate=0;
			for(int j=0;j<largeTravelGdpList.size();j++){
				if(largeGdp.get(i).getLacode().equals(largeTravelGdpList.get(j).getLacode())){
					
					TravelLargeGdpContribute travelLargeGdpContribute = new TravelLargeGdpContribute();
					travelLargeGdpContribute.setGdp(largeGdp.get(i).getLagdp());
					travelLargeGdpContribute.setTrgdp(largeTravelGdpList.get(j).getLtgdp());
					if(largeGdp.get(j).getLagdp()!=0){
					rate=largeTravelGdpList.get(j).getLtgdp()/largeGdp.get(i).getLagdp();
					}
					
					travelLargeGdpContribute.setRate(rate);
					travelLargeGdpContribute.setYear(year);
					travelLargeGdpContribute.setPlace(place);
					travelLargeGdpContribute.setLaname(largeGdp.get(i).getLaname());
					travelLargeGdpContribute.setLacode(largeGdp.get(i).getLacode());
					
					travelLargeGdpContributeList.add(travelLargeGdpContribute);
				}
				
				
			}
		}
		
	}
/**
 * 
 * @param classGdpList
 * @param classTravelGdpList
 * @param year
 * @param place
 * @param classGdpContributeList
 * 门类gdp贡献值计算
 */
	public static void getClassGdpContribute(List<ClassGdp> classGdpList,
			List<ClassTravelGdp> classTravelGdpList, String year, String place,
			List<TravelClassGdpContribute> classGdpContributeList) {
		// TODO Auto-generated method stub
		double rate = 0;
		for(int i=0;i< classGdpList.size();i++){
		 rate = 0;
			for(int j=0;j<classTravelGdpList.size();j++){
				if(classGdpList.get(i).getClcode().equals(classTravelGdpList.get(j).getClcode())){
					TravelClassGdpContribute travelClassGdpContribute = new TravelClassGdpContribute();
					travelClassGdpContribute.setClcode(classGdpList.get(i).getClcode());
					travelClassGdpContribute.setClname(classGdpList.get(i).getClname());
					travelClassGdpContribute.setGdp(classGdpList.get(i).getClgdp());
					travelClassGdpContribute.setTrgdp(classTravelGdpList.get(j).getCtgdp());
				    if(classGdpList.get(i).getClgdp()!=0){
				    	rate= classTravelGdpList.get(j).getCtgdp()/classGdpList.get(i).getClgdp();
				    }
				    travelClassGdpContribute.setRate(rate);
				    travelClassGdpContribute.setYear(year);
				    travelClassGdpContribute.setPlace(place);
				    classGdpContributeList.add(travelClassGdpContribute);
				}
			}
			
		}
		
	}
	
public static void getIndustryGdpContribute(List<IndustryDictionary> industryLineList,
		List<TravelLargeGdpContribute> largeGdpList, List<TravelClassGdpContribute> classGdpList, List<TravelIndustryGdpContribute> industryGdpContributeList, String year, String place) {
	// TODO Auto-generated method stub
	Map<String,TravelLargeGdpContribute> largeGdpContributeMap = new HashMap<String, TravelLargeGdpContribute>();
	Map<String,TravelClassGdpContribute> classGdpContributeMap = new HashMap<String, TravelClassGdpContribute>();
	   ListChnageMap.GdpContributeChange(largeGdpList,classGdpList,largeGdpContributeMap,classGdpContributeMap);
	double gdp;
	double travelGdp;
	double rate;
	   for(int i=0;i<industryLineList.size();i++){
		   gdp=0;
		   travelGdp=0;
		   rate =0;
		   TravelIndustryGdpContribute travelIndustryGdpContribute = new TravelIndustryGdpContribute();
		   travelIndustryGdpContribute.setIncode(industryLineList.get(i).getIncode());
		   travelIndustryGdpContribute.setInname(industryLineList.get(i).getInname());
		    
		   if(industryLineList.get(i).getIncode().equals("一")){
			   gdp = largeGdpContributeMap.get("01").getGdp()+gdp;
			   gdp = largeGdpContributeMap.get("02").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("03").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("04").getGdp()+gdp;
			   travelGdp  = largeGdpContributeMap.get("01").getTrgdp()+travelGdp;
			   travelGdp = largeGdpContributeMap.get("02").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("03").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("04").getTrgdp()+travelGdp;
			    
		   }else if(industryLineList.get(i).getIncode().equals("二")){
		   
			   gdp = largeGdpContributeMap.get("B").getGdp()+gdp;
			   gdp = largeGdpContributeMap.get("C").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("D").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("E").getGdp()+gdp;
			   travelGdp  = largeGdpContributeMap.get("B").getTrgdp()+travelGdp;
			   travelGdp = largeGdpContributeMap.get("C").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("D").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("E").getTrgdp()+travelGdp;
			  
		   }else if(industryLineList.get(i).getIncode().equals("三")){
			   gdp = largeGdpContributeMap.get("05").getGdp()+gdp;
			   gdp = largeGdpContributeMap.get("11").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("43").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("F").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("G").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("H").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("I").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("J").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("K").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("L").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("E").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("M").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("N").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("O").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("P").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("Q").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("R").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("S").getGdp()+gdp;
			   gdp= largeGdpContributeMap.get("T").getGdp()+gdp;
			   travelGdp  = largeGdpContributeMap.get("05").getTrgdp()+travelGdp;
			   travelGdp = largeGdpContributeMap.get("11").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("43").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("F").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("G").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("H").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("I").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("J").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("K").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("L").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("M").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("N").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("O").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("P").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("Q").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("R").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("S").getTrgdp()+travelGdp;
			   travelGdp= largeGdpContributeMap.get("T").getTrgdp()+travelGdp;
			 
		   }else{
			   gdp = largeGdpContributeMap.get(industryLineList.get(i).getIncode()).getGdp()+gdp;
			    travelGdp = largeGdpContributeMap.get(industryLineList.get(i).getIncode()).getTrgdp()+travelGdp;
		   }
		   
		   if(gdp!=0){
			   rate = travelGdp/gdp;
			   }   
		   travelIndustryGdpContribute.setGdp(gdp);
		   travelIndustryGdpContribute.setRate(rate);
		   travelIndustryGdpContribute.setTrgdp(travelGdp);
		   travelIndustryGdpContribute.setYear(year);
		   travelIndustryGdpContribute.setPlace(place);
		   industryGdpContributeList.add(travelIndustryGdpContribute);
	   }
	   
	  
}

/**
 * 
 * @param year
 * @param place
 * @param travelLargeTaxContributeList
 * @param largeTravelTaxList
 * @param largeTaxList
 * 大类旅游贡献
 */
public static void getLargeTaxContribute(String year, String place,
		List<TravelLargeTaxContribute> travelLargeTaxContributeList, List<LargeTravelTax> largeTravelTaxList,
		List<LargeTax> largeTaxList) {
	// TODO Auto-generated method stub
	
	double rate=0;
	for(int i=0;i<largeTaxList.size();i++){
		rate=0;
		for(int j=0;j<largeTravelTaxList.size();j++){
			if(largeTaxList.get(i).getLacode().equals(largeTravelTaxList.get(j).getLacode())){
				
				TravelLargeTaxContribute travelLargeTaxContribute = new TravelLargeTaxContribute();
				travelLargeTaxContribute.setTax(largeTaxList.get(i).getLatax());
				travelLargeTaxContribute.setTrtax(largeTravelTaxList.get(j).getLttax());
				if(largeTaxList.get(j).getLatax()!=0){
				rate=largeTravelTaxList.get(j).getLttax()/largeTaxList.get(i).getLatax();
				}
				
				travelLargeTaxContribute.setRate(rate);
				travelLargeTaxContribute.setYear(year);
				travelLargeTaxContribute.setPlace(place);
				travelLargeTaxContribute.setLaname(largeTaxList.get(i).getLaname());
				travelLargeTaxContribute.setLacode(largeTaxList.get(i).getLacode());
				
				travelLargeTaxContributeList.add(travelLargeTaxContribute);
			}
			
			
		}
	}
	
}
public static void getClassTaxContribute(List<ClassTax> classTaxList, List<ClassTravelTax> classTravelTaxList,
		String year, String place, List<TravelClassTaxContribute> classTaxContributeList) {
	// TODO Auto-generated method stub
	double rate = 0;
	for(int i=0;i< classTaxList.size();i++){
	 rate = 0;
		for(int j=0;j<classTravelTaxList.size();j++){
			if(classTaxList.get(i).getClcode().equals(classTravelTaxList.get(j).getClcode())){
				TravelClassTaxContribute travelClassTaxContribute = new TravelClassTaxContribute();
				travelClassTaxContribute.setClcode(classTaxList.get(i).getClcode());
				travelClassTaxContribute.setClname(classTaxList.get(i).getClname());
				travelClassTaxContribute.setTax(classTaxList.get(i).getCltax());
				travelClassTaxContribute.setTrtax(classTravelTaxList.get(j).getCttax());
			    if(classTaxList.get(i).getCltax()!=0){
			    	rate= classTravelTaxList.get(j).getCttax()/classTaxList.get(i).getCltax();
			    }
			    travelClassTaxContribute.setRate(rate);
			    travelClassTaxContribute.setYear(year);
			    travelClassTaxContribute.setPlace(place);
			    classTaxContributeList.add(travelClassTaxContribute);
			}
		}
		
	}
}
public static void getIndustryTaxContribute(List<IndustryDictionary> industryLineList,
		List<TravelLargeTaxContribute> largeTaxList, List<TravelClassTaxContribute> classTaxList,
		List<TravelIndustryTaxContribute> industryTaxContributeList, String year, String place) {
	// TODO Auto-generated method stub
	
	Map<String,TravelLargeTaxContribute> largeTaxContributeMap = new HashMap<String, TravelLargeTaxContribute>();
	Map<String,TravelClassTaxContribute> classTaxContributeMap = new HashMap<String, TravelClassTaxContribute>();
	   ListChnageMap.TaxContributeChange(largeTaxList,classTaxList,largeTaxContributeMap,classTaxContributeMap);
	double tax;
	double travelTax;
	double rate;
	   for(int i=0;i<industryLineList.size();i++){
		   tax=0;
		   travelTax=0;
		   rate =0;
		   TravelIndustryTaxContribute travelIndustryTaxContribute = new TravelIndustryTaxContribute();
		   travelIndustryTaxContribute.setIncode(industryLineList.get(i).getIncode());
		   travelIndustryTaxContribute.setInname(industryLineList.get(i).getInname());
		    
		   if(industryLineList.get(i).getIncode().equals("一")){
			   tax = largeTaxContributeMap.get("01").getTax()+tax;
			   tax = largeTaxContributeMap.get("02").getTax()+tax;
			   tax= largeTaxContributeMap.get("03").getTax()+tax;
			   tax= largeTaxContributeMap.get("04").getTax()+tax;
			   travelTax  = largeTaxContributeMap.get("01").getTrtax()+travelTax;
			   travelTax = largeTaxContributeMap.get("02").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("03").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("04").getTrtax()+travelTax;
			    
		   }else if(industryLineList.get(i).getIncode().equals("二")){
		   
			   tax = largeTaxContributeMap.get("B").getTax()+tax;
			   tax = largeTaxContributeMap.get("C").getTax()+tax;
			   tax= largeTaxContributeMap.get("D").getTax()+tax;
			   tax= largeTaxContributeMap.get("E").getTax()+tax;
			   travelTax  = largeTaxContributeMap.get("B").getTrtax()+travelTax;
			   travelTax = largeTaxContributeMap.get("C").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("D").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("E").getTrtax()+travelTax;
			  
		   }else if(industryLineList.get(i).getIncode().equals("三")){
			   tax = largeTaxContributeMap.get("05").getTax()+tax;
			   tax = largeTaxContributeMap.get("11").getTax()+tax;
			   tax= largeTaxContributeMap.get("43").getTax()+tax;
			   tax= largeTaxContributeMap.get("F").getTax()+tax;
			   tax= largeTaxContributeMap.get("G").getTax()+tax;
			   tax= largeTaxContributeMap.get("H").getTax()+tax;
			   tax= largeTaxContributeMap.get("I").getTax()+tax;
			   tax= largeTaxContributeMap.get("J").getTax()+tax;
			   tax= largeTaxContributeMap.get("K").getTax()+tax;
			   tax= largeTaxContributeMap.get("L").getTax()+tax;
			   tax= largeTaxContributeMap.get("E").getTax()+tax;
			   tax= largeTaxContributeMap.get("M").getTax()+tax;
			   tax= largeTaxContributeMap.get("N").getTax()+tax;
			   tax= largeTaxContributeMap.get("O").getTax()+tax;
			   tax= largeTaxContributeMap.get("P").getTax()+tax;
			   tax= largeTaxContributeMap.get("Q").getTax()+tax;
			   tax= largeTaxContributeMap.get("R").getTax()+tax;
			   tax= largeTaxContributeMap.get("S").getTax()+tax;
			   tax= largeTaxContributeMap.get("T").getTax()+tax;
			   travelTax  = largeTaxContributeMap.get("05").getTrtax()+travelTax;
			   travelTax = largeTaxContributeMap.get("11").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("43").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("F").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("G").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("H").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("I").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("J").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("K").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("L").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("M").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("N").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("O").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("P").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("Q").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("R").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("S").getTrtax()+travelTax;
			   travelTax= largeTaxContributeMap.get("T").getTrtax()+travelTax;
			 
		   }else{
			   tax = largeTaxContributeMap.get(industryLineList.get(i).getIncode()).getTax()+tax;
			    travelTax = largeTaxContributeMap.get(industryLineList.get(i).getIncode()).getTax()+travelTax;
		   }
		   
		   if(tax!=0){
			   rate = travelTax/tax;
			   }   
		   travelIndustryTaxContribute.setTax(tax);
		   travelIndustryTaxContribute.setRate(rate);
		   travelIndustryTaxContribute.setTrtax(travelTax);
		   travelIndustryTaxContribute.setYear(year);
		   travelIndustryTaxContribute.setPlace(place);
		   industryTaxContributeList.add(travelIndustryTaxContribute);
	   }
	
}

}
