package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.*;

import com.zmst.Domain.Gdp;
import com.zmst.Domain.GdpMiddleTable;
import com.zmst.Domain.LargeAndClassDictionary;
import com.zmst.Domain.LargeGdp;
import com.zmst.Domain.SubGdp;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.LargeAndClassDictionaryMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.SubGdpMapper;
import com.zmst.Service.GdpCalculateService;
import com.zmst.Tools.GdpAnalyze;
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
	public List<SubGdp> getSubgdp(String year, String place) {
		// TODO Auto-generated method stub
		List<LargeGdp>largeGdpList = new ArrayList<LargeGdp>();
		
		List<GdpMiddleTable>gdpMiddleList = new ArrayList<GdpMiddleTable>();
		
		List<SubGdp>subGdpList = new ArrayList<SubGdp>();
		
		largeGdpList = largeGdpDao.findByYearPlace(year,place);
		
		List<Gdp>gdpList = gdpDao.getAllGdp(year,place);
		
		List<LargeAndClassDictionary>largeAndClass=largeAndClassDao.findAll();
		
		GdpAnalyze.sloveLSMGdp(gdpList,gdpMiddleList,subGdpList,largeAndClass,largeGdpList,year,place);
		
		for(LargeGdp largegdp:largeGdpList){
			largeGdpDao.save(largegdp);
		}
		 for(SubGdp sub:subGdpList){
			 subGdpDao.save(sub);
		 }
		
		 
		return null;
	}

}
