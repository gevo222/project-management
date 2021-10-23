package com.gevo.pma.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.gevo.pma.entities.Employee;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:schema.sql",
		"classpath:data1.sql" }) })

public class EmployeeRepositoryIntegrationTest {

	@Autowired
	IEmployeeRepository empRepo;

	@Test
	public void ifEmployeeDataInserted_thenSuccess() {
		List<Employee> employees = (List<Employee>) empRepo.findAll();
		assertEquals(9, employees.size());

	}
}
