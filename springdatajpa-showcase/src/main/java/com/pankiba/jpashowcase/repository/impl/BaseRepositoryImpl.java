package com.pankiba.jpashowcase.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.pankiba.jpashowcase.domain.Employee;
import com.pankiba.jpashowcase.repository.BaseRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public List<Employee> findAllEmployeesUsingCriteriaApi() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> fromEmployee = query.from(Employee.class);

		query.where(criteriaBuilder.equal(fromEmployee.get("firstName"), "John"));

		return entityManager.createQuery(query).getResultList();
	}

}
