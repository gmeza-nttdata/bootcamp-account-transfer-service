package com.nttdata.bootcamp.accounttransferservice.application.service;

import com.nttdata.bootcamp.accounttransferservice.domain.entity.Account;
import org.springframework.stereotype.Component;

/** This class implements CRUD methods.
 *
 * @author gmeza
 *
 */
@Component
public interface AccountService extends IService<Account, String> {


}
