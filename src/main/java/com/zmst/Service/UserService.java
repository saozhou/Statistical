package com.zmst.Service;

import com.zmst.Domain.User;
/**
 * 
 * @author Zhou
 *用户service
 */
public interface UserService {

	 

	User findByUser(User users);//根据用户对象查找用户数据

	boolean getSameName(String username);//查找是否有相同的用户名

	void login(User user);//用户保存

	User findByUserName(String username);//通过用户名查找用户

	void userUpdate(String username, String password, String address);//用户信息修改

	void userPowerUpdate(User user);//用户权限修改

	void userDelet(String username);//用户删除

}
