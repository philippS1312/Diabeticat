package de.pdbm.business.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * MeasurementData entity from database. Holds Measurement data.
 */
@Entity
public class MeasurementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime timestamp;
    private String field1;
    private String field2;

    @ManyToOne
    @JoinColumn(name = "id")
    private Pet pet;

    public MeasurementData() {

    }
    public MeasurementData(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "MeasurementData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
