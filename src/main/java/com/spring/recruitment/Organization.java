package com.spring.recruitment;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="organization")
public class Organization {
	
	@Id
	@Column(name="orgid")
	private int orgId;
	
	@NotEmpty(message="Organization name must be given")
	@Length(max=30)
	@Column(name="orgname")
	private String orgName;
	
	@NotEmpty(message="Organization postal code must be given")
	@Pattern(regexp="^[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1}[- ]{0,1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}$", message="Postal Code is invalid")
	@Length(max=30)
	@Column(name="postalcode") 
	private String postalCode;
	
	@NotEmpty(message="Organization address must be given")
	@Length(max=200)
	@Column(name="address")
	private String address;
	
	@NotEmpty(message="Organization phone number must be given")
	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Phone number is invalid")
	@Length(max=20)
	@Column(name="phoneno")
	private String phoneNo;
	
	@NotEmpty(message="Organization email must be given")
	@Email(message="Valid Email Address")
	@Length(max=30)
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="Organization website must be given")
	@Length(max=50)
	@Column(name="website")
	private String website;

	public Organization() {
		super();
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
