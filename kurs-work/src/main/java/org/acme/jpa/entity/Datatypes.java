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


    @Transient
    private Integer age;



}
