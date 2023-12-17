package com.pankiba.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetomany.domain.BusinessUnit;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> {

}
