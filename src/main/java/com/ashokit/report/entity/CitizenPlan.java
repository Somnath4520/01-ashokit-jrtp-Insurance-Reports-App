package com.ashokit.report.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Citizen_Plan_Details")
public class CitizenPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
    private String citizenName;
    private String planName;
    private String planStatus;
    private String gender;
    private LocalDate start_date;
    private LocalDate end_date;
    private Double benefit_amnt;
    private String denial_reason;
    private String termination_reason;
    private LocalDate terminated_date;

}
