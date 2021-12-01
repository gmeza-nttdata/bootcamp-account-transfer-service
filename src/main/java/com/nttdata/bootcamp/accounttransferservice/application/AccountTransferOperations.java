package com.nttdata.bootcamp.accounttransferservice.application;

import com.nttdata.bootcamp.accounttransferservice.domain.TransferStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface AccountTransferOperations {

    Mono<TransferStatement> transfer(String source, String target, BigDecimal amount);
    Flux<TransferStatement> getTransfersByNumber(String number);

}
