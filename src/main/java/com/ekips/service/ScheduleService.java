package com.ekips.service;

import com.ekips.model.Account;
import com.ekips.model.Schedule;

import java.util.List;

public interface ScheduleService {

    void createSchedule(Account account);
    void saveSchedule(List<Schedule> scheduleList);
    List<Schedule> getScheduleByAccount(Account account);
}
