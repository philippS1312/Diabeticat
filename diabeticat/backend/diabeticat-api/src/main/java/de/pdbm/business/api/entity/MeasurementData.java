package de.pdbm.business.api.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * MeasurementData entity from database. Holds Measurement data.
 */
@Entity
public class MeasurementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private LocalDateTime timestamp;
    @Getter
    @Setter
    private String blood_sugar;
    @Getter
    @Setter
    private String insulin_dose;
    @Getter
    @Setter
    private String notice;

    @ManyToOne
    @JoinColumn(name = "id")
    @Getter
    private Pet pet;

    public MeasurementData() {

    }
    public MeasurementData(Pet pet, String blood_sugar, String insulin_dose, String notice) {
        this.blood_sugar = blood_sugar;
        this.insulin_dose = insulin_dose;
        this.notice = notice;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "MeasurementData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", blood_sugar='" + blood_sugar + '\'' +
                ", insulin_dose='" + insulin_dose + '\'' +
                ", notice='" + notice + '\'' +
                ", pet=" + pet +
                '}';
    }
}
