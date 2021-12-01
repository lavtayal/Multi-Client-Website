package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.Delievery;

@Repository
public interface DelieveryRepository extends JpaRepository<Delievery,Long>{
	Delievery findByDeliveryContact(String deliveryContact);
}
