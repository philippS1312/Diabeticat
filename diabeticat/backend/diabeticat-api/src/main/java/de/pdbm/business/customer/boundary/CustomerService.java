package de.pdbm.business.customer.boundary;


import de.pdbm.business.customer.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class acts as a communicator to the database.
 */
@Stateless
public class CustomerService {

    @PersistenceContext
    EntityManager em;

    /**
     * Method to get a list of customers with pagination features.
     * @param limit Max elements in list.
     * @param offset First element in list.
     * @return List of customers.
     */
    public List<Customer> getCustomersWithOffset(int limit, int offset){
        return em.createQuery("SELECT c FROM Customer c ORDER BY c.customer_id", Customer.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    /**
     * Method to get a list of addresses with pagination features.
     * @param limit Max elements in list.
     * @param offset First element in list.
     * @return List of addresses.
     */
    public List<Address> getAddressesWithOffset(int limit, int offset){
        return em.createQuery("SELECT a FROM Address a ORDER BY a.address_id", Address.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public Address getAddressById(int addressId){
        return em.find(Address.class, addressId);
    }

    public long getAddressesCount(){
        return em.createQuery("SELECT COUNT(a) FROM Address a", Long.class).getSingleResult();
    }

    public long getCustomerCount(){
        return em.createQuery("SELECT COUNT(c) FROM Customer c", Long.class).getSingleResult();
    }

    public Customer getCustomerById(int customerId){
        return em.find(Customer.class, customerId);
    }

    public List<Payment> getPaymentsByCustomerId(int customerId){
        return em.createQuery("SELECT p FROM Payment p WHERE p.customer.customer_id = :customerId", Payment.class).setParameter("customerId", customerId).getResultList();
    }

    public Payment getPaymentById(int paymentId){
        return em.find(Payment.class, paymentId);
    }

    public Country getCountryById(int countryId){
        return em.find(Country.class, countryId);
    }

    public City getCityById(int cityId){
        return em.find(City.class, cityId);
    }
    public List<Country> getCountryByName(String country){
        return em.createQuery("SELECT c FROM Country c WHERE c.country = :country", Country.class).setParameter("country", country).getResultList();
    }

    public List<City> getCityByNameAndCountryName(String city, String country){
        return em.createQuery("SELECT c FROM City c WHERE c.city = :city AND c.country.country = :country", City.class)
                .setParameter("city", city).setParameter("country", country).getResultList();
    }

    public void persistAddress(Address address) {
        em.persist(address);
    }

    public void persistCustomer(Customer customer) {
        em.persist(customer);
    }

    public void persistPayment(Payment payment) {
        em.persist(payment);
    }
}
