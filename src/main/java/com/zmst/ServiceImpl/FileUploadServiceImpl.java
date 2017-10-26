package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.*;
import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.GdpMiddleTable;
import com.zmst.Domain.LandTax;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.SubGdp;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GFReferenceMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.GdpMiddleTableMapper;
import com.zmst.IDao.LandTaxMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.Service.FileUploadService;
import com.zmst.Tools.GdpAnalyze;

import LCMap.ListChnageMap;
import ListChnage.ListChangeUtil;
@Service("uploadService")
public class FileUploadServiceImpl implements FileUploadService {
     @Resource
     private AllCodeDictionaryMapper codeDictionaryDao;
     @Resource
     private GFReferenceMapper gfReferenceDao;
     @Resource
     private GFCoefficientMapper gfCorfficientDao;
     @Resource
     private LandTaxMapper landTaxDao;
     @Resource
     private CentralTaxMapper centralTaxDao;
     @Resource
     private GdpMiddleTableMapper gdpMiddleDao;
     @Resource 
    private LargeAndClassDictionaryMapper  largeAndClassDao; 
     @Resource
     private  GdpMapper gdpDao;
     @Resource 
     private SubGdpMapper subGdpDao;
     @Resource
     private LargeGdpMapper largeGdpDao;
	public List<AllCodeDictionary> changeLineExcel(List<List<String>> list) {
		// TODO Auto-generated method stub
		List<AllCodeDictionary>codeDi = new ArrayList<AllCodeDictionary>();
		codeDi = ListChangeUtil.ChangeCodeList(list);
		return codeDi;
	}

	public List<AllCodeDictionary> selectAllData() {
		// TODO Auto-generated method stub
		List<AllCodeDictionary>codeDictionary=codeDictionaryDao.selectAll();
		return codeDictionary;
	}

	 
	public void deleteCodeDictionary() {
		// TODO Auto-generated method stub
		codeDictionaryDao.deleteAll();
	}

	public void saveCodeDictionary(List<AllCodeDictionary> codeDictionary) {
		// TODO Auto-generated method stub
		 
		for(AllCodeDictionary code: codeDictionary){
			codeDictionaryDao.save(code);
		}
	}

	public List<GFReference> changeGFReferenceExcel(List<List<String>> list,String place,String year) {
		// TODO Auto-generated method stub
		List<GFReference>gfReference = new ArrayList<GFReference>();
		gfReference = ListChangeUtil.changeGFReference(list,year,place);
		return gfReference;
		 
	}

	public List<GFReference> selectGFReferenceByYP(String place, String year) {
		// TODO Auto-generated method stub
		
		List<GFReference>gfReferenceList=gfReferenceDao.selectByYearAndPlace(year,place);
		return gfReferenceList;
	}

	public void deleteGFReference(String year, String place) {
		// TODO Auto-generated method stub
		gfReferenceDao.deleteByYearPlace(year,place);
	}

	public void saveGFReference(List<GFReference> gfReference) {
		// TODO Auto-generated method stub
		for(GFReference gf: gfReference){
			gfReferenceDao.save(gf);
		}
	}

	public boolean getExitsCorfficient(String year, String place) {
		// TODO Auto-generated method stub
		GFCoefficient gfCorfficient=null;
		gfCorfficient=gfCorfficientDao.selectByYearPlace(year,place);
		if(gfCorfficient!=null){
			return true;
		}
		
		return false;
	}

	public void update(GFCoefficient corfficient, String year, String place) {
		// TODO Auto-generated method stub
		gfCorfficientDao.deleteByYearPlace(year,place);
		gfCorfficientDao.save(corfficient);
	}

	public void save(GFCoefficient corfficient) {
		// TODO Auto-generated method stub
		gfCorfficientDao.save(corfficient);
	}

	public List<LandTax> changeLandTax(List<List<String>> list, String year, String place,int matchingWay) {
		// TODO Auto-generated method stub
		List<LandTax>landTaxList = ListChangeUtil.changeLandTax(list,year,place, matchingWay);
		List<AllCodeDictionary>codeDictionary=codeDictionaryDao.selectAll();
		 
		ListChangeUtil.matchingLandTax(landTaxList,codeDictionary,matchingWay);
		return landTaxList;
	}

	public List<LandTax> getLandTax(String year, String place) {
		// TODO Auto-generated method stub
		List<LandTax> landTax = landTaxDao.getAllLandTax(year,place);
		return landTax;
	}

	public void updateLandTax(List<LandTax> landTax, String year, String place) {
		// TODO Auto-generated method stub
		landTaxDao.deleteByYearPlace(year,place);
		for(LandTax la: landTax){
			landTaxDao.save(la);
		}
	}

	public void saveLandTax(List<LandTax> landTax) {
		// TODO Auto-generated method stub
		for(LandTax la: landTax){
			landTaxDao.save(la);
		}
	}

	public List<CentralTax> changeCentralTax(List<List<String>> list, String year, String place, int matchingWay) {
		// TODO Auto-generated method stub
		List<CentralTax>centralTaxList = ListChangeUtil.changeCentralTax(list,year,place, matchingWay);
		List<AllCodeDictionary>codeDictionary=codeDictionaryDao.selectAll();
		ListChangeUtil.matchingCentralTax(centralTaxList,codeDictionary,matchingWay);
		return centralTaxList;
	}

	public List<CentralTax> getCentralTax(String year, String place) {
		// TODO Auto-generated method stub
		
		List<CentralTax> centralTax = centralTaxDao.getAllCentralTax(year,place);
		return centralTax;
	}

	public void updateCentralTax(List<CentralTax> centralTax, String year, String place) {
		// TODO Auto-generated method stub
		centralTaxDao.deleteByYearPlace(year,place);
		for(CentralTax ce: centralTax){
			centralTaxDao.save(ce);
		}
	}

	public void saveCentralTax(List<CentralTax> centralTax) {
		// TODO Auto-generated method stub
		for(CentralTax ce: centralTax){
			centralTaxDao.save(ce);
		}
	}

	public List<Gdp> changeGdp(List<List<String>> list, String year, String place) {
		// TODO Auto-generated method stub
		List<Gdp>gdplist = ListChangeUtil.changeGdp(list,year,place);
		return gdplist;
	}

	public List<Gdp> getOldGdp(String year, String place) {
		// TODO Auto-generated method stub
		List<Gdp> gdpList = gdpDao.getAllGdp(year,place);
		return gdpList;
		 
	}

	public void updateGdp(List<Gdp> gdpList, String year, String place) {
		// TODO Auto-generated method stub
		gdpDao.deleteByYearPlace(year,place);
		gdpMiddleDao.deleteByYearPlace(year,place);	
		for(Gdp ce: gdpList){
			gdpDao.save(ce);
		}
	}

	public void saveGdp(List<Gdp> gdpList) {
		// TODO Auto-generated method stub
		for(Gdp ce: gdpList){
			gdpDao.save(ce);
		}
	}

	/**
	 * gdp分解
	 * 1.将已存在的大类gdp挑出
	 */
	public void gdpAnalyze(List<Gdp> gdpList, String year, String place) {
		// TODO Auto-generated method stub
		List<LargeGdp>largeGdpList = new ArrayList<LargeGdp>();
		
		List<GdpMiddleTable>gdpMiddleList = new ArrayList<GdpMiddleTable>();
		
		List<SubGdp>subGdpList = new ArrayList<SubGdp>();
		
		List<LargeAndClassDictionary>largeAndClass=largeAndClassDao.findAll();
		
		GdpAnalyze.sloveLSMGdp(gdpList,gdpMiddleList,subGdpList,largeAndClass,largeGdpList,year,place);
		
		for(LargeGdp largegdp:largeGdpList){
			largeGdpDao.save(largegdp);
		}
		 for(SubGdp sub:subGdpList){
			 subGdpDao.save(sub);
		 }
		
		 for(GdpMiddleTable gdpMiddle:gdpMiddleList){
			 gdpMiddleDao.save(gdpMiddle);
		 }
		
		
	}
    
	
	 
}
