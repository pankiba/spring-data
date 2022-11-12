package com.pankiba.onetomany.service;

import java.util.List;

import com.pankiba.onetomany.domain.BusinessUnit;

public interface BusinessUnitService {

	public BusinessUnit saveBusinessUnit(BusinessUnit businessUnit);

	public List<BusinessUnit> saveBusinessUnits(List<BusinessUnit> businessUnits);

	public BusinessUnit findBusinessUnitById(Long businessUnitId);

	public List<BusinessUnit> findAllBusinessUnits();

}
