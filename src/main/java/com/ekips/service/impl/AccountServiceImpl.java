package com.ekips.service.impl;

import com.ekips.dto.IncomingAccount;
import com.ekips.model.Account;
import com.ekips.repository.AccountRepository;
import com.ekips.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Account createAccount(IncomingAccount incomingAccount) {
        Account account = modelMapper.map(incomingAccount, Account.class);
        account.isNew();

        System.out.println(account);
        return null;
    }
}
