package com.anudip.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.anudip.app.entities.GreenhouseConfiguration;

public class GreenhouseConfigurationDaompl { 

	Configuration config;
	SessionFactory factory;
	Session session;
	
	public GreenhouseConfigurationDaompl()
	{
		config = new Configuration();
		//Filling up the configuration object from the 
		//Hibernate configuraton file 'hibernate.cfg.xml'
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();  
	}
	
	public List getAllGreenhouseConfigurationDetails()
	{
		session=factory.openSession();
		Query query=session.createQuery("from GreenhouseConfiguration");
		return query.list();
	}
	
	
	public void updateGreenhouseConfiguration(int id,String value)
	{
		session = factory.openSession();
		Query query=session.createQuery("update GreenhouseConfiguration set Value=:p where ConfigurationID=:i");
		query.setParameter("p", value);
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		query.executeUpdate();
		t.commit();
		System.out.println("GreenhouseConfiguration updated successfully...");
		
	}
	
	
	
	public void removeGreenhouseConfigurationById(int id)
	{
		session = factory.openSession();
		Query query=session.createQuery("delete from GreenhouseConfiguration where ConfigurationID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		int a = query.executeUpdate();
		t.commit();
		if(a!=0) {
			System.out.println("GreenhouseConfiguration deleted permanently...");
			} else {
				System.out.println("The entered  id is invalid");
			}
		
		session.close();
		}
	
	
        
		
	
	public void registerGreenhouseConfigurationDetails(GreenhouseConfiguration configuration)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(configuration);
		t.commit();
		System.out.println("GreenhouseConfiguration registred successfully");
		session.close();
		
		
		
	}
	
}
