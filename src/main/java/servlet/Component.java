package servlet;



import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Component.User;

public class Component {

	static String url = "jdbc:postgresql://127.0.0.1:5432/donationsDB";
	static String name = "postgres";
	static String password = "1986";

	private static final EntityManagerFactory m_factory = Persistence.createEntityManagerFactory("charity");


	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,name,password);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
}
