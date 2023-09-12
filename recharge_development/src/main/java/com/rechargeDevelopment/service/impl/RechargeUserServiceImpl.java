package com.rechargeDevelopment.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.GetAllConfigCategoryDTO;
import com.rechargeDevelopment.DTO.GetAllTelecomOperatorNameDTO;
import com.rechargeDevelopment.DTO.GetRechargePlanDTO;
import com.rechargeDevelopment.DTO.RechargeUserDTO;
import com.rechargeDevelopment.model.ConfigRechargeCategory;
import com.rechargeDevelopment.model.RechargePlan;
import com.rechargeDevelopment.model.RechargeUser;
import com.rechargeDevelopment.model.RechargeVendor;
import com.rechargeDevelopment.repository.ConfigRechargeCategoryRepository;
import com.rechargeDevelopment.repository.RechargePlanRepository;
import com.rechargeDevelopment.repository.RechargeUserRepository;
import com.rechargeDevelopment.repository.RechargeVendorRepository;
import com.rechargeDevelopment.service.RechargeUserService;

@Service
public class RechargeUserServiceImpl implements RechargeUserService {

	@Autowired
	RechargeUserRepository userRepo;

	@Autowired
	RechargeVendorRepository vendorRepo;

	@Autowired
	ConfigRechargeCategoryRepository catRepo;

	@Autowired
	RechargePlanRepository planRepo;

	@Override
	public RechargeUserDTO addRechargeUser(RechargeUserDTO rechargeUserDTO) {

		RechargeUser user = new RechargeUser();

		user.setFirstName(rechargeUserDTO.getFirstName());
		user.setLastName(rechargeUserDTO.getLastName());
		user.setEmail(rechargeUserDTO.getEmail());
		user.setGender(rechargeUserDTO.getGender());
		user.setDob(rechargeUserDTO.getDob());
		user.setMobileNumber(rechargeUserDTO.getMobileNumber());
		user.setAdhaarNumber(rechargeUserDTO.getAdhaarNumber());
		user.setBalance(new BigDecimal(2000));

		// Validate Email format
		String email = rechargeUserDTO.getEmail();
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new RuntimeException("Invalid Email format.");
		} else {

			RechargeUser userMail = userRepo.findByEmail(rechargeUserDTO.getEmail());
			if (userMail == null) {

				// validate Adhaar format
				String adhaarNumber = rechargeUserDTO.getAdhaarNumber();
				if (!adhaarNumber.matches("\\d{12}")) {
					throw new RuntimeException("Invalid Aadhaar Number format.");
				} else {

					RechargeUser uAdhaar = userRepo.findByAdhaarNumber(rechargeUserDTO.getAdhaarNumber());
					if (uAdhaar == null) {

						RechargeUser newUser = userRepo.save(user);

						rechargeUserDTO.setUserId(newUser.getUserId());
						rechargeUserDTO.setFirstName(newUser.getFirstName());
						rechargeUserDTO.setLastName(newUser.getLastName());
						rechargeUserDTO.setEmail(newUser.getEmail());
						rechargeUserDTO.setGender(newUser.getGender());
						rechargeUserDTO.setDob(newUser.getDob());
						rechargeUserDTO.setMobileNumber(newUser.getMobileNumber());
						rechargeUserDTO.setAdhaarNumber(newUser.getAdhaarNumber());
						rechargeUserDTO.setBalance(newUser.getBalance());
						rechargeUserDTO.setCreatedOn(newUser.getCreatedOn());
						rechargeUserDTO.setLastUpdatedOn(newUser.getLastUpdatedOn());

					} else {
						throw new RuntimeException("Given AdhaarNumber is in use.");
					}
				}
			} else {
				throw new RuntimeException("Given email is in use .");
			}
		}
		return rechargeUserDTO;
	}

	@Override
	public RechargeUserDTO updateUser(RechargeUserDTO rechargeUserDTO) {

		RechargeUser rechargeUser = userRepo.findById(rechargeUserDTO.getUserId());

		if (rechargeUser != null) {

			rechargeUser.setFirstName(rechargeUserDTO.getFirstName());
			rechargeUser.setLastName(rechargeUserDTO.getLastName());
			rechargeUser.setEmail(rechargeUserDTO.getEmail());
			rechargeUser.setGender(rechargeUserDTO.getGender());
			rechargeUser.setDob(rechargeUserDTO.getDob());
			rechargeUser.setMobileNumber(rechargeUserDTO.getMobileNumber());
			rechargeUser.setAdhaarNumber(rechargeUserDTO.getAdhaarNumber());
			rechargeUser.setBalance(new BigDecimal(2000));

			// Validate Email format
			String email = rechargeUserDTO.getEmail();
			if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
				throw new RuntimeException("Invalid Email format.");
			} else {

				RechargeUser userMail = userRepo.findByEmail(rechargeUserDTO.getEmail());
				if (userMail == null) {

					// validate Adhaar format
					String adhaarNumber = rechargeUserDTO.getAdhaarNumber();
					if (!adhaarNumber.matches("\\d{12}")) {
						throw new RuntimeException("Invalid Aadhaar Number format.");
					} else {

						RechargeUser userAdhaar = userRepo.findByAdhaarNumber(rechargeUserDTO.getAdhaarNumber());
						if (userAdhaar == null) {

							RechargeUser newUser = userRepo.save(rechargeUser);

							rechargeUserDTO.setUserId(newUser.getUserId());
							rechargeUserDTO.setFirstName(newUser.getFirstName());
							rechargeUserDTO.setLastName(newUser.getLastName());
							rechargeUserDTO.setEmail(newUser.getEmail());
							rechargeUserDTO.setGender(newUser.getGender());
							rechargeUserDTO.setDob(newUser.getDob());
							rechargeUserDTO.setMobileNumber(newUser.getMobileNumber());
							rechargeUserDTO.setAdhaarNumber(newUser.getAdhaarNumber());
							rechargeUserDTO.setBalance(newUser.getBalance());
							rechargeUserDTO.setCreatedOn(newUser.getCreatedOn());
							rechargeUserDTO.setLastUpdatedOn(newUser.getLastUpdatedOn());

						} else {
							throw new RuntimeException("Given AdhaarNumber is in use.");
						}
					}
				} else {
					throw new RuntimeException("Given email is in use .");
				}
			}
		}
		return rechargeUserDTO;
	}

	@Override
	public RechargeUserDTO getUserById(long id) {

		RechargeUser rUser = userRepo.findById(id);

		if (rUser != null) {

			RechargeUserDTO userDto = new RechargeUserDTO();

			userDto.setUserId(rUser.getUserId());
			userDto.setFirstName(rUser.getFirstName());
			userDto.setLastName(rUser.getLastName());
			userDto.setEmail(rUser.getEmail());
			userDto.setGender(rUser.getGender());
			userDto.setDob(rUser.getDob());
			userDto.setMobileNumber(rUser.getMobileNumber());
			userDto.setAdhaarNumber(rUser.getAdhaarNumber());
			userDto.setBalance(rUser.getBalance());
			userDto.setCreatedOn(rUser.getCreatedOn());
			userDto.setLastUpdatedOn(rUser.getLastUpdatedOn());

			return userDto;
		} else {
			throw new RuntimeException("User is Not present.");
		}
	}

	@Override
	public List<GetAllTelecomOperatorNameDTO> getOperatorName() {

		List<RechargeVendor> vendor = vendorRepo.findAll();

		List<GetAllTelecomOperatorNameDTO> dto = new ArrayList<>();

		for (RechargeVendor telecomOperatorName : vendor) {

			GetAllTelecomOperatorNameDTO tDto = new GetAllTelecomOperatorNameDTO();

			tDto.setTelecomOperatorName(telecomOperatorName.getTelecomOperatorName());

			dto.add(tDto);
		}
		return dto;
	}

	@Override
	public List<GetAllConfigCategoryDTO> getConfigRechargeCategory() {

		List<ConfigRechargeCategory> configCat = catRepo.findAll();

		List<GetAllConfigCategoryDTO> configDto = new ArrayList<>();

		for (ConfigRechargeCategory ConfigCatDTO : configCat) {

			GetAllConfigCategoryDTO catDto = new GetAllConfigCategoryDTO();

			catDto.setCatId(ConfigCatDTO.getCatId());
			catDto.setCategoryName(ConfigCatDTO.getCategoryName());

			configDto.add(catDto);
		}
		return configDto;
	}

	@Override
	public GetRechargePlanDTO getAllPlanById(long id) {

		RechargePlan rechargePlan = planRepo.findById(id);
		if (rechargePlan != null) {

			GetRechargePlanDTO planDto = new GetRechargePlanDTO();

			planDto.setId(rechargePlan.getId());
			planDto.setRechargeAmount(rechargePlan.getRechargeAmount());
			planDto.setDescription(rechargePlan.getDescription());
			planDto.setValidity(rechargePlan.getValidity());
			planDto.setVendorId(rechargePlan.getRechargeVendor().getVendorId());
			planDto.setCatId(rechargePlan.getConfigRechargeCategory().getCatId());

			return planDto;
		} else {
			throw new RuntimeException("RechargePlan with given Id is not present.");
		}
	}

	@Override
	public List<GetRechargePlanDTO> getPlanByVendorId(long vendorId) {

		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vendorId);
		if (rechargeVendor != null) {

			List<RechargePlan> rplan = planRepo.findByRechargeVendorVendorId(vendorId);
			List<GetRechargePlanDTO> planDto = new ArrayList<>();

			for (RechargePlan rechargePlan : rplan) {

				GetRechargePlanDTO pdto = new GetRechargePlanDTO();

				pdto.setId(rechargePlan.getId());
				pdto.setRechargeAmount(rechargePlan.getRechargeAmount());
				pdto.setDescription(rechargePlan.getDescription());
				pdto.setValidity(rechargePlan.getValidity());
				pdto.setVendorId(rechargePlan.getRechargeVendor().getVendorId());
				pdto.setCatId(rechargePlan.getConfigRechargeCategory().getCatId());

				planDto.add(pdto);
			}
			return planDto;
		} else {
			throw new RuntimeException("vendorId is not present.");
		}
	}

	@Override
	public List<GetRechargePlanDTO> getPlanByVendorIdAndCatId(long vendorId, long catId) {

		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vendorId);
		if (rechargeVendor != null) {

			ConfigRechargeCategory rechargeCat = catRepo.findByCatId(catId);
			if (rechargeCat != null) {

				List<RechargePlan> vendorAndCatId = planRepo
						.findByRechargeVendorVendorIdAndConfigRechargeCategoryCatId(vendorId, catId);
					List<GetRechargePlanDTO> planDto = new ArrayList<>();

					for (RechargePlan rechargePlan : vendorAndCatId) {

						GetRechargePlanDTO pdto = new GetRechargePlanDTO();

						pdto.setId(rechargePlan.getId());
						pdto.setRechargeAmount(rechargePlan.getRechargeAmount());
						pdto.setDescription(rechargePlan.getDescription());
						pdto.setValidity(rechargePlan.getValidity());
						pdto.setVendorId(rechargePlan.getRechargeVendor().getVendorId());
						pdto.setCatId(rechargePlan.getConfigRechargeCategory().getCatId());

						planDto.add(pdto);
					}
					return planDto;

			} else {
				throw new RuntimeException("Category id is not present.");
			}
		} else {
			throw new RuntimeException("Vendor is not present.");
		}
	}
}
