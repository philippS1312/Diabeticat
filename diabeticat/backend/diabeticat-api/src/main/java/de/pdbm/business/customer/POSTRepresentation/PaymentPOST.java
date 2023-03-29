package de.pdbm.business.customer.POSTRepresentation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.executable.ValidateOnExecution;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Payment entity representation for post request. Was not defined in API.
 */
@ValidateOnExecution
public class PaymentPOST {

    @NotNull(message="amount (decimal) should not be null")
    @Positive
    private BigDecimal amount;
    @NotNull(message="rental (int) should not be null")
    @Positive
    private int rental;
    @NotNull(message="customer (int) should not be null")
    @Positive
    private int customer;
    @NotNull(message="staff (int) should not be null")
    @Positive
    private int staff;
    @NotNull(message="date (String) should not be null")
    @NotEmpty(message="date (String) should not be null")
    @Pattern(message="date value has a wrong pattern",regexp="^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) ([0-2][0-9]:[0-6][0-9])$")
    private String date; //Pattern: yyyy-MM-dd HH:mm

    public PaymentPOST(){

    }

    public PaymentPOST(BigDecimal amount, int rental, int customer, int staff, String date) {
        this.amount = amount;
        this.rental = rental;
        this.customer = customer;
        this.staff = staff;
        this.date = date;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getRental() {
        return rental;
    }

    public void setRental(int rental) {
        this.rental = rental;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDateTime getDateRaw(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

}
