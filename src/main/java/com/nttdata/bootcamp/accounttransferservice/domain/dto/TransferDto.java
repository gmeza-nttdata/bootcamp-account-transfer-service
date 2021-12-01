package com.nttdata.bootcamp.accounttransferservice.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private String source;
    private String target;
    private BigDecimal amount;
}
