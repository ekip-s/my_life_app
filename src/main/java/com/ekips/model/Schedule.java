package com.ekips.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;
    @Column(name = "schedule_num")
    private Integer scheduleNum;
    @Column(name = "pay_amount")
    private BigDecimal payAmount;
    @Column(name = "pay_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate payDate;

    public Schedule(Integer scheduleNum, BigDecimal payAmount, LocalDate payDate) {
        this.scheduleNum = scheduleNum;
        this.payAmount = payAmount;
        this.payDate = payDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
