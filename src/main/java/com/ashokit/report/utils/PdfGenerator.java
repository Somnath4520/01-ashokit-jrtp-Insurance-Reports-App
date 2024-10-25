package com.ashokit.report.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ashokit.report.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {
	
	public void generatePdf(HttpServletResponse response, List<CitizenPlan> plansList,File f) throws  Exception {
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		PdfWriter.getInstance(document, new FileOutputStream(f));
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Citizen Plan Data",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.0f, 3.0f, 4.0f, 4.5f, 4.5f, 3.5f, 3.5f});
        table.setSpacingBefore(10);
		
		table.addCell("ID");
		table.addCell("Holder Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit amnt");
		table.addCell("Gender");
		
		//List<CitizenPlan> plans = repository.findAll();
		
		for(CitizenPlan plan:plansList) {
			table.addCell(plan.getCitizenId().toString());
			table.addCell(plan.getCitizenName().toString());
			table.addCell(plan.getPlanName().toString());
			table.addCell(plan.getPlanStatus().toString());
			
			if(plan.getStart_date()!=null) {
				table.addCell(plan.getStart_date().toString());
			}else {
				table.addCell("N/A");
			}
			if(plan.getEnd_date()!=null) {
				table.addCell(plan.getEnd_date().toString());
			}else {
				table.addCell("N/A");
			}
			if(plan.getBenefit_amnt()!=null) {
				table.addCell(plan.getBenefit_amnt().toString());
			}else {
				table.addCell("N/A");
			}
			
			table.addCell(plan.getGender().toString());
			
		}
		
		
		document.add(p);
		document.add(table);
		
		document.close();
		
	}

}
