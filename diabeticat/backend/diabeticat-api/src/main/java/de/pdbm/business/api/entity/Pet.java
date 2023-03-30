package de.pdbm.business.api.entity;

import de.pdbm.business.api.entity.old.City;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Pet entity from database. Holds pet data.
 */
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy="pet")
    private List<MeasurementData> measurementDataList;

    public Pet() {

    }
    public Pet(String name, String type, Date birthdate) {
        this.name = name;
        this.type = type;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
