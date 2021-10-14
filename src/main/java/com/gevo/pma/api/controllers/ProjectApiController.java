package com.gevo.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	@Autowired
	IProjectRepository projectRepo;
	
	@GetMapping
	public Iterable<Project> getProjects()
	{
		return projectRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id)
	{
		return projectRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(@RequestBody @Valid Project project)
	{
		return projectRepo.save(project);
	}
	
	@PutMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project updateProject(@RequestBody @Valid Project project)
	{
		return projectRepo.save(project);
	}
	
	@PatchMapping(consumes="application/json",path="/{id}")
	public Project patchProject(@RequestBody @Valid Project newProjectData, @PathVariable("id") Long id)
	{
		Project project = projectRepo.findById(id).get();
		
		if(newProjectData.getName() != null)
		{
			project.setName(newProjectData.getName());
		}
		if(newProjectData.getDescription() != null)
		{
			project.setDescription(newProjectData.getDescription());
		}
		if(newProjectData.getStage() != null)
		{
			project.setStage(newProjectData.getStage());
		}
		
		return projectRepo.save(project);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable("id") Long id)
	{
		projectRepo.deleteById(id);
	}
}
