package com.ekips.service.impl;

import com.ekips.dto.IncomingAccount;
import com.ekips.dto.ScheduleInfo;
import com.ekips.model.Account;
import com.ekips.repository.AccountRepository;
import com.ekips.service.AccountService;
import com.ekips.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ScheduleService scheduleService, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.scheduleService = scheduleService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Account createAccount(IncomingAccount incomingAccount) {
        Account account = modelMapper.map(incomingAccount, Account.class);
        account.isNew();
        ScheduleInfo scheduleInfo = scheduleService.createSchedule(account);

        account.setAccountSumAmount(scheduleInfo.getScheduleSum());

        Account outgoingAccount = accountRepository.save(account);
        scheduleService.saveSchedule(scheduleInfo.getSchedule(), account);
        return outgoingAccount;
    }

    private Account getById(UUID id) {
        return accountRepository.findById(id).get();
    }
}
