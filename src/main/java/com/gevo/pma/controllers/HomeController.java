package com.gevo.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.dto.EmployeeProject;
import com.gevo.pma.dto.ChartData;
import com.gevo.pma.entities.Employee;
import com.gevo.pma.entities.Project;
import com.gevo.pma.services.EmployeeService;
import com.gevo.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectService projectRepo;
	
	@Autowired
	EmployeeService employeeRepo;
	
	@GetMapping("/")
	public String displayProjects(Model model) throws JsonProcessingException
	{
		Map<String, Object> map = new HashMap<>();
		
		model.addAttribute("versionNumber", ver);
		
		//Project data
		List<Project> projects = (List<Project>) projectRepo.findAll();
		model.addAttribute("projects", projects);
		
		
		//Project stage data
		List<ChartData> projectStageData = projectRepo.stage();
	
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectStageData);
		model.addAttribute("projectStageData", jsonString);
		
		//Employee data
		List<EmployeeProject> employeesProjectCount = employeeRepo.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		
		return("main/home.html");
	}
}
