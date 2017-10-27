package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.LandTaxMapper;
import javax.annotation.*;
import com.zmst.Service.BasedQueryService;
@Service("baseQueryService")
public class BasedQueryServiceImpl implements BasedQueryService {

	@Resource
	private GdpMapper gdpDao;
	@Resource
	private LandTaxMapper landTaxDao;
	@Resource
	private CentralTaxMapper centralTaxDao;
	@Resource
	private GFCoefficientMapper gfCoefficientDao;
	
	public List<LandTax> getLandTax(String year, String place) {
		// TODO Auto-generated method stub
		List<LandTax> landTax = new ArrayList<LandTax>();
		System.out.println(year);
		System.out.println(place);
		landTax=landTaxDao.getAllLandTax(year, place);
		return landTax;
	}

	public GFCoefficient getGFCoefficient(String year, String place) {
		// TODO Auto-generated method stub
		GFCoefficient gfCoefficient = gfCoefficientDao.findCorfficient(year, place);
		return gfCoefficient;
	}

	public List<Gdp> getGdpTax(String year, String place) {
		// TODO Auto-generated method stub
		List<Gdp> gdp = gdpDao.findByYearPlace(year, place);
		
		return gdp;
	}

	public List<CentralTax> getCentralTax(String year, String place) {
		// TODO Auto-generated method stub
		List<CentralTax> centralTax =centralTaxDao.getAllCentralTax(year, place);
		return centralTax;
	}

}
