package de.pdbm.business.customer.components.schemas;

import de.pdbm.business.customer.entity.Address;

import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

/**
 * AddressPOJO class as defined in customer.openapi. Represents address entity for REST interaction and holds selected information.
 */
@ValidateOnExecution
public class AddressPOJO {

    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String postalCode;
    private int id;
    private String address2;
    private String district;
    private String phone;
    public AddressPOJO(){
    }
    public AddressPOJO(Address address){
        this.id = address.getAddress_id();
        this.address = address.getAddress();
        this.address2 = address.getAddress2();
        this.city = address.getCity().getCity();
        this.country = address.getCity().getCountry().getCountry();
        this.district = address.getDistrict();
        this.phone = address.getPhone();
        this.postalCode = address.getPostal_code();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}


