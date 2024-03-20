package com.anudip.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.anudip.app.entities.User;
import com.anudip.app.entities.UserRole;



public class UserDaompl {
	
	
	Configuration config;
	SessionFactory factory;
	Session session;
	
	public UserDaompl()
	{
		config = new Configuration();
		//Filling up the configuration object from the 
		//Hibernate configuraton file 'hibernate.cfg.xml'
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();  
	}
	
	public List getAllUserDetails()
	{
		session=factory.openSession();
		Query query=session.createQuery("from User");
		return query.list();
	}
	
	
	public void updateUserRole(int userId, UserRole newRole) {
	    session = factory.openSession();
	    Transaction transaction = null;
	    
	    try {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the user by ID
	        User user = session.get(User.class, userId);
	        
	        // If the user exists, update the role
	        if (user != null) {
	            user.setRole(newRole);
	            session.update(user);
	            transaction.commit();
	            System.out.println("User role updated successfully.");
	        } else {
	            System.out.println("User not found.");
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	
	
	
	public void removeUserById(int id)
	{
		session = factory.openSession();
		Query query=session.createQuery("delete from User where UserID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		int a =query.executeUpdate();
		t.commit();
		if(a!=0) {
			System.out.println("User deleted permanently...");
			} else {
				System.out.println("The entered  id is invalid");
			}
		
		session.close();
		}
	
	
        
		
	
	public void registerUserDetails(User user)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(user);
		t.commit();
		System.out.println("User registred successfully");
		session.close();
		
		
		
	}
	public User authenticateUser(String username, String password) {
        session = factory.openSession();
        Query<User> query = session.createQuery("from User where username = :username and password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

}
