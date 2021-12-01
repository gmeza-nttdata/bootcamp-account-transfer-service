package com.nttdata.bootcamp.accounttransferservice.application.repository;

import com.nttdata.bootcamp.accounttransferservice.domain.TransferStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransferStatementRepository {
    Flux<TransferStatement> queryAll();
    Mono<TransferStatement> findById(String id);
    Mono<TransferStatement> create(TransferStatement statement);
    Mono<TransferStatement> update(String id, TransferStatement statement);
    Mono<Void> delete(String id);
}
