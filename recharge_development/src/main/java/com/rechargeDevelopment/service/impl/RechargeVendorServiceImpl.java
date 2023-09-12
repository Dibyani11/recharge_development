package com.rechargeDevelopment.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.GetRechargeVendorBalanceDTO;
import com.rechargeDevelopment.DTO.RechargeVendorDTO;
import com.rechargeDevelopment.model.RechargeVendor;
import com.rechargeDevelopment.repository.RechargeVendorRepository;
import com.rechargeDevelopment.service.RechargeVendorService;

@Service
public class RechargeVendorServiceImpl implements RechargeVendorService {

	@Autowired
	private RechargeVendorRepository vendorRepo;

	@Override
	public RechargeVendorDTO createRechargeVendor(RechargeVendorDTO vendorDto) {

		RechargeVendor rechargeVendor = new RechargeVendor();

		rechargeVendor.setFirstName(vendorDto.getFirstName());
		rechargeVendor.setLastName(vendorDto.getLastName());
		rechargeVendor.setGender(vendorDto.getGender());
		rechargeVendor.setAddressLine1(vendorDto.getAddressLine1());
		rechargeVendor.setAddressLine2(vendorDto.getAddressLine2());
		rechargeVendor.setDob(vendorDto.getDob());
		rechargeVendor.setTelecomOperatorName(vendorDto.getTelecomOperatorName());
		rechargeVendor.setEmailId(vendorDto.getEmailId());
		rechargeVendor.setBalance(new BigDecimal(0));

		RechargeVendor vendorEmailId = vendorRepo.findByEmailId(vendorDto.getEmailId());

		if (vendorEmailId == null) {

			RechargeVendor vendorOperatorName = vendorRepo
					.findByTelecomOperatorName(vendorDto.getTelecomOperatorName());

			if (vendorOperatorName == null) {

				RechargeVendor newvendor = vendorRepo.save(rechargeVendor);

				vendorDto.setVendorId(newvendor.getVendorId());
				vendorDto.setFirstName(newvendor.getFirstName());
				vendorDto.setLastName(newvendor.getLastName());
				vendorDto.setGender(newvendor.getGender());
				vendorDto.setAddressLine1(newvendor.getAddressLine1());
				vendorDto.setAddressLine2(newvendor.getAddressLine2());
				vendorDto.setDob(newvendor.getDob());
				vendorDto.setTelecomOperatorName(newvendor.getTelecomOperatorName());
				vendorDto.setEmailId(newvendor.getEmailId());
				vendorDto.setBalance(newvendor.getBalance());
				vendorDto.setCreatedOn(newvendor.getCreatedOn());
				vendorDto.setLastUpdatedOn(newvendor.getLastUpdatedOn());
			} else {
				throw new RuntimeException("Vendor with given TelecomOperatorName is already present.");
			}
		} else {
			throw new RuntimeException("Vendor with same emailId is present.");
		}
		return vendorDto;
	}

	@Override
	public RechargeVendorDTO getVendorById(long id) {

		RechargeVendor vendor = vendorRepo.findById(id);

		if (vendor != null) {

			RechargeVendorDTO vendorDto = new RechargeVendorDTO();
			vendorDto.setVendorId(vendor.getVendorId());
			vendorDto.setFirstName(vendor.getFirstName());
			vendorDto.setLastName(vendor.getLastName());
			vendorDto.setGender(vendor.getGender());
			vendorDto.setAddressLine1(vendor.getAddressLine1());
			vendorDto.setAddressLine2(vendor.getAddressLine2());
			vendorDto.setDob(vendor.getDob());
			vendorDto.setTelecomOperatorName(vendor.getTelecomOperatorName());
			vendorDto.setEmailId(vendor.getEmailId());
			vendorDto.setBalance(vendor.getBalance());
			vendorDto.setCreatedOn(vendor.getCreatedOn());
			vendorDto.setLastUpdatedOn(vendor.getLastUpdatedOn());

			return vendorDto;
		} else {
			throw new RuntimeException("vendor is not present");
		}
	}

	@Override
	public RechargeVendorDTO updateVendorById(RechargeVendorDTO rechargeVendorDTO) {

		RechargeVendor vendor = vendorRepo.findById(rechargeVendorDTO.getVendorId());

		if (vendor != null) {
			vendor.setFirstName(rechargeVendorDTO.getFirstName());
			vendor.setLastName(rechargeVendorDTO.getLastName());
			vendor.setGender(rechargeVendorDTO.getGender());
			vendor.setAddressLine1(rechargeVendorDTO.getAddressLine1());
			vendor.setAddressLine2(rechargeVendorDTO.getAddressLine2());
			vendor.setDob(rechargeVendorDTO.getDob());
			vendor.setTelecomOperatorName(rechargeVendorDTO.getTelecomOperatorName());
			vendor.setEmailId(rechargeVendorDTO.getEmailId());

			RechargeVendor updatedVendor = vendorRepo.save(vendor);

			rechargeVendorDTO.setVendorId(vendor.getVendorId());
			rechargeVendorDTO.setFirstName(vendor.getFirstName());
			rechargeVendorDTO.setLastName(vendor.getLastName());
			rechargeVendorDTO.setGender(vendor.getGender());
			rechargeVendorDTO.setAddressLine1(vendor.getAddressLine1());
			rechargeVendorDTO.setAddressLine2(vendor.getAddressLine2());
			rechargeVendorDTO.setDob(vendor.getDob());
			rechargeVendorDTO.setTelecomOperatorName(vendor.getTelecomOperatorName());
			rechargeVendorDTO.setEmailId(vendor.getEmailId());
			rechargeVendorDTO.setBalance(vendor.getBalance());
			rechargeVendorDTO.setCreatedOn(vendor.getCreatedOn());
			rechargeVendorDTO.setLastUpdatedOn(vendor.getLastUpdatedOn());

			return rechargeVendorDTO;
		} else {
			throw new RuntimeException("Vendor with given Id is not present.");
		}
	}

	@Override
	public GetRechargeVendorBalanceDTO getVendorBalanceById(long id) {

		RechargeVendor rVendor = vendorRepo.findById(id);

		if (rVendor != null) {

			GetRechargeVendorBalanceDTO vendorBalDto = new GetRechargeVendorBalanceDTO();

			vendorBalDto.setVendorId(rVendor.getVendorId());
			vendorBalDto.setFirstName(rVendor.getFirstName());
			vendorBalDto.setLastName(rVendor.getLastName());
			vendorBalDto.setEmailId(rVendor.getEmailId());
			vendorBalDto.setBalance(rVendor.getBalance());

			return vendorBalDto;
		} else {
			throw new RuntimeException("vendor is not present");
		}
	}

}
