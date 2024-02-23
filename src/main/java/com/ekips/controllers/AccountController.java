package com.ekips.controllers;

import com.ekips.dto.IncomingAccount;
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
@Tag(name="custom-controller", description = "Кастомные методы")
public class AccountController {

    @Operation(
            summary = "Создание договора",
            description = "Создаем договор: кредитный или депозитный. Запускает создание графика."
    )
    @PostMapping
    public void createAccount(@RequestBody IncomingAccount incomingAccount) {

    }
}
