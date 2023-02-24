package org.acme.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class ValidFromTo {

    @Column(name="from", nullable = true)
    private LocalDateTime from = LocalDateTime.now();

    @Column(name="to", nullable = true)
    private LocalDateTime to = LocalDateTime.now();

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "ValidFromTo{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
