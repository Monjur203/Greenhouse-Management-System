package com.anudip.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.anudip.app.entities.SensorData;

public class SensorDaompl {
	
	Configuration config;
	SessionFactory factory;
	Session session;
	
	public SensorDaompl()
	{
		config = new Configuration();
		//Filling up the configuration object from the 
		//Hibernate configuraton file 'hibernate.cfg.xml'
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();  
	}
	
	public List getSensorDataByDateRange(Date startDate, Date endDate) {
        session = factory.openSession();
        Query query = session.createQuery("from SensorData where timestamp between :start and :end");
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        List<SensorData> sensorDataList = query.list();
        session.close();
        return sensorDataList;
    }
	
	
	public List getAllSensorDetails()
	{
		session = factory.openSession();
	    Query query = session.createQuery("from SensorData order by id desc");
	    query.setMaxResults(5); // Set maximum results to 5
	    List<SensorData> sensorDataList = query.list();
	    session.close();
	    return sensorDataList;
	}
	
	
	
	
	
	public void removeUserById(int id)
	{
		session = factory.openSession();
		Query query=session.createQuery("delete from SensorData where ReadingID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		query.executeUpdate();
		t.commit();
		System.out.println("Data deleted permanently...");
		session.close();
		}
	
	
        
		
	
	public void registerUserDetails(SensorData data)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(data);
		t.commit();
		//System.out.println("SensorData registred successfully");
		session.close();
		
		
		
	}

}
