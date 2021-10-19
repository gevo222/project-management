package com.gevo.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gevo.pma.dto.EmployeeProject;
import com.gevo.pma.dto.TimelineData;
import com.gevo.pma.dto.ChartData;
import com.gevo.pma.entities.Project;
@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface IProjectRepository extends PagingAndSortingRepository<Project, Long>{

	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> stage();
	
	@Query(nativeQuery=true, value="SELECT name as label, start_date as startDate, end_date as endDate "
			+ "FROM project WHERE start_date is not null")
	public List<TimelineData> dates();

	public Project getByProjectId(long id);
}
