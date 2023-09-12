package com.rechargeDevelopment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rechargeDevelopment.DTO.ConfigRechargeCategoryDTO;
import com.rechargeDevelopment.model.ConfigRechargeCategory;
import com.rechargeDevelopment.repository.ConfigRechargeCategoryRepository;
import com.rechargeDevelopment.service.ConfigRechargeCategoryService;

@Service
public class ConfigRechargeCategoryServiceImpl implements ConfigRechargeCategoryService {

	@Autowired
	ConfigRechargeCategoryRepository configCategoryRepo;

	@Override
	public ConfigRechargeCategoryDTO addConfigCategory(ConfigRechargeCategoryDTO configRechargeCategoryDTO) {

		ConfigRechargeCategory configCat = new ConfigRechargeCategory();

		configCat.setCategoryName(configRechargeCategoryDTO.getCategoryName());
		
		ConfigRechargeCategory addCategory = configCategoryRepo.save(configCat);

		configRechargeCategoryDTO.setCatId(addCategory.getCatId());
		configRechargeCategoryDTO.setCategoryName(addCategory.getCategoryName());
		configRechargeCategoryDTO.setCreatedOn(addCategory.getCreatedOn());
		configRechargeCategoryDTO.setLastUpdatedOn(addCategory.getLastUpdatedOn());

		return configRechargeCategoryDTO;
	}

	@Override
	public List<ConfigRechargeCategoryDTO> getAllRechargeCategory() {

		List<ConfigRechargeCategory> rconfigCategory = configCategoryRepo.findAll();

		List<ConfigRechargeCategoryDTO> configDto = new ArrayList<>();

		for (ConfigRechargeCategory getAllCategory : rconfigCategory) {

			ConfigRechargeCategoryDTO configcategoryDto = new ConfigRechargeCategoryDTO();

			configcategoryDto.setCatId(getAllCategory.getCatId());
			configcategoryDto.setCategoryName(getAllCategory.getCategoryName());
			configcategoryDto.setCreatedOn(getAllCategory.getCreatedOn());
			configcategoryDto.setLastUpdatedOn(getAllCategory.getLastUpdatedOn());

			configDto.add(configcategoryDto);
		}
		return configDto;
	}
}
