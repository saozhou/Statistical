package com.zmst.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.zmst.IDao.AllCodeDictionaryMapper;
import com.zmst.IDao.CentralTaxMapper;
import com.zmst.IDao.GFCoefficientMapper;
import com.zmst.IDao.GFReferenceMapper;
import com.zmst.IDao.GdpMapper;
import com.zmst.IDao.LandTaxMapper;
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
import com.zmst.Service.FileDownloadService;
import com.zmst.Tools.FileDownloadUtil;


/**
 * 
 * @author Zhou
 *文件下载
 *数据编码
 *gdp表    1   小类税收           5      大类gdp贡献   9      大类税收贡献  12     gf系数参照表   15
 *地税          2   小类gdp    6     门类gdp贡献     10     门类税收贡献   13    代码库  16
 *国税          3   小类旅游税收          7    产业gdp贡献       11    产业税收贡献   14
 *gf系数   4   小类旅游gdp   8 
 *
 *
 *
 *
 *
 */
@Service("fileDownLoadService")
public class FileDownloadServiceImpl implements FileDownloadService {

	@Resource
	private GdpMapper gdpDao;
	@Resource
	private LandTaxMapper landTaxDao;
	@Resource
	private CentralTaxMapper centralTaxDao;
	@Resource 
	private GFCoefficientMapper gfCoefficientDao;
	@Resource
    private SubTaxMapper subTaxDao;
	@Resource
	private SubGdpMapper subGdpDao;
	@Resource
	private SubTravelTaxMapper subTravelTaxDao;
	@Resource
	private SubTravelGdpMapper subTraveGdpDao;
	@Resource
	private TravelLargeTaxContributeMapper largeTaxContributeDao;
	@Resource
	private TravelLargeGdpContributeMapper largeGdpContributeDao;
	@Resource
	private TravelClassGdpContributeMapper classGdpContributeDao;
	@Resource
	private TravelClassTaxContributeMapper classTaxContributeDao;
	@Resource
	private TravelIndustryTaxContributeMapper industryTaxContributeDao;
	@Resource
	private TravelIndustryGdpContributeMapper industryGdpContributeDao;
	@Resource
	private GFReferenceMapper gfReferenceDao;
	@Resource
	private AllCodeDictionaryMapper allCodeDao;
	@Override
	public boolean export(String year, String place, HttpServletResponse response,int tableNumber) {
		// TODO Auto-generated method stub
		List<Object> objectList = new ArrayList<Object>();
		String tableName ="GdpBiao";
		String sheetname = null;
		// TODO Auto-generated method stub		 
		 FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
		if(tableNumber==1){
			 objectList = gdpDao.find(year, place);
		}else if(tableNumber==2){
			objectList =  landTaxDao.find(year,place);
		}else if(tableNumber==3){
			objectList =  centralTaxDao.find(year,place);
		}else if(tableNumber==4){
			objectList =  gfCoefficientDao.find(year,place);
		}else if(tableNumber==5){
			objectList =  subTaxDao.find(year,place);
		}else if(tableNumber==6){
			objectList =  subGdpDao.find(year,place);
		}else if(tableNumber==7){
			objectList =  subTravelTaxDao.find(year,place);
		}else if(tableNumber==8){
			objectList =  subTraveGdpDao.find(year,place);
		}else if(tableNumber==9){
			objectList =  largeGdpContributeDao.find(year,place);
		}else if(tableNumber==10){
			objectList =  classGdpContributeDao.find(year,place);
		}else if(tableNumber==11){
			objectList =  industryGdpContributeDao.find(year,place);
		}else if(tableNumber==12){
			objectList =  largeTaxContributeDao.find(year,place);
		}else if(tableNumber==13){
			objectList =  classTaxContributeDao.find(year,place);
		}else if(tableNumber==14){
			objectList =  industryTaxContributeDao.find(year,place);
		}else if(tableNumber==15){
			objectList =  gfReferenceDao.find(year,place);
		}else if(tableNumber==16){
			objectList =  allCodeDao.find();
		}
		 
		    sheetname = year+place+tableName;
			HSSFWorkbook wb = fileDownloadUtil.generateExcel();
			wb = fileDownloadUtil.generateSheet(wb, sheetname, objectList);
			fileDownloadUtil.export(sheetname,wb, response);
			return true;
	 
	}

}
