package com.rechargeDevelopment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargeDevelopment.model.RechargeOrder;

public interface RechargeOrderRepository extends JpaRepository<RechargeOrder, Long>{

	List<RechargeOrder> findByRechargeUserUserId(long userId);

	List<RechargeOrder> findByRechargePlanRechargeVendorVendorId(long id);

	List<RechargeOrder> findByRechargePlanId(long id);

	List<RechargeOrder> findByRechargePlanConfigRechargeCategoryCatId(long id);

	RechargeOrder findById(long id);
}
