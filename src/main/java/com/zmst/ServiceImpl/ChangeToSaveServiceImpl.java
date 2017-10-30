package com.zmst.ServiceImpl;

import javax.annotation.Resource;

import com.zmst.Domain.Gdp;
import com.zmst.IDao.GdpMapper;
import com.zmst.Service.ChangeToSaveService;

public class ChangeToSaveServiceImpl implements ChangeToSaveService{

	@Resource
	private GdpMapper GdpDao;
	@Override
	public int ChangeGdp(Gdp gdp) {
		GdpDao.updateByPrimaryKey(gdp);
		return 0;
	}

}
