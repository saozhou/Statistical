package com.zmst.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Zhou
 *文件下载service
 */
public interface FileDownloadService {

	boolean export(String year, String city, HttpServletResponse response, int tableNumber);

}
