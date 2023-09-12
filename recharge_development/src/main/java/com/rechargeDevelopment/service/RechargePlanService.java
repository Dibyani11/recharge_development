package com.rechargeDevelopment.service;

import java.util.List;

import com.rechargeDevelopment.DTO.RechargePlanDTO;

public interface RechargePlanService {
	
    public RechargePlanDTO addRechargePlan(RechargePlanDTO rechargePlanDTO);
    
    public RechargePlanDTO updatePlanByVendorId(RechargePlanDTO rechargePlanDTO);

	public RechargePlanDTO getRechargePlanById(long id);

	public List<RechargePlanDTO> getRechargePlanByRechargeVendorId(long vendorId);
    
}
