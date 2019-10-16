package com.cristovantamayo.veryBasicRetailerAdmin.db;

import java.io.Serializable;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

public class Mysql implements Serializable {

	private static final long serialVersionUID = -7972335867411234025L;
	private static DB db;
	private boolean flag;

	public boolean mountdb() {
		flag = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*Connection conn = */ DriverManager.getConnection("jdbc:mysql://localhost:55551/mysql?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (SQLException e2 ) {
			flag = true;
		} catch (ClassNotFoundException e) {
			flag = true;
		}
		
		System.out.println("Mysql - flag: " +  flag);
		
		try {
			if (flag) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
				config.setPort(55551);
				config.setDataDir("src/db");
				db = DB.newEmbeddedDB(config.build());
				db.start();

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ManagedProcessException exception) {
			exception.printStackTrace();
		}
		
		return getFlag();
		
	}

	public static void unmoutdb() {
		try {
			System.out.println("db: " + db.toString());
			db.stop();
			System.out.println("unmount");
		} catch (ManagedProcessException e) {
			e.printStackTrace();
		}
	}
	
	private boolean getFlag() {
		return flag;
	}

}
