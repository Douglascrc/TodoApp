package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;
import util.ConnectionFactory;

public class TaskController {
	
	public void save(Task task) {
		
		String sql = "INSERT INTO TASKS ( NAME, DESCRIPTION, COMPLETED, NOTES,"
				+ " CREATEDAT, "
				+ " UPDATEDAT, DEADLINE, IDPROJECT)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection connection = null; 
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(8, task.getIdProject());
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setBoolean(3, task.getIsCompleted());
			statement.setString(4, task.getNotes());
			statement.setDate(5, (Date) task.getCreatedAt());
			statement.setDate(6, (Date) task.getUpdatedAt());
			statement.setDate(7, (Date) task.getDeadline());
			statement.execute();
			
		} catch (Exception e) {
			throw new RuntimeException("Erro a salvar a tarefa " + e.getMessage(), e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
	
	public void update(Task task) {
		
		String sql = "UPDATE TASKS SET NAME = ?, DESCRIPTION = ?, COMPLETED = ?, NOTES = ?,"
				+ " CREATEDAT = ?, UPDATEDAT = ?, DEADLINE = ?, IDPROJECT = ?, WHERE ID = ?";
		
		Connection connection = null;	
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setBoolean(3, task.getIsCompleted());
			statement.setString(4, task.getNotes());
			statement.setDate(5, (Date) task.getCreatedAt());
			statement.setDate(6, (Date) task.getUpdatedAt());
			statement.setDate(7, (Date) task.getDeadline());
			statement.setInt(8, task.getIdProject());
			statement.execute();
			
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao deleta a tarefa " + ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		
	}
	
	public void removeById(int taskid) throws SQLException {
		
		String sql = "DELETE FROM TASKS WHERE ID = ?";
		Connection connection = null;	
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareCall(sql);
			statement.setInt(1, taskid);
			statement.execute();
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao deleta a tarefa " + ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
	
	public List <Task> getAll(int idProject) {
		
		String sql = "SELECT * FROM TASKS WHERE IDPROJECT = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		//Lista de tarefas que será devolvida quando o metodo for chamado
		List<Task> tasks = new ArrayList<Task>();
		
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProject);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Task task = new Task();
				task.setId(resultSet.getInt("ID"));
				task.setName(resultSet.getString("NAME"));
				task.setDescription(resultSet.getString("DESCRIPTION"));
				task.setIsCompleted(resultSet.getBoolean("COMPLETED"));
				task.setNotes(resultSet.getString("NOTES"));
				task.setCreatedAt(resultSet.getDate("CREATEDAT"));
				task.setUpdatedAt(resultSet.getDate("UPDATEDAT"));
				task.setDeadline(resultSet.getDate("DEADLINE"));
				task.setIdProject(resultSet.getInt("IDPROJECT"));
				tasks.add(task);
				
			}
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao deleta a tarefa " + ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}	
		return tasks;	
	}
	
}
