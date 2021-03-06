package com.gevo.pma.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="project_seq")
	private long projectId;
	
	@NotBlank(message = "Please enter a name")
	@Size(min=2, max=50)
	private String name;
	
	private String stage;
	private String description;
	
	@NotNull(message="Please enter a date")
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date startDate;
	
	@NotNull(message="Please enter a date")
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date endDate;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	
	@JsonIgnore
	/*
	 * uncomment if we want at least one employee assigned when creating a project
	 * 
	 * @NotEmpty(message="Must select at least one employee")
	 */
	private List<Employee> employees;

	public Project() {

	}

	public Project(long projectId, String name, String stage, String description,
			Date startDate, Date endDate, List<Employee> employees) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.stage = stage;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void addEmployee(Employee emp) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}

}
