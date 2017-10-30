package com.zmst.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;
import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GFReferenceMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.LandTaxMapper;
import com.zmst.Service.ChangeToSaveService;
@Service("change2saveService")
public class ChangeToSaveServiceImpl implements ChangeToSaveService{

	@Resource
	private GdpMapper GdpDao;
	@Resource
	private CentralTaxMapper centralTaxDao;
	@Resource
	private GFReferenceMapper	gfReferenceDao;
	@Resource
	private GFCoefficientMapper gfCoefficientDao;
	@Resource
	private LandTaxMapper	landTaxDao;
	@Resource
	private AllCodeDictionaryMapper AllCodeDao;
	
	@Override
	public int ChangeGdp(Gdp gdp) {
		GdpDao.updateByYearPlace(gdp);
		return 0;
	}
	@Override
	public int ChangeCentraTax(CentralTax centralTax) {
		centralTaxDao.updateByPrimaryKeySelective(centralTax);
		return 0;
	}
	@Override
	public int ChangeGFRe(GFReference gfReference) {
		gfReferenceDao.updateByPrimaryKeySelective(gfReference);
		return 0;
	}
	@Override
	public int ChangeGFCo(GFCoefficient gfCoefficient) {
		gfCoefficientDao.updateByPrimaryKeySelective(gfCoefficient);
		return 0;
	}
	@Override
	public int ChangeLandTax(LandTax landTax) {
		landTaxDao.updateByPrimaryKeySelective(landTax);
		return 0;
	}
	@Override
	public int ChangeAllCode(AllCodeDictionary AllCode) {
		AllCodeDao.updateByPrimaryKeySelective(AllCode);
		return 0;
	}

}
