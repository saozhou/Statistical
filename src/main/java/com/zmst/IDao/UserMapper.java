package com.zmst.IDao;

import com.zmst.Domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer usid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer usid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByPrimaryKey(int u);
 
	User selectByUser(User users);//根据用户对象查询用户数据

	User selectByUserName(String username);//查找相同用户名的对象

	void save(User user);//保存用户对象

	void userUpdate(User user);//用户信息修改

	void userPowerUpdate(User user);//用户权限修改

	void userDelete(String username);//用户删除
}