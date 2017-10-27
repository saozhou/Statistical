package com.zmst.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;
import com.zmst.Service.BasedQueryService;
import com.zmst.Service.SelfDefinedSearchService;
import com.zmst.Tools.Class2SelfSearch;
@Service("sf")
public class SelfDefinedSearchServiceImpl implements SelfDefinedSearchService {
@Resource
private BasedQueryService basedQuery;

	public JSONArray GetDoor(int type,String year,String place){
		
		List<ClassTravelGdp> ctGdp=null;
		List<ClassTravelTax> ctTax =null;
		List<ClassGdp> cGdp =null;
		List<ClassTax> cTax =null;
		if((type&1)!=0)	ctGdp= basedQuery.GetctGdp(year, place);
		if((type&2)!=0) 	ctTax = basedQuery.GetctTax(year, place);
		if((type&4)!=0) 	cGdp = basedQuery.GetcGdp(year, place);
		if((type&8)!=0) 	 cTax = basedQuery.GetcTax(year, place);

		List<Object> selfSearch =(List<Object>) Class2SelfSearch.ClassDoit(cGdp, cTax, ctGdp, ctTax);
		JSONArray json = new JSONArray(selfSearch);
		System.out.println(json);
		return json;
	}

	@Override
	public JSONArray GetSub(int type, String year, String place) {
		List<SubTravelGdp> tGdp=null;
		List<SubTravelTax> tTax =null;
		List<SubGdp> Gdp =null;
		List<SubTax> Tax =null;
		if((type&1)!=0)	tGdp= basedQuery.GetsTravelGdp(year, place);
		if((type&2)!=0) 	tTax = basedQuery.GetsTravelTax(year, place);
		if((type&4)!=0) 	Gdp = basedQuery.GetsGdp(year, place);
		if((type&8)!=0) 	 Tax = basedQuery.GetsTax(year, place);

		List<Object> selfSearch =(List<Object>) Class2SelfSearch.SubDoit(Gdp, Tax, tGdp, tTax);
		JSONArray json = new JSONArray(selfSearch);
		System.out.println(json);
		return json;
	}

	@Override
	public JSONArray GetLarge(int type, String year, String place) {
		List<LargeTravelGdp> tGdp=null;
		List<LargeTravelTax> tTax =null;
		List<LargeGdp> Gdp =null;
		List<LargeTax> Tax =null;
		if((type&1)!=0)	tGdp= basedQuery.GetlTravelGdp(year, place);
		if((type&2)!=0) 	tTax = basedQuery.GetlTravelTax(year, place);
		if((type&4)!=0) 	Gdp = basedQuery.GetlGdp(year, place);
		if((type&8)!=0) 	 Tax = basedQuery.GetlTax(year, place);

		List<Object> selfSearch =(List<Object>) Class2SelfSearch.LargeDoit(Gdp, Tax, tGdp, tTax);
		JSONArray json = new JSONArray(selfSearch);
		System.out.println(json);
		return json;
	}

}
