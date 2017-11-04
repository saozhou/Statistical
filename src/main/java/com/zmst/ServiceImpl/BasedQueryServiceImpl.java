package com.zmst.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

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
import com.zmst.IDao.ClassGdpMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.ClassTravelGdpMapper;
import com.zmst.IDao.ClassTravelTaxMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.LargeTravelGdpMapper;
import com.zmst.IDao.LargeTravelTaxMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.IDao.SubTravelGdpMapper;
import com.zmst.IDao.SubTravelTaxMapper;
import com.zmst.Service.BasedQueryService;
import com.zmst.Tools.HttpReturn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
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
private ClassGdpMapper cGdpDao;
@Resource
private ClassTaxMapper cTaxDao;
@Resource
private ClassTravelGdpMapper ctGdpDao;
@Resource
private ClassTravelTaxMapper ctTaxDao;

/*
 * 门类查询
 */
	public List<ClassGdp> GetcGdp(String year ,String place){
		List<ClassGdp> cGdpList = cGdpDao.findByYearPlace(year, place);
		return cGdpList;
	}
	public List<ClassTax> GetcTax(String year ,String place){
		List<ClassTax> cTaxList = cTaxDao.findByYearPlace(year, place);
		return cTaxList;
	}
	public List<ClassTravelGdp> GetctGdp(String year ,String place){
		List<ClassTravelGdp> ctGdpList = ctGdpDao.findByYearPlace(year, place);
		return ctGdpList;
	}
	public List<ClassTravelTax> GetctTax(String year ,String place){
		List<ClassTravelTax> ctTaxList = ctTaxDao.findByYearPlace(year, place);
		return ctTaxList;
	}
	
	@Resource
	private SubGdpMapper sGdpDao;
	@Resource
	private SubTaxMapper sTaxDao;
	@Resource
	private SubTravelGdpMapper sTravelGdpDao;
	@Resource
	private SubTravelTaxMapper sTravelTaxDao;
/*
 *  小类查询
 */
	public List<SubGdp	> GetsGdp(String year,String place){
		List<SubGdp> sGdpList = sGdpDao.findByYearPlace(year, place);
		return sGdpList;
	}
	public List<SubTax> GetsTax(String year,String place){
		List<SubTax> sGdpList = sTaxDao.findSubTaxByYearPlace(year, place);
		return sGdpList;
	}	public List<SubTravelGdp> GetsTravelGdp(String year,String place){
		List<SubTravelGdp> sGdpList = sTravelGdpDao.findByYearPlace(year, place);
		return sGdpList;
	}	public List<SubTravelTax> GetsTravelTax(String year,String place){
		List<SubTravelTax> sGdpList = sTravelTaxDao.findSubTravelTaxByYearPlace(year, place);
		return sGdpList;
	}
	
	@Resource
	private LargeGdpMapper lGdpDao;
	@Resource
	private LargeTaxMapper lTaxDao;
	@Resource
	private LargeTravelGdpMapper lTraveGdpDao;
	@Resource
	private LargeTravelTaxMapper lTraveTaxDao;
	/*
	 *  大类查询
	 */
		public List<LargeGdp	> GetlGdp(String year,String place){
			List<LargeGdp> lGdpList = lGdpDao.findByYearPlace(year, place);
			return lGdpList;
		}
		public List<LargeTax> GetlTax(String year,String place){
			List<LargeTax> lGdpList = lTaxDao.getByYearPlace(year, place);
			return lGdpList;
		}	public List<LargeTravelGdp> GetlTravelGdp(String year,String place){
			List<LargeTravelGdp> lGdpList = lTraveGdpDao.findByYearPlace(year, place);
			return lGdpList;
		}	public List<LargeTravelTax> GetlTravelTax(String year,String place){
			List<LargeTravelTax> lGdpList = lTraveTaxDao.findByYearPlace(year, place);
			return lGdpList;
		}

	@Resource
	private GdpMapper gdpDao;
	@Resource
	private LandTaxMapper landTaxDao;
	@Resource
	private CentralTaxMapper centralTaxDao;
	@Resource
	private GFCoefficientMapper gfCoefficientDao;
	
	public List<LandTax> getLandTax(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<LandTax> landTax = new ArrayList<LandTax>();
 
		landTax=landTaxDao.getAllLandTax(year, place);
		   if(landTax.size()==0){
			   String json = JSON.toJSONString("地税表未上传");
			   HttpReturn.reponseBody(response, json);
			    
			   return null;
		   }
		return landTax;
	}

	public GFCoefficient getGFCoefficient(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		GFCoefficient gfCoefficient =null;
		gfCoefficient=	gfCoefficientDao.selectByYearPlace(year, place);
		if(gfCoefficient==null){
			 String json = JSON.toJSONString("gf系数未上传");
			   HttpReturn.reponseBody(response, json);

			   return null;
		   }
		return gfCoefficient;
	}

	public List<Gdp> getGdpTax(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Gdp> gdp = new ArrayList<Gdp>(); 
		gdp=gdpDao.findByYearPlace(year, place);
		if(gdp.size()==0){
			 String json = JSON.toJSONString("gdp表未上传");
			   HttpReturn.reponseBody(response, json);
			  
			   return null;
		   }
		return gdp;
	}

	public List<CentralTax> getCentralTax(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<CentralTax> centralTax = new ArrayList<CentralTax>();
		centralTax = centralTaxDao.getAllCentralTax(year, place);
		if(centralTax.size()==0){
			
			 String json = JSON.toJSONString("国税表未上传");
			   HttpReturn.reponseBody(response, json);
			  
 
			return null;
		}
		return centralTax;
	}


}
