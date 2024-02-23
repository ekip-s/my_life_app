package com.ekips.dto;

import com.ekips.enums.AccountType;
import com.ekips.enums.Capitalization;
import com.ekips.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность для создания нового счета")
public class IncomingAccount {
    @Schema(description = "credit")
    private AccountType type;
    @Schema(description = "RUB")
    private Currency currency;
    @Schema(description = "monthly")
    private Capitalization capitalization;
    @Schema(description = "2020-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;
    @Schema(description = "2020-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstPaymentDate;
    @Schema(description = "2020-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastPaymentDate;
    private Double rate;
    private Double payment;
    private Double depositAmount;
    private Double lastPayment;
}
