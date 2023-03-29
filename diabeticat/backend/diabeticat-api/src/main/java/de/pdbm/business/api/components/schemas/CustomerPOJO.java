package de.pdbm.business.api.components.schemas;

import de.pdbm.business.api.entity.old.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import java.time.format.DateTimeFormatter;

/**
 * CustomerPOJO class as defined in customer.openapi. Represents customer entity for REST interaction and holds selected information.
 */
@ValidateOnExecution
public class CustomerPOJO {

    @Email
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private int id;
    private int active;
    private boolean activebool;
    private AddressHref address;
    private String createDate;
    private StoreHref store;

    public CustomerPOJO(){

    }

    public CustomerPOJO(Customer customer){
        this.id = customer.getCustomer_id();
        this.active = customer.getActive();
        this.activebool = customer.isActivebool();
        this.address = new AddressHref(customer.getAddress());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createDate = customer.getCreate_date().format(formatter);
        this.email = customer.getEmail();
        this.firstName = customer.getFirst_name();
        this.lastName = customer.getLast_name();
        this.store = new StoreHref(customer.getStore_id());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public boolean isActivebool() {
        return activebool;
    }

    public void setActivebool(boolean activebool) {
        this.activebool = activebool;
    }

    public AddressHref getAddress() {
        return address;
    }

    public void setAddress(AddressHref address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StoreHref getStore() {
        return store;
    }

    public void setStore(StoreHref store) {
        this.store = store;
    }
}


