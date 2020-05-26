package com.mycom.dao;

import org.hibernate.query.Query;

import com.mycom.pojo.User;

public class UserDao extends Dao {
	public User getUser(long id) {
		return (User) getSession().get("com.mycom.pojo.User",id);
	}
	
	public User registerUser(User u) throws Exception{
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return u;
	}
	
	public User loginUser(String userEmail, String userPassword, String type) {
		try {
			begin();
			
			Query query = getSession().createQuery("from User where userEmail = :userEmail and userPassword = :userPassword and role = :role");
			query.setString("userEmail", userEmail);
			query.setString("userPassword", userPassword);
			query.setString("role", type);
			User user = (User) query.uniqueResult();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			rollback();
		}
		return null;
	}
	
	public User getUserEmail(String userEmail) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :userEmail");
			q.setString("userEmail", userEmail);
				
			User user = (User) q.uniqueResult();
			commit();
			return user;
			
		} catch (Exception e) {
			// TODO: handle exception
						rollback();
		}
		return null;
	}
}
