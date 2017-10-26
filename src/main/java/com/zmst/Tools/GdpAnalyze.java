package com.zmst.Tools;

import java.util.ArrayList;
import java.util.List;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.GdpCaculate;
import com.zmst.Domain.GdpMiddleTable;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeMiddleClass;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;

public class GdpAnalyze {

	public static void sloveLSMGdp(List<Gdp> gdpList, List<GdpMiddleTable> gdpMiddleList, List<SubGdp> subGdpList,
			List<LargeAndClassDictionary> largeAndClass, List<LargeGdp> largeGdpList, String year, String place) {
		// TODO Auto-generated method stub
		for (Gdp gdp : gdpList) {

			if (gdp.getGdpcode().length() == 2) {
				LargeGdp largeGdp = new LargeGdp();
				largeGdp.setLacode(gdp.getGdpcode());
				largeGdp.setLagdp(gdp.getGdp());
				largeGdp.setLaname(gdp.getGdpname());
				largeGdp.setPlace(place);
				largeGdp.setYear(year);
				largeGdpList.add(largeGdp);
			} else if (gdp.getGdpcode().length() == 4) {
				SubGdp subGdp = new SubGdp();
				subGdp.setPlace(place);
				subGdp.setYear(year);
				subGdp.setSmcode(gdp.getGdpcode());
				subGdp.setSmname(gdp.getGdpname());
				subGdp.setSmgdp(gdp.getGdp());
				subGdpList.add(subGdp);
			}
		}

	}

	public static List<Gdp> getMiddleGdp(List<Gdp> gdpList, String year, String place) {
		// TODO Auto-generated method stub

		List<Gdp> middleGdp = new ArrayList<Gdp>();
		for (Gdp gdp : gdpList) {

			if (gdp.getGdpcode().length() > 4) {
				middleGdp.add(gdp);
			}
		}
		return middleGdp;
	}

	public static void sloveMiddleGdp(String year, String place, List<Gdp> middleGdpList, List<LargeGdp> largeGdpList,
			List<LargeTax> largeTaxList) {
		// TODO Auto-generated method stub
		List<GdpCaculate> gdpLast = new ArrayList<GdpCaculate>();
		List<GdpCaculate> largeGdpLast = new ArrayList<GdpCaculate>();
		for (int i = 0; i < middleGdpList.size(); i++) {//
			String[] inCode = middleGdpList.get(i).getGdpcode().split("、");
			double gdp = middleGdpList.get(i).getGdp();
			if (inCode.length == 1) {//找出未分出的gdp集合项

				String[] ouCode = inCode[0].split("-");

				for (int j = 0; j < largeGdpList.size(); j++) {

					String laCode = largeGdpList.get(j).getLacode();
					String firstnumber = laCode.substring(0, 1);
					String secondnumber = laCode.substring(1, 2);
					int firstcode = Integer.valueOf(firstnumber);
					int code;
					if (firstcode == 0) {
						code = Integer.valueOf(secondnumber);
					} else {
						code = Integer.valueOf(laCode);
					}
					int max = Integer.valueOf(ouCode[1]);
					int min = Integer.valueOf(ouCode[0]);
					if (code >= min && code <= max) {
						gdp = gdp - largeGdpList.get(j).getLagdp();
					}

				}
				if (gdp != 0) {
					GdpCaculate gdpCaculate = new GdpCaculate();
					gdpCaculate.setGdp(gdp);
					gdpCaculate.setLacode(middleGdpList.get(i).getGdpcode());
					gdpCaculate.setLaname(middleGdpList.get(i).getGdpname());
					gdpLast.add(gdpCaculate);
				}

			} else if (inCode.length > 1) {

				for (int w = 0; w < inCode.length; w++) {
					String[] ouCode = inCode[w].split("-");

					for (int j = 0; j < largeGdpList.size(); j++) {

						String laCode = largeGdpList.get(j).getLacode();
						String firstnumber = laCode.substring(0, 1);
						String secondnumber = laCode.substring(1, 2);
						int firstcode = Integer.valueOf(firstnumber);
						int code;
						if (firstcode == 0) {
							code = Integer.valueOf(secondnumber);
						} else {
							code = Integer.valueOf(laCode);
						}
						int max = Integer.valueOf(ouCode[1]);
						int min = Integer.valueOf(ouCode[0]);
						if (code >= min && code <= max) {
							gdp = gdp - largeGdpList.get(j).getLagdp();
						}

					}

				}
				if (gdp != 0) {
					GdpCaculate gdpCaculate = new GdpCaculate();
					gdpCaculate.setGdp(gdp);
					gdpCaculate.setLacode(middleGdpList.get(i).getGdpcode());
					gdpCaculate.setLaname(middleGdpList.get(i).getGdpname());
					gdpLast.add(gdpCaculate);
				}
			}

		}
		
		for (int i = 0; i < gdpLast.size(); i++) {//找出集合税收
			String[] inCode = gdpLast.get(i).getLacode().split("、");
			double tax = 0;
			if (inCode.length == 1) {

				String[] ouCode = inCode[0].split("-");

				for (int j = 0; j < largeTaxList.size(); j++) {

					String laCode = largeTaxList.get(j).getLacode();
					String firstnumber = laCode.substring(0, 1);
					String secondnumber = laCode.substring(1, 2);
					int firstcode = Integer.valueOf(firstnumber);
					int code;
					if (firstcode == 0) {
						code = Integer.valueOf(secondnumber);
					} else {
						code = Integer.valueOf(laCode);
					}
					int max = Integer.valueOf(ouCode[1]);
					int min = Integer.valueOf(ouCode[0]);
					if (code >= min && code <= max) {
						tax = tax + largeTaxList.get(j).getLatax();
					}

				}
				 
				gdpLast.get(i).setTax(tax); 
				 

			} else if (inCode.length > 1) {

				for (int w = 0; w < inCode.length; w++) {
					String[] ouCode = inCode[w].split("-");

					for (int j = 0; j < largeTaxList.size(); j++) {

						String laCode = largeTaxList.get(j).getLacode();
						String firstnumber = laCode.substring(0, 1);
						String secondnumber = laCode.substring(1, 2);
						int firstcode = Integer.valueOf(firstnumber);
						int code;
						if (firstcode == 0) {
							code = Integer.valueOf(secondnumber);
						} else {
							code = Integer.valueOf(laCode);
						}
						int max = Integer.valueOf(ouCode[1]);
						int min = Integer.valueOf(ouCode[0]);
						if (code >= min && code <= max) {
							tax = tax + largeTaxList.get(j).getLatax();
						}

					}

				 }
				gdpLast.get(i).setTax(tax); 
				 
				}
			}

		
		
		
		
		for (int j = 0; j < largeTaxList.size(); j++) {//找出未在gdp集合中大类的税收
			
			int log=0;
			for(int w=0;w<largeGdpList.size();w++){
				if(largeTaxList.get(j).getLacode().equals(largeGdpList.get(j).getLacode())){
					log=1;
				}
				
				
			}
			if(log==0){
				GdpCaculate gd =  new GdpCaculate();
				gd.setTax(largeTaxList.get(j).getLatax());
				gd.setLacode(largeTaxList.get(j).getLacode());
				gd.setLaname(largeTaxList.get(j).getLaname());
				largeGdpLast.add(gd);
			}
		
		
		}
		
		
		for(int j=0;j<largeGdpLast.size();j++){//计算大类gdp
			
			
			LargeGdp gdpTag = new LargeGdp();
			String laCode = largeTaxList.get(j).getLacode();
			String firstnumber = laCode.substring(0, 1);
			String secondnumber = laCode.substring(1, 2);
			int firstcode = Integer.valueOf(firstnumber);
			int code;
			if (firstcode == 0) {
				code = Integer.valueOf(secondnumber);
			} else {
				code = Integer.valueOf(laCode);
			}
			
			double gdp = 0;
			double tax = largeGdpLast.get(j).getTax();
			for (int i = 0; i < gdpLast.size(); i++) {
				String[] inCode = gdpLast.get(i).getLacode().split("、");
		 
				if (inCode.length == 1) {

					    String[] ouCode = inCode[0].split("-");
 
						int max = Integer.valueOf(ouCode[1]);
						int min = Integer.valueOf(ouCode[0]);
						if (code >= min && code <= max) {
							gdp =  tax/gdpLast.get(i).getTax()*gdpLast.get(i).getGdp();
							gdpTag.setLagdp(gdp);
							gdpTag.setPlace(place);
							gdpTag.setYear(year);
							gdpTag.setLacode(largeGdpLast.get(j).getLacode());
							gdpTag.setLaname(largeGdpLast.get(j).getLaname());
							largeGdpList.add(gdpTag);
						    
						}


				} else if (inCode.length > 1) {

					for (int w = 0; w < inCode.length; w++) {
						String[] ouCode = inCode[w].split("-");

							int max = Integer.valueOf(ouCode[1]);
							int min = Integer.valueOf(ouCode[0]);
							if (code >= min && code <= max) {
								gdp =  tax/gdpLast.get(i).getTax()*gdpLast.get(i).getGdp();
								gdpTag.setLagdp(gdp);
								gdpTag.setPlace(place);
								gdpTag.setYear(year);
								gdpTag.setLacode(largeGdpLast.get(j).getLacode());
								gdpTag.setLaname(largeGdpLast.get(j).getLaname());
								largeGdpList.add(gdpTag);
							}

						}

					 
					}
				}
			
		}
		
		
	}

	public static void getSubGdp(List<SubGdp> subGdpList, List<SubTax> subTaxList, List<LargeGdp> largeGdpList,
			List<LargeTax> largeTaxList, String year, String place) {
		// TODO Auto-generated method stub
		List<GdpCaculate> gdpCaculateList = new ArrayList<GdpCaculate>();
		for(int i=0;i<largeGdpList.size();i++){
			for(int j=0;j<largeTaxList.size();j++){
				
				if(largeGdpList.get(i).getLacode().equals(largeTaxList.get(j).getLacode())){
					GdpCaculate gdpCaculate =new GdpCaculate();
					gdpCaculate.setGdp(largeGdpList.get(i).getLagdp());
					gdpCaculate.setLacode(largeGdpList.get(i).getLacode());
					gdpCaculate.setLaname(largeGdpList.get(i).getLaname());
					gdpCaculate.setTax(largeTaxList.get(j).getLatax());
					gdpCaculateList.add(gdpCaculate);
				}
				
			}
		}
		
		
		for(int i=0;i<subTaxList.size();i++){
			for(int j=0;j<gdpCaculateList.size();j++){
				
				if(subTaxList.get(i).getLacode().substring(0, 2).equals(gdpCaculateList.get(j).getLacode())){
					SubGdp subGdp = new SubGdp();
					double gdp = (subTaxList.get(i).getSmtax()/gdpCaculateList.get(j).getTax())*gdpCaculateList.get(j).getGdp();
					subGdp.setPlace(place);
					subGdp.setYear(year);
					subGdp.setSmcode(subTaxList.get(i).getSmcode());
					subGdp.setSmname(subTaxList.get(i).getSmname());
					subGdp.setSmgdp(gdp);
					subGdpList.add(subGdp);
				}
				
			}
		}
		
		
	}

	public static void getClassGdp(List<LargeGdp> largeGdpList, List<AllCodeDictionary> classLineList,
			List<LargeAndClassDictionary> classDidctionary, String place, String year, List<ClassGdp> classGdpList) {
		// TODO Auto-generated method stub
		List<LargeMiddleClass>largeMiddleTax = new ArrayList<LargeMiddleClass>();
		for(AllCodeDictionary classCode:classLineList){
			ClassGdp classGdp = new ClassGdp();
			classGdp.setPlace(place);
			classGdp.setYear(year);
			classGdp.setClcode(classCode.getIncode());
			classGdp.setClname(classCode.getInname());
			classGdpList.add(classGdp);
		}
		
		for(LargeAndClassDictionary lcd:classDidctionary){
			LargeMiddleClass lm = new LargeMiddleClass();
			lm.setClasscode(lcd.getClcode());
			lm.setLacode(lcd.getLacode());
			largeMiddleTax.add(lm);
		}
		
		for(int i=0;i<largeMiddleTax.size();i++){
			for(int j=0;j<largeGdpList.size();j++){
				if(largeMiddleTax.get(i).getLacode().equals(largeGdpList.get(j).getLacode())){
					largeMiddleTax.get(i).setLatax(largeGdpList.get(i).getLagdp());
				}else{
					continue;
				}
			}
		}
		
		double tax=0;
		for(int i=0;i<classGdpList.size();i++){
		
			tax=0;
			for(int j=0;j<largeMiddleTax.size();j++){
				if(classGdpList.get(i).getClcode().equals(largeMiddleTax.get(j).getClasscode())){
					tax=tax+largeMiddleTax.get(j).getLatax();
				}
			}
			
			classGdpList.get(i).setClgdp(tax);
		}
	}

	
}
