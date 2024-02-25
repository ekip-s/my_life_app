package com.ekips.dto;

import com.ekips.model.Schedule;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {

    private List<Schedule> schedule;
    private BigDecimal scheduleSum;
}
