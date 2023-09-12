package com.rechargeDevelopment.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "RECHARGE_PLAN")
public class RechargePlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "RECHARGE_AMOUNT")
	private BigDecimal rechargeAmount;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "VALIDITY")
	private String validity;
	
	@Column(name = "CREATED_ON", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdOn;
	
	@Column(name = "LAST_UPDATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastUpdatedOn;
	
	@ManyToOne
	@JoinColumn(name = "VENDOR_ID")
	private RechargeVendor rechargeVendor;
	
	@ManyToOne
	@JoinColumn(name = "CAT_ID")
	private ConfigRechargeCategory configRechargeCategory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public RechargeVendor getRechargeVendor() {
		return rechargeVendor;
	}

	public void setRechargeVendor(RechargeVendor rechargeVendor) {
		this.rechargeVendor = rechargeVendor;
	}

	public ConfigRechargeCategory getConfigRechargeCategory() {
		return configRechargeCategory;
	}

	public void setConfigRechargeCategory(ConfigRechargeCategory configRechargeCategory) {
		this.configRechargeCategory = configRechargeCategory;
	}
}
