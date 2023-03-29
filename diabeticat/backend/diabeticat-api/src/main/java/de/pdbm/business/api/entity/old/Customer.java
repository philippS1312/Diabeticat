package de.pdbm.business.api.entity.old;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Customer entity from database. Holds customer data.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    private int store_id;
    private String first_name;
    private String last_name;
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private boolean activebool;
    private LocalDate create_date;
    private LocalDateTime last_update;
    private int active;

    public Customer() {

    }

    public Customer(int store_id, String first_name, String last_name, String email, Address address, boolean activebool, LocalDate create_date, int active) {
        this.store_id = store_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
        this.activebool = activebool;
        this.create_date = create_date;
        this.active = active;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActivebool() {
        return activebool;
    }

    public void setActivebool(boolean activebool) {
        this.activebool = activebool;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    @PrePersist
    @PreUpdate
    protected void refreshLastUpdate(){
        this.last_update = LocalDateTime.now();
    }
}
