package com.zmst.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeMiddleClass;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;

import LCMap.ListChnageMap;

public class TravelGdpCaculate {

	public static void getSubTravelGdp(List<SubGdp> subGdpList, String year, String place,
			List<SubTravelGdp> subTravelGdpList, List<GFReference> gfReference, GFCoefficient coefficient) {
		// TODO Auto-generated method stub
		double gstandard = coefficient.getGsta();
		  double fstandard = coefficient.getFsta();
		  double travelGdpdata;
		  
		  Map<String,String>gfReferenceMap = ListChnageMap.gfReferenceChange(gfReference);
		  
			for(SubGdp subGdp:subGdpList){
				SubTravelGdp subTravelGdp = new SubTravelGdp();
				travelGdpdata=0;
				 String subCorfficient =null;
				 subCorfficient=gfReferenceMap.get(subGdp.getSmcode());
				 					         
				            if(subCorfficient==null){
			    	           travelGdpdata=0;
			                  }else if(subCorfficient.equals("t")){
									 
									travelGdpdata = subGdp.getSmgdp();
								    
								}
								
								else if(subCorfficient.equals("g")){
									travelGdpdata = subGdp.getSmgdp()*gstandard;
									 
								    
								}
								
								else if(subCorfficient.equals("f")){
									travelGdpdata =  subGdp.getSmgdp()*fstandard;
								   
								}
						 
					 
								subTravelGdp.setSmcode(subGdp.getSmcode()); 
								subTravelGdp.setSmname(subGdp.getSmname()); 
								subTravelGdp.setPlace(place);
								subTravelGdp.setYear(year);
								subTravelGdp.setStgdp(travelGdpdata);
								subTravelGdpList.add(subTravelGdp);
			}

	}

	public static void getLargeTravelGdp(List<AllCodeDictionary> largeLineList, String place, String year,
			List<LargeTravelGdp> largeTravelGdpList, List<SubTravelGdp> subTravelGdpList) {
		// TODO Auto-generated method stub
		for(AllCodeDictionary code:largeLineList){
			LargeTravelGdp largeTravelGdp = new LargeTravelGdp();
			largeTravelGdp.setLacode(code.getIncode());
			largeTravelGdp.setLaname(code.getInname());
			largeTravelGdp.setPlace(place);
			largeTravelGdp.setYear(year);
			largeTravelGdpList.add(largeTravelGdp);
		}
		
		for(int i=0;i<largeTravelGdpList.size();i++){
			double gdp=0;
			for(int j=0;j<subTravelGdpList.size();j++){
				if(largeTravelGdpList.get(i).getLacode().equals(subTravelGdpList.get(j).getSmcode().substring(0,2)))
				{
					gdp = gdp + subTravelGdpList.get(j).getStgdp();
				}
			}
			largeTravelGdpList.get(i).setLtgdp(gdp);
		}
	}
	
	

	public static void getClassTravelGdp(List<LargeTravelGdp> largeTravelGdpList, List<AllCodeDictionary> classLineList,
			List<LargeAndClassDictionary> classDidctionary, String place, String year,
			List<ClassTravelGdp> classTravelGdpList) {
		// TODO Auto-generated method stub
		List<LargeMiddleClass>largeMiddleGdp = new ArrayList<LargeMiddleClass>();
		for(AllCodeDictionary classCode:classLineList){
			ClassTravelGdp classTravelGdp = new ClassTravelGdp();
			classTravelGdp.setPlace(place);
			classTravelGdp.setYear(year);
			classTravelGdp.setClcode(classCode.getIncode());
			classTravelGdp.setClname(classCode.getInname());
			classTravelGdpList.add(classTravelGdp);
		}
		
		for(LargeAndClassDictionary lcd:classDidctionary){
			LargeMiddleClass lm = new LargeMiddleClass();
			lm.setClasscode(lcd.getClcode());
			lm.setLacode(lcd.getLacode());
			largeMiddleGdp.add(lm);
		}
		
		for(int i=0;i<largeMiddleGdp.size();i++){
			for(int j=0;j<largeTravelGdpList.size();j++){
				if(largeMiddleGdp.get(i).getLacode().equals(largeTravelGdpList.get(j).getLacode())){
					largeMiddleGdp.get(i).setLagdp(largeTravelGdpList.get(j).getLtgdp());
				}else{
					continue;
				}
			}
		}
		
		double gdp=0;
		for(int i=0;i<classTravelGdpList.size();i++){
		
			gdp=0;
			for(int j=0;j<largeMiddleGdp.size();j++){
				if(classTravelGdpList.get(i).getClcode().equals(largeMiddleGdp.get(j).getClasscode())){
					if(largeMiddleGdp.get(j).getLagdp()!=null)
					gdp=gdp+largeMiddleGdp.get(j).getLagdp();
					 
				}
			}
			System.out.println(gdp);
			classTravelGdpList.get(i).setCtgdp(gdp);
		}
	}

}
