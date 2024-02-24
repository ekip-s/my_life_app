package com.ekips.model;

import com.ekips.enums.AccountType;
import com.ekips.enums.Capitalization;
import com.ekips.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "account_type")
    @Enumerated(EnumType.ORDINAL)
    private AccountType type;
    @Column(name = "currency")
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;
    @Column(name = "capitalization")
    @Enumerated(EnumType.ORDINAL)
    private Capitalization capitalization;
    @Column(name = "opening_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;
    @Column(name = "first_payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstPaymentDate;
    @Column(name = "last_payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastPaymentDate;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "payment")
    private Double payment;
    @Column(name = "account_sum_amount")
    private Double accountSumAmount;
    @Column(name = "deposit_amount")
    private Double depositAmount;
    @Column(name = "last_payment")
    private Double lastPayment;
    @Column(name = "create_dt")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime createDT;
    @Column(name = "last_update_dt")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime lastUpdateDT;
    @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
    private List<Schedule> schedule;

    public void isNew() {
        this.createDT=LocalDateTime.now();
        this.lastUpdateDT=LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
