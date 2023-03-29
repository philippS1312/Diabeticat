package de.pdbm.business.customer.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Country entity from database. Holds country data.
 */
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    private String country;
    private LocalDateTime last_update;

    
    public Country(){
        
    }
    
    public Country(int country_id, String country) {
        this.country_id = country_id;
        this.country = country;
    }


    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    @PrePersist
    @PreUpdate
    protected void refreshLastUpdate(){
        this.last_update = LocalDateTime.now();
    }
}
