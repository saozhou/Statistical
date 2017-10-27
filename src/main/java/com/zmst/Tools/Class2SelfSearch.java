package com.zmst.Tools;

import java.util.ArrayList;
import java.util.List;

import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SelfSearch;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;

public class Class2SelfSearch {

	public static List<?> ClassDoit(List<ClassGdp>Gdp,List<ClassTax>Tax,List<ClassTravelGdp>TraveGdp,List<ClassTravelTax>TraveTax){
		List<SelfSearch> selfSearch = new ArrayList<SelfSearch>();
		SelfSearch son = null;
		if(Gdp!=null){
			for(ClassGdp temp : Gdp){
				son = new SelfSearch();
				son.setName(temp.getClname());
				son.setCode(temp.getClcode());
				son.setGdp(temp.getClgdp());
				selfSearch.add(son);
			}
		}
		if(Tax!=null){
					if(selfSearch.size()==0){
						for(ClassTax temp : Tax){
							son = new SelfSearch();
							son.setName(temp.getClname());
							son.setCode(temp.getClcode());
							son.setTax(temp.getCltax());
							selfSearch.add(son);
						}
					}else{
						for(int i=0;i<selfSearch.size();i++){
							selfSearch.get(i).setTax(Tax.get(i).getCltax());
						}
					}
		}
		if(TraveGdp!=null){
				if(selfSearch.size()==0){
					for(ClassTravelGdp temp : TraveGdp){
						son = new SelfSearch();
						son.setName(temp.getClname());
						son.setCode(temp.getClcode());
						son.setTravegdp(temp.getCtgdp());
						selfSearch.add(son);
					}
				}else{
					for(int i=0;i<selfSearch.size();i++){
						selfSearch.get(i).setTravegdp(TraveGdp.get(i).getCtgdp());
					}
				}
		}
		if(TraveTax!=null){
			if(selfSearch.size()==0){
				for(ClassTravelTax temp : TraveTax){
					son = new SelfSearch();
					son.setName(temp.getClname());
					son.setCode(temp.getClcode());
					son.setTravetax(temp.getCttax());
					selfSearch.add(son);
				}
			}else{
				for(int i=0;i<selfSearch.size();i++){
					selfSearch.get(i).setTravetax(TraveTax.get(i).getCttax());
				}
			}
		}
		return selfSearch;
	}

	public static List<?> SubDoit(List<SubGdp> Gdp, List<SubTax> Tax, List<SubTravelGdp> TraveGdp,List<SubTravelTax> TraveTax) {
		List<SelfSearch> selfSearch = new ArrayList<SelfSearch>();
		SelfSearch son = null;
		if(Gdp!=null){
			for(SubGdp temp : Gdp){
				son = new SelfSearch();
				son.setName(temp.getSmname());
				son.setCode(temp.getSmcode());
				son.setGdp(temp.getSmgdp());
				selfSearch.add(son);
			}
		}
		if(Tax!=null){
					if(selfSearch.size()==0){
						for(SubTax temp : Tax){
							son = new SelfSearch();
							son.setName(temp.getSmname());
							son.setCode(temp.getSmcode());
							son.setTax(temp.getSmtax());
							selfSearch.add(son);
						}
					}else{
						for(int i=0;i<selfSearch.size();i++){
							selfSearch.get(i).setTax(Tax.get(i).getSmtax());
						}
					}
		}
		if(TraveGdp!=null){
				if(selfSearch.size()==0){
					for(SubTravelGdp temp : TraveGdp){
						son = new SelfSearch();
						son.setName(temp.getSmname());
						son.setCode(temp.getSmcode());
						son.setTravegdp(temp.getStgdp());
						selfSearch.add(son);
					}
				}else{
					for(int i=0;i<selfSearch.size();i++){
						selfSearch.get(i).setTravegdp(TraveGdp.get(i).getStgdp());
					}
				}
		}
		if(TraveTax!=null){
			if(selfSearch.size()==0){
				for(SubTravelTax temp : TraveTax){
					son = new SelfSearch();
					son.setName(temp.getSmname());
					son.setCode(temp.getSmcode());
					son.setTravetax(temp.getSttax());
					selfSearch.add(son);
				}
			}else{
				for(int i=0;i<selfSearch.size();i++){
					selfSearch.get(i).setTravetax(TraveTax.get(i).getSttax());
				}
			}
		}
		return selfSearch;
	}

	public static List<?> LargeDoit(List<LargeGdp> Gdp, List<LargeTax> Tax, List<LargeTravelGdp> TraveGdp,List<LargeTravelTax> TraveTax) {
		List<SelfSearch> selfSearch = new ArrayList<SelfSearch>();
		SelfSearch son = null;
		if(Gdp!=null){
			for(LargeGdp temp : Gdp){
				son = new SelfSearch();
				son.setName(temp.getLaname());
				son.setCode(temp.getLacode());
				son.setGdp(temp.getLagdp());
				selfSearch.add(son);
			}
		}
		if(Tax!=null){
					if(selfSearch.size()==0){
						for(LargeTax temp : Tax){
							son = new SelfSearch();
							son.setName(temp.getLaname());
							son.setCode(temp.getLacode());
							son.setTax(temp.getLatax());
							selfSearch.add(son);
						}
					}else{
						for(int i=0;i<selfSearch.size();i++){
							selfSearch.get(i).setTax(Tax.get(i).getLatax());
						}
					}
		}
		if(TraveGdp!=null){
				if(selfSearch.size()==0){
					for(LargeTravelGdp temp : TraveGdp){
						son = new SelfSearch();
						son.setName(temp.getLaname());
						son.setCode(temp.getLacode());
						son.setTravegdp(temp.getLtgdp());
						selfSearch.add(son);
					}
				}else{
					for(int i=0;i<selfSearch.size();i++){
						selfSearch.get(i).setTravegdp(TraveGdp.get(i).getLtgdp());
					}
				}
		}
		if(TraveTax!=null){
			if(selfSearch.size()==0){
				for(LargeTravelTax temp : TraveTax){
					son = new SelfSearch();
					son.setName(temp.getLaname());
					son.setCode(temp.getLacode());
					son.setTravetax(temp.getLttax());
					selfSearch.add(son);
				}
			}else{
				for(int i=0;i<selfSearch.size();i++){
					selfSearch.get(i).setTravetax(TraveTax.get(i).getLttax());
				}
			}
		}
		return selfSearch;
	}
}
