package de.pdbm.business.api.components.schemas;

import de.pdbm.business.api.entity.old.Payment;

import java.math.BigDecimal;

/**
 * PaymentPOJO class as defined in customer.openapi. Represents payment entity for REST interaction and holds selected information.
 */
public class PaymentPOJO {

    private int id;
    private BigDecimal amount;
    private CustomerHref customer;
    private RentalHref rental;
    private StaffHref staff;

    public PaymentPOJO(Payment payment){
        this.id = payment.getPayment_id();
        this.amount = payment.getAmount();
        this.customer = new CustomerHref(payment.getCustomer());
        this.rental = new RentalHref(payment.getRental_id());
        this.staff = new StaffHref(payment.getStaff_id());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CustomerHref getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerHref customer) {
        this.customer = customer;
    }

    public RentalHref getRental() {
        return rental;
    }

    public void setRental(RentalHref rental) {
        this.rental = rental;
    }

    public StaffHref getStaff() {
        return staff;
    }

    public void setStaff(StaffHref staff) {
        this.staff = staff;
    }
}


