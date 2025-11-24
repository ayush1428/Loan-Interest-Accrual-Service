package com.delcapital.loanservice.controller;

import com.delcapital.loanservice.entity.Loan;
import com.delcapital.loanservice.entity.InterestHistory;
import com.delcapital.loanservice.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanDetails(@PathVariable Long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/{loanId}/interest-history")
    public ResponseEntity<List<InterestHistory>> getInterestHistory(@PathVariable Long loanId) {
        List<InterestHistory> history = loanService.getInterestHistory(loanId);
        return ResponseEntity.ok(history);
    }
}
