package com.niit.angular.dao;

import java.util.List;
import com.niit.angular.model.User;


public interface UserDAO {
	
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(String emailId);
	public User get(String emailId);
	public List<User> list();
	public User validate(String emailId, String password);
}
