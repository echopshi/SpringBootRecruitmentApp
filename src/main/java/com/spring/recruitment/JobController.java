package com.spring.recruitment;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobController {

	@Autowired
	private JobRepository jobRepo;
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	// get the jobs list from organization repository, and display it by list html
	@GetMapping("/jobs")
	public String jobList(Job job, Model model) {
		List<Job> jobs = jobRepo.findAll();
		for(Job j : jobs) {
			Organization org = orgRepo.findById(j.getOrgId()).orElseThrow(() -> new IllegalArgumentException("Invalid Entity"));
			j.setOrgName(org.getOrgName());
			
			Category cat = catRepo.findById(j.getJobCatId()).orElseThrow(() -> new IllegalArgumentException("Invalid Entity"));
			j.setCatName(cat.getCatName());
		}
		model.addAttribute("jobs", jobs);
		return "job-list";
	}
	
	// get the add html
	@GetMapping("/job/add")
	public String renderAdd(Job job, Model model) {
		model.addAttribute("organizations", orgRepo.findAll());
		model.addAttribute("categories", catRepo.findAll());
		return "job-add";
	}

	// post method, to save the job to database through repository
	// handles error by try catch block
	// if successfully saved to database, get the job list and show in list page
	@PostMapping("/job/add")
	public String add(@Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "job-add";
		}
		try {
			jobRepo.save(job);
			model.addAttribute("jobs", jobRepo.findAll());
			return "job-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Organization Id and Category Id must be matched with the Organization and Category List, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}

	// get the specific job by id through organization repository calls
	// render that job information and displayed in update html
	@GetMapping("/job/edit/{id}")
	public String renderUpdate(@PathVariable("id") int no, Model model) {
		Job job = jobRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid job number:" + no));
		model.addAttribute("job", job);
		model.addAttribute("organizations", orgRepo.findAll());
		model.addAttribute("categories", catRepo.findAll());
		return "job-update";
	}

	// post method, to update the job to database through repository
	// handles error by try catch block
	// if successfully saved to database, get the job list and show in list page
	@PostMapping("/job/update/{id}")
	public String update(@PathVariable("id") int no, @Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			job.setJobId(no);
			return "job-update";
		}
		try {
			jobRepo.save(job);
			model.addAttribute("jobs", jobRepo.findAll());
			return "job-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Organization Id and Category Id must be matched with the Organization and Category List, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}

	// delete method, to delete the job from database through repository
	// if successfully deleted, get the updated job list and show in list page 
	@GetMapping("/job/delete/{id}")
	public String delete(@PathVariable("id") int no, Model model) {
		Job job = jobRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid job number:" + no));
		jobRepo.delete(job);
		model.addAttribute("jobs", jobRepo.findAll());
		return "job-list";
	}
}
