package main;

import java.util.List;

import controller.ProjectController;
import model.Project;

public class Main {

	public static void main(String[] args) {
		//Comando para criar projetos
//		ProjectController projectController = new ProjectController();
//		Project project = new Project();
//		project.setName(" projeto 2");
//		project.setDescription("Um Projeto 2ss");
//		projectController.save(project);
		
		
		//Comando para fazer select na tabela
//		List<Project> projects =  ProjectController.getAll();
//		System.out.println("O total de projetos é " + projects.size());
		
		
		// Comando de Update
//		ProjectController projectController = new ProjectController();
//		Project project = new Project();
//		project.setId(2);
//		project.setName("Teste projeto 2");
//		project.setDescription(" Descrição 2");
//		
//		projectController.update(project);

		//Comando Remover
		ProjectController projectController = new ProjectController();	
		
		
		projectController.removeById(2);
		
	}

}
