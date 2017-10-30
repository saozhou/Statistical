package com.zmst.Controller;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zmst.Domain.AllCodeDictionary;
import com.zmst.Domain.CentralTax;
import com.zmst.Domain.GFCoefficient;
import com.zmst.Domain.GFReference;
import com.zmst.Domain.Gdp;
import com.zmst.Domain.LandTax;
import com.zmst.Service.DeleteService;
import com.zmst.Service.FileUploadService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.Json2Map;
import com.zmst.Tools.POIUtil;


import javax.annotation.Resource;

/**
 * 
 * @author Zhou
 *文件上传控制类
 *codeDictionary  行业代码库上传  
 */
@Controller
@RequestMapping("/FileUpload")
public class FileUploadController {

	  static int i=0;
	 @Resource 
	 private FileUploadService  uploadService;
	 @Resource
	 private DeleteService deleteService;
	/**
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @throws Exception
	 * 行业代码库上传
	 */
	@RequestMapping(value="/codeDictionaryUpload",method=RequestMethod.POST)  
    @ResponseBody  
    public void codeDictionaryUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{  
		  
			String path = "D:\\Users";
			String fileLevel = file.getOriginalFilename();
			fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
			String fileName ="行业代码库"+fileLevel;
	        String allPath = path+"\\"+fileName;
	        File dir = new File(path,fileName);          
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        //MultipartFile自带的解析方法  
	        file.transferTo(dir);    
	        HttpReturn.reponseBody(response, "1");
	        List<List<String>>list	= null;
	        if("xls".equals(fileLevel)) list=POIUtil.readXls(allPath);
	        if("xlsx".equals(fileLevel)) list = POIUtil.readXlsx(allPath);
	        List<AllCodeDictionary>codeDictionary =  uploadService.changeLineExcel(list);
	      
	       
	        List<AllCodeDictionary>oldCodeDictionary = uploadService.selectAllData();
	        if(oldCodeDictionary!=null){
	        	uploadService.deleteCodeDictionary();
	        }
	        uploadService.saveCodeDictionary(codeDictionary);
			 
	}
	
	/**
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @throws Exception
	 * 行业特征系数对照上传
	 */
	@RequestMapping(value="/gfReferenceUpload",method=RequestMethod.POST)  
    @ResponseBody  
    public void GFReferenceUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{  
		    HttpSession session = request.getSession();		 		
		    String fileName=null;
			String city =  null;
			String county = null;
			String year = null; 
			String place=null;
			String fileLevel = file.getOriginalFilename();
			city = (String) session.getAttribute("city");
			county= (String) session.getAttribute("county");
			year=(String)session.getAttribute("year");
			 
			 
			fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
			if(county!=null){
				 place=county;
				 
			}else{
				 
			    place=city;
			}
			fileName = year+place+"行业分类特征系数对照表."+fileLevel;
			String path = "D:\\Users";
			 
			fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
		 
	        String allPath = path+"\\"+fileName;
	        File dir = new File(path,fileName);          
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        //MultipartFile自带的解析方法  
	        file.transferTo(dir);    
	        HttpReturn.reponseBody(response, "1");
	        List<List<String>>list	= null;
	        if("xls".equals(fileLevel)) list=POIUtil.readXls(allPath);
	        if("xlsx".equals(fileLevel)) list = POIUtil.readXlsx(allPath);
	        List<GFReference>gfReference =  uploadService.changeGFReferenceExcel(list,place,year);
	        List<GFReference>oldgfReference = uploadService.selectGFReferenceByYP(place,year);
	        
	        if(oldgfReference!=null){
	        	uploadService.deleteGFReference(year,place);
	        	deleteService.DeleteTravelTax(year, place);  
				deleteService.DeleteTravelGdp(year, place);
	        }
	        uploadService.saveGFReference(gfReference);
			 
	}
	
	 /**
	  * 
	  * @param request
	  * @param response
	  * @return
	  * 当量系数计算
	  * 传入值 
	  */	 
		@RequestMapping(value="/gfCorfficientmath",method=RequestMethod.POST)  
		public void GCorfficientMath(HttpServletRequest request,HttpServletResponse response,@RequestBody String json){
		  Map<String, String> map = Json2Map.JSON2Map(json);
		  DecimalFormat df = new DecimalFormat("0.00000");	 
		  GFCoefficient corfficient = new GFCoefficient();	 	 
		  HttpSession session = request.getSession();
		  
	  		
	 
			String city =  null;
			String county = null;
			String year = null; 
			String place=null;
		 
			city = (String) session.getAttribute("city");
			county= (String) session.getAttribute("county");
			year=(String)session.getAttribute("year");
		 
		  
		  if(county!=null){
			  place = county;
		  }else{
			  place=city;
		  }
		  
	      boolean corfficientExits = uploadService.getExitsCorfficient(year,place);  	
	      
	      Double yearAvergeSpendDay =  Double.valueOf(map.get("yearAvergeSpendDay"));//年均消费
		  Double avergeSpend = Double.valueOf(map.get("avergeSpend"));//输入 人均消费
		  Double travelSpendday = Double.valueOf(map.get("travelSpendday"));//输入旅游消费天数
		  Double townAvergeSpend = Double.valueOf(map.get("townAvergeSpend"));//输入城乡居民消费
		  Double livePeople =Double.valueOf(map.get("livePeople"));//输入常驻人口
		  Double dayTravlePeople = Double.valueOf(map.get("dayTravlePeople"));//输入游客总数
		  Double allTravelPeople;//游客总数
		  Double middleNumber;
		  Double Gcorfficient;
		  Double lastNumber;
		  Double Fcorfficient;
		  allTravelPeople = dayTravlePeople/(travelSpendday-1); 
		  lastNumber = (travelSpendday*allTravelPeople)/yearAvergeSpendDay;
		  middleNumber = allTravelPeople*avergeSpend/townAvergeSpend;
		  Fcorfficient= lastNumber/(lastNumber+livePeople);	 
		  Gcorfficient =middleNumber/(middleNumber+livePeople);	  	 
		  Fcorfficient=Double.valueOf(df.format(Fcorfficient)); 
		  Gcorfficient=Double.valueOf(df.format(Gcorfficient)); 
		  corfficient.setGsta(Gcorfficient);
		  corfficient.setFsta(Fcorfficient);
		  corfficient.setAvspend(avergeSpend);
		  corfficient.setSpday(travelSpendday);
		  corfficient.setTpsum(dayTravlePeople);
		  corfficient.setLipeople(livePeople);
		  corfficient.setCpaspend(townAvergeSpend);
		  corfficient.setYsday(yearAvergeSpendDay);
		  corfficient.setPlace(place);
		  corfficient.setYear(year);
		  
		  if(corfficientExits){
			  uploadService.update(corfficient,year,place);
			  deleteService.DeleteTravelTax(year, place);  
			  deleteService.DeleteTravelGdp(year, place);
		  }
		  else{
			  uploadService.save(corfficient);	   
		  }
		  
		}
	
		/**
		 * 
		 * @param file
		 * @param request
		 * @return
		 * @throws Exception
		 * 地税表文件上传及存入数据库
		 */
		
		@RequestMapping(value="/landTaxUpload",method=RequestMethod.POST)  
	    @ResponseBody  
	   
	    public void landTaxUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{  
			response.setContentType("text/html;charset=utf-8");
				String path = "D:\\Users";  
				  HttpSession session = request.getSession();		 		
				    String fileName=null;
					String city =  "张家界";
					String county = null;
					String year = "2017"; 
					String place=null;
					String fileLevel = file.getOriginalFilename();
					//city = (String) session.getAttribute("city");
					//county= (String) session.getAttribute("county");
					//year=(String)session.getAttribute("year");
				
				int matchingWay=1;//Integer.valueOf(request.getParameter("matchingway"));
				if(county!=null){
					 place=county;
				}else{
					place=city;
				}
				
			  fileName = year+place+"地税表."+fileLevel;
				fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
							
				 
		        String allPath = path+"\\"+fileName;
		        File dir = new File(path,fileName);          
		        if(!dir.exists()){  
		            dir.mkdirs();  
		        }  
		        //MultipartFile自带的解析方法  
		        file.transferTo(dir);    
		        
		        List<List<String>>list	= null;
		        if("xls".equals(fileLevel)) list=POIUtil.readXls(allPath);
		        if("xlsx".equals(fileLevel)) list = POIUtil.readXlsx(allPath);
		        List<LandTax>landTax =  uploadService.changeLandTax(list,year,place,matchingWay,response);
		        List<LandTax>oldLandTax = null; 
		        oldLandTax=uploadService.getLandTax(year,place);
		       if(landTax!=null){
		    	   
		       
		        if(oldLandTax!=null){     	      	      	  
		        	  uploadService.updateLandTax(landTax,year,place);
					  deleteService.DeleteTravelTax(year, place);  
					  deleteService.DeleteTravelGdp(year, place);
					  deleteService.DeleteGDP(year,place);
					  deleteService.DeleteTax(year,place);
		        }else{
		        	uploadService.saveLandTax(landTax);
		        }
				 
		       }
			 
				
		}
	 
		
		/**
		 * 
		 * @param file
		 * @param request
		 * @return
		 * @throws Exception
		 * 国税表文件上传及存入数据库
		 */
		@RequestMapping(value="/centralTaxUpload",method=RequestMethod.POST)  
	    @ResponseBody  
	   
	    public void centralTaxUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{  
			 
			response.setContentType("text/html;charset=utf-8");
				String path = "D:\\Users";  
				  HttpSession session = request.getSession();		 		
				    String fileName=null;
					String city =  null;
					String county ="张家界";// null;
					String year ="2017"; 
					String place=null;
					String fileLevel = file.getOriginalFilename();
					//city = (String) session.getAttribute("city");
					//county= (String) session.getAttribute("county");
					//year=(String)session.getAttribute("year");
				int matchingWay=  2;//Integer.valueOf(request.getParameter("matchingway"));//匹配fan
				if(county!=null){
					 place=county;
				}else{
					place=city;
				}
				
				  fileName = year+place+"国税表."+fileLevel;
				fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
							
				 
		        String allPath = path+"\\"+fileName;
		        File dir = new File(path,fileName);          
		        if(!dir.exists()){  
		            dir.mkdirs();  
		        }
		        
		        //MultipartFile自带的解析方法  
		        file.transferTo(dir);    
		        List<List<String>>list	= null;
		        if("xls".equals(fileLevel)) list=POIUtil.readXls(allPath);
		        if("xlsx".equals(fileLevel)) list = POIUtil.readXlsx(allPath);
		        
		        
		        List<CentralTax>centralTax =  uploadService.changeCentralTax(list,year,place,matchingWay,response,i);
		        List<CentralTax>oldCentralTax = null; 
		        oldCentralTax=uploadService.getCentralTax(year,place);
		        
		        
		        
		      if(centralTax!=null){
		        if(oldCentralTax!=null){     	      	      	  
		        	  uploadService.updateCentralTax(centralTax,year,place);
		        	  deleteService.DeleteTravelTax(year, place);  
					  deleteService.DeleteTravelGdp(year, place);
					  deleteService.DeleteGDP(year,place);
					  deleteService.DeleteTax(year,place);
		        }else{
		        	uploadService.saveCentralTax(centralTax);
		        }
		      }
				 
			 
				
		}
		
		/**
		 * 
		 * @param file
		 * @param request
		 * @return
		 * @throws Exception
		 * gdp表
		 */
		@RequestMapping(value="/gdpUpload",method=RequestMethod.POST)  
	    @ResponseBody  
	   
	    public void gdpUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{  
			 
			String path = "D:\\Users";  
			  HttpSession session = request.getSession();		 		
			    String fileName=null;
				String city =  null;
				String county ="张家界";// null;
				String year ="2017"; 
				String place=null;
				String fileLevel = file.getOriginalFilename();
				//city = (String) session.getAttribute("city");
				//county= (String) session.getAttribute("county");
				//year=(String)session.getAttribute("year");
		 
			if(county!=null){
				 place=county;
			}else{
				place=city;
			}
				  fileName = year+place+"Gdp."+fileLevel;
				fileLevel=fileLevel.substring(fileLevel.indexOf(".")+1, fileLevel.length());
							
				 
		        String allPath = path+"\\"+fileName;
		        File dir = new File(path,fileName);          
		        if(!dir.exists()){  
		            dir.mkdirs();  
		        }  
		        //MultipartFile自带的解析方法  
		        file.transferTo(dir);    
		        HttpReturn.reponseBody(response, "1");
		        List<List<String>>list	= null;
		        if("xls".equals(fileLevel)) list=POIUtil.readXls(allPath);
		        if("xlsx".equals(fileLevel)) list = POIUtil.readXlsx(allPath);
		        System.out.println(list.size());
		        List<Gdp>gdpList =  uploadService.changeGdp(list,year,place);
		        List<Gdp>oldGdpList = null; 
		        oldGdpList=uploadService.getOldGdp(year,place);
		      
		        if(oldGdpList!=null){     	     
		        	  deleteService.DeleteTravelGdp(year, place);
					  deleteService.DeleteGDP(year,place);
		        	  uploadService.updateGdp(gdpList,year,place);
		        	  uploadService.gdpAnalyze(gdpList, year, place);
		        	  
		        }else{
		        	uploadService.saveGdp(gdpList);
		        }
				 
			 
				
		}
	
}
