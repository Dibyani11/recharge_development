package com.rechargeDevelopment.service;

import java.util.List;

import com.rechargeDevelopment.DTO.GetUserOrderHistoryDTO;
import com.rechargeDevelopment.DTO.RechargeOrderDTO;

public interface RechargeOrderService {

	public RechargeOrderDTO createRechargeOrder(RechargeOrderDTO rechargeOrderDTO);
	
	public List<GetUserOrderHistoryDTO> getHistoryById(long id);

	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByVendorId(long id);
	
	public GetUserOrderHistoryDTO getRechargeHistoryByOrderId(long id);
	
	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByPlanId(long id);
	
	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByCatId(long id);
}
