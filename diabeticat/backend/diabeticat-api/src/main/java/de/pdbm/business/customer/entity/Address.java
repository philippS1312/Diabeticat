package de.pdbm.business.customer.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Address entity from database. Holds address data.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;

    private String address;
    private String address233;
    private String district;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private String postal_code;
    private String phone;
    private LocalDateTime last_update;

    public Address () {
        
    }

    public Address(String address, String adress2, String district, City city, String postal_code, String phone) {
        this.address = address;
        this.address233 = adress2;
        this.district = district;
        this.city = city;
        this.postal_code = postal_code;
        this.phone = phone;
    }

    
    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address233;
    }

    public void setAddress2(String adress2) {
        this.address233 = adress2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
