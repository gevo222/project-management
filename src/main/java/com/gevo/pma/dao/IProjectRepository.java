package com.gevo.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gevo.pma.dto.EmployeeProject;
import com.gevo.pma.dto.ChartData;
import com.gevo.pma.entities.Project;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long>{

	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> stage();
}
