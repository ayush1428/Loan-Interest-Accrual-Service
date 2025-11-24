package com.delcapital.loanservice.repository;

import com.delcapital.loanservice.entity.InterestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InterestHistoryRepository extends JpaRepository<InterestHistory, Long> {

    List<InterestHistory> findByLoanId(Long loanId);

    boolean existsByLoanIdAndInterestDate(Long loanId, LocalDate interestDate);
}
