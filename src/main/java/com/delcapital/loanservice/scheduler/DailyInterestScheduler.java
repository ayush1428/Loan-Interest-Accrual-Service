package com.delcapital.loanservice.scheduler;

import com.delcapital.loanservice.dto.InterestResponseDTO;
import com.delcapital.loanservice.service.InterestCalculationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyInterestScheduler {

    private final InterestCalculationService interestCalculationService;

    public DailyInterestScheduler(InterestCalculationService interestCalculationService) {
        this.interestCalculationService = interestCalculationService;
    }

    // Run every day at 8:11 PM
    @Scheduled(cron = "0 11 20 * * ?")
    public void runDailyInterestJob() {
        InterestResponseDTO message = interestCalculationService.calculateDailyInterest();
        System.out.println(message);
    }
}
