package com.spring.recruitment;
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
public class OrganizationController {
	@Autowired
	private OrganizationRepository orgRepo;
	
	// index page
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	// get the organizations list from organization repository, and display it by list html
	@GetMapping("/organizations")
	public String organizationList(Organization organization, Model model) {
		model.addAttribute("organizations", orgRepo.findAll());
		return "organ-list";
	}
	
	// get the add html
	@GetMapping("/organization/add")
	public String renderAdd(Organization organization) {
		return "organ-add";
	}
	
	// post method, to save the organization to database through repository
	// if successfully saved to database, get the organization list and show in list page
	@PostMapping("/organization/add")
	public String add(@Valid Organization organization, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "organ-add";
		}
		orgRepo.save(organization);
		model.addAttribute("organizations", orgRepo.findAll());
		return "organ-list";
	}

	// get the specific organization by id through organization repository calls
	// render that organization information and displayed in update html
	@GetMapping("/organization/edit/{id}")
	public String renderUpdate(@PathVariable("id") int no, Model model) {
		Organization organ = orgRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid organization number:" + no));
		model.addAttribute("organization", organ);
		return "organ-update";
	}

	// post method, to update the organization to database through repository
	// handles error by try catch block
	// if successfully saved to database, get the organization list and show in list page
	@PostMapping("/organization/update/{id}")
	public String update(@PathVariable("id") int no, @Valid Organization organization, BindingResult result, Model model) {
		if (result.hasErrors()) {
			organization.setOrgId(no);
			return "organ-update";
		}
		orgRepo.save(organization);
		model.addAttribute("organizations", orgRepo.findAll());
		return "organ-list";
	}

	// delete method, to delete the organization from database through repository
	// handles error by try catch block
	// if successfully deleted, get the updated organization list and show in list page 
	@GetMapping("/organization/delete/{id}")
	public String delete(@PathVariable("id") int no, Model model) {
		Organization organ = orgRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid organization number:" + no));
		try {
			orgRepo.delete(organ);
			model.addAttribute("organizations", orgRepo.findAll());
			return "organ-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Organization may be linked to existing Category or Job, cannot be deleted, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}
}
