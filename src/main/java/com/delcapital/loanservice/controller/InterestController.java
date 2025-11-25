package com.delcapital.loanservice.controller;

import com.delcapital.loanservice.dto.InterestResponseDTO;
import com.delcapital.loanservice.service.InterestCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interest")
public class InterestController {

    private final InterestCalculationService interestCalculationService;

    public InterestController(InterestCalculationService interestCalculationService) {
        this.interestCalculationService = interestCalculationService;
    }

    @PostMapping("/run")
    public ResponseEntity<InterestResponseDTO> runInterestManually() {
        InterestResponseDTO response = interestCalculationService.calculateDailyInterest();
        return ResponseEntity.ok(response);
    }
}
