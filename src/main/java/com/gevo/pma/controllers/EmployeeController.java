package com.gevo.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.entities.Employee;
import com.gevo.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeRepo;

	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = (List<Employee>) employeeRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, BindingResult bindingResult, Model model, Errors errors) {

		if (bindingResult.hasErrors()) {
			return "employees/new-employee";
		}
		employeeRepo.save(employee);

		return "redirect:/employees";
	}

	@GetMapping("/update")
	public String updateEmployee(@RequestParam("id") long id, Model model) {
		model.addAttribute("employee", employeeRepo.getByEmployeeId(id));
		return "employees/new-employee";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id, Model model) {
		employeeRepo.deleteById(id);
		return "redirect:/employees";
	}

}