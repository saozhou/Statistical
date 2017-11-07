package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.*;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTax;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.GdpMiddleTable;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.ClassGdpMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.GdpMiddleTableMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.Service.GdpCalculateService;
import com.zmst.Tools.GdpAnalyze;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.TaxCaculateUtil;
@Service("gdpService")
public class GdpCalculateServiceImpl implements GdpCalculateService {

	@Resource
	private GdpMapper gdpDao;
	@Resource
	private SubGdpMapper subGdpDao;
	@Resource
	private LargeGdpMapper largeGdpDao;
	@Resource
	private LargeAndClassDictionaryMapper largeAndClassDao;
	@Resource
	private LargeTaxMapper largeTaxDao;
	@Resource
	private GdpMiddleTableMapper gdpMiddleDao;
	@Resource
	private SubTaxMapper subTaxDao;
	@Resource
	private AllCodeDictionaryMapper codeDictionaryDao;
	@Resource
	private ClassGdpMapper classGdpDao;
 
	
	public List<SubGdp> findSubGdpByYearPlace(String year, String place) {
		// TODO Auto-generated method stub
		List<SubGdp>list = subGdpDao.findByYearPlace(year,place);
		return list;
	}
	
	/**
	 * 大类
	 * 小类
	 * 门类
	 */
	public List<SubGdp> getSubgdp(String year, String place, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<LargeGdp>largeGdpList = new ArrayList<LargeGdp>();
		
	 
		List<SubGdp>subGdpList = new ArrayList<SubGdp>();
		
		 
		
		//largeGdpList = largeGdpDao.findByYearPlace(year,place);
		
		
	    List<SubTax>subTaxList = subTaxDao.findSubTaxByYearPlace(year, place);
	    
	    if(subTaxList.size()==0){
	    	   String json = JSON.toJSONString("小类税收未计算");
	    	   HttpReturn.reponseBody(response, json);
	    
	    	return null;
	    }
		
		List<Gdp>gdpList = gdpDao.getAllGdp(year,place);
		
		
		
		  if(gdpList.size()==0){
			  
			  String json = JSON.toJSONString("gdp表未上传");
	    	   HttpReturn.reponseBody(response, json);

		    	return null;
		    }
		  
		
		 GdpAnalyze.getMiddleLargeGdp(gdpList,largeGdpList,year,place); 
		  
		List<Gdp>middleGdpList = GdpAnalyze.getMiddleGdp(gdpList,year,place);
		
		
		List<LargeTax> largeTaxList =  largeTaxDao.getByYearPlace(year,place);
		
		
        List<ClassGdp>classGdpList = new ArrayList<ClassGdp>();
		  
		  
	    List<AllCodeDictionary>classLineList = codeDictionaryDao.getClassLine();
	    
		  
	    List<LargeAndClassDictionary>largeAndClassDictionary = largeAndClassDao.findAll();
	    
	    
		GdpAnalyze.getLargeGdp(year,place,middleGdpList,largeGdpList,largeTaxList);//获得大类gdp
		
		
	    GdpAnalyze.getSubGdp(gdpList,subGdpList,subTaxList,largeGdpList,largeTaxList,year,place);//小类gdp
		
		  
	    GdpAnalyze.getClassGdp(largeGdpList,classLineList,largeAndClassDictionary,place,year,classGdpList);
		 
	    
	    largeGdpDao.deleteByYearPlace(year, place);
	   for(LargeGdp largegdp:largeGdpList){
		    largeGdpDao.save(largegdp);
		   //System.out.println(largegdp.getLacode()+" "+largegdp.getLaname()+" "+largegdp.getLagdp());
		}
		 for(SubGdp sub:subGdpList){
	    	subGdpDao.save(sub);
		 }
		for(ClassGdp cla:classGdpList){
			classGdpDao.save(cla);
			 //System.out.println(cla.getClcode()+" "+cla.getClname()+" "+cla.getClgdp());
		}
		 
		return subGdpList;
	}

}
