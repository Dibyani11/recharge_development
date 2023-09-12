package com.rechargeDevelopment.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.GetUserOrderHistoryDTO;
import com.rechargeDevelopment.DTO.RechargeOrderDTO;
import com.rechargeDevelopment.model.ConfigRechargeCategory;
import com.rechargeDevelopment.model.RechargeOrder;
import com.rechargeDevelopment.model.RechargePlan;
import com.rechargeDevelopment.model.RechargeUser;
import com.rechargeDevelopment.model.RechargeVendor;
import com.rechargeDevelopment.repository.ConfigRechargeCategoryRepository;
import com.rechargeDevelopment.repository.RechargeOrderRepository;
import com.rechargeDevelopment.repository.RechargePlanRepository;
import com.rechargeDevelopment.repository.RechargeUserRepository;
import com.rechargeDevelopment.repository.RechargeVendorRepository;
import com.rechargeDevelopment.service.RechargeOrderService;

@Service
public class RechargeOrderServiceImpl implements RechargeOrderService {

	@Autowired
	RechargeOrderRepository orderRepo;

	@Autowired
	RechargeUserRepository userRepo;

	@Autowired
	RechargePlanRepository planRepo;

	@Autowired
	RechargeVendorRepository vendorRepo;

	@Autowired
	ConfigRechargeCategoryRepository configRepo;

	@Override
	public RechargeOrderDTO createRechargeOrder(RechargeOrderDTO rechargeOrderDTO) {

		RechargeUser rechargeUser = userRepo.findById(rechargeOrderDTO.getUserId());
		if (rechargeUser != null) {

			RechargePlan plan = planRepo.findById(rechargeOrderDTO.getPlanId());
			if (plan != null) {

				RechargeOrder order = new RechargeOrder();

				BigDecimal rechargeAmnt = plan.getRechargeAmount();
				BigDecimal userBalance = rechargeUser.getBalance();

				int deduct = userBalance.compareTo(rechargeAmnt);

				if (deduct >= 0) {
					BigDecimal currentBalance = userBalance.subtract(rechargeAmnt);
					rechargeUser.setBalance(currentBalance);
					userRepo.save(rechargeUser);
					
					BigDecimal add = plan.getRechargeVendor().getBalance().add(rechargeAmnt);
					plan.getRechargeVendor().setBalance(add);

					order.setDescription("Successful Order");
					order.setAmount(rechargeAmnt);
					order.setContactNo(rechargeOrderDTO.getContactNo());
					order.setTransactionId(UUID.randomUUID().toString());
					order.setRechargeUser(rechargeUser);
					order.setRechargePlan(plan);

					RechargeOrder newOrder = orderRepo.save(order);

					rechargeOrderDTO.setOrderId(newOrder.getOrderId());
					rechargeOrderDTO.setDescription(newOrder.getDescription());
					rechargeOrderDTO.setAmount(newOrder.getAmount());
					rechargeOrderDTO.setContactNo(newOrder.getContactNo());
					rechargeOrderDTO.setTransactionId(newOrder.getTransactionId());
					rechargeOrderDTO.setUserId(newOrder.getRechargeUser().getUserId());
					rechargeOrderDTO.setPlanId(newOrder.getRechargePlan().getId());
					rechargeOrderDTO.setCreatedOn(newOrder.getCreatedOn());
					rechargeOrderDTO.setLastUpdatedOn(newOrder.getLastUpdatedOn());

				} else
					throw new RuntimeException("User Balance is low.");
			} else
				throw new RuntimeException("planId is not present.");
		} else
			throw new RuntimeException("userId is not present.");
		return rechargeOrderDTO;
	}

	@Override
	public List<GetUserOrderHistoryDTO> getHistoryById(long userId) {

		RechargeUser uId = userRepo.findById(userId);
		if (uId != null) {

			List<RechargeOrder> order = orderRepo.findByRechargeUserUserId(userId);
			List<GetUserOrderHistoryDTO> dtos = new ArrayList<>();

			for (RechargeOrder order1 : order) {

				GetUserOrderHistoryDTO dto = new GetUserOrderHistoryDTO();

				dto.setOrderId(order1.getOrderId());
				dto.setAmount(order1.getAmount());
				dto.setContactNo(order1.getContactNo());
				dto.setTransactionId(order1.getTransactionId());
				dto.setUserId(order1.getRechargeUser().getUserId());
				dto.setPlanId(order1.getRechargePlan().getId());

				dtos.add(dto);
			}
			return dtos;
		} else
			throw new RuntimeException("user Id is not present .");
	}

	@Override
	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByVendorId(long id) {

		RechargeVendor vendor = vendorRepo.findById(id);
		if (vendor != null) {

			List<RechargeOrder> order = orderRepo.findByRechargePlanRechargeVendorVendorId(id);

			List<GetUserOrderHistoryDTO> orderDto = new ArrayList<>();

			for (RechargeOrder getOrder : order) {

				GetUserOrderHistoryDTO DTO = new GetUserOrderHistoryDTO();

				DTO.setOrderId(getOrder.getOrderId());
				DTO.setAmount(getOrder.getAmount());
				DTO.setContactNo(getOrder.getContactNo());
				DTO.setTransactionId(getOrder.getTransactionId());
				DTO.setUserId(getOrder.getRechargeUser().getUserId());
				DTO.setPlanId(getOrder.getRechargePlan().getId());

				orderDto.add(DTO);
			}
			return orderDto;
		} else
			throw new RuntimeException("vendor Id is not present.");
	}

	@Override
	public GetUserOrderHistoryDTO getRechargeHistoryByOrderId(long id) {

		RechargeOrder orderId = orderRepo.findById(id);
		if (orderId != null) {
			
		  GetUserOrderHistoryDTO hisDto = new GetUserOrderHistoryDTO();
		  
		  hisDto.setOrderId(orderId.getOrderId());
		  hisDto.setAmount(orderId.getAmount());
		  hisDto.setContactNo(orderId.getContactNo());
          hisDto.setTransactionId(orderId.getTransactionId());
          hisDto.setUserId(orderId.getRechargeUser().getUserId());
          hisDto.setPlanId(orderId.getRechargePlan().getId());
          
          return hisDto;
		}else 
			throw new RuntimeException("orderId is not present.");
	}
	
	
	@Override
	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByPlanId(long id) {

		RechargePlan plan = planRepo.findById(id);
		if (plan != null) {

			List<RechargeOrder> order = orderRepo.findByRechargePlanId(id);
			List<GetUserOrderHistoryDTO> dto = new ArrayList<>();

			for (RechargeOrder orderPlaced : order) {

				GetUserOrderHistoryDTO orderDto = new GetUserOrderHistoryDTO();

				orderDto.setOrderId(orderPlaced.getOrderId());
				orderDto.setAmount(orderPlaced.getAmount());
				orderDto.setContactNo(orderPlaced.getContactNo());
				orderDto.setTransactionId(orderPlaced.getTransactionId());
				orderDto.setUserId(orderPlaced.getRechargeUser().getUserId());
				orderDto.setPlanId(orderPlaced.getRechargePlan().getId());
				
				dto.add(orderDto);
				
			}
			return dto;
		}else 
			throw new RuntimeException("plan id is not present.");
	}

	@Override
	public List<GetUserOrderHistoryDTO> getAllOrderHistoryByCatId(long id) {
		
		ConfigRechargeCategory catId = configRepo.findById(id);		
		if (catId != null) {

			List<RechargeOrder> order = orderRepo.findByRechargePlanConfigRechargeCategoryCatId(id);
			List<GetUserOrderHistoryDTO> dto = new ArrayList<>();

			for (RechargeOrder rOrder : order) {

				GetUserOrderHistoryDTO DTO = new GetUserOrderHistoryDTO();

				DTO.setOrderId(rOrder.getOrderId());
				DTO.setAmount(rOrder.getAmount());
				DTO.setContactNo(rOrder.getContactNo());
				DTO.setTransactionId(rOrder.getTransactionId());
				DTO.setUserId(rOrder.getRechargeUser().getUserId());
				DTO.setPlanId(rOrder.getRechargePlan().getId());

				dto.add(DTO);
			}
			return dto;
		} else
			throw new RuntimeException("cat Id is not present.");
	}
}
