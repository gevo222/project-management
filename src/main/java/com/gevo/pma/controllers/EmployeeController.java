package com.gevo.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	IEmployeeRepository employeetRepo;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model)
	{
		model.addAttribute("employee", new Employee());
		return "new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model)
	{
		employeetRepo.save(employee);
		
		return "redirect:/employees/new";
	}
}