package com.rechargeDevelopment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.UserRechargeAccountDTO;
import com.rechargeDevelopment.model.RechargeUser;
import com.rechargeDevelopment.model.UserRechargeAccount;
import com.rechargeDevelopment.repository.RechargeUserRepository;
import com.rechargeDevelopment.repository.UserRechargeAccountRepository;
import com.rechargeDevelopment.service.UserRechargeAccountService;

@Service
public class UserRechargeAccountServiceImpl implements UserRechargeAccountService {

	@Autowired
	UserRechargeAccountRepository rechargeAccountRepo;

	@Autowired
	RechargeUserRepository userRepo;

	@Override
	public UserRechargeAccountDTO addRechargeAccount(UserRechargeAccountDTO userAccountDto) {

		RechargeUser userId = userRepo.findById(userAccountDto.getUserId());
		if (userId != null) {

			UserRechargeAccount userMob = rechargeAccountRepo
					.findByRechargeUserUserIdAndMobileNo(userAccountDto.getUserId(), userAccountDto.getMobileNo());
			if (userMob == null) {

				UserRechargeAccount account = new UserRechargeAccount();

				account.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
				account.setMobileNo(userAccountDto.getMobileNo());
				account.setNickName(userAccountDto.getNickName());
				account.setStatus(1);
				account.setFavNumber(userAccountDto.isFavNumber());
				account.setDefaultNumber(userAccountDto.isDefaultNumber());
				account.setRechargeUser(userId);

				if (userAccountDto.isDefaultNumber() == true) {

					UserRechargeAccount userDefaultNo = rechargeAccountRepo.findByRechargeUserUserIdAndDefaultNumber(
							userAccountDto.getUserId(), userAccountDto.isDefaultNumber());

					if (userDefaultNo == null) {

						UserRechargeAccount addAccount = rechargeAccountRepo.save(account);

						UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

						accDto.setId(addAccount.getId());
						accDto.setTelecomOperatorName(addAccount.getTelecomOperatorName());
						accDto.setMobileNo(addAccount.getMobileNo());
						accDto.setNickName(addAccount.getNickName());
						accDto.setStatus(addAccount.getStatus());
						accDto.setFavNumber(addAccount.isFavNumber());
						accDto.setDefaultNumber(addAccount.isDefaultNumber());
						accDto.setUserId(addAccount.getRechargeUser().getUserId());

						return accDto;

					} else {

						userDefaultNo.setDefaultNumber(false);
						rechargeAccountRepo.save(userDefaultNo);

						UserRechargeAccount addAccount = rechargeAccountRepo.save(account);

						UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

						accDto.setId(addAccount.getId());
						accDto.setTelecomOperatorName(addAccount.getTelecomOperatorName());
						accDto.setMobileNo(addAccount.getMobileNo());
						accDto.setNickName(addAccount.getNickName());
						accDto.setStatus(addAccount.getStatus());
						accDto.setFavNumber(addAccount.isFavNumber());
						accDto.setDefaultNumber(addAccount.isDefaultNumber());
						accDto.setUserId(addAccount.getRechargeUser().getUserId());

						return accDto;

					}
				} else {

					UserRechargeAccount addAccount = rechargeAccountRepo.save(account);

					UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

					accDto.setId(addAccount.getId());
					accDto.setTelecomOperatorName(addAccount.getTelecomOperatorName());
					accDto.setMobileNo(addAccount.getMobileNo());
					accDto.setNickName(addAccount.getNickName());
					accDto.setStatus(addAccount.getStatus());
					accDto.setFavNumber(addAccount.isFavNumber());
					accDto.setDefaultNumber(addAccount.isDefaultNumber());
					accDto.setUserId(addAccount.getRechargeUser().getUserId());
					return accDto;
				}
			} else
				throw new RuntimeException("mobile Number is already in use.");
		} else
			throw new RuntimeException("user is not present.");
	}

	@Override
	public UserRechargeAccountDTO updateRechargeAccount(UserRechargeAccountDTO userAccountDTO) {

		UserRechargeAccount account = rechargeAccountRepo.findById(userAccountDTO.getId());
		if (account != null) {

			UserRechargeAccount userMob = rechargeAccountRepo
					.findByRechargeUserUserIdAndMobileNo(userAccountDTO.getUserId(), userAccountDTO.getMobileNo());
			if (userMob == null) {

				account.setTelecomOperatorName(userAccountDTO.getTelecomOperatorName());
				account.setMobileNo(userAccountDTO.getMobileNo());
				account.setNickName(userAccountDTO.getNickName());
				account.setFavNumber(userAccountDTO.isFavNumber());
				account.setDefaultNumber(userAccountDTO.isDefaultNumber());

				if (userAccountDTO.isDefaultNumber() == true) {

					UserRechargeAccount userDefaultNo = rechargeAccountRepo.findByRechargeUserUserIdAndDefaultNumber(
							userAccountDTO.getUserId(), userAccountDTO.isDefaultNumber());

					if (userDefaultNo == null) {

						UserRechargeAccount updateAccount = rechargeAccountRepo.save(account);

						UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

						accDto.setId(updateAccount.getId());
						accDto.setTelecomOperatorName(updateAccount.getTelecomOperatorName());
						accDto.setMobileNo(updateAccount.getMobileNo());
						accDto.setNickName(updateAccount.getNickName());
						accDto.setStatus(updateAccount.getStatus());
						accDto.setFavNumber(updateAccount.isFavNumber());
						accDto.setDefaultNumber(updateAccount.isDefaultNumber());
						accDto.setUserId(updateAccount.getRechargeUser().getUserId());

						return accDto;

					} else {

						userDefaultNo.setDefaultNumber(false);
						rechargeAccountRepo.save(userDefaultNo);

						UserRechargeAccount updateAccount = rechargeAccountRepo.save(account);

						UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

						accDto.setId(updateAccount.getId());
						accDto.setTelecomOperatorName(updateAccount.getTelecomOperatorName());
						accDto.setMobileNo(updateAccount.getMobileNo());
						accDto.setNickName(updateAccount.getNickName());
						accDto.setStatus(updateAccount.getStatus());
						accDto.setFavNumber(updateAccount.isFavNumber());
						accDto.setDefaultNumber(updateAccount.isDefaultNumber());
						accDto.setUserId(updateAccount.getRechargeUser().getUserId());

						return accDto;
					}
				} else {

					UserRechargeAccount updateAccount = rechargeAccountRepo.save(account);

					UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

					accDto.setId(updateAccount.getId());
					accDto.setTelecomOperatorName(updateAccount.getTelecomOperatorName());
					accDto.setMobileNo(updateAccount.getMobileNo());
					accDto.setNickName(updateAccount.getNickName());
					accDto.setStatus(updateAccount.getStatus());
					accDto.setFavNumber(updateAccount.isFavNumber());
					accDto.setDefaultNumber(updateAccount.isDefaultNumber());
					accDto.setUserId(updateAccount.getRechargeUser().getUserId());

					return accDto;
				}
			} else
				throw new RuntimeException("mobile Number is already in use.");
		} else
			throw new RuntimeException("UserRechargeAccountId is not present.");
	}

	@Override
	public List<UserRechargeAccountDTO> getSavedAccountById(long id) {

		RechargeUser user = userRepo.findById(id);
		if (user != null) {

			List<UserRechargeAccount> allUser = rechargeAccountRepo.findByRechargeUserUserId(id);
			List<UserRechargeAccountDTO> userDto = new ArrayList<UserRechargeAccountDTO>();

			for (UserRechargeAccount accountDTO : allUser) {

				UserRechargeAccountDTO accDto = new UserRechargeAccountDTO();

				accDto.setId(accountDTO.getId());
				accDto.setTelecomOperatorName(accountDTO.getTelecomOperatorName());
				accDto.setMobileNo(accountDTO.getMobileNo());
				accDto.setNickName(accountDTO.getNickName());
				accDto.setStatus(accountDTO.getStatus());
				accDto.setFavNumber(accountDTO.isFavNumber());
				accDto.setDefaultNumber(accountDTO.isDefaultNumber());
				accDto.setUserId(accountDTO.getRechargeUser().getUserId());

				userDto.add(accDto);
			}
			return userDto;
		} else
			throw new RuntimeException("userId is not present.");
	}

	@Override
	public Object deleteAccountById(long id) {

		UserRechargeAccount accountId = rechargeAccountRepo.findById(id);
		if (accountId != null) {

			accountId.setStatus(0);
			rechargeAccountRepo.save(accountId);

			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "Deleted successfully .");
			return map;

//		    UserRechargeAccountDTO dto = new UserRechargeAccountDTO();
//		    dto.setStatus(0);
//		    
//		    return dto;
		} else
			throw new RuntimeException("AccountId is not present.");
	}

	@Override
	public List<UserRechargeAccountDTO> getAllFavNumberByUserId(long id) {

		RechargeUser userId = userRepo.findById(id);
		if (userId != null) {

			List<UserRechargeAccount> favNumber = rechargeAccountRepo.findByRechargeUserUserIdAndFavNumber(id, true);
			List<UserRechargeAccountDTO> allFavNum = new ArrayList<UserRechargeAccountDTO>();

			for (UserRechargeAccount favNo : favNumber) {

				UserRechargeAccountDTO fNumDto = new UserRechargeAccountDTO();

				fNumDto.setId(favNo.getId());
				fNumDto.setTelecomOperatorName(favNo.getTelecomOperatorName());
				fNumDto.setMobileNo(favNo.getMobileNo());
				fNumDto.setNickName(favNo.getNickName());
				fNumDto.setStatus(favNo.getStatus());
				fNumDto.setFavNumber(favNo.isFavNumber());
				fNumDto.setDefaultNumber(favNo.isDefaultNumber());
				fNumDto.setUserId(favNo.getRechargeUser().getUserId());

				allFavNum.add(fNumDto);
			}
			return allFavNum;
		} else
			throw new RuntimeException("userId is not present.");
	}

	@Override
	public UserRechargeAccountDTO getDefaultNumberByUserId(long id) {

		RechargeUser userId = userRepo.findById(id);
		if (userId != null) {

			UserRechargeAccount defaultNo = rechargeAccountRepo.findByRechargeUserUserIdAndDefaultNumber(id, true);

			UserRechargeAccountDTO Dto = new UserRechargeAccountDTO();

			Dto.setId(defaultNo.getId());
			Dto.setTelecomOperatorName(defaultNo.getTelecomOperatorName());
			Dto.setMobileNo(defaultNo.getMobileNo());
			Dto.setNickName(defaultNo.getNickName());
			Dto.setStatus(defaultNo.getStatus());
			Dto.setFavNumber(defaultNo.isFavNumber());
			Dto.setDefaultNumber(defaultNo.isDefaultNumber());
			Dto.setUserId(defaultNo.getRechargeUser().getUserId());

			return Dto;
		} else
			throw new RuntimeException("userId is not present.");
	}

}
