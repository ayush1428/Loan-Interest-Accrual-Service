package com.delcapital.loanservice.service.impl;

import com.delcapital.loanservice.entity.InterestHistory;
import com.delcapital.loanservice.entity.Loan;
import com.delcapital.loanservice.repository.InterestHistoryRepository;
import com.delcapital.loanservice.repository.LoanRepository;
import com.delcapital.loanservice.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final InterestHistoryRepository interestHistoryRepository;

    public LoanServiceImpl(LoanRepository loanRepository,
                           InterestHistoryRepository interestHistoryRepository) {
        this.loanRepository = loanRepository;
        this.interestHistoryRepository = interestHistoryRepository;
    }

    @Override
    public Loan getLoanById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
    }

    @Override
    public List<InterestHistory> getInterestHistory(Long loanId) {
        return interestHistoryRepository.findByLoanId(loanId);
    }
}
