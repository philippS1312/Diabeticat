package de.pdbm.business.customer.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * City entity from database. Holds city data.
 */
@Entity
public class City {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;

    private String city;


    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private LocalDateTime last_update;

    public Country getCountry() {
        return country;
    }


    public City() {
        
    }

    public City(int city_id, String city, Country country) {
        this.city_id = city_id;
        this.city = city;
        this.country = country;
    }


    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry_id() {
        return country;
    }

    public void setCountry_id(Country country) {
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
