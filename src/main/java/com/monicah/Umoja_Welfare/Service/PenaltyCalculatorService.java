package com.monicah.Umoja_Welfare.Service;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PenaltyCalculatorService {
    BaseApiResponse calculateMonthlyContributionPenalty(LocalDate paymentDate);

}
