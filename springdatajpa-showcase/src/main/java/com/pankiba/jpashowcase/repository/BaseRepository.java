package com.pankiba.jpashowcase.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.pankiba.jpashowcase.domain.Employee;

/**
 * 
 * NoRepositoryBean annotation. This ensures that Spring Data JPA won't try to create an implementation for the
 * BaseRepository interface
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	List<Employee> findAllEmployeesUsingCriteriaApi();
}
