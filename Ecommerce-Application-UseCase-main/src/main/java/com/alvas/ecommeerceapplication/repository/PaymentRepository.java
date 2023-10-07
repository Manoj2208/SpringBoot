package com.alvas.ecommeerceapplication.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


	Page<Payment> findByUserUserIdAndPaymentDateBetween(long userId, LocalDate monthStartDate, LocalDate monthEndDate,
			Pageable pageable);


}
