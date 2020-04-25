package com.spring.recruitment;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	// get the categories list from category repository, and display it by list html
	@GetMapping("/categories")
	public String categoryList(Category category, Model model) {
		getNames(model);
		return "cat-list";
	}
	
	// get the add html
	@GetMapping("/category/add")
	public String renderAdd(Category category, Model model) {
		model.addAttribute("organizations", orgRepo.findAll());
		return "cat-add";
	}

	// post method, to save the category to database through repository
	// handles error by try catch block
	// if successfully saved to database, get the category list and show in list page
	@PostMapping("/category/add")
	public String add(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cat-add";
		}
		try {
			catRepo.save(category);
			getNames(model);
			return "cat-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Organization Id must be matched with the Organization List, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}

	// get the specific category by id through category repository calls
	// render that category information and displayed in update html
	@GetMapping("/category/edit/{id}")
	public String renderUpdate(@PathVariable("id") int no, Model model) {
		Category category = catRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category number:" + no));
		model.addAttribute("category", category);
		model.addAttribute("organizations", orgRepo.findAll());
		return "cat-update";
	}

	// post method, to update the category to database through repository
	// handles error by try catch block
	// if successfully saved to database, get the category list and show in list page 
	@PostMapping("/category/update/{id}")
	public String update(@PathVariable("id") int no, @Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			category.setJobCatId(no);
			return "cat-update";
		}
		try {
			catRepo.save(category);
			getNames(model);
			return "cat-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Organization Id must be matched with the Organization List, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}

	// delete method, to delete the category from database through repository
	// handles error by try catch block
	// if successfully deleted, get the updated category list and show in list page 
	@GetMapping("/category/delete/{id}")
	public String delete(@PathVariable("id") int no, Model model) {
		Category category = catRepo.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category number:" + no));
		try {
			catRepo.delete(category);
			getNames(model);
			return "cat-list";
		}
		catch(Exception e) {
			model.addAttribute("errorMsg", "Category may be linked to existing Job, cannot be deleted, please check!");
			model.addAttribute("detail", e.getMessage());
			return "error";
		}
	}
	
	private void getNames(Model model) {
		List<Category> categories = catRepo.findAll();
		for(Category cat : categories) {
			Organization org = orgRepo.findById(cat.getOrgId()).orElseThrow(() -> new IllegalArgumentException("Invalid Entity"));
			cat.setOrgName(org.getOrgName());
		}
		model.addAttribute("categories", categories);
	}
}
