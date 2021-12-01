package com.nttdata.bootcamp.accounttransferservice.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Account {

    private String number;
    private Integer userId;
    private String type;
    private String currencyName;
    private BigDecimal balance;

}
