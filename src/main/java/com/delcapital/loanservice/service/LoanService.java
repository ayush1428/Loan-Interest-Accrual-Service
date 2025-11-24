package com.delcapital.loanservice.service;

import com.delcapital.loanservice.entity.Loan;
import com.delcapital.loanservice.entity.InterestHistory;

import java.util.List;

public interface LoanService {

    Loan getLoanById(Long loanId);

    List<InterestHistory> getInterestHistory(Long loanId);
}
