package com.nttdata.bootcamp.accounttransferservice.domain;

import com.nttdata.bootcamp.accounttransferservice.domain.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransferStatement {
    private String id;
    private String sourceNumber;
    private String targetNumber;
    private BigDecimal amount;
    private String info;
    private LocalDateTime dateTime;
    private BigDecimal fee;

    public static TransferStatement doTransfer(Account source, Account target, BigDecimal amount)
        throws IllegalArgumentException
    {
        if (source.getBalance().compareTo(amount) < 0)
            throw new IllegalArgumentException("Amount is greater than Balance in source");

        source.setBalance(source.getBalance().subtract(amount));
        target.setBalance(target.getBalance().add(amount));

        return TransferStatement.builder()
                .sourceNumber(source.getNumber())
                .targetNumber(target.getNumber())
                .amount(amount)
                .dateTime(LocalDateTime.now()).build();

    }


}
