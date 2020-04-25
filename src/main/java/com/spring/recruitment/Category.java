package com.spring.recruitment;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@Column(name="jobcatid")
	private int jobCatId;
	
	@Column(name="catcode")
	@Length(max=10)
	@NotEmpty(message="Category Code must be given")
	private String catCode;
	
	@Column(name="catname")
	@Length(max=30)
	@NotEmpty(message="Category name must be given")
	private String catName;
	
	@Column(name="catdesc")
	@Length(max=1000)
	@NotEmpty(message="Category description must be given")
	private String catDesc;
	
	@Column(name="orgid")
	private int orgId;
	

	public Category() {
		super();
	}

	public int getJobCatId() {
		return jobCatId;
	}

	public void setJobCatId(int jobCatId) {
		this.jobCatId = jobCatId;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	
	
}
