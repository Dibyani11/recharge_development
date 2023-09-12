package com.rechargeDevelopment.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargeDevelopment.model.RechargePlan;

public interface RechargePlanRepository extends JpaRepository<RechargePlan, Long>{
   
	RechargePlan findById(long id);

	RechargePlan findByRechargeVendorVendorIdAndRechargeAmount(long vendorId, BigDecimal rechargeAmount);

	List<RechargePlan> findByRechargeVendorVendorId(long vendorId);

	List<RechargePlan>  findByRechargeVendorVendorIdAndConfigRechargeCategoryCatId(long vendorId,long catId);

	RechargePlan findById(BigDecimal amount);
}
