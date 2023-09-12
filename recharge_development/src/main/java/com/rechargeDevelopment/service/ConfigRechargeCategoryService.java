package com.rechargeDevelopment.service;

import java.util.List;

import com.rechargeDevelopment.DTO.ConfigRechargeCategoryDTO;

public interface ConfigRechargeCategoryService {

	public ConfigRechargeCategoryDTO addConfigCategory(ConfigRechargeCategoryDTO configRechargeCategoryDTO);

	public List<ConfigRechargeCategoryDTO> getAllRechargeCategory();
}
