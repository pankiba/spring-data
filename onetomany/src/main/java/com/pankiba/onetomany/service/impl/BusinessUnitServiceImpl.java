package com.pankiba.onetomany.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankiba.onetomany.domain.BusinessUnit;
import com.pankiba.onetomany.repository.BusinessUnitRepository;
import com.pankiba.onetomany.service.BusinessUnitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	@Autowired
	private BusinessUnitRepository businessUnitRepository;

	@Transactional
	@Override
	public BusinessUnit saveBusinessUnit(BusinessUnit businessUnit) {
		return businessUnitRepository.save(businessUnit);
	}

	@Transactional
	@Override
	public List<BusinessUnit> saveBusinessUnits(List<BusinessUnit> businessUnits) {
		return businessUnitRepository.saveAll(businessUnits);
	}

	@Transactional
	@Override
	public BusinessUnit findBusinessUnitById(Long businessUnitId) {
		return businessUnitRepository.findById(businessUnitId).get();
	}

	@Transactional
	@Override
	public List<BusinessUnit> findAllBusinessUnits() {
		return businessUnitRepository.findAll();
	}

}
