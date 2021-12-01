package com.nttdata.bootcamp.accounttransferservice.infrastructure.rest;

import com.nttdata.bootcamp.accounttransferservice.application.AccountTransferOperations;
import com.nttdata.bootcamp.accounttransferservice.domain.TransferStatement;
import com.nttdata.bootcamp.accounttransferservice.domain.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/account-transfers")
@RequiredArgsConstructor
public class AccountTransferController {

    private final AccountTransferOperations operations;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<TransferStatement>> transfer(@RequestBody TransferDto t) {
        return operations.transfer(t.getSource(), t.getTarget(), t.getAmount())
                .doOnError(err -> log.error(err.toString()))
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> Mono.just(ResponseEntity.badRequest()
                        .body(TransferStatement.builder().info(throwable.toString()).build()))
                );
    }

    @GetMapping(value = "/{source}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Flux<TransferStatement>>> getStatements(@PathVariable String source) {
        return Mono.just(
                ResponseEntity.ok(operations.getTransfersByNumber(source))
        );
    }

}
