package com.gevo.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.entities.Employee;
import com.gevo.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	IProjectRepository projectRepo;
	
	@GetMapping
	public String displayProjects(Model model)
	{
		List<Project> projects = (List<Project>) projectRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model)
	{
		model.addAttribute("project", new Project());
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, Model model)
	{
		projectRepo.save(project);
		
		return "redirect:/projects/new";
	}
	
}
