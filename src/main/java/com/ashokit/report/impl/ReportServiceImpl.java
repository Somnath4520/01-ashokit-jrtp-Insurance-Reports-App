package com.ashokit.report.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.report.entity.CitizenPlan;
import com.ashokit.report.repo.CitizenPlanRepository;
import com.ashokit.report.request.SearchRequest;
import com.ashokit.report.service.ReportService;
import com.ashokit.report.utils.EmailSender;
import com.ashokit.report.utils.ExcelGenerator;
import com.ashokit.report.utils.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanRepository repository;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailSender mailSender;
	
	@Override
	public List<String> getPlanNames() {
		return repository.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repository.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan plan = new CitizenPlan();
		
		if(request.getPlanName()!=null && request.getPlanName()!="") {
			plan.setPlanName(request.getPlanName());
		}
		if(request.getPlanStatus()!=null && request.getPlanStatus()!="") {
			plan.setPlanStatus(request.getPlanStatus());
		}
		if(request.getGender()!=null && request.getGender()!="") {
			plan.setGender(request.getGender());
		}
		if(request.getStartDate()!=null && request.getStartDate()!="") {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(startDate, formatter);
			plan.setStart_date(date);
		}
		if(request.getEndDate()!=null && request.getEndDate()!="") {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(endDate, formatter);
			plan.setEnd_date(date);
		}
		return repository.findAll(Example.of(plan));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File file = new File("plan.xls");
		
		List<CitizenPlan> allPlans = repository.findAll();
		
		excelGenerator.generateExcel(response, allPlans,file);
		
		String subject = "Test mail subject";
		String body = "<h3> Welcome to test mail</h3>";
		String to = "nabadiptaghosh2001@gmail.com";
		mailSender.sendMail(subject, body, to,file);
		
		file.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		File file = new File("plan.pdf");
		
		List<CitizenPlan> allPlans = repository.findAll();
		
		pdfGenerator.generatePdf(response, allPlans,file);
		
		String subject = "Test mail subject";
		String body = "<h3> Welcome to test mail</h3>";
		String to = "nabadiptaghosh2001@gmail.com";
		mailSender.sendMail(subject, body, to,file);
		
		file.delete();
		
		return true;
	}

}
