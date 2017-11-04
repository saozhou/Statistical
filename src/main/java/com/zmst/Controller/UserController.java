package com.zmst.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zmst.Domain.User;
import com.zmst.Service.UserService;
import com.zmst.Tools.HttpReturn;
import com.zmst.Tools.Json2Map;

/**
 * 
 * @author Zhou 用户控制类
 */
@Controller
@RequestMapping("/User")
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 
	 * @param request
	 * @param model
	 * @return 用户登陆
	 */
	@RequestMapping(value = "/userindex", method = RequestMethod.POST)
	@ResponseBody
	public void index(HttpServletRequest request, @RequestBody String json, HttpServletResponse response) {

		Map<String, String> map = Json2Map.JSON2Map(json);
		response.setContentType("text/html;charset=utf-8");
		int i = 0;

		String username = map.get("username");
		String password = map.get("password");
		String userprower = map.get("userpower");
		User users = new User();
		users.setUsname(username);
		users.setUspassword(password);
		users.setUspower(userprower);

		User user = this.userService.findByUser(users);
		if (user == null) {
			i = 1;
			HttpReturn.reponseBody(response, "IndexFail");

		}
		if (i == 0) {
			HttpSession session = request.getSession();
			session.setAttribute("datainsert", user.getDainsert());
			session.setAttribute("datamath", user.getDamath());
			session.setAttribute("datacheck", user.getDacheck());
			session.setAttribute("dataserch", user.getDasearch());
			session.setAttribute("year", map.get("year"));
			String jsons = String.valueOf(user.getDainsert()) + String.valueOf(user.getDamath())
					+ String.valueOf(user.getDasearch()) + String.valueOf(user.getDacheck());
			if (user.getUspower() == 1) {
				HttpReturn.reponseBody(response, jsons);

			} else if (user.getUspower() == 2) {
				HttpReturn.reponseBody(response, jsons);

			} else {

				String city = map.get("city");
				if (user.getUsplace().equals(city)) {

					String county = map.get("county");
					String province = map.get("province");
					session.setAttribute("province", province);
					session.setAttribute("county", county);
					session.setAttribute("city", city);
					HttpReturn.reponseBody(response, jsons);

				} else {
					 		HttpReturn.reponseBody(response, "failchoise");
				}

			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return 用户添加
	 */
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request, @RequestBody String json, HttpServletResponse response) {
		Map<String, String> map = Json2Map.JSON2Map(json);
		String username = map.get("username");
		int i = 0;
		int userpower = Integer.valueOf(map.get("userpower"));
		boolean usernameexit = userService.getSameName(username);

		String place = map.get("county");
		if (place == null) {
			place = map.get("city");
		}

		if (usernameexit) {
			HttpReturn.reponseBody(response, "用户名已存在");
			i = 1;
		}
		if (i == 0) {
			User user = new User();
			if (userpower == 2) {
				user.setUspassword(map.get("password"));
				user.setUsname(username);
				user.setUspower("2");
				user.setDacheck(1);
				user.setDainsert(1);
				user.setDamath(1);
				user.setDasearch(1);
				userService.login(user);

			} else {
				user.setUspassword(map.get("password"));
				user.setUsname(username);
				user.setUsplace(place);
				user.setDacheck(Integer.valueOf(map.get("datacheck")));
				user.setDainsert(Integer.valueOf(map.get("datainsert")));
				user.setDamath(Integer.valueOf(map.get("datamath")));
				user.setDasearch(Integer.valueOf(map.get("datasearch")));
				user.setUspower("3");
				userService.login(user);
			}
			HttpReturn.reponseBody(response, "添加成功");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param username
	 *            查找用户
	 */
	@RequestMapping(value = "/userfind", method = RequestMethod.POST)
	@ResponseBody
	public void findUser(HttpServletRequest request, HttpServletResponse response, @RequestBody String username) {
		User user = new User();
		System.out.println(username);
		user = userService.findByUserName(username);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(user);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		String json = JSON.toJSONString(jsonObject.toString());
		HttpReturn.reponseBody(response, json);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param username
	 *            获取全部用户
	 */
	@RequestMapping(value = "/alluser", method = RequestMethod.POST)
	@ResponseBody
	public void allUser(HttpServletRequest request, HttpServletResponse response) {
		List<User> user = userService.getAllUser();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		String json = JSON.toJSONString(user);
		HttpReturn.reponseBody(response, json);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 *            用户信息修改
	 */
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	@ResponseBody
	public void userUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
		Map<String, String> map = Json2Map.JSON2Map(json);
		String username = map.get("username");
		String password = map.get("password");
		String address = map.get("address");
		userService.userUpdate(username, password, address);
		HttpReturn.reponseBody(response, "保存成功");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 *            用户权限修改
	 */
	@RequestMapping(value = "/userpowerupdate", method = RequestMethod.POST)
	@ResponseBody
	public String userPowerUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
		User user = new User();
		Map<String, String> map = Json2Map.JSON2Map(json);
		String username = map.get("username");
		String datainert = map.get("datainsert");
		String datamath = map.get("datamath");
		String datasearch = map.get("datasearch");
		String datacheck = map.get("datacheck");
		user.setUsname(username);
		user.setDainsert(Integer.valueOf(datainert));
		user.setDamath(Integer.valueOf(datamath));
		user.setDasearch(Integer.valueOf(datasearch));
		user.setDacheck(Integer.valueOf(datacheck));
		userService.userPowerUpdate(user);
		return "modifySuccess";

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 *            用户删除
	 */
	@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
	@ResponseBody
	public String userDelet(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
		Map<String, String> map = Json2Map.JSON2Map(json);
		String username = map.get("username");
		User user = new User();
		user = userService.findByUserName(username);
		if (user.getUspower() == 1) {
			return "manager can not delete";
		}
		userService.userDelet(username);
		System.out.println(username);
		return "deleteSuccess";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 *            Session更改
	 */
	@RequestMapping(value = "/sessionchange", method = RequestMethod.POST)
	@ResponseBody
	public String SessionChange(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
		Map<String, String> map = Json2Map.JSON2Map(json);
		Set<String> key = map.keySet();
		HttpSession session = request.getSession();
		String value = null;
		for (String temp : key) {
			value = map.get(temp);
			session.setAttribute(temp, value);
		}
		return json;
	}
}
