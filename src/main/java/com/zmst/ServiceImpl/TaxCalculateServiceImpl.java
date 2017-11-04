package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.*;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.LandTax;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.SubTax;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.LandTaxMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.Service.TaxCalculateService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.TaxCaculateUtil;
@Service("taxService")
public class TaxCalculateServiceImpl implements TaxCalculateService {

	@Resource
	private SubTaxMapper subTaxDao;
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
	
	
	public List<SubTax> getSubTaxt(String year, String place, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<SubTax>subTaxList = null; 
		subTaxList=subTaxDao.findSubTaxByYearPlace(year,place);
		
		if(subTaxList.size()==0){//如果总税收未计算则进行计算处理
			subTaxList = new ArrayList<SubTax>();  
			  List<LandTax>landTaxList= landTaxDao.getAllLandTax(year,place);//获得当年总地税
			  System.out.println(landTaxList.size());
			  if(landTaxList.size()==0){
				   String json = JSON.toJSONString("地税表未上传");
				   HttpReturn.reponseBody(response, json);
				   
				  return null;
			  }
			  
			  List<CentralTax>centralTaxlist=	centralTaxDao.getAllCentralTax(year,place);//获得当年国税
			  
			  if(centralTaxlist.size()==0){
				   String json = JSON.toJSONString("国税表未上传");
				   HttpReturn.reponseBody(response, json);
 
				  return null;
			  }
			  
			  List<LargeTax>largeTaxList = new ArrayList<LargeTax>();
			  List<ClassTax>classTaxList = new ArrayList<ClassTax>();
			  TaxCaculateUtil.getSubTax(subTaxList,year,place,landTaxList,centralTaxlist);
			  List<AllCodeDictionary>largeLineList = codeDictionaryDao.getLargeLine();
			  
			  if(largeLineList.size()==0){
				  
				   String json = JSON.toJSONString("代码库未上传");
				   HttpReturn.reponseBody(response, json);
 
			  }
			  
			  List<AllCodeDictionary>classLineList = codeDictionaryDao.getClassLine();
			  List<LargeAndClassDictionary>classDidctionary = classDao.findAll();
			  TaxCaculateUtil.getLargeTax(largeLineList,place,year,largeTaxList,subTaxList);
			 TaxCaculateUtil.getClassTax(largeTaxList,classLineList,classDidctionary,place,year,classTaxList);
			  
		  for(SubTax subTax:subTaxList){
			  subTaxDao.save(subTax);
		  }
		  
			  for(LargeTax largeTax:largeTaxList){
				  largeTaxDao.save(largeTax);
			  }
			  for(ClassTax classTax:classTaxList){
				  classTaxDao.save(classTax);
			  }
			    
			}
	 
		return  subTaxList;
		 
	}

}
