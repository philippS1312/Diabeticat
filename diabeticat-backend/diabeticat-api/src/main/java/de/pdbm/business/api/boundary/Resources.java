package de.pdbm.business.api.boundary;

import de.pdbm.business.api.entity.User;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * In this class, REST endpoints are defined.
 */
@ApplicationPath("")
public class Resources {
    @Inject
    UserService userService;

    /**
     * Endpoint to verify that the service is running.
     * @return Status code 200 if service is running.
     */
    @GET
    @Path("/status")
    public Response status() {
        return Response.ok().build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public User payment(@Context HttpServletResponse response) { //Return Data
        User user = new User("usernameXYZ","carsten.gmail.com","diabeticatistcool");
        userService.persistUser(user);
        return user;
    }







    /**===============================================================================================**/
    /**Beispiele:                                                                                     **/
    /**===============================================================================================**/

//    /*@Inject
//    CustomerService customerService;
//
//
//
//     * Endpoint to get the count of addresses.
//     * @return Count of addresses.
//
//    @GET
//    @Path("/addresses/count")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Integer addressesCount() {
//        return (int) customerService.getAddressesCount();
//    }
//
//    /**
//     * Endpoint to get a list of customers. Limit and offset are for pagination purposes.
//     * @param limit The count of displayed customers.
//     * @param offset The first customer to be displayed.
//     * @return List of customers.
//     */
//    @GET
//    @Path("/customers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<CustomerPOJO> customers(@QueryParam("limit") Integer limit,@QueryParam("offset") Integer offset) {
//        if(limit == null || limit == 0){
//            limit = 20;
//        }else if(limit > 100){
//            limit = 100;
//        }
//        if(offset == null){
//            offset = 0;
//        }
//        return customerService.getCustomersWithOffset(limit, offset).stream().map(CustomerPOJO::new).collect(Collectors.toList());
//    }
//
//
//     Ab hier beginnen die in der Vorlesung definierten Endpunkte.
//
//      GET METHODEN SOWEIT IMPLEMENTIERT, ANDERE METHODEN PLATZHALTER
//
//
//
//    /**
//     * Endpoint to get the root of DVD Rental Customer.
//     * @return Array of primary resources.
//     */
//    @GET
//    @Path("")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response resources() {
//        String addresses = Configuration.DVD_RENTAL_CUSTOMER +  "resources/addresses";
//        String customers = Configuration.DVD_RENTAL_CUSTOMER +  "resources/customers";
//        String payments = Configuration.DVD_RENTAL_CUSTOMER +  "resources/payments";
//        JsonObject json = Json.createObjectBuilder().add("addresses", addresses).add("customers", customers).add("payments", payments).build();
//        return Response.ok(json).build();
//    }
//
//    /**
//     * Endpoint to get a list of addresses (max 100).
//     * @param limit The count of displayed addresses.
//     * @param offset The first address to be displayed.
//     * @return List of addresses.
//     */
//    @GET
//    @Path("/addresses")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AddressPOJO> addresses(@QueryParam("limit") Integer limit,@QueryParam("offset") Integer offset) {
//        if(limit == null || limit == 0){
//            limit = 20;
//        } else if(limit > 100){
//            limit = 100;
//        }
//        if(offset == null){
//            offset = 0;
//        }
//        return customerService.getAddressesWithOffset(limit, offset).stream().map(AddressPOJO::new).collect(Collectors.toList());
//    }
//
//    /**
//     * Endpoint to create a new address. Country and City must exist already.
//     * @param addressPOJO The address data.
//     * @return Status code 201: Address created. Status code 404: Country and/or City do not exist.
//     */
//    @POST
//    @Path("/addresses")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAddress(@Valid AddressPOJO addressPOJO) {
//        List<Country> countryList = customerService.getCountryByName(addressPOJO.getCountry());
//        List<City> cityList = customerService.getCityByNameAndCountryName(addressPOJO.getCity(), addressPOJO.getCountry());
//        if(cityList.isEmpty() || countryList.isEmpty()){
//            throw new NotFoundException();
//        } else {
//            Address address = new Address(addressPOJO.getAddress(), addressPOJO.getAddress2(), addressPOJO.getDistrict(), cityList.get(0), addressPOJO.getPostalCode(), addressPOJO.getPhone());
//            customerService.persistAddress(address);
//            URI res = UriBuilder.fromResource(Resources.class).path(Configuration.DVD_RENTAL_CUSTOMER + "resources/addresses/{id}" ).build(address.getAddress_id());
//            return Response.created(res).build();
//        }
//    }
//
//    /**
//     * Endpoint to get address with addressId.
//     * @param addressId The address id.
//     * @return The address with the addressId.
//     */
//    @GET
//    @Path("/addresses/{addressId : \\d+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public AddressPOJO address(@PathParam("addressId") Integer addressId) {
//        Address address = customerService.getAddressById(addressId);
//        if(address == null){
//            throw new NotFoundException();
//        }
//        return new AddressPOJO(address);
//    }
//
//    /**
//     * Endpoint to create a new customer.
//     * @param customerPOJO The customer data.
//     * @param address The address id.
//     * @param store The store id.
//     * @return Status code 201: Customer created. Status code 404: Address not found.
//     */
//    @POST
//    @Path("/customers")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response customers(@Valid CustomerPOJO customerPOJO, @QueryParam("address") Integer address, @QueryParam("store") Integer store) {
//        Address customerAddress = customerService.getAddressById(address);
//        if(customerAddress == null){
//            throw new BadRequestException();
//        }
//        Customer customer = new Customer(store, customerPOJO.getFirstName(), customerPOJO.getLastName(), customerPOJO.getEmail(),customerAddress , true, LocalDate.now(), 1);
//        customerService.persistCustomer(customer);
//        return Response.status(201).header("Location", Configuration.DVD_RENTAL_CUSTOMER + "resources/customers/" + customer.getCustomer_id()).build();
//    }
//
//    /**
//     * Endpoint to get the customer count.
//     * @return The customer count.
//     */
//    @GET
//    @Path("/customers/count")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Integer customersCount() {
//        return (int) customerService.getCustomerCount();
//    }
//
//    /**
//     * Endpoint to get customer with customerId.
//     * @param customerId The customer id.
//     * @return The customer with the customerId.
//     */
//    @GET
//    @Path("/customers/{customerId : \\d+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public CustomerPOJO customer(@PathParam("customerId") Integer customerId) {
//        Customer customer = customerService.getCustomerById(customerId);
//        if (customer == null) {
//            throw new NotFoundException();
//        }
//        return new CustomerPOJO(customer);
//    }
//
//    /**
//     * Endpoint to get payments of customer with customerId.
//     * @param customerId The customer id.
//     * @return A list of payments form customer with customerId.
//     */
//    @GET
//    @Path("/customers/{customerId : \\d+}/payments")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<PaymentPOJO> customerPayments(@PathParam("customerId") Integer customerId) {
//        if(customerService.getCustomerById(customerId) == null){
//            throw new NotFoundException();
//        }
//        return customerService.getPaymentsByCustomerId(customerId).stream().map(PaymentPOJO::new).collect(Collectors.toList());
//    }
//
//    /**
//     * Endpoint to create a payment.
//     * @param paymentPOST The payment data.
//     * @return Status code 201: Payment created. Status code 400: Only allowed: amount (decimal), customer (int), rental (int),\
//     *             \ staff (int), date (yyyy-MM-dd HH:mm). Status code 404: Some involved entity does not exist. See message body.
//     */
//    @POST
//    @Path("/payments")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response payments(@Valid PaymentPOST paymentPOST) { //Update Data
//        Customer customer = customerService.getCustomerById(paymentPOST.getCustomer());
//        Client client = ClientBuilder.newClient();
//        Response resRental = client.target(Configuration.DVD_RENTAL_STORE + "resources/rentals/" + paymentPOST.getRental()).request().get();
//        client.close();
//        client = ClientBuilder.newClient();
//        Response resStaff = client.target(Configuration.DVD_RENTAL_STORE + "resources/staff/" + paymentPOST.getStaff()).request().get();
//        client.close();
//        if(customer == null || resRental.getStatus() == 404 || resStaff.getStatus() == 404){
//            throw new NotFoundException();
//        }
//        Payment payment = new Payment(customer, paymentPOST.getStaff(), paymentPOST.getRental(), paymentPOST.getAmount(), paymentPOST.getDateRaw());
//        customerService.persistPayment(payment);
//        URI res = UriBuilder.fromResource(Resources.class).path(Configuration.DVD_RENTAL_CUSTOMER + "resources/payments/{id}").build(payment.getPayment_id());
//        return Response.created(res).build();
//    }
//
//    /**
//     * Endpoint to get payment with paymentId.
//     * @param paymentId The payment id.
//     * @param response ServletResponse provided by @Context.
//     * @return The payment data.
//     */
//    @GET
//    @Path("/payments/{paymentId : \\d+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public PaymentPOJO payment(@PathParam("paymentId") Integer paymentId, @Context HttpServletResponse response) { //Return Data
//        Payment payment = customerService.getPaymentById(paymentId);
//        if(payment == null){
//            throw new NotFoundException();
//        }
//        response.setHeader("Link", Configuration.DVD_RENTAL_CUSTOMER + "resources/payments/" + paymentId);
//        return new PaymentPOJO(payment);
//    }
//
//    /**
//     * Endpoint to delete a payment (for accounting). A reverse accounting entry is created, referenced by Header 'Link'.
//     * @param paymentId The payment id.
//     * @return Status code 204: Payment was deleted for accounting. Status code 404: Payment not found.
//     */
//    @DELETE
//    @Path("/payments/{paymentId : \\d+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deletePayment(@PathParam("paymentId") int paymentId) {
//        Payment oldPayment = customerService.getPaymentById(paymentId);
//        if (oldPayment == null){
//            throw new NotFoundException();
//        }else {
//            Payment newPayment = new Payment(oldPayment.getCustomer(), oldPayment.getStaff_id(), oldPayment.getRental_id(), oldPayment.getAmount().negate(), LocalDateTime.now());
//            customerService.persistPayment(newPayment);
//            return Response.status(204).header("Link", Configuration.DVD_RENTAL_CUSTOMER + "resources/payments/" + newPayment.getPayment_id()).build();
//        }
//    }
//    */
}
