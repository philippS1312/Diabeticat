package de.pdbm.business.customer.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment entity from database. Holds payment data.
 */
@Entity
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private int staff_id;
    private int rental_id;
    private BigDecimal amount;
    private LocalDateTime payment_date;


    public Payment () {
        
    }

    public Payment(Customer customer, int staff_id, int rental_id, BigDecimal amount, LocalDateTime payment_date) {
        this.customer = customer;
        this.staff_id = staff_id;
        this.rental_id = rental_id;
        this.amount = amount;
        this.payment_date = payment_date;
    }


    public int getPayment_id() {
        return payment_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer_id) {
        this.customer = customer;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }
}
