package com.gevo.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.gevo.pma.dao.IEmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeRepository empRepo;

	public EmployeeService(IEmployeeRepository empRepo)
	{
		this.empRepo = empRepo;
	}
	
	@Autowired
	public void setEmpRepo(IEmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
}
