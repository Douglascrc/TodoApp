package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {

	public void save(Project project) {
		
		String sql = "INSERT INTO PROJECTS (NAME, DESCRIPTION, CREATEDAT, UPDATEDAT)"
				+ " VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, (Date) project.getCreatedAt());
			statement.setDate(4, (Date) project.getUpdatedAt());
			statement.execute();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir os dados no banco no ProjectController",ex);
		}
		
	}
	
	public void update(Project project) {
		String sql = "UPDATE PROJECTS SET NAME = ?, DESCRIPTION = ?, CREATEDAT = ?,"
				+ " UPDATEDAT = ? WHERE ID = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, (Date)project.getCreatedAt());
			statement.setDate(4, (Date) project.getUpdatedAt());
			statement.setInt(5, project.getId());
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erro ao executar o update do projeto", ex);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
	
	public void removeById(int id) {
		String sql ="DELETE FROM PROJECTS WHERE ID = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar o projeto");
		  } finally {
			  ConnectionFactory.closeConnection(connection, statement);
		  }
	}
	
	public static List <Project> getAll() {
		
		String sql = "SELECT * FROM PROJECTS";
		
		List <Project> projects = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Project project = new Project();
				project.setId(resultSet.getInt("ID"));
				project.setName(resultSet.getString("NAME"));
				project.setDescription(resultSet.getString("DESCRIPTION"));
				project.setCreatedAt(resultSet.getDate("CREATEDAT"));
				project.setUpdatedAt(resultSet.getDate("UPDATEDAT"));
				
				// Adiciona o projeto na Lista de projetos
				projects.add(project);
			}
		} catch (SQLException e) {
			throw new  RuntimeException("Erro ao Captura dados da tabela");
		}
			
		return null;
	}
	
	
}
