package com.nttdata.bootcamp.accounttransferservice.infrastructure.model.dao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferStatementDao {
    private String id;
    private String sourceNumber;
    private String targetNumber;
    private BigDecimal amount;
    private LocalDateTime dateTime;


}
