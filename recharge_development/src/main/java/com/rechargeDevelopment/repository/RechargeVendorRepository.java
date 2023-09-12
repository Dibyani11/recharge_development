package com.rechargeDevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargeDevelopment.model.RechargeVendor;

public interface RechargeVendorRepository extends JpaRepository<RechargeVendor, Long> {
	
	RechargeVendor findByEmailId(String emailId);
	
	RechargeVendor findById(long id);

	RechargeVendor findByVendorId(long vendorId);

	RechargeVendor findByTelecomOperatorName(String telecomOperatorName);
}
