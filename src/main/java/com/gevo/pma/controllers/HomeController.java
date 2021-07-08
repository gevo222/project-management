package com.gevo.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.entities.Employee;
import com.gevo.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	IProjectRepository projectRepo;
	
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@GetMapping("/")
	public String displayProjects(Model model)
	{
		List<Project> projects = (List<Project>) projectRepo.findAll();
		model.addAttribute("projects", projects);
		
		List<Employee> employees = (List<Employee>) employeeRepo.findAll();
		model.addAttribute("employees", employees);
		
		return("home.html");
	}
}
