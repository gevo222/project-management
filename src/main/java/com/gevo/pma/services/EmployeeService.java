package com.gevo.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.dto.EmployeeProject;
import com.gevo.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeRepository empRepo;

	public Employee save(Employee employee)
	{
		return empRepo.save(employee);
	}
	
	public Iterable<Employee> findAll()
	{
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects()
	{
		return empRepo.employeeProjects();
	}
}
