package com.ashokit.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.report.entity.CitizenPlan;
import com.ashokit.report.request.SearchRequest;
import com.ashokit.report.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/")
	public String loadIndex(Model model) {
		model.addAttribute("searchObj", new SearchRequest());
		init(model);
		return "index";
	}

	public void init(Model model) {
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchObj") SearchRequest request, Model model) {
		init(model);
		//model.addAttribute("searchObj", request); //or use @ModelAttribute("searchObj") before SearchRequest in param
		List<CitizenPlan> planList = service.search(request);
		model.addAttribute("plans", planList);
		return "index";
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		
		response.addHeader("Content-Disposition", "attachment; filename=plan.xls");
		
		service.exportExcel(response);
	}
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		response.addHeader("Content-Disposition", "attachment; filename=plan.pdf");
		
		service.exportPdf(response);
		
	}

}
