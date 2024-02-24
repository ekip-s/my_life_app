package com.ekips.controllers;

import com.ekips.dto.IncomingAccount;
import com.ekips.model.Account;
import com.ekips.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/my_life/account/api/v1")
@Tag(name="account_controller", description = "Управление счетами")
public class AccountController {

    private final AccountService accountService;

    @Operation(
            summary = "Создание договора",
            description = "Создаем договор: кредитный или депозитный. Запускает создание графика."
    )
    @PostMapping
    public Account createAccount(@RequestBody IncomingAccount incomingAccount) {
        log.info("AccountController POST запрос к методу createAccount. Параметры: {}.", incomingAccount);
        return accountService.createAccount(incomingAccount);
    }
}
