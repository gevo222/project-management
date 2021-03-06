package com.gevo.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.dto.ChartData;
import com.gevo.pma.dto.TimelineData;
import com.gevo.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	IProjectRepository projectRepo;

	public Project save(Project project)
	{
		return projectRepo.save(project);
	}
	
	public Iterable<Project> findAll()
	{
		return projectRepo.findAll();
	}
	
	public List<ChartData> stage()
	{
		return projectRepo.stage();
	}
	
	public List<TimelineData> dates()
	{
		return projectRepo.dates();
	}
	
	public void deleteById(long id) {
		projectRepo.deleteById(id);
	}

	public Project getByProjectId(long id) {
		return projectRepo.getByProjectId(id);
	}
}
