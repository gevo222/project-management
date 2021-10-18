package com.gevo.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gevo.pma.dto.EmployeeProject;
import com.gevo.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel="apiprojects", path="apiprojects")
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long>{

	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();
	
	public Employee findByEmail(String value);

	public Employee getByEmployeeId(long id);
}
