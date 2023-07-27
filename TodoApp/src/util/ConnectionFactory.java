package util;

import java.sql.*;

public class ConnectionFactory {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/todoapp?useTimezone=true&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASS = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER); // Carrega o Driver do Mysql para a aplicação
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception excecao) {
			throw new RuntimeException("Erro na conexão com o banco de dados", excecao);
		}
	}
	
	public static void closeConnection (Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch(Exception excecao) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", excecao);
		}
	}
	
	public static void closeConnection (Connection connection, PreparedStatement statement) {
		try {
			if (connection != null) {
				connection.close();
			}
		  if(statement != null) {
			  statement.close();
		  }
		} catch(Exception excecao) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", excecao);
		}
	}
	public static void closeConnection (Connection connection, PreparedStatement statement, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			
		  if(resultSet != null) {
			  resultSet.close();
		  }
		  if(statement != null) {
			  statement.close();
		  }
		} catch(Exception excecao) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", excecao);
		}
	}
}
