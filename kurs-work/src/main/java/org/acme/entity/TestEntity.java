package org.acme.entity;

import javax.persistence.*;

@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="user_name", length= 100, nullable = false)
    private String userName;


    @Column(name="vorname", length= 50, nullable = true)
    private String vorname;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
