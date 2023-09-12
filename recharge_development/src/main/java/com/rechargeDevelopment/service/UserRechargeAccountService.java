package com.rechargeDevelopment.service;

import java.util.List;

import com.rechargeDevelopment.DTO.UserRechargeAccountDTO;

public interface UserRechargeAccountService {

	UserRechargeAccountDTO addRechargeAccount(UserRechargeAccountDTO userAccountDto);
	
	UserRechargeAccountDTO updateRechargeAccount(UserRechargeAccountDTO userAccountDTO);
	
	List<UserRechargeAccountDTO> getSavedAccountById(long id);
	
	Object deleteAccountById(long id);
	
	List<UserRechargeAccountDTO> getAllFavNumberByUserId(long id);
	
	UserRechargeAccountDTO getDefaultNumberByUserId(long id);
}
