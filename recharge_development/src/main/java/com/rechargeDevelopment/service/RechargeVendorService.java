package com.rechargeDevelopment.service;

import com.rechargeDevelopment.DTO.GetRechargeVendorBalanceDTO;
import com.rechargeDevelopment.DTO.RechargeVendorDTO;

public interface RechargeVendorService {
	public RechargeVendorDTO createRechargeVendor(RechargeVendorDTO rechargeVendorDTO);

	public RechargeVendorDTO getVendorById(long id);
	
	public RechargeVendorDTO updateVendorById(RechargeVendorDTO rechargeVendorDTO);
	
	public GetRechargeVendorBalanceDTO getVendorBalanceById(long id);
}
