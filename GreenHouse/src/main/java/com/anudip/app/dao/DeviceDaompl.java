package com.anudip.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.anudip.app.entities.Device;

public class DeviceDaompl {
	
	Configuration config;
	SessionFactory factory;
	Session session;
	
	public DeviceDaompl (){
			config = new Configuration();
			//Filling up the configuration object from the 
			//Hibernate configuraton file 'hibernate.cfg.xml'
	        config.configure("hibernate.cfg.xml");
	        factory = config.buildSessionFactory();  
		}
	
	public void registerDevice(Device Device)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(Device);
		t.commit();
		System.out.println("Device registred successfully");
		session.close();
		
		
		
	}
	
	public void removeDeviceById(int id)
	{
		session = factory.openSession();
		Query query=session.createQuery("delete from Device where DeviceID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		int a = query.executeUpdate();
		t.commit();
		if(a!=0) {
		System.out.println("Device deleted permanently...");
		} else {
			System.out.println("The entered  id is invalid");
		}
		session.close();
		
		}
	
	public List getAllDeviceDetails()
	{
		session=factory.openSession();
		Query query=session.createQuery("from Device");
		return query.list();
	}

}
