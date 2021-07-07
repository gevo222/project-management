package com.gevo.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.gevo.pma.entities.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Long>{

}
