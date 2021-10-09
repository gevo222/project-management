package com.gevo.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.entities.Employee;
import com.gevo.pma.entities.Project;
import com.gevo.pma.services.EmployeeService;
import com.gevo.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectRepo;
	
	@Autowired
	EmployeeService empRepo;
	
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
		Project project =  new Project();
		List<Employee> employees = (List<Employee>) empRepo.findAll();
		
		model.addAttribute("project", project);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model)
	{
		projectRepo.save(project);
		
		return "redirect:/projects";
	}
	
}
