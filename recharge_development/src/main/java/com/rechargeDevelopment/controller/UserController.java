package com.rechargeDevelopment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rechargeDevelopment.DTO.GetAllConfigCategoryDTO;
import com.rechargeDevelopment.DTO.GetAllTelecomOperatorNameDTO;
import com.rechargeDevelopment.DTO.GetRechargePlanDTO;
import com.rechargeDevelopment.DTO.GetUserOrderHistoryDTO;
import com.rechargeDevelopment.DTO.RechargeOrderDTO;
import com.rechargeDevelopment.DTO.RechargeUserDTO;
import com.rechargeDevelopment.DTO.UserRechargeAccountDTO;
import com.rechargeDevelopment.service.RechargeOrderService;
import com.rechargeDevelopment.service.RechargeUserService;
import com.rechargeDevelopment.service.UserRechargeAccountService;

@RestController
@RequestMapping("/u1/user")
public class UserController {

	@Autowired
	RechargeUserService userService;

	@Autowired
	RechargeOrderService orderService;
	
	@Autowired
	UserRechargeAccountService accountService;

	//localhost:8085/u1/user/addUser
	@PostMapping("/addUser")
	private RechargeUserDTO createRechargeUser(@RequestBody RechargeUserDTO rechargeUserDTO) {
		return userService.addRechargeUser(rechargeUserDTO);
	}

	//localhost:8085/u1/user/updateUser
	@PutMapping("/updateUser")
	private RechargeUserDTO updateRechargeUser(@RequestBody RechargeUserDTO rechargeUserDTO) {
		return userService.updateUser(rechargeUserDTO);
	}

	//localhost:8085/u1/user/getUser/1
	@GetMapping("/getUser/{id}")
	private RechargeUserDTO getRechargeUser(@PathVariable long id) {
		return userService.getUserById(id);
	}

	//localhost:8085/u1/user/getOperatorName
	@GetMapping("/getOperatorName")
	private List<GetAllTelecomOperatorNameDTO> getAllOperatorName() {
		return userService.getOperatorName();
	}

	//localhost:8085/u1/user/getAllCategory
	@GetMapping("/getAllCategory")
	private List<GetAllConfigCategoryDTO> getAllConfigCategory() {
		return userService.getConfigRechargeCategory();
	}

	//localhost:8085/u1/user/getPlanId/1
	@GetMapping("/getPlanId/{id}")
	private GetRechargePlanDTO getRechargePlanById(@PathVariable long id) {
		return userService.getAllPlanById(id);
	}

	//localhost:8085/u1/user/planByVendorId/1
	@GetMapping("/planByVendorId/{id}")
	private List<GetRechargePlanDTO> getRechargePlanByVendorID(@PathVariable long id) {
		return userService.getPlanByVendorId(id);
	}

	//localhost:8085/u1/user/planByVendorIdAndCatId/1/1
	@GetMapping("/planByVendorIdAndCatId/{vendorId}/{catId}")
	private List<GetRechargePlanDTO> getRechargePlanByVenderAndCatId(@PathVariable long vendorId,
			@PathVariable long catId) {
		return userService.getPlanByVendorIdAndCatId(vendorId, catId);
	}

	//localhost:8085/u1/user/order
	@PostMapping("/order")
	private RechargeOrderDTO createRechargeOrder(@RequestBody RechargeOrderDTO rechargeOrderDTO) {
		return orderService.createRechargeOrder(rechargeOrderDTO);
	}

	//localhost:8085/u1/user/getBy/1
	@GetMapping("/getBy/{id}")
	private List<GetUserOrderHistoryDTO> getOrderByUserId(@PathVariable long id) {
		return orderService.getHistoryById(id);
	}

	//localhost:8085/u1/user/order/1
	@GetMapping("/order/{id}")
	private GetUserOrderHistoryDTO getOrderByOrderId(@PathVariable long id) {
		return orderService.getRechargeHistoryByOrderId(id);
	}
	
	//localhost:8085/u1/user/addAccount
	@PostMapping("/addAccount")
	private UserRechargeAccountDTO createRechargeUserAccount(@RequestBody UserRechargeAccountDTO userRechargeAccountDTO) {
		return accountService.addRechargeAccount(userRechargeAccountDTO);
	}
	
	//localhost:8085/u1/user/updateAccount
	@PutMapping("/updateAccount")
	private UserRechargeAccountDTO updateRechargeUserAccount(@RequestBody UserRechargeAccountDTO userRechargeAccountDTO) {
		return accountService.updateRechargeAccount(userRechargeAccountDTO);
	}
	
	//localhost:8085/u1/user/getAccount/1
	@GetMapping("/getAccount/{id}")
	private List<UserRechargeAccountDTO> getAccountById(@PathVariable long id){
		return accountService.getSavedAccountById(id);
	}
	
	//localhost:8085/u1/user/deleteAccount/2
	@DeleteMapping("/deleteAccount/{id}")
	private Object deleteAccountById(@PathVariable long id) {
		return accountService.deleteAccountById(id);
	}
	
	//localhost:8085/u1/user/getAllFavNum/1
	@GetMapping("/getAllFavNum/{id}")
	private List<UserRechargeAccountDTO> getRechargeUserFavNumber(@PathVariable long id){
		return accountService.getAllFavNumberByUserId(id);
	}
	
	//localhost:8085/u1/user/getDefaultNum/1
	@GetMapping("/getDefaultNum/{id}")
	private UserRechargeAccountDTO getRechargeUserDefaultNumber(@PathVariable long id) {
		return accountService.getDefaultNumberByUserId(id);
	}

}
