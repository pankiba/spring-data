package com.pankiba.onetooneforeignkeyasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetooneforeignkeyasso.domain.EmployeeDetails;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

}
