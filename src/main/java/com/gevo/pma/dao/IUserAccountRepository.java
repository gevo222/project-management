package com.gevo.pma.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gevo.pma.entities.UserAccount;

public interface IUserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{

}
