package com.zmst.Service;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;

/**
 * 
 * @author Zhou
 *修改保存service
 */
public interface ChangeToSaveService {

public  int ChangeGdp(Gdp gdp);
public int ChangeCentraTax(CentralTax centralTax);
public int ChangeGFRe(GFReference gfReference);
public int ChangeGFCo(GFCoefficient gfCoefficient);
public int ChangeLandTax(LandTax landTax);
public int ChangeAllCode(AllCodeDictionary AllCode);
}
