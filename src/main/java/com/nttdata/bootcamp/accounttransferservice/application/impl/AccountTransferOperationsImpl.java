package com.nttdata.bootcamp.accounttransferservice.application.impl;

import com.nttdata.bootcamp.accounttransferservice.application.AccountTransferOperations;
import com.nttdata.bootcamp.accounttransferservice.domain.TransferStatement;
import com.nttdata.bootcamp.accounttransferservice.infrastructure.repository.TransferStatementCrudRepository;
import com.nttdata.bootcamp.accounttransferservice.infrastructure.service.AccountWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountTransferOperationsImpl implements AccountTransferOperations {

    private final AccountWebService accountWebService;
    private final TransferStatementCrudRepository statementRepository;


    @Override
    public Mono<TransferStatement> transfer(String source, String target, BigDecimal amount) {
        return accountWebService.get(source)
                .flatMap( s -> accountWebService.get(target)
                            .flatMap( t -> {
                                TransferStatement statement;
                                try {
                                     statement = TransferStatement.doTransfer(s, t, amount);
                                }
                                catch (IllegalArgumentException e) {
                                    return Mono.error(e);
                                }
                                return accountWebService.patch(s.getNumber(), s)
                                        .then(accountWebService.patch(t.getNumber(), t)
                                            .then(statementRepository.create(statement))
                                        );
                                    }
                            )

                );
    }

    @Override
    public Flux<TransferStatement> getTransfersByNumber(String number) {
        return statementRepository.queryAll()
                .filter(statement -> this.filterStatementById(statement, number));
    }

    @Override
    public Flux<TransferStatement> getAllTransferStatement() {
        return statementRepository.queryAll();
    }

    private Boolean filterStatementById(TransferStatement statement, String number) {
        return statement.getSourceNumber().equals(number) || statement.getTargetNumber().equals(number);
    }

}
