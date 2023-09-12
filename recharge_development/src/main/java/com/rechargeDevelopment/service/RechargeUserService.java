package com.rechargeDevelopment.service;

import java.util.List;

import com.rechargeDevelopment.DTO.GetAllConfigCategoryDTO;
import com.rechargeDevelopment.DTO.GetAllTelecomOperatorNameDTO;
import com.rechargeDevelopment.DTO.GetRechargePlanDTO;
import com.rechargeDevelopment.DTO.RechargeUserDTO;

public interface RechargeUserService {

	public RechargeUserDTO addRechargeUser(RechargeUserDTO rechargeUserDTO);

	public RechargeUserDTO updateUser(RechargeUserDTO rechargeUserDTO);

	public RechargeUserDTO getUserById(long id);
	
	public List<GetAllTelecomOperatorNameDTO> getOperatorName();

	public List<GetAllConfigCategoryDTO> getConfigRechargeCategory();
	
	public GetRechargePlanDTO getAllPlanById(long id);
	
	public List<GetRechargePlanDTO> getPlanByVendorId(long vendorId);

	public List<GetRechargePlanDTO> getPlanByVendorIdAndCatId(long vendorId, long catId);
}
