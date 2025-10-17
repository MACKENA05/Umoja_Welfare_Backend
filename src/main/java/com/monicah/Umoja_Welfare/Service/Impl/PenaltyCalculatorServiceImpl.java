package com.monicah.Umoja_Welfare.Service.Impl;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.Service.PenaltyCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
@RequiredArgsConstructor
@Slf4j
@Service
public class PenaltyCalculatorServiceImpl implements PenaltyCalculatorService {
    private static final BigDecimal BASE_AMOUNT = new BigDecimal("1000");
    private static final BigDecimal DAILY_LATE_PENALTY = new BigDecimal("50");
    @Override
    public BaseApiResponse calculateMonthlyContributionPenalty(LocalDate paymentDate) {
        LocalDate dueDate = LocalDate.of(paymentDate.getYear(), paymentDate.getMonth(), 20);
        if (!paymentDate.isAfter(dueDate)) {
            BigDecimal penalty = BigDecimal.ZERO;

            return new BaseApiResponse(
                    true,
                    200,
                    "Payment made on time. Base contribution: KES "+ BASE_AMOUNT,
                    penalty
            );

        }
        log.info("PenaltyCalc - paymentDate: {}, dueDate: {}", paymentDate, dueDate);
        log.info("PenaltyCalc - isAfter: {}", paymentDate.isAfter(dueDate));
        log.info("PenaltyCalc - daysLate: {}", Duration.between(dueDate.atStartOfDay(), paymentDate.atStartOfDay()).toDays());

        long daysLate = Duration.between(dueDate.atStartOfDay(), paymentDate.atStartOfDay()).toDays();

        BigDecimal penalty = DAILY_LATE_PENALTY.multiply(BigDecimal.valueOf(daysLate));

              String message = String.format(
                "Payment late by %d days. Penalty: KES %s. ",
                daysLate, penalty );

        return new BaseApiResponse(penalty,200, "Payment late by %d days. Penalty: KES %s.", null );
    }
}
