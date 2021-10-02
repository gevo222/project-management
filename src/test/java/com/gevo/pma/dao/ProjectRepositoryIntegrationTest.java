package com.gevo.pma.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.gevo.pma.ProjectManagementApplication;
import com.gevo.pma.dao.IProjectRepository;
import com.gevo.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:schema.sql", "classpath:data1.sql"})})

public class ProjectRepositoryIntegrationTest {

	@Autowired
	IProjectRepository projectRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess()
	{
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		projectRepo.save(newProject);
		List<Project> projects = (List<Project>) projectRepo.findAll();
		assertEquals(5, projects.size());
		
	}
}
