package com.anudip.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.SensorData;

public class LogDaompl{

	Configuration config;
	SessionFactory factory;
	Session session;
	
	public LogDaompl() {
		
		config = new Configuration();
		//Filling up the configuration object from the 
		//Hibernate configuraton file 'hibernate.cfg.xml'
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();  
	}
	
	public List getAllLog()
	{
		session=factory.openSession();
		Query query=session.createQuery("from Log");
		return query.list();
	}
	
	
	
	
	public void removeLog(int id)
	{
		session=factory.openSession();
		Query query=session.createQuery("delete from Log where LogID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		query.executeUpdate();
		t.commit();
		System.out.println("Log deleted permanently...");
		session.close();
		
		}
	
	
        
		
	
	public void registerLog(Log log)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(log);
		t.commit();
		System.out.println("----->");
		session.close();
		
		
		
	}
	public List getSensorDataByDateRange(Date startDate, Date endDate) {
        session = factory.openSession();
        Query query = session.createQuery("from Log where timestamp between :start and :end");
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        List<Log> log = query.list();
        session.close();
        return log;
    }
	public List<Log> getLogByLogType(LogType logType) {
        session = factory.openSession();
        Query<Log> query = session.createQuery("from Log where logType = :type", Log.class);
        query.setParameter("type", logType);
        List<Log> log = query.list();
        session.close();
        return log;
    }
	
	
}
