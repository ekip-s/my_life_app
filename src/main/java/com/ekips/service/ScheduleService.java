package com.ekips.service;

import com.ekips.dto.ScheduleInfo;
import com.ekips.model.Account;
import com.ekips.model.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleInfo createSchedule(Account account);
    void saveSchedule(List<Schedule> scheduleList, Account account);
    List<Schedule> getScheduleByAccount(Account account);
}
