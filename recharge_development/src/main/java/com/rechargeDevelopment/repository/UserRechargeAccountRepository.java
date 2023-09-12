package com.rechargeDevelopment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargeDevelopment.model.UserRechargeAccount;

public interface UserRechargeAccountRepository extends JpaRepository<UserRechargeAccount, Long> {

	UserRechargeAccount findByRechargeUserUserIdAndMobileNo(long userId, String mobileNo);

//	List<UserRechargeAccount> findByRechargeUserUserIdAndDefaultNumber(long userId, boolean defaultNumber);
	
	UserRechargeAccount findByRechargeUserUserIdAndDefaultNumber(long userId, boolean defaultNumber);

    UserRechargeAccount findById(long id);

	List<UserRechargeAccount> findByRechargeUserUserId(long id);

	List<UserRechargeAccount> findByRechargeUserUserIdAndFavNumber(long id, boolean b);
}
