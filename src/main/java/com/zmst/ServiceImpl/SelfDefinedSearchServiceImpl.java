package com.zmst.ServiceImpl;

import javax.annotation.Resource;

import com.zmst.IDao.ClassGdpMapper;
import com.zmst.IDao.ClassTaxMapper;
import com.zmst.IDao.ClassTravelGdpMapper;
import com.zmst.IDao.ClassTravelTaxMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.LargeGdpMapper;
import com.zmst.IDao.LargeTaxMapper;
import com.zmst.IDao.LargeTravelGdpMapper;
import com.zmst.IDao.LargeTravelTaxMapper;
import com.zmst.IDao.SubTaxMapper;
import com.zmst.IDao.SubTravelGdpMapper;
import com.zmst.Service.SelfDefinedSearchService;


@SuppressWarnings("restriction")
public class SelfDefinedSearchServiceImpl implements SelfDefinedSearchService {

	 @Resource
     private ClassGdpMapper classGdpDao;
	 @Resource
	 private ClassTaxMapper classTaxDao;
	 @Resource
	 private ClassTravelGdpMapper classTraverGdpDao;
	 @Resource
	 private ClassTravelTaxMapper classTraverTaxDao;
	 @Resource
	 private GdpMapper subGdpDao;
	 @Resource
	 private SubTaxMapper subTaxDao;
	 @Resource
	 private SubTravelGdpMapper subTravelGdpDao;
	 @Resource
	 private LargeGdpMapper LGdpDao;
	 @Resource
	 private LargeTaxMapper LTaxDao;
	 @Resource
	 private LargeTravelGdpMapper LTravelGdpDao;
	 @Resource
	 private LargeTravelTaxMapper LTravelTaxDao;
	 
	 	
	 
	
}
