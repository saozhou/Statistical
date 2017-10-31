package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.*;
import javax.servlet.http.HttpServletResponse;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.ClassGdp;
import com.zmst.Domain.ClassTravelGdp;
import com.zmst.Domain.ClassTravelTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.LargeTravelGdp;
import com.zmst.Domain.LargeTravelTax;
import com.zmst.Domain.SubGdp;
import com.zmst.Domain.SubTax;
import com.zmst.Domain.SubTravelGdp;
import com.zmst.Domain.SubTravelTax;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.ClassTravelGdpMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GFReferenceMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeTravelGdpMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.IDao.SubTravelGdpMapper;
import com.zmst.Service.TravelGdpCalculateService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.TaxCaculateUtil;
import com.zmst.Tools.TravelGdpCaculate;

@Service("travelGdpCalculateService")
public class TravelGdpCalculateServiceImpl implements TravelGdpCalculateService {

	@Resource
	private SubTravelGdpMapper subTravelGdpDao;
	@Resource
	private SubGdpMapper subGdpDao;
	@Resource
	private GFReferenceMapper gfReferenceDao;
	@Resource
	private GFCoefficientMapper gfCoefficientDao;
	@Resource
	private AllCodeDictionaryMapper codeDictionaryDao;
	@Resource
	private LargeAndClassDictionaryMapper classDao;
	@Resource
	private LargeTravelGdpMapper largeTravelGdpDao;
	@Resource
	private ClassTravelGdpMapper classTravelGdpDao;
	 
	
	public List<SubTravelGdp> findByYearPlace(String year, String place) {
		// TODO Auto-generated method stub
		
		List<SubTravelGdp> subTravelGdp = new ArrayList<SubTravelGdp>();
		subTravelGdp = subTravelGdpDao.findByYearPlace(year,place);
		return subTravelGdp;
	}
	
	
	public List<SubTravelGdp> getSubTravelGdp(String year, String place,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<SubTravelGdp>subTravelGdpList  = new ArrayList<SubTravelGdp>();
		
	    List<SubGdp>subGdpList    = subGdpDao.findByYearPlace(year, place);
	    
	    if(subGdpList.size()==0){
	    	HttpReturn.reponseBody(response, "小类gdp未计算");
	    	return null;
	    }
	    
	    List<GFReference>gfReference=gfReferenceDao.selectByYearAndPlace(year,place);
	    
	    if(gfReference.size()==0){
	    	HttpReturn.reponseBody(response, "当量系数参照表未上传");
	    	return null;
	    }
	    
	    GFCoefficient coefficient = gfCoefficientDao.selectByYearPlace(year,place);
	    
	    if(coefficient.getAvspend()==null){
	    	HttpReturn.reponseBody(response, "gf系数未添加");
	    	return null;
	    }
		
	    List<LargeTravelGdp> largeTravelGdpList = new ArrayList<LargeTravelGdp>();
		 
	    List<ClassTravelGdp>classTravelGdpList = new ArrayList<ClassTravelGdp>();
		
        List<AllCodeDictionary>largeLineList = codeDictionaryDao.getLargeLine();
		
        if(largeLineList.size()<1){
        	HttpReturn.reponseBody(response, "代码库未上传");
            return null;
        }
        
	    List<AllCodeDictionary>classLineList = codeDictionaryDao.getClassLine();
		
	    List<LargeAndClassDictionary>classDidctionary = classDao.findAll();
	    
	    
	    TravelGdpCaculate.getSubTravelGdp(subGdpList,year,place,subTravelGdpList,gfReference,coefficient);
		
	   
		
	    TravelGdpCaculate.getLargeTravelGdp(largeLineList,place,year,largeTravelGdpList,subTravelGdpList);
		
	    TravelGdpCaculate.getClassTravelGdp(largeTravelGdpList,classLineList,classDidctionary,place,year,classTravelGdpList); 
		
	    for(SubTravelGdp subTravelGdp:subTravelGdpList){
	    	subTravelGdpDao.save(subTravelGdp);
		  }  
		  for(LargeTravelGdp largeTravelGdp:largeTravelGdpList){
			  largeTravelGdpDao.save(largeTravelGdp);
			  
		  }
		  for(ClassTravelGdp classTravelDao:classTravelGdpList){
			  classTravelGdpDao.save(classTravelDao);
		  }
		    
		

	return  subTravelGdpList;
		
		 
	}

}
