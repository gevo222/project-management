package com.gevo.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gevo.pma.dao.IEmployeeRepository;
import com.gevo.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

	@Autowired
	IEmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees()
	{
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)
	{
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody @Valid Employee employee)
	{
		return empRepo.save(employee);
	}
	
	@PutMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@RequestBody @Valid Employee employee)
	{
		return empRepo.save(employee);
	}
	
	@PatchMapping(consumes="application/json",path="/{id}")
	public Employee patchEmployee(@RequestBody @Valid Employee newEmployeeData, @PathVariable("id") Long id)
	{
		Employee employee = empRepo.findById(id).get();
		
		if(newEmployeeData.getFirstName() != null)
		{
			employee.setFirstName(newEmployeeData.getFirstName());
		}
		if(newEmployeeData.getLastName() != null)
		{
			employee.setLastName(newEmployeeData.getLastName());
		}
		if(newEmployeeData.getEmail() != null)
		{
			employee.setEmail(newEmployeeData.getEmail());
		}
		
		return empRepo.save(employee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id)
	{
		empRepo.deleteById(id);
	}
	
	@GetMapping(params ={"page","size"})
	public Iterable<Employee> getEmployeePaginated(@RequestParam("page") int page, @RequestParam("size")int size)
	{
		Pageable pageAndSize = PageRequest.of(page,size);
		return empRepo.findAll(pageAndSize);
	}
}
