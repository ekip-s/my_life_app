package com.ekips.service.impl;

import com.ekips.model.Account;
import com.ekips.model.Schedule;
import com.ekips.repository.ScheduleRepository;
import com.ekips.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void createSchedule(Account account) {

    }

    @Override
    public void saveSchedule(List<Schedule> scheduleList) {

    }

    @Override
    public List<Schedule> getScheduleByAccount(Account account) {
        return null;
    }
}
