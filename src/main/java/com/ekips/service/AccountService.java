package com.ekips.service;

import com.ekips.dto.IncomingAccount;
import com.ekips.model.Account;

public interface AccountService {

    Account createAccount(IncomingAccount incomingAccount);
}
