package com.nttdata.bootcamp.accounttransferservice.infrastructure.repository;

import com.nttdata.bootcamp.accounttransferservice.application.repository.TransferStatementRepository;
import com.nttdata.bootcamp.accounttransferservice.domain.TransferStatement;
import com.nttdata.bootcamp.accounttransferservice.infrastructure.model.dao.TransferStatementDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransferStatementCrudRepository implements TransferStatementRepository {
    private final ITransferStatementCrudRepository repository;


    @Override
    public Flux<TransferStatement> queryAll() {
        return repository.findAll().map(this::mapStatementFromDao);
    }

    @Override
    public Mono<TransferStatement> findById(String id) {
        return repository.findById(id).map(this::mapStatementFromDao);
    }

    @Override
    public Mono<TransferStatement> create(TransferStatement statement) {
        return Mono.just(statement)
                .doOnNext(s -> s.setId(null))
                .map(this::mapStatementToDao)
                .flatMap(repository::save)
                .map(this::mapStatementFromDao);
    }

    @Override
    public Mono<TransferStatement> update(String id, TransferStatement statement) {
        return repository.findById(id)
                .flatMap(s -> {
                    s.setId(id);
                    return Mono.just(statement)
                            .map(this::mapStatementToDao);
                }).flatMap(repository::save)
                .map(this::mapStatementFromDao);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    private TransferStatementDao mapStatementToDao(TransferStatement statement) {
        TransferStatementDao t = new TransferStatementDao();
        BeanUtils.copyProperties(statement, t);
        return t;
    }

    private TransferStatement mapStatementFromDao(TransferStatementDao td) {
        TransferStatement t = TransferStatement.builder().build();
        BeanUtils.copyProperties(td, t);
        return t;
    }

}
