package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
	
	//Un metodo estatico es un metodo que se puede usar sin tener que crear una instancia de la clase.
	//AdministradorDeConexiones.metodo();
	
	public static Connection getConnection() {
		String userName = "root";
		String password = "nikoxd123";
		String port = "3306";
		String host = "127.0.0.1";
		String dbName = "integrador_cac";
		
		String diverName = "com.mysql.cj.jdbc.Driver";
		
		String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName + "?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
		
		try {
			Class.forName(diverName);
			return DriverManager.getConnection(dbUrl, userName, password);
		}catch (Exception e) {
			throw new IllegalArgumentException("No se pudo conectar a la db:" + dbUrl);
		}
	}
	
}
