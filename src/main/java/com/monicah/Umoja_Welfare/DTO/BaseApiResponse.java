package com.monicah.Umoja_Welfare.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseApiResponse {
    private Object data;
    private int status;
    private String message;
    private Object errors;
}
