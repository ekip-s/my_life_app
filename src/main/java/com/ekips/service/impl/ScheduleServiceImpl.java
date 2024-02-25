package com.ekips.service.impl;

import com.ekips.dto.ScheduleInfo;
import com.ekips.enums.AccountType;
import com.ekips.enums.Capitalization;
import com.ekips.exception.BadRequestException;
import com.ekips.model.Account;
import com.ekips.model.Schedule;
import com.ekips.repository.ScheduleRepository;
import com.ekips.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleInfo createSchedule(Account account) {
        if (account.getType() == AccountType.deposit) {
            return createDebtSchedule(account);
        } else if (account.getType() == AccountType.credit) {
            return createCreditSchedule(account);
        } else {
            throw new BadRequestException("Такого типа кредита -> нет.", "Некорректный запрос.");
        }
    }

    @Override
    public void saveSchedule(List<Schedule> scheduleList, Account account) {
        Account AccountProxy = new Account(account.getId());
        List<Schedule> newScheduleList = scheduleList
                .stream()
                .peek(s -> s.setAccount(AccountProxy))
                .toList();
        scheduleRepository.saveAll(newScheduleList);
    }

    @Override
    public List<Schedule> getScheduleByAccount(Account account) {
        return null;
    }

    private BigDecimal getSumMath(BigDecimal amount, BigDecimal interestRate, long numberOfDays) {
        BigDecimal dailyInterestRate = interestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP)
                .divide(new BigDecimal("365"), 10, RoundingMode.HALF_UP);
        BigDecimal totalAmount = amount.multiply(dailyInterestRate.add(BigDecimal.ONE).pow(Long.valueOf(numberOfDays).intValue()));
        BigDecimal earnings = totalAmount.subtract(amount);
        return earnings.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal scheduleSum(Capitalization capitalization, BigDecimal amount,
                               BigDecimal rate, LocalDate initDate) {
        LocalDate endDate = initDate.plusMonths(1);

        if (capitalization == Capitalization.monthly) {
            BigDecimal yearSum = amount.divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).multiply(rate);
            BigDecimal daySum = yearSum.divide(BigDecimal.valueOf(365), 5, RoundingMode.HALF_UP);
            BigDecimal result = daySum.multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(initDate, endDate)));

            return result.setScale(2, RoundingMode.HALF_UP);
        } else {
            return getSumMath(amount, rate, ChronoUnit.DAYS.between(initDate, endDate));
        }
    }

    private ScheduleInfo createDebtSchedule(Account account) {
        List<Schedule> schedules = new ArrayList<>();
        ScheduleInfo scheduleInfo = new ScheduleInfo();
        BigDecimal sum = account.getDepositAmount();
        LocalDate lastPayDate = account.getOpeningDate();

        while (lastPayDate.plusMonths(1).isBefore(LocalDate.now())) {
            sum = sum.add(scheduleSum(account.getCapitalization(), sum, account.getRate(), lastPayDate));
            lastPayDate = lastPayDate.plusMonths(1);
        }

        scheduleInfo.setScheduleSum(sum);

        for (int i=0; i < 12; i ++) {
            BigDecimal nodeSum = scheduleSum(account.getCapitalization(), sum, account.getRate(), lastPayDate);
            Schedule scheduleNode = new Schedule(i+1, nodeSum, lastPayDate.plusMonths(1));
            sum = sum.add(nodeSum);
            schedules.add(scheduleNode);
            lastPayDate = lastPayDate.plusMonths(1);
        }

        scheduleInfo.setSchedule(schedules);

        return scheduleInfo;
    }




    private ScheduleInfo createCreditSchedule(Account account) {
        // проверяем даты;
        // создаем график;
        return null;
    }
}
