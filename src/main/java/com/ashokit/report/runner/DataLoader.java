package com.ashokit.report.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashokit.report.entity.CitizenPlan;
import com.ashokit.report.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner{

	private CitizenPlanRepository planRepository;

    public DataLoader(CitizenPlanRepository planRepository){
        this.planRepository= planRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        planRepository.deleteAll();

        CitizenPlan c1 = new CitizenPlan();
        c1.setCitizenName("John");
        c1.setGender("Male");
        c1.setPlanName("Cash");
        c1.setPlanStatus("Approved");
        c1.setStart_date(LocalDate.now());
        c1.setEnd_date(LocalDate.now().plusMonths(6));
        c1.setBenefit_amnt(4000.00);

        CitizenPlan c2 = new CitizenPlan();
        c2.setCitizenName("Smith");
        c2.setGender("Male");
        c2.setPlanName("Cash");
        c2.setPlanStatus("Denied");
        c2.setDenial_reason("high  property");

        CitizenPlan c3 = new CitizenPlan();
        c3.setCitizenName("Param");
        c3.setGender("Male");
        c3.setPlanName("Cash");
        c3.setPlanStatus("Terminated");
        c3.setStart_date(LocalDate.now().minusMonths(4));
        c3.setEnd_date(LocalDate.now().plusMonths(2));
        c3.setBenefit_amnt(5000.00);
        c3.setTerminated_date(LocalDate.now());
        c3.setTermination_reason("Employed");


        CitizenPlan c4 = new CitizenPlan();
        c4.setCitizenName("Cathy");
        c4.setGender("FeMale");
        c4.setPlanName("Medical");
        c4.setPlanStatus("Approved");
        c4.setStart_date(LocalDate.now().minusMonths(3));
        c4.setEnd_date(LocalDate.now().plusMonths(3));
        c4.setBenefit_amnt(4500.00);

        CitizenPlan c5 = new CitizenPlan();
        c5.setCitizenName("Arun");
        c5.setGender("Male");
        c5.setPlanName("Medical");
        c5.setPlanStatus("Denied");
        c5.setDenial_reason("Rental income");

        CitizenPlan c6 = new CitizenPlan();
        c6.setCitizenName("Orlen");
        c6.setGender("FeMale");
        c6.setPlanName("Medical");
        c6.setPlanStatus("Terminated");
        c6.setStart_date(LocalDate.now().minusMonths(8));
        c6.setEnd_date(LocalDate.now().plusMonths(2));
        c6.setBenefit_amnt(3500.00);
        c6.setTerminated_date(LocalDate.now());
        c6.setTermination_reason("property problem");


        CitizenPlan c7 = new CitizenPlan();
        c7.setCitizenName("Simmy");
        c7.setGender("FeMale");
        c7.setPlanName("Food");
        c7.setPlanStatus("Approved");
        c7.setStart_date(LocalDate.now().plusMonths(1));
        c7.setEnd_date(LocalDate.now().plusMonths(5));
        c7.setBenefit_amnt(6000.00);

        CitizenPlan c8 = new CitizenPlan();
        c8.setCitizenName("Buttler");
        c8.setGender("Male");
        c8.setPlanName("Food");
        c8.setPlanStatus("Denied");
        c8.setDenial_reason("property income");

        CitizenPlan c9 = new CitizenPlan();
        c9.setCitizenName("Gautam");
        c9.setGender("Male");
        c9.setPlanName("Food");
        c9.setPlanStatus("Terminated");
        c9.setStart_date(LocalDate.now().minusMonths(5));
        c9.setEnd_date(LocalDate.now().plusMonths(1));
        c9.setBenefit_amnt(4000.00);
        c9.setTerminated_date(LocalDate.now());
        c9.setTermination_reason("Another plan available");


        CitizenPlan c10 = new CitizenPlan();
        c10.setCitizenName("Jenny");
        c10.setGender("FeMale");
        c10.setPlanName("Employment");
        c10.setPlanStatus("Approved");
        c10.setStart_date(LocalDate.now().minusMonths(7));
        c10.setEnd_date(LocalDate.now().plusMonths(1));
        c10.setBenefit_amnt(5500.00);

        CitizenPlan c11 = new CitizenPlan();
        c11.setCitizenName("Lilly");
        c11.setGender("FeMale");
        c11.setPlanName("Employment");
        c11.setPlanStatus("Denied");
        c11.setDenial_reason("Government Service");

        CitizenPlan c12 = new CitizenPlan();
        c12.setCitizenName("Vikas");
        c12.setGender("Male");
        c12.setPlanName("Employment");
        c12.setPlanStatus("Terminated");
        c12.setStart_date(LocalDate.now().minusMonths(4));
        c12.setEnd_date(LocalDate.now().plusMonths(2));
        c12.setBenefit_amnt(3500.00);
        c12.setTerminated_date(LocalDate.now());
        c12.setTermination_reason("Completed");

        List<CitizenPlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);

        planRepository.saveAll(list);

    }

}
