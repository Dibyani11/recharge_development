package com.rechargeDevelopment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rechargeDevelopment.DTO.ConfigRechargeCategoryDTO;
import com.rechargeDevelopment.DTO.GetRechargeVendorBalanceDTO;
import com.rechargeDevelopment.DTO.GetUserOrderHistoryDTO;
import com.rechargeDevelopment.DTO.RechargePlanDTO;
import com.rechargeDevelopment.DTO.RechargeVendorDTO;
import com.rechargeDevelopment.service.ConfigRechargeCategoryService;
import com.rechargeDevelopment.service.RechargeOrderService;
import com.rechargeDevelopment.service.RechargePlanService;
import com.rechargeDevelopment.service.RechargeVendorService;

@RestController
@RequestMapping("/v1/vendor")
public class VendorController {

	@Autowired
	private RechargeVendorService rechargeVendorService;

	@Autowired
	private ConfigRechargeCategoryService configService;

	@Autowired
	private RechargePlanService planService;

	@Autowired
	private RechargeOrderService orderService;

	//localhost:8085/v1/vendor/nv
	@PostMapping("/nv")
	private RechargeVendorDTO createVendor(@RequestBody RechargeVendorDTO rechargeVendorDTO) {
		return rechargeVendorService.createRechargeVendor(rechargeVendorDTO);
	}

	//localhost:8085/v1/vendor/vId/1
	@GetMapping("/vId/{id}")
	private RechargeVendorDTO getRechargeVendorById(@PathVariable long id) {
		return rechargeVendorService.getVendorById(id);
	}

	//localhost:8085/v1/vendor/update
	@PutMapping("/update")
	private RechargeVendorDTO updateRechargeVendor(@RequestBody RechargeVendorDTO rechargeVendorDTO) {
		return rechargeVendorService.updateVendorById(rechargeVendorDTO);
	}

	//localhost:8085/v1/vendor/addCategory
	@PostMapping("/addCategory")
	private ConfigRechargeCategoryDTO addConfigCategory(
			@RequestBody ConfigRechargeCategoryDTO configRechargeCategoryDTO) {
		return configService.addConfigCategory(configRechargeCategoryDTO);
	}

	//localhost:8085/v1/vendor/allCategory
	@GetMapping("/allCategory")
	private List<ConfigRechargeCategoryDTO> getAllConfigCategory() {
		return configService.getAllRechargeCategory();
	}

	//localhost:8085/v1/vendor/addPlan
	@PostMapping("/addPlan")
	private RechargePlanDTO addRechargePlan(@RequestBody RechargePlanDTO rechargePlanDTO) {
		return planService.addRechargePlan(rechargePlanDTO);
	}

	//localhost:8085/v1/vendor/updatePlan
	@PutMapping("/updatePlan")
	private RechargePlanDTO updateRechargePlanByPlanId(@RequestBody RechargePlanDTO rechargePlanDTO) {
		return planService.updatePlanByVendorId(rechargePlanDTO);
	}

	//localhost:8085/v1/vendor/getPlanById/1
	@GetMapping("/getPlanById/{id}")
	private RechargePlanDTO getRplanById(@PathVariable long id) {
		return planService.getRechargePlanById(id);
	}

	//localhost:8085/v1/vendor/getPlanByVid/1
	@GetMapping("/getPlanByVid/{id}")
	private List<RechargePlanDTO> getPlanByVendorId(@PathVariable long id) {
		return planService.getRechargePlanByRechargeVendorId(id);
	}

	//localhost:8085/v1/vendor/vBalance/1
	@GetMapping("/vBalance/{id}")
	private GetRechargeVendorBalanceDTO getBalanceOfVendor(@PathVariable long id) {
		return rechargeVendorService.getVendorBalanceById(id);
	}

	//localhost:8085/v1/vendor/vendorhistory/1
	@GetMapping("/vendorhistory/{id}")
	private List<GetUserOrderHistoryDTO> getAllOrderByVendorId(@PathVariable long id) {
		return orderService.getAllOrderHistoryByVendorId(id);
	}

	//localhost:8085/v1/vendor/order/1
	@GetMapping("/order/{id}")
	private GetUserOrderHistoryDTO getOrderByOrderId(@PathVariable long id) {
		return orderService.getRechargeHistoryByOrderId(id);
	}

	//localhost:8085/v1/vendor/plan/1
	@GetMapping("/plan/{id}")
	private List<GetUserOrderHistoryDTO> getAllOrderHistoryByPlanId(@PathVariable long id) {
		return orderService.getAllOrderHistoryByPlanId(id);
	}

	//localhost:8085/v1/vendor/cat/1
	@GetMapping("/cat/{id}")
	private List<GetUserOrderHistoryDTO> getAllOrderHistoryByCatId(@PathVariable long id) {
		return orderService.getAllOrderHistoryByCatId(id);
	}

}
