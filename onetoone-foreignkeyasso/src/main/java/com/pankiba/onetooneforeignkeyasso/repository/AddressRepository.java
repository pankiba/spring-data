package com.pankiba.onetooneforeignkeyasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetooneforeignkeyasso.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
