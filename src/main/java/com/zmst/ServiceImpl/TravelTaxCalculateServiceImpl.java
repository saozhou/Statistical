package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.LandTax;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelTax;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.ClassTravelTaxMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GFReferenceMapper;
import com.zmst.IDao.LandTaxMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.LargeTravelTaxMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.IDao.SubTravelTaxMapper;
import com.zmst.Service.TravelTaxCalculateService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.TaxCaculateUtil;
 
@Service("travelTaxService")
public class TravelTaxCalculateServiceImpl implements TravelTaxCalculateService {

	@Resource
	private SubTravelTaxMapper subTravleTaxDao;
	@Resource
	private LandTaxMapper landTaxDao;
	@Resource
	private CentralTaxMapper centralTaxDao;
	@Resource
	private AllCodeDictionaryMapper codeDictionaryDao;
	@Resource
	private LargeAndClassDictionaryMapper classDao;
	@Resource
	private LargeTaxMapper largeTaxDao;
	@Resource
	private ClassTaxMapper classTaxDao;
	@Resource
	private SubTaxMapper subTaxDao;
	@Resource
	private GFReferenceMapper gfReferenceDao;
	@Resource
	private GFCoefficientMapper gfCoefficientDao;
	@Resource
	private LargeTravelTaxMapper largeTravelTaxDao;
	@Resource
	private ClassTravelTaxMapper classTravelTaxDao;
	
	public List<SubTravelTax> getSubTravelTaxt(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<SubTravelTax>subTravelTaxList = null; 
		subTravelTaxList=subTravleTaxDao.findSubTravelTaxByYearPlace(year,place);
		if(subTravelTaxList.size()==0){//如果总税收未计算则进行计算处理
			  subTravelTaxList = new ArrayList<SubTravelTax>();  
		      List<SubTax>subTaxList = subTaxDao.findSubTaxByYearPlace(year, place);
		      if(subTaxList.size()==0){
		    	  String json = JSON.toJSONString("小类税收未计算");
				   HttpReturn.reponseBody(response, json);
		    	  return null;
		      }
		      
		      
		      List<GFReference>gfReference=gfReferenceDao.selectByYearAndPlace(year,place);
		      
		      if(gfReference.size()==0){
		    	  String json = JSON.toJSONString("当量系数参照表未上传");
		    	  System.out.println(4);
				   HttpReturn.reponseBody(response, json);
		    	  
		    	  return null;
		      }
		      GFCoefficient coefficient = gfCoefficientDao.selectByYearPlace(year,place);
		      if(coefficient==null){
		      	  String json = JSON.toJSONString("gf系数未计算");
		      	  System.out.println(3);
				   HttpReturn.reponseBody(response, json);
		    	  
		    	 
		    	  return null;
		      }
		      
			  List<LargeTravelTax>largeTravelTaxList = new ArrayList<LargeTravelTax>();
			  List<ClassTravelTax>classTravelTaxList = new ArrayList<ClassTravelTax>();
			  TaxCaculateUtil.getSubTravelTax(subTaxList,year,place,subTravelTaxList,gfReference,coefficient);
			  List<AllCodeDictionary>largeLineList = codeDictionaryDao.getLargeLine();
			   if(largeLineList.size()<1){
 			     	  String json = JSON.toJSONString("行业代码库未上传");
					   HttpReturn.reponseBody(response, json);
			    	  
			    	  return null;
			      }
			  
			  
			  List<AllCodeDictionary>classLineList = codeDictionaryDao.getClassLine();
			  List<LargeAndClassDictionary>classDidctionary = classDao.findAll();
			  TaxCaculateUtil.getLargeTravelTax(largeLineList,place,year,largeTravelTaxList,subTravelTaxList);
			  TaxCaculateUtil.getClassTravelTax(largeTravelTaxList,classLineList,classDidctionary,place,year,classTravelTaxList); 
			  for(SubTravelTax subTax:subTravelTaxList){
				  subTravleTaxDao.save(subTax);
			  }  
			  for(LargeTravelTax largeTax:largeTravelTaxList){
				  largeTravelTaxDao.save(largeTax);
			  }
			  for(ClassTravelTax classTax:classTravelTaxList){
				  classTravelTaxDao.save(classTax);
			  }
			    
			}
	 
		return  subTravelTaxList;
		 
	}

}
