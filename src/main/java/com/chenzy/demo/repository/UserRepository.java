package com.chenzy.demo.repository;

import java.util.List;

import com.chenzy.demo.domain.User;

/**
 * 
 * @author chensharp
 *
 */
public interface UserRepository {
	
	User saveOrUpdateUser(User user); //新增或者修改用户

    void deleteUsere(Long id); //删除用户

    User getUserById(Long id); //根据用户id获取用户

    List<User> userList (); //获取所有用户的列表

}
