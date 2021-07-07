package com.gevo.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.gevo.pma.entities.Project;

public interface IProjectRepository extends CrudRepository<Project, Long>{

}
