package com.zmst.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zmst.Domain.User;
import com.zmst.IDao.UserMapper;
import com.zmst.Service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userDao;
	
	 
/*
 * (non-Javadoc)
 * @see com.zmst.Service.UserService#findByUser(com.zmst.Domain.User)
 * 根据用户对象查找用户
 */
	public User findByUser(User users) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUser(users);
		return user;
	}

/**
 * 获得相同用户名
 */
	public boolean getSameName(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUserName(username);
		if(user!=null){
			return true;
		}else{
			return false;
		}
		 
	}

/**
 * 添加用户保存
 */
	public void login(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

/**
 * 通过用户名查找用户
 */
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUserName(username);
		return user;
	}

	/**
	 * 用户信息修改
	 */
   public void userUpdate(String username, String password, String address) {
	// TODO Auto-generated method stub
	    User user = new User();
		user.setUspassword(password);
		user.setUsname(username);
		user.setUsplace(address);
		userDao.userUpdate(user);
    }

	public void userPowerUpdate(User user) {
		// TODO Auto-generated method stub
		userDao.userPowerUpdate(user);
	}

	public void userDelet(String username) {
		// TODO Auto-generated method stub
		userDao.userDelete(username);
	}

}
