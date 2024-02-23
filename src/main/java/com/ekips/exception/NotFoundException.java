package com.ekips.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class NotFoundException extends RuntimeException {

    private final String reason;
    private final LocalDateTime timestamp;

    public NotFoundException(String message, String reason) {
        super(message);
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotFoundException that = (NotFoundException) o;
        return Objects.equals(reason, that.reason) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, timestamp);
    }
}
