package org.acme.resource.json.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@XmlRootElement
public class TestDTO {

    String name;

    String vorname;

    // ocalDateTime datum = LocalDateTime.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }


    @Override
    public String toString() {
        return "TestDTO{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                '}';
    }
}

