package com.zmst.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.LandTax;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeMiddleClass;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelTax;
 

import LCMap.ListChnageMap;

public class TaxCaculateUtil {
/**
 * 
 * @param subTaxList
 * @param year
 * @param place
 * @param landTaxList
 * @param centralTaxList
 * 计算得小类税收
 */
	public static void getSubTax(List<SubTax> subTaxList, String year, String place, List<LandTax> landTaxList,
			List<CentralTax> centralTaxList) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<landTaxList.size();i++){
			SubTax subTax = new SubTax();
			subTax.setLacode(landTaxList.get(i).getLacode());
            subTax.setSmcode(landTaxList.get(i).getSmcode());
            subTax.setSmname(landTaxList.get(i).getSmname());
            subTax.setPlace(place);
            subTax.setYear(year);
            subTax.setSmtax(landTaxList.get(i).getLatax());
          
		    subTaxList.add(subTax);
		} 
			
		int log = 0;
		double tax;
		for(int i=0;i<centralTaxList.size();i++){
				  log=0;
				  SubTax subTax = new SubTax();
				  tax=0;
			for(int j=0;j<subTaxList.size();j++){
				
				if(centralTaxList.get(i).getSmcode().equals(subTaxList.get(j).getSmcode())){
				 
					 tax= subTaxList.get(i).getSmtax()+centralTaxList.get(j).getCntax();
					 subTaxList.get(i).setSmtax(tax);
				
					log=1;
				}
				
				
			}
			
			if(log==0){
				  
				tax= centralTaxList.get(i).getCntax();
				subTax.setLacode(centralTaxList.get(i).getLacode());
				subTax.setPlace(place);
				subTax.setYear(year);
				subTax.setSmname(subTaxList.get(i).getSmname());
				subTax.setSmcode(centralTaxList.get(i).getSmcode());
				subTax.setSmtax(tax);
				subTaxList.add(subTax);
				
			}
			
		}
		
	}
/**
 * 
 * @param largeLineList
 * @param place
 * @param year
 * @param largeTaxList
 * @param subTaxList
 * 计算得大类税收
 */
	public static void getLargeTax(List<AllCodeDictionary> largeLineList, String place, String year,
			List<LargeTax> largeTaxList, List<SubTax> subTaxList) {
		// TODO Auto-generated method stub
		
		for(AllCodeDictionary code:largeLineList){
			LargeTax largeTax = new LargeTax();
			largeTax.setLacode(code.getIncode());
			largeTax.setLaname(code.getInname());
			largeTax.setPlace(place);
			largeTax.setYear(year);
	   
			largeTaxList.add(largeTax);
		}
		double tax=0;
		 
		
		for(int i=0;i<largeTaxList.size();i++){
			System.out.println(largeTaxList.get(i).getLacode());
		    tax=0;
			for(int j=0;j<subTaxList.size();j++){
				 
				if(largeTaxList.get(i).getLacode().equals(subTaxList.get(j).getLacode()))
			{
				tax = tax + subTaxList.get(j).getSmtax();
				 
			} 
			}
			 
			largeTaxList.get(i).setLatax(tax);
		}
		
	}

	
	/**
	 * 
	 * @param largeTaxList
	 * @param classLineList
	 * @param classDidctionary
	 * @param place
	 * @param year
	 * @param classTaxList
	 * 计算得门类税收
	 */
	public static void getClassTax(List<LargeTax> largeTaxList, List<AllCodeDictionary> classLineList, List<LargeAndClassDictionary> classDidctionary,
			String place, String year, List<ClassTax> classTaxList) {
		// TODO Auto-generated method stub
		List<LargeMiddleClass>largeMiddleTax = new ArrayList<LargeMiddleClass>();
		for(AllCodeDictionary classCode:classLineList){
			ClassTax classTax = new ClassTax();
			classTax.setPlace(place);
			classTax.setYear(year);
			classTax.setClcode(classCode.getIncode());
			classTax.setClname(classCode.getInname());
			classTaxList.add(classTax);
		}
		
		for(LargeAndClassDictionary lcd:classDidctionary){
			LargeMiddleClass lm = new LargeMiddleClass();
			lm.setClasscode(lcd.getClcode());
			lm.setLacode(lcd.getLacode());
			largeMiddleTax.add(lm);
		}
		
		for(int i=0;i<largeMiddleTax.size();i++){
			for(int j=0;j<largeTaxList.size();j++){
				if(largeMiddleTax.get(i).getLacode().equals(largeTaxList.get(j).getLacode())){
					largeMiddleTax.get(i).setLatax(largeTaxList.get(i).getLatax());
				}else{
					continue;
				}
			}
		}
		
		double tax=0;
		for(int i=0;i<classTaxList.size();i++){
		
			tax=0;
			for(int j=0;j<largeMiddleTax.size();j++){
				if(classTaxList.get(i).getClcode().equals(largeMiddleTax.get(j).getClasscode())){
					tax=tax+largeMiddleTax.get(j).getLatax();
				}
			}
			
			classTaxList.get(i).setCltax(tax);
		}
		
	}

	/**
	 * 
	 * @param subTaxList
	 * @param year
	 * @param place
	 * @param subTravelTaxList
	 * @param gfReference
	 * @param coefficient
	 * 计算的小类旅游税收
	 */
	public static void getSubTravelTax(List<SubTax> subTaxList, String year, String place,
			List<SubTravelTax> subTravelTaxList, List<GFReference> gfReference, GFCoefficient coefficient) {
		// TODO Auto-generated method stub
		 double gstandard = coefficient.getGsta();
		  double fstandard = coefficient.getFsta();
		  double traveltaxdata;
		  
		  Map<String,String>gfReferenceMap = ListChnageMap.gfReferenceChange(gfReference);
		  
			for(SubTax subTax:subTaxList){
				SubTravelTax subTravelTax = new SubTravelTax();
				traveltaxdata=0;
				 String subCorfficient =null;
				 subCorfficient= gfReferenceMap.get(subTax.getSmcode());
				 					
						       if(subCorfficient==null){
						    	   System.out.println(11);
						    	  System.out.println(subTax.getSmcode());
						    	  traveltaxdata=0;
						       }else if(subCorfficient.equals("t")){
									 
						      		traveltaxdata = subTax.getSmtax();
								    
								}
								
								else if(subCorfficient.equals("g")){
									traveltaxdata = subTax.getSmtax()*gstandard;
									 
								    
								}
								
								else if(subCorfficient.equals("f")){
									traveltaxdata =  subTax.getSmtax()*fstandard;
								   
								}
						 
					 
			subTravelTax.setLacode(subTax.getLacode()); 
			subTravelTax.setSmcode(subTax.getSmcode()); 
			subTravelTax.setSmname(subTax.getSmname()); 
			subTravelTax.setPlace(place);
			subTravelTax.setYear(year);
			subTravelTax.setSttax(traveltaxdata);
			subTravelTaxList.add(subTravelTax);
			}

		 
	}

	public static void getLargeTravelTax(List<AllCodeDictionary> largeLineList, String place, String year,
			List<LargeTravelTax> largeTravelTaxList, List<SubTravelTax> subTravelTaxList) {
		// TODO Auto-generated method stub
		for(AllCodeDictionary code:largeLineList){
			LargeTravelTax largeTravelTax = new LargeTravelTax();
			largeTravelTax.setLacode(code.getIncode());
			largeTravelTax.setLaname(code.getInname());
			largeTravelTax.setPlace(place);
			largeTravelTax.setYear(year);
			largeTravelTaxList.add(largeTravelTax);
		}
		double tax=0;
		for(int i=0;i<largeTravelTaxList.size();i++){
		 tax=0;
			for(int j=0;j<subTravelTaxList.size();j++){
				
				if(largeTravelTaxList.get(i).getLacode().equals(subTravelTaxList.get(j).getLacode()))
				{
					tax = tax + subTravelTaxList.get(j).getSttax();
				}
			}
			
			largeTravelTaxList.get(i).setLttax(tax);
		}
	}

	public static void getClassTravelTax(List<LargeTravelTax> largeTravelTaxList, List<AllCodeDictionary> classLineList,
			List<LargeAndClassDictionary> classDidctionary, String place, String year,
			List<ClassTravelTax> classTravelTaxList) {
		// TODO Auto-generated method stub
		List<LargeMiddleClass>largeMiddleTax = new ArrayList<LargeMiddleClass>();
		for(AllCodeDictionary classCode:classLineList){
			ClassTravelTax classTravelTax = new ClassTravelTax();
			classTravelTax.setPlace(place);
			classTravelTax.setYear(year);
			classTravelTax.setClcode(classCode.getIncode());
			classTravelTax.setClname(classCode.getInname());
			classTravelTaxList.add(classTravelTax);
		}
		
		for(LargeAndClassDictionary lcd:classDidctionary){
			LargeMiddleClass lm = new LargeMiddleClass();
			lm.setClasscode(lcd.getClcode());
			lm.setLacode(lcd.getLacode());
			largeMiddleTax.add(lm);
		}
		
		for(int i=0;i<largeMiddleTax.size();i++){
			for(int j=0;j<largeTravelTaxList.size();j++){
				if(largeMiddleTax.get(i).getLacode().equals(largeTravelTaxList.get(j).getLacode())){
					largeMiddleTax.get(i).setLatax(largeTravelTaxList.get(j).getLttax());
					System.out.println(largeTravelTaxList.get(j).getLttax());
				}else{
					continue;
				}
			}
		}
		
		double tax=0;
		for(int i=0;i<classTravelTaxList.size();i++){
		
			tax=0;
			for(int j=0;j<largeMiddleTax.size();j++){
				if(classTravelTaxList.get(i).getClcode().equals(largeMiddleTax.get(j).getClasscode())){
				 if(largeMiddleTax.get(j).getLatax()!=null)
					tax=tax+largeMiddleTax.get(j).getLatax();
				}
			}
			
			classTravelTaxList.get(i).setCttax(tax);
		}
		
	}

 

}
