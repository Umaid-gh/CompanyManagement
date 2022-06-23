package com.umaid.demoquickstart.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.umaid.demoquickstart.domain.CompanyDAO;

public interface CompanyRepository extends CrudRepository<CompanyDAO, Integer> {

	Optional<CompanyDAO> findByEid(int eid);
	
	@Transactional
	void deleteByEid(int eid);

}
