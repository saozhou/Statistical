package com.zmst.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;

/**
 * 
 * @author Zhou
 *文件上传service
 */
public interface FileUploadService {

	List<AllCodeDictionary> changeLineExcel(List<List<String>> list);//代码库转换

	List<AllCodeDictionary> selectAllData();

	 

	 
	void deleteCodeDictionary();

	void saveCodeDictionary(List<AllCodeDictionary> codeDictionary);

	List<GFReference> changeGFReferenceExcel(List<List<String>> list, String place, String year);

	List<GFReference> selectGFReferenceByYP(String place, String year);

	void deleteGFReference(String year, String place);

	void saveGFReference(List<GFReference> gfReference);

	boolean getExitsCorfficient(String year, String place);

	void update(GFCoefficient corfficient, String year, String place);

	void save(GFCoefficient corfficient);

	List<LandTax> changeLandTax(List<List<String>> list, String year, String place, int matchingWay, HttpServletResponse response);

	List<LandTax> getLandTax(String year, String place);

	void updateLandTax(List<LandTax> landTax, String year, String place);

	void saveLandTax(List<LandTax> landTax);

	List<CentralTax> changeCentralTax(List<List<String>> list, String year, String place, int matchingWay, HttpServletResponse response, int i);

	List<CentralTax> getCentralTax(String year, String place);

	void updateCentralTax(List<CentralTax> centralTax, String year, String place);

	void saveCentralTax(List<CentralTax> centralTax);

	List<Gdp> changeGdp(List<List<String>> list, String year, String place);

	List<Gdp> getOldGdp(String year, String place);

	void updateGdp(List<Gdp> gdpList, String year, String place);

	void saveGdp(List<Gdp> gdpList);

	void gdpAnalyze(List<Gdp> gdpList, String year, String place);
 

}
