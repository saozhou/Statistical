package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.IndustryDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;
import com.zmst.Domain.TravelClassGdpContribute;
import com.zmst.Domain.TravelClassTaxContribute;
import com.zmst.Domain.TravelIndustryGdpContribute;
import com.zmst.Domain.TravelIndustryTaxContribute;
import com.zmst.Domain.TravelLargeGdpContribute;
import com.zmst.Domain.TravelLargeTaxContribute;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.ClassGdpMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.ClassTravelGdpMapper;
import com.zmst.IDao.ClassTravelTaxMapper;
import com.zmst.IDao.IndustryDictionaryMapper;
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
import com.zmst.Service.IntegratedQueryService;
import com.zmst.Tools.ContributeUtil;


@Service("integratedQueryService")
public class IntegratedQuaryServiceImpl implements IntegratedQueryService {

	@Resource
	private TravelLargeGdpContributeMapper  largeGdpContributeDao;
	@Resource
	private LargeGdpMapper largeGdpDao;
	@Resource
	private LargeTravelGdpMapper largeTravelGdpDao;
	@Resource
	private TravelClassGdpContributeMapper classTravelGdpContributeDao;
	@Resource
	private AllCodeDictionaryMapper codeDictionaryDao;
	@Resource
	private ClassGdpMapper classGdpDao;
	@Resource
	private ClassTravelGdpMapper classTravelGdpDao;
	@Resource
	private TravelIndustryGdpContributeMapper industryGdpDao;
	@Resource
	private IndustryDictionaryMapper industryLineDao;
	@Resource
	private TravelLargeTaxContributeMapper largeTaxContributeDao;
	@Resource
	private LargeTravelTaxMapper largeTravelTaxDao;
	@Resource
	private LargeTaxMapper largeTaxDao;
	@Resource
	private TravelClassTaxContributeMapper classTaxContributeDao;
	@Resource
	private ClassTaxMapper classTaxDao;
	@Resource
	private ClassTravelTaxMapper classTravelTaxDao;
	@Resource
	private TravelIndustryTaxContributeMapper industryTaxContributeDao;
	@Resource
	private SubTravelTaxMapper subTravelTaxDao;
	@Resource
	private SubTaxMapper subTaxDao;
	@Resource
	private SubGdpMapper subGdpDao;
	@Resource
	private SubTravelGdpMapper subTravelGdpDao;
	/**
	 * 数据库查询
	 */
	public List<TravelLargeGdpContribute> getTravelLargeGdpContribute(String year, String place) {
		// TODO Auto-generated method stub
		List<TravelLargeGdpContribute> largeGdpContributeList = largeGdpContributeDao.getByYearPlace(year,place);
		return largeGdpContributeList;
	}

/**
 * 大类旅游gdp综合贡献
 */
	public List<TravelLargeGdpContribute> getLargeGdpContribute(
			List<TravelLargeGdpContribute> travelLargeGdpContributeList, String year, String place) {
		// TODO Auto-generated method stub
		   List<LargeTravelGdp> largeTravelGdpList = new  ArrayList<LargeTravelGdp>();
		   List<LargeGdp>largeGdp = new ArrayList<LargeGdp>();
		   largeGdp = largeGdpDao.findByYearPlace(year, place);
		   System.out.println(largeGdp.size());
		  
		   largeTravelGdpList = largeTravelGdpDao.findByYearPlace(year,place);  System.out.println(largeTravelGdpList.size());
		   ContributeUtil.getLargeGdpContribute(year,place,travelLargeGdpContributeList,largeTravelGdpList,largeGdp);
		
		    for(TravelLargeGdpContribute travelLargeGdpContribute:travelLargeGdpContributeList){
		    	largeGdpContributeDao.save(travelLargeGdpContribute);
		    }
		   System.out.println(travelLargeGdpContributeList.size());
		   
		return travelLargeGdpContributeList;
	}

	
	/**
	 * 门类贡献数据库查询
	 */
public List<TravelClassGdpContribute> getClassGdpContribute(String year, String place,List<TravelClassGdpContribute> classGdpContributeList) {
	// TODO Auto-generated method stub
	
	classGdpContributeList=classTravelGdpContributeDao.findByYearPlace(year,place);
	return classGdpContributeList;
}
/**
 * 门类贡献数据计算
 */
public List<TravelClassGdpContribute> getClassGdpContributeList(List<TravelClassGdpContribute> classGdpContributeList,
		String year, String place) {
	// TODO Auto-generated method stub
	
	System.out.println(year);
    System.out.println(place);
    List<ClassGdp> classGdpList = new ArrayList<ClassGdp>();
    List<ClassTravelGdp> classTravelGdpList = new ArrayList<ClassTravelGdp>();
    classGdpList = classGdpDao.findByYearPlace(year,place);
    System.out.println(classGdpList.size());
    classTravelGdpList = classTravelGdpDao.findByYearPlace(year,place);
    ContributeUtil.getClassGdpContribute(classGdpList,classTravelGdpList,year,place,classGdpContributeList);
    for(TravelClassGdpContribute  classGdpContribute:classGdpContributeList){
    	classTravelGdpContributeDao.save(classGdpContribute);
    }
	return classGdpContributeList;
}


/**
 * 数据库gdp产业贡献查询
 */
public List<TravelIndustryGdpContribute> getIndustryGdpContribute(String year, String place,
		List<TravelIndustryGdpContribute> industryGdpContributeList) {
	// TODO Auto-generated method stub
	industryGdpContributeList=industryGdpDao.findByYearPlace(year,place);
	
	return industryGdpContributeList;
}

/**
 * gdp产业贡献计算
 */
public List<TravelIndustryGdpContribute> getIndustryGdpContributeList(
		List<TravelIndustryGdpContribute> industryGdpContributeList, String year, String place) {
	// TODO Auto-generated method stub
	
	List<IndustryDictionary> industryLineList = industryLineDao.findAll();
	List<TravelLargeGdpContribute>largeGdpList = largeGdpContributeDao.getByYearPlace(year, place);
	List<TravelClassGdpContribute>classGdpList =classTravelGdpContributeDao.findByYearPlace(year, place);
	ContributeUtil.getIndustryGdpContribute(industryLineList,largeGdpList,classGdpList,industryGdpContributeList,year,place);
	for(TravelIndustryGdpContribute inContribute:industryGdpContributeList){
		industryGdpDao.save(inContribute);
	}
	
	return industryGdpContributeList;
}

/**
 *大类旅游税收贡献查询 
 * 
 */
public List<TravelLargeTaxContribute> getTravelLargeTaxContribute(String year, String place,
		List<TravelLargeTaxContribute> travelLargeTaxContributeList) {
	// TODO Auto-generated method stub
	travelLargeTaxContributeList=largeTaxContributeDao.findByYearPlace(year,place);
	return travelLargeTaxContributeList;
 
}

/**
 * 大类旅游税收贡献计算
 */
public List<TravelLargeTaxContribute> getLargeTaxContribute(List<TravelLargeTaxContribute> travelLargeTaxContributeList,
		String year, String place) {
	// TODO Auto-generated method stub
	   List<LargeTravelTax> largeTravelTaxList = new  ArrayList<LargeTravelTax>();
	   List<LargeTax>largeTaxList = new ArrayList<LargeTax>();
	   largeTaxList = largeTaxDao.getByYearPlace(year, place);
	   largeTravelTaxList = largeTravelTaxDao.findByYearPlace(year,place);
	   ContributeUtil.getLargeTaxContribute(year,place,travelLargeTaxContributeList,largeTravelTaxList,largeTaxList);
	
	    for(TravelLargeTaxContribute travelLargeTaxContribute:travelLargeTaxContributeList){
	    	largeTaxContributeDao.save(travelLargeTaxContribute);
	    }
	   
	   
	return travelLargeTaxContributeList;
}

/**
 * 数据库查询门类税收贡献
 */
public  List<TravelClassTaxContribute> getClassTaxContribute(String year, String place,
		List<TravelClassTaxContribute> classTaxContributeList) {
	// TODO Auto-generated method stub
	
	
	classTaxContributeList=classTaxContributeDao.findByYearPlace(year,place);
	return classTaxContributeList;
 
}

/**
 * 税收门类贡献计算
 */
public List<TravelClassTaxContribute> getClassTaxContributeList(List<TravelClassTaxContribute> classTaxContributeList,
		String year, String place) {
	// TODO Auto-generated method stub
	

    List<ClassTax> classTaxList = new ArrayList<ClassTax>();
    List<ClassTravelTax> classTravelTaxList = new ArrayList<ClassTravelTax>();
    classTaxList = classTaxDao.findByYearPlace(year,place);
    classTravelTaxList = classTravelTaxDao.findByYearPlace(year,place);
    ContributeUtil.getClassTaxContribute(classTaxList,classTravelTaxList,year,place,classTaxContributeList);
    for(TravelClassTaxContribute  classTaxContribute:classTaxContributeList){
    	classTaxContributeDao.save(classTaxContribute);
    }
	return classTaxContributeList;
 
}

public List<TravelIndustryTaxContribute> getIndustryTaxContribute(String year, String place,
		List<TravelIndustryTaxContribute> industryTaxContributeList) {
	// TODO Auto-generated method stub
	
	industryTaxContributeList=industryTaxContributeDao.findByYearPlace(year,place);
	return industryTaxContributeList;
}

public List<TravelIndustryTaxContribute> getIndustryTaxContributeList(
		List<TravelIndustryTaxContribute> industryTaxContributeList, String year, String place) {
	// TODO Auto-generated method stub

	List<IndustryDictionary> industryLineList = industryLineDao.findAll();
	List<TravelLargeTaxContribute>largeTaxList = largeTaxContributeDao.findByYearPlace(year, place);
	List<TravelClassTaxContribute>classTaxList =classTaxContributeDao.findByYearPlace(year, place);
	System.out.println(classTaxList.size());
	System.out.println(largeTaxList.size());
	
	ContributeUtil.getIndustryTaxContribute(industryLineList,largeTaxList,classTaxList,industryTaxContributeList,year,place);
	for(TravelIndustryTaxContribute inContribute:industryTaxContributeList){
		industryTaxContributeDao.save(inContribute);
	}
	
	return industryTaxContributeList;
	 
}
	

public List<SubTax> getSubTaxt(String year, String place) {
	// TODO Auto-generated method stub
	List<SubTax> subTaxList = subTaxDao.findSubTaxByYearPlace(year, place);
	return subTaxList;
}

public List<SubTravelTax> getSubTravelTaxt(String year, String place) {
	// TODO Auto-generated method stub
	List<SubTravelTax> subTravelTax = subTravelTaxDao.findSubTravelTaxByYearPlace(year, place);
	return subTravelTax;
}

public List<SubGdp> getSubGdp(String year, String place) {
	// TODO Auto-generated method stub
	
	List<SubGdp> subGdp =subGdpDao.findByYearPlace(year, place);
	return subGdp;
}

public List<SubTravelGdp> getSubTravelGdp(String year, String place) {
	// TODO Auto-generated method stub
	 List<SubTravelGdp> subTravelGdp = subTravelGdpDao.findByYearPlace(year, place);
	return subTravelGdp;
}


}
