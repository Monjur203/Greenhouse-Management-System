package com.anudip.app;

import java.util.List;

import com.anudip.app.dao.LogDaompl;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;

public class a {

	static LogDaompl ldao =new LogDaompl();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Log> warningLogs = ldao.getLogByLogType(LogType.warning);
        
        // Display the retrieved logs
        System.out.println("Logs of type 'warning':");
        for (Log log : warningLogs) {
            System.out.println(log);
        }
	}

}
