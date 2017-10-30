package com.zmst.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassGdp;
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
		List<Gdp> secondGdp = new ArrayList<Gdp>();

		for (Gdp gdp : gdpList) {

			if (gdp.getGdpcode().length() > 4) {
				middleGdp.add(gdp);
				secondGdp.add(gdp);
			}
		}

		int tlog=0;
		int max = 0;
		int min = 0;
		int tmax = 0;
		int tmin = 0;
		int ary[] = new int[100];
		for (int i = 0; i < secondGdp.size(); i++) {
			tlog=0;
			String codeName = null;
			Arrays.fill(ary, 0);
			String[] inCode = secondGdp.get(i).getGdpcode().split("、");
			double gdp = 0;
			int log = 0;
			if (inCode.length == 1) {
				String[] ouCode = inCode[0].split("-");
				max = Integer.valueOf(ouCode[1]);
				min = Integer.valueOf(ouCode[0]);
				for (int t = min; t <= max; t++) {
					ary[t] = 1;
				}

			} else if (inCode.length > 1) {

				for (int w = 0; w < inCode.length; w++) {
					String[] ouCode = inCode[0].split("-");
					max = Integer.valueOf(ouCode[1]);
					min = Integer.valueOf(ouCode[0]);
					for (int t = min; t <= max; i++) {
						ary[t] = 1;
					}
				}

			}
			for (int j = i + 1; j < middleGdp.size(); j++) {

				tmax = 0;
				tmin = 0;

				String[] middleCode = middleGdp.get(j).getGdpcode().split("、");

				gdp = 0;

				if (middleCode.length == 1) {
					String[] tou = middleCode[0].split("-");
					tmax = Integer.valueOf(tou[1]);
					tmin = Integer.valueOf(tou[0]);
					if (tmin >= min && tmax <= max) {
						log = 1;
						for (int e = tmin; e <= tmax; e++) {
							ary[e] = 0;
						}
						System.out.println(tmin + tmax);
						gdp = secondGdp.get(i).getGdp() - middleGdp.get(j).getGdp();
					}

				} else if (middleCode.length > 1) {

					for (int e = 0; e < middleCode.length; e++) {
						String[] tou = middleCode[e].split("-");
						tmax = Integer.valueOf(tou[1]);
						tmin = Integer.valueOf(tou[0]);
						if (tmin >= min && tmax <= max) {
							log = 1;
							for (int q = tmin; q <= tmax; q++) {
								ary[q] = 0;
							}
						}
					}
					gdp = secondGdp.get(i).getGdp() - middleGdp.get(j).getGdp();

				}

			}

			if (log == 1) {
				
				for (int w = min; w < ary.length; w++) {
					if(ary[w]==1){
						if(ary[w-1]==0){
							min=w;
						}
						
					}
					
					if (ary[w] == 0) {
						if (ary[w - 1] == 1) {

							if (codeName != null) {
								codeName = codeName + "、" + min + "-" + (w-1);
					
							} else {
								codeName = min + "-" + (w-1);
							}
						}
					}

				}

				secondGdp.get(i).setGdp(gdp);
				if(codeName==null){
					secondGdp.get(i).setGdpcode("0");
				}else{
					
				secondGdp.get(i).setGdpcode(codeName);
				}
			}

		}

		for (int w = 0; w < secondGdp.size(); w++) {
			System.out.println(secondGdp.get(w).getGdpcode() + " " + secondGdp.get(w).getGdp());
		}
		return secondGdp;
	}

	private static String[] getCode(String[] split) {
		// TODO Auto-generated method stub
		for (int i = 0; i < split.length; i++) {
			String firstnumber = split[i].substring(0, 1);
			String secondnumber = split[i].substring(1, 2);
			int firstcode = Integer.valueOf(firstnumber);
			String code;
			if (firstcode == 0) {
				code = secondnumber;
			} else {
				code = split[i];
			}
			split[i] = code;
		}

		return split;
	}

	/**
	 * 
	 * @param year
	 * @param place
	 * @param middleGdpList
	 * @param largeGdpList
	 * @param largeTaxList
	 *            鑾峰緱鎵撶被gdp
	 */
	public static void getLargeGdp(String year, String place, List<Gdp> middleGdpList, List<LargeGdp> largeGdpList,
			List<LargeTax> largeTaxList) {
		// TODO Auto-generated method stub
		List<GdpCaculate> gdpLast = new ArrayList<GdpCaculate>();
		List<GdpCaculate> largeGdpLast = new ArrayList<GdpCaculate>();

		for (int i = 0; i < middleGdpList.size(); i++) {

			String[] inCode = middleGdpList.get(i).getGdpcode().split("、");

			double gdp = middleGdpList.get(i).getGdp();
			if (inCode.length == 1) {// 鍗曚竴闆嗗悎椤�

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

					if (ouCode.length == 1) {
						break;
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

		for (int i = 0; i < gdpLast.size(); i++) {// 鎵惧嚭闆嗗悎绋庢敹
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

		for (int j = 0; j < largeTaxList.size(); j++) {// 鎵惧嚭鏈湪gdp闆嗗悎涓ぇ绫荤殑绋庢敹

			int log = 0;
			for (int w = 0; w < largeGdpList.size(); w++) {
				if (largeTaxList.get(j).getLacode().equals(largeGdpList.get(w).getLacode())) {
					log = 1;
				}

			}
			if (log == 0) {
				GdpCaculate gd = new GdpCaculate();
				gd.setTax(largeTaxList.get(j).getLatax());
				gd.setLacode(largeTaxList.get(j).getLacode());
				gd.setLaname(largeTaxList.get(j).getLaname());
				largeGdpLast.add(gd);

			}

		}

		for (int j = 0; j < largeGdpLast.size(); j++) {// 璁＄畻澶х被gdp

			LargeGdp gdpTag = new LargeGdp();
			String laCode = largeGdpLast.get(j).getLacode();
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
						gdp = tax / gdpLast.get(i).getTax() * gdpLast.get(i).getGdp();
						gdpTag.setLagdp(gdp);
						gdpTag.setPlace(place);
						gdpTag.setYear(year);
						gdpTag.setLacode(largeGdpLast.get(j).getLacode());
						gdpTag.setLaname(largeGdpLast.get(j).getLaname());
						largeGdpList.add(gdpTag);
						break;
					}

				} else if (inCode.length > 1) {

					for (int w = 0; w < inCode.length; w++) {
						String[] ouCode = inCode[w].split("-");

						int max = Integer.valueOf(ouCode[1]);
						int min = Integer.valueOf(ouCode[0]);
						if (code >= min && code <= max) {
							gdp = tax / gdpLast.get(i).getTax() * gdpLast.get(i).getGdp();
							gdpTag.setLagdp(gdp);
							gdpTag.setPlace(place);
							gdpTag.setYear(year);
							gdpTag.setLacode(largeGdpLast.get(j).getLacode());
							gdpTag.setLaname(largeGdpLast.get(j).getLaname());
							largeGdpList.add(gdpTag);
							break;
						}

					}

				}

			}

		}

	}

	public static void getSubGdp(List<Gdp> gdpList, List<SubGdp> subGdpList, List<SubTax> subTaxList,
			List<LargeGdp> largeGdpList, List<LargeTax> largeTaxList, String year, String place) {
		// TODO Auto-generated method stub
		List<GdpCaculate> gdpCaculateList = new ArrayList<GdpCaculate>();
		List<SubGdp> smallGdplist = new ArrayList<SubGdp>();
		for (int i = 0; i < largeGdpList.size(); i++) {
			for (int j = 0; j < largeTaxList.size(); j++) {

				if (largeGdpList.get(i).getLacode().equals(largeTaxList.get(j).getLacode())) {
					GdpCaculate gdpCaculate = new GdpCaculate();
					gdpCaculate.setGdp(largeGdpList.get(i).getLagdp());
					gdpCaculate.setLacode(largeGdpList.get(i).getLacode());
					gdpCaculate.setLaname(largeGdpList.get(i).getLaname());
					gdpCaculate.setTax(largeTaxList.get(j).getLatax());
					gdpCaculateList.add(gdpCaculate);
				}

			}
		}

		for (int i = 0; i < gdpList.size(); i++) {
			if (gdpList.get(i).getGdpcode().length() == 4) {
				SubGdp subGdp = new SubGdp();
				subGdp.setPlace(place);
				subGdp.setYear(year);
				subGdp.setSmname(gdpList.get(i).getGdpname());
				subGdp.setSmcode(gdpList.get(i).getGdpcode());
				subGdp.setSmgdp(gdpList.get(i).getGdp());
			}
		}

		for (int i = 0; i < subTaxList.size(); i++) {
			for (int j = 0; j < gdpCaculateList.size(); j++) {

				if (subTaxList.get(i).getLacode().substring(0, 2).equals(gdpCaculateList.get(j).getLacode())) {
					SubGdp subGdp = new SubGdp();
					double gdp = (subTaxList.get(i).getSmtax() / gdpCaculateList.get(j).getTax())
							* gdpCaculateList.get(j).getGdp();
					subGdp.setPlace(place);
					subGdp.setYear(year);
					subGdp.setSmcode(subTaxList.get(i).getSmcode());
					subGdp.setSmname(subTaxList.get(i).getSmname());
					subGdp.setSmgdp(gdp);

					subGdpList.add(subGdp);
				}

			}
		}

		for (int i = 0; i < subGdpList.size(); i++) {
			for (int j = 0; j < gdpList.size(); j++) {
				if (subGdpList.get(i).getSmcode().equals(gdpList.get(j).getGdpcode())) {
					subGdpList.get(i).setSmgdp(gdpList.get(j).getGdp());
					break;
				}
			}
		}

	}

	public static void getClassGdp(List<LargeGdp> largeGdpList, List<AllCodeDictionary> classLineList,
			List<LargeAndClassDictionary> classDidctionary, String place, String year, List<ClassGdp> classGdpList) {
		// TODO Auto-generated method stub
		List<LargeMiddleClass> largeMiddleGdp = new ArrayList<LargeMiddleClass>();
		for (AllCodeDictionary classCode : classLineList) {
			ClassGdp classGdp = new ClassGdp();
			classGdp.setPlace(place);
			classGdp.setYear(year);
			classGdp.setClcode(classCode.getIncode());
			classGdp.setClname(classCode.getInname());
			classGdpList.add(classGdp);
		}

		for (int i = 0; i < classDidctionary.size(); i++) {

			for (int j = 0; j < largeGdpList.size(); j++) {
				if (classDidctionary.get(i).getLacode().equals(largeGdpList.get(j).getLacode())) {
					LargeMiddleClass lm = new LargeMiddleClass();
					lm.setClasscode(classDidctionary.get(i).getClcode());
					lm.setLacode(classDidctionary.get(i).getLacode());
					lm.setLagdp(largeGdpList.get(j).getLagdp());
					largeMiddleGdp.add(lm);
				} else {
					continue;
				}
			}

		}

		double gdp = 0;
		for (int i = 0; i < classGdpList.size(); i++) {

			gdp = 0;
			for (int j = 0; j < largeMiddleGdp.size(); j++) {
				if (classGdpList.get(i).getClcode().equals(largeMiddleGdp.get(j).getClasscode())) {
					gdp = gdp + largeMiddleGdp.get(j).getLagdp();
				}
			}

			classGdpList.get(i).setClgdp(gdp);
		}
	}

}
