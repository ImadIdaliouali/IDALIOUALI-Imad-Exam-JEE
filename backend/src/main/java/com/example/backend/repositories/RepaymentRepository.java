package com.example.backend.repositories;

import com.example.backend.entities.Repayment;
import com.example.backend.enums.RepaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByCreditId(Long creditId);
    List<Repayment> findByType(RepaymentType type);
    List<Repayment> findByDateBetween(Date startDate, Date endDate);
}
