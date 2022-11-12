package com.pankiba.jpashowcase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pankiba.jpashowcase.domain.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {

	// Simple JPQL query
	@Query("SELECT emp FROM Employee emp")
	List<Employee> findAllEmployeesJpql();

	public List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	// JPQL Select @Query with Index Parameters
	@Query(value = "SELECT emp FROM Employee emp where emp.firstName = ?1 and emp.lastName = ?2")
	List<Employee> findByFirstNameAndLastNameIndexJpql(String firstName, String lastName);

	// Native SQL Select @Query with Index Parameters
	@Query(value = "SELECT * FROM Employee where first_name = ?1 and last_name = ?2", nativeQuery = true)
	List<Employee> findByFirstNameAndLastNameIndexNative(String firstName, String lastName);

	// JPQL @Query with Named Parameters
	@Query(value = "SELECT emp FROM Employee emp where emp.firstName = :firstName and emp.lastName = :lastName")
	List<Employee> findByFirstNameAndLastNameNamedJpql(@Param("firstName") String firstName,
			@Param("lastName") String lastName);

	// Native SQL @Query with Named Parameters
	@Query(value = "SELECT * FROM Employee where first_name = :firstName and last_name = :lastName", nativeQuery = true)
	List<Employee> findByFirstNameAndLastNameNamedNative(@Param("firstName") String firstName,
			@Param("lastName") String lastName);

	// Sorting with @Query
	@Query("SELECT emp FROM Employee emp where emp.salary = ?1")
	List<Employee> findEmmplyeeBySalaryAndSort(Long salary, Sort sort);

	// Using pagination in the JPQL query - HQL
	@Query(value = "SELECT emp FROM Employee emp ORDER BY firstName")
	Page<Employee> findAllEmployeesWithPagination(Pageable pageable);

}
