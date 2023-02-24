package org.acme.jpa.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Datatypes {

    @Temporal(TemporalType.TIME)
    public LocalTime time;

    @Temporal(TemporalType.DATE)
    public LocalDate birth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_time", nullable = true)
    private LocalDateTime creationTime;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;

    @Embedded
    private ValidFromTo valid;

    @Transient
    private Integer age;

}
