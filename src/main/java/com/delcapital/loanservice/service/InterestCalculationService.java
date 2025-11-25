package com.delcapital.loanservice.service;

import com.delcapital.loanservice.dto.InterestResponseDTO;

public interface InterestCalculationService {

    InterestResponseDTO calculateDailyInterest();
}
