package org.acme.jpa.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


// Attributes of the @Basic annotation are applied to JPA entities,
// whereas the attributes of @Column are applied to the database columns
// @Basic annotation's optional attribute defines whether the entity field can be null or not;
// on the other hand,
// @Column annotation's nullable attribute specifies whether the corresponding database column can be null
// use @Basic to indicate that a field should be lazily loaded
// The @Column annotation allows us to specify the name of the mapped database column
//
@Entity
public class Datatypes {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @Column(name = "a_long", nullable = true)
    Long aLong;

    @Column(name="a_string", length= 50, nullable = true)
    private String aString;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active = ActiveEnum.Active;

//    @Embedded
//    private ValidFromTo valid = new ValidFromTo();


    // will not be persisted
    @Transient
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public ActiveEnum getActive() {
        return active;
    }

    public void setActive(ActiveEnum active) {
        this.active = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
