package com.delcapital.loanservice.service.impl;

import com.delcapital.loanservice.dto.InterestResponseDTO;
import com.delcapital.loanservice.entity.InterestHistory;
import com.delcapital.loanservice.entity.Loan;
import com.delcapital.loanservice.repository.InterestHistoryRepository;
import com.delcapital.loanservice.repository.LoanRepository;
import com.delcapital.loanservice.service.InterestCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InterestCalculationServiceImpl implements InterestCalculationService {

    private final LoanRepository loanRepository;
    private final InterestHistoryRepository interestHistoryRepository;

    public InterestCalculationServiceImpl(LoanRepository loanRepository,
                                          InterestHistoryRepository interestHistoryRepository) {
        this.loanRepository = loanRepository;
        this.interestHistoryRepository = interestHistoryRepository;
    }



    @Override
    public InterestResponseDTO calculateDailyInterest() {
        int skippedLoans = 0 , processedLoans = 0;
        LocalDate yesterday = LocalDate.now().minusDays(1);

        // Fetch all loans
        List<Loan> loans = loanRepository.findAll();

        for (Loan loan : loans) {

            // Check if interest already exists for that date to prevent duplicates
            boolean exists = interestHistoryRepository
                    .existsByLoanIdAndInterestDate(loan.getLoanId(), yesterday);

            if (exists) {
                // Skip — already calculated
                skippedLoans++;
                continue;
            }

            // Calculate daily interest
            BigDecimal dailyInterest = loan.getPrincipalOutstanding()
                    .multiply(loan.getAnnualInterestRate())
                    .divide(BigDecimal.valueOf(365), 8, BigDecimal.ROUND_HALF_UP);

            // Save history record
            InterestHistory history = new InterestHistory();
            history.setLoanId(loan.getLoanId());
            history.setInterestDate(yesterday);
            history.setInterestAmount(dailyInterest);

            interestHistoryRepository.save(history);

            // Update cumulative total
            BigDecimal updatedTotal = loan.getTotalInterestAccrued().add(dailyInterest);
            loan.setTotalInterestAccrued(updatedTotal);

            loanRepository.save(loan);

            processedLoans++;

        }

        return new InterestResponseDTO(processedLoans, skippedLoans,"Daily interest calculated successfully");

    }
}
