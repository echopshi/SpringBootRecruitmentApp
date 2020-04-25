package com.spring.recruitment;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="job")
public class Job {
	
	@Id
	@Column(name="jobid")
	private int jobId;
	
	@Column(name="jobcode")
	@Length(max=30)
	@NotEmpty(message="Job Code must be given")
	private String jobCode;
	
	@Column(name="jobname")
	@Length(max=30)
	@NotEmpty(message="Job Name must be given")
	private String jobName;
	
	@Column(name="jobdesc")
	@Length(max=3000)
	@NotEmpty(message="Job Desc must be given")
	private String jobDesc;
	
	@Column(name="pubdate")
	@Pattern(regexp="^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$", message="Date format is invalid")
	@NotNull(message="Publish Date must be given")
	private String pubDate;
	
	@Column(name="numvacancy")
	@Min(1)
	@NotNull(message="Number of vacancy must be given")
	private int numVacancy;
	
	@Column(name="orgid")
	private int orgId;
	
	@Column(name="jobcatid")
	private int jobCatId;
	
	@Column(name="orgname")
	private String orgName;
	
	@Column(name="catname")
	private String catName;
	
	public Job() {
		super();
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getNumVacancy() {
		return numVacancy;
	}

	public void setNumVacancy(int numVacancy) {
		this.numVacancy = numVacancy;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getJobCatId() {
		return jobCatId;
	}

	public void setJobCatId(int jobCatId) {
		this.jobCatId = jobCatId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String jobcatName) {
		this.catName = jobcatName;
	}
	
	
}
