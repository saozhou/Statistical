package com.zmst.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.*;



import com.zmst.Domain.TravelIndustryTaxContribute;
import com.zmst.IDao.ClassGdpMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.ClassTravelGdpMapper;
import com.zmst.IDao.ClassTravelTaxMapper;
import com.zmst.IDao.GdpMiddleTableMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.LargeTravelGdpMapper;
import com.zmst.IDao.LargeTravelTaxMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.IDao.SubTravelGdpMapper;
import com.zmst.IDao.SubTravelTaxMapper;
import com.zmst.IDao.TravelClassGdpContributeMapper;
import com.zmst.IDao.TravelClassTaxContributeMapper;
import com.zmst.IDao.TravelIndustryGdpContributeMapper;
import com.zmst.IDao.TravelIndustryTaxContributeMapper;
import com.zmst.IDao.TravelLargeGdpContributeMapper;
import com.zmst.IDao.TravelLargeTaxContributeMapper;
import com.zmst.Service.DeleteService;

@Service("deleteService")
public class DeleteServiceImpl implements DeleteService {

	@Resource
	private SubTravelGdpMapper subTravelGdpDao;
	@Resource
	private SubTravelTaxMapper subTravelTaxDao;
	@Resource
	private LargeTravelGdpMapper largeTravelGdpDao;
	@Resource
	private  LargeTravelTaxMapper largeTravelTaxDao;
	@Resource
	private ClassTravelTaxMapper classTravelTaxDao; 
	@Resource
	private ClassTravelGdpMapper classTravelGdpDao;
	@Resource
	private TravelLargeGdpContributeMapper TravelLargeGdpContributeDao;
	@Resource
	private TravelClassGdpContributeMapper travelClassGdpContributeDao;
	@Resource
	private TravelIndustryGdpContributeMapper travelIndustryGdpContributeDao;
	@Resource
	private TravelLargeTaxContributeMapper travelLargeTaxContributeDao;
	@Resource
	private TravelClassTaxContributeMapper travelClassTaxContributeDao;
	@Resource
	private TravelIndustryTaxContributeMapper travelIndustryTaxContributeDao;
	@Resource
	private GdpMiddleTableMapper gdpMiddleDao;
	@Resource
	private SubGdpMapper subGdpDao;
	@Resource
	private LargeGdpMapper largeGdpDao;
	@Resource
	private ClassGdpMapper classGdpDao;
	@Resource
	private SubTaxMapper subTaxDao;
	@Resource
	private LargeTaxMapper largeTaxDao;
	@Resource
	private ClassTaxMapper classTaxDao;
	public void DeleteTravelTax(String year, String place) {
		// TODO Auto-generated method stub
		 
		subTravelTaxDao.deleteByYearPlace(year,place);
		largeTravelTaxDao.deleteByYearPlace(year,place);
		classTravelTaxDao.deleteByYearPlace(year,place);
		travelLargeTaxContributeDao.deleteByYearPlace(year,place);
		travelClassTaxContributeDao.deleteByYearPlace(year,place);
		travelIndustryTaxContributeDao.deleteByYearPlace(year,place);
	}

	public void DeleteTravelGdp(String year, String place) {
		// TODO Auto-generated method stub
		subTravelGdpDao.deleteByYearPlace(year,place);
		largeTravelGdpDao.deleteByYearPlace(year,place);
		classTravelGdpDao.deleteByYearPlace(year,place);
		TravelLargeGdpContributeDao.deleteByYearPlace(year,place);
		travelClassGdpContributeDao.deleteByYearPlace(year,place);
		travelIndustryGdpContributeDao.deleteByYearPlace(year,place);
	}

	public void DeleteGDP(String year, String place) {
		// TODO Auto-generated method stub
		subGdpDao.deleteByYearPlace(year,place);
		largeGdpDao.deleteByYearPlace(year,place);
		classGdpDao.deleteByYearPlace(year,place);
	}

	public void DeleteTax(String year, String place) {
		// TODO Auto-generated method stub
		subTaxDao.deleteByYearPlace(year,place);
		largeTaxDao.deleteByYearPlace(year,place);
		classTaxDao.deleteByYearPlace(year,place);
		
	}

}
