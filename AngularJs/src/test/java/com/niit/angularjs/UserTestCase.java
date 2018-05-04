package com.niit.angularjs;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.angular.dao.UserDAO;
import com.niit.angular.model.User;


public class UserTestCase {

	@Autowired
	private static User user;
	
	@Autowired
	private static UserDAO userDAO;
	
	private static AnnotationConfigApplicationContext context;   
	//need to create instance of above only once
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
		user = (User)context.getBean("user");
	}
	
	@Test
	public void addUserTestCase()
	{
		user.setEmailId("Austrin@gmail.com");
		user.setName("Austrin");
		user.setPassword("1234");
		user.setDetails("Velachary");
		Assert.assertEquals("Add User Test Case",true,userDAO.save(user));
		
	}
	
	@Test
	public void updateUserTestCase()
	{
		user = userDAO.get("Au@gmail.com");
		user.setMobile("77777777");
		boolean actual = userDAO.update(user);
	    Assert.assertEquals("Update User", true, actual );
	}
	
	@Test
	public void getUserTestCase()
	{
		
		Assert.assertNotNull("Get User Test Cases", userDAO.get("Au@gmail.com"));
	}
	
	@Test
	public void validateUserTestCase()
	{
	 Assert.assertNotNull("Validate Testcase",userDAO.validate("Austrin@gmail.com","1234"));
	}
}
