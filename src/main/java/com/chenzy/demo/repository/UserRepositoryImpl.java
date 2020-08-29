package com.chenzy.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.chenzy.demo.domain.User;

/**
 * 
 * @author chensharp
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

	private  static AtomicLong counterId = new AtomicLong();
	
	private final ConcurrentMap<Long ,User> userConcurrentMap =new ConcurrentHashMap<Long,User>();
	
	@Override
	public User saveOrUpdateUser(User user) {
		Long id =user.getId();
        if (id==null ){
            id=counterId.incrementAndGet();
            user.setId(id);
        }
        this.userConcurrentMap.put(id,user);
        return user;
		
	}

	@Override
	public void deleteUsere(Long id) {
		// TODO Auto-generated method stub
		this.userConcurrentMap.remove(id);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return this.userConcurrentMap.get(id);
	}

	@Override
	public List<User> userList() {
		// TODO Auto-generated method stub
		return new ArrayList<User>(this.userConcurrentMap.values());
	}
	

}
