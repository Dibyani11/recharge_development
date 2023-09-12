package com.rechargeDevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargeDevelopment.model.RechargeUser;

public interface RechargeUserRepository extends JpaRepository<RechargeUser, Long>{

	RechargeUser findByEmail(String email);

	RechargeUser findByAdhaarNumber(String adhaarNumber);

	RechargeUser findById(long id);

	RechargeUser findByUserId(long userId);
}
