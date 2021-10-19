package com.gevo.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.dto.ChartData;
import com.gevo.pma.dto.TimelineData;
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
	
	@GetMapping("/timeline")
	public String displayTimeline(Model model) throws JsonProcessingException
	{
				List<TimelineData> projectDateData = projectRepo.dates();
			
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonString = objectMapper.writeValueAsString(projectDateData);
				model.addAttribute("projectDateData", jsonString);
				
				return("projects/project-timeline");
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
	public String createProject(@Valid Project project, BindingResult bindingResult, @RequestParam(required=false) List<Long> employees, Model model)
	{
		if(bindingResult.hasErrors())
		{
			List<Employee> employeeso = (List<Employee>) empRepo.findAll();
			
			model.addAttribute("project", project);
			model.addAttribute("allEmployees", employeeso);
			return "projects/new-project";
		}
		projectRepo.save(project);
		
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String updateProject(@RequestParam("id") long id, Model model)
	{
		model.addAttribute("project", projectRepo.getByProjectId(id));
		List<Employee> employees = (List<Employee>) empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String updateProject(@RequestParam("id") long id)
	{
		projectRepo.deleteById(id);
		return "redirect:/projects";
	}
}
