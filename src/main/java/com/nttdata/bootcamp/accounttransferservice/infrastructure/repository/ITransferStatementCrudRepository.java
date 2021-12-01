package com.nttdata.bootcamp.accounttransferservice.infrastructure.repository;

import com.nttdata.bootcamp.accounttransferservice.infrastructure.model.dao.TransferStatementDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ITransferStatementCrudRepository extends ReactiveCrudRepository<TransferStatementDao, String> {
}
