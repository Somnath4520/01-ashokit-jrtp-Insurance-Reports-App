package com.ashokit.report.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.ashokit.report.entity.CitizenPlan;



@Component
public class ExcelGenerator {
	
	public void generateExcel(HttpServletResponse response, List<CitizenPlan> planList, File file) throws Exception {
		
		//Workbook workbook = new XSSFWorkbook();
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("plans-sheet");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Start date");
		headerRow.createCell(5).setCellValue("End date");
		headerRow.createCell(6).setCellValue("Benefit amnt");
		headerRow.createCell(7).setCellValue("Gender");
		
		//List<CitizenPlan> planList = repository.findAll();
		int dataRowIndex=1;
		
		for(CitizenPlan plan:planList) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			if(plan.getStart_date()!=null) {
				dataRow.createCell(4).setCellValue(plan.getStart_date()+"");
			}else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			if(plan.getEnd_date()!=null) {
				dataRow.createCell(5).setCellValue(plan.getEnd_date()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if(plan.getBenefit_amnt()!=null) {
				dataRow.createCell(6).setCellValue(plan.getBenefit_amnt());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			dataRow.createCell(7).setCellValue(plan.getGender());
			
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
	}

}
