package com.gevo.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.gevo.pma.entities.UserAccount;

public interface IUserAccountRepository extends CrudRepository<UserAccount, Long>{

}
