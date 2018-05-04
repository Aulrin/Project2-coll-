package com.niit.angular.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.angular.dao.UserDAO;
import com.niit.angular.model.User;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	public boolean save(User user) {
		try {
			if(user.getRole()==null || user.getRole()==' ')
			{
				user.setRole('S');
			}
			user.setStatus('N');
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	}

	public boolean delete(String emailId) {
		try {
			sessionFactory.getCurrentSession().delete(emailId,User.class);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	}

	public User get(String emailId) {
			return	(User) sessionFactory.getCurrentSession().get(User.class,emailId);
	}

	public List<User> list() {
		return sessionFactory.getCurrentSession().createCriteria("from User").list();
	}

	public User validate(String emailId, String password) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("emailId", emailId))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}
	

}
