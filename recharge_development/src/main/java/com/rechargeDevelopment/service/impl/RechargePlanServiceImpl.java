package com.rechargeDevelopment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.RechargePlanDTO;
import com.rechargeDevelopment.model.ConfigRechargeCategory;
import com.rechargeDevelopment.model.RechargePlan;
import com.rechargeDevelopment.model.RechargeVendor;
import com.rechargeDevelopment.repository.ConfigRechargeCategoryRepository;
import com.rechargeDevelopment.repository.RechargePlanRepository;
import com.rechargeDevelopment.repository.RechargeVendorRepository;
import com.rechargeDevelopment.service.RechargePlanService;

@Service
public class RechargePlanServiceImpl implements RechargePlanService {

	@Autowired
	RechargeVendorRepository vendorRepo;

	@Autowired
	RechargePlanRepository rplanRepo;

	@Autowired
	ConfigRechargeCategoryRepository configRepo;

	@Override
	public RechargePlanDTO addRechargePlan(RechargePlanDTO rechargePlanDTO) {

		RechargePlan rplan = new RechargePlan();

		RechargeVendor rvid = vendorRepo.findByVendorId(rechargePlanDTO.getVendorId());
		if (rvid != null) {
			ConfigRechargeCategory cid = configRepo.findByCatId(rechargePlanDTO.getCatId());
			if (cid != null) {

				RechargePlan vendorAmount = rplanRepo.findByRechargeVendorVendorIdAndRechargeAmount(
						rechargePlanDTO.getVendorId(), rechargePlanDTO.getRechargeAmount());

				if (vendorAmount == null) {
					rplan.setRechargeAmount(rechargePlanDTO.getRechargeAmount());
					rplan.setDescription(rechargePlanDTO.getDescription());
					rplan.setValidity(rechargePlanDTO.getValidity());
					rplan.setRechargeVendor(rvid);
					rplan.setConfigRechargeCategory(cid);

					RechargePlan addPlan = rplanRepo.save(rplan);

					rechargePlanDTO.setId(addPlan.getId());
					rechargePlanDTO.setRechargeAmount(addPlan.getRechargeAmount());
					rechargePlanDTO.setDescription(addPlan.getDescription());
					rechargePlanDTO.setValidity(addPlan.getValidity());
					rechargePlanDTO.setVendorId(addPlan.getRechargeVendor().getVendorId());
					rechargePlanDTO.setCatId(addPlan.getConfigRechargeCategory().getCatId());
					rechargePlanDTO.setCreatedOn(addPlan.getCreatedOn());
					rechargePlanDTO.setLastUpdatedOn(addPlan.getLastUpdatedOn());

				} else {
					throw new RuntimeException("Vendor with same Amount is present .");
				}
			} else {
				throw new RuntimeException("RechargeCategory is not present .");
			}
		} else {
			throw new RuntimeException("Recharge vendor is not present .");
		}
		return rechargePlanDTO;
	}

	@Override
	public RechargePlanDTO updatePlanByVendorId(RechargePlanDTO rechargePlanDTO) {

		RechargePlan plan = rplanRepo.findById(rechargePlanDTO.getId());
		if (plan != null) {

			RechargePlan vendorAmount = rplanRepo.findByRechargeVendorVendorIdAndRechargeAmount(
					rechargePlanDTO.getVendorId(), rechargePlanDTO.getRechargeAmount());

			if (vendorAmount == null) {
				plan.setId(rechargePlanDTO.getId());
				plan.setRechargeAmount(rechargePlanDTO.getRechargeAmount());
				plan.setDescription(rechargePlanDTO.getDescription());
				plan.setValidity(rechargePlanDTO.getValidity());

				RechargePlan addPlan = rplanRepo.save(plan);

				rechargePlanDTO.setId(addPlan.getId());
				rechargePlanDTO.setRechargeAmount(addPlan.getRechargeAmount());
				rechargePlanDTO.setDescription(addPlan.getDescription());
				rechargePlanDTO.setValidity(addPlan.getValidity());
				rechargePlanDTO.setVendorId(addPlan.getRechargeVendor().getVendorId());
				rechargePlanDTO.setCatId(addPlan.getConfigRechargeCategory().getCatId());
				rechargePlanDTO.setCreatedOn(addPlan.getCreatedOn());
				rechargePlanDTO.setLastUpdatedOn(addPlan.getLastUpdatedOn());

			} else {
				throw new RuntimeException("Vendor with same Amount is present .");
			}
		} else {
			throw new RuntimeException("Recharge plan is not present .");
		}

		return rechargePlanDTO;
	}

	@Override
	public RechargePlanDTO getRechargePlanById(long id) {

		RechargePlan plan = rplanRepo.findById(id);

		if (plan != null) {

			RechargePlanDTO planDto = new RechargePlanDTO();

			planDto.setId(plan.getId());
			planDto.setRechargeAmount(plan.getRechargeAmount());
			planDto.setDescription(plan.getDescription());
			planDto.setValidity(plan.getValidity());
			planDto.setVendorId(plan.getRechargeVendor().getVendorId());
			planDto.setCatId(plan.getConfigRechargeCategory().getCatId());
			planDto.setCreatedOn(plan.getCreatedOn());
			planDto.setLastUpdatedOn(plan.getLastUpdatedOn());

			return planDto;
		} else {
			throw new RuntimeException("No such RechargePlan with id present.");
		}
	}

	@Override
	public List<RechargePlanDTO> getRechargePlanByRechargeVendorId(long vendorId) {

		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vendorId);
		if (rechargeVendor != null) {

			List<RechargePlan> rplan = rplanRepo.findByRechargeVendorVendorId(vendorId);
			List<RechargePlanDTO> planDto = new ArrayList<>();

			for (RechargePlan rechargePlan : rplan) {

				RechargePlanDTO pdto = new RechargePlanDTO();
				pdto.setId(rechargePlan.getId());
				pdto.setRechargeAmount(rechargePlan.getRechargeAmount());
				pdto.setDescription(rechargePlan.getDescription());
				pdto.setValidity(rechargePlan.getValidity());
				pdto.setVendorId(rechargePlan.getRechargeVendor().getVendorId());
				pdto.setCatId(rechargePlan.getConfigRechargeCategory().getCatId());
				pdto.setCreatedOn(rechargePlan.getCreatedOn());
				pdto.setLastUpdatedOn(rechargePlan.getLastUpdatedOn());

				planDto.add(pdto);
			}
			return planDto;
		} else {
			throw new RuntimeException("vendorId is not present.");
		}
	}

}
