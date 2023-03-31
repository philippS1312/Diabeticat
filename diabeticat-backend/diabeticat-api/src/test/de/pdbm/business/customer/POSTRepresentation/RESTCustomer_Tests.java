package de.pdbm.business.api.POSTRepresentation;
import de.pdbm.business.api.components.schemas.AddressHref;
import de.pdbm.business.api.components.schemas.AddressPOJO;
import de.pdbm.business.api.components.schemas.CustomerPOJO;
import de.pdbm.config.Configuration;

import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class RESTCustomer_Tests {
    

    /**
     * get JSON File from URL
     * @param url
     * @return HttpResponseString
     */
    private HttpResponse<String> getJson(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * Check 'http://localhost:8080/dvd-rental-film/resources/' response
     */
    @Test
    public void resources_Statuscode() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/dvd-rental-customer/resources/")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        assertEquals(200, response.statusCode(),    "Statuscode: " + response.statusCode() + "\n" +
                                                                    "2xx: Success code\n" +
                                                                    "4xx: Client error code\n" +
                                                                    "5xx: Server error code\n");
    }

    /**
     * This function tests the addition of a new address with the post method
     * POST
     *   __
     *   Create address
     *   add address
     *   check if new address exists
     *   check if attributes are correct
     *   delete address
     */
    @Test
    public void createAddress() throws IOException, InterruptedException {
        // set verification attributes
        Random random = new Random();
        String validAddress = random.nextInt(30) + "street";

        AddressPOJO address = new AddressPOJO();
        address.setAddress(validAddress);
        address.setAddress2("");
        address.setCity("Hamilton");
        address.setDistrict("Hamilton");
        address.setCountry("New Zealand");
        address.setPostalCode("77948");
        address.setPhone("0179 3223993939");

        String addressLocation;


        Client client1 = ClientBuilder.newClient();
        int status = client1.target(Configuration.DVD_RENTAL_CUSTOMER + "resources/status").request().get().getStatus();
        client1.close();

        assertEquals(200, status, "| resources/status not available. Statuscode: "+ status + " |");
        if(status == 200) {

            // add address
            Client client2 = ClientBuilder.newClient();
            Response response2 = client2.target(Configuration.DVD_RENTAL_CUSTOMER + "resources/addresses").request(MediaType.APPLICATION_JSON).post(Entity.json(address));
            client2.close();
            addressLocation = response2.getHeaderString("Location");
            try {
                address.setId(Integer.parseInt(addressLocation.substring(addressLocation.lastIndexOf('/') + 1))); // Get ID from JSON
            } catch (NumberFormatException e) {
                System.out.println(e);
            }


            // search for the new address
            Client client3 = ClientBuilder.newClient();
            Response response3 = client3.target(addressLocation).request(MediaType.APPLICATION_JSON).get();
            client3.close();


            // check if attributes are correct
            HttpResponse<String> response4 = getJson(addressLocation);
            assertTrue(response4.body().contains(validAddress), "Adding failed, wrong address");
            assertTrue(response4.body().contains("Hamilton"), "Adding failed, wrong city");
            assertTrue(response4.body().contains("New Zealand"), "Adding failed, wrong country");
            assertTrue(response4.body().contains("77948"), "Adding failed, wrong postal code");

            assertEquals(201, response2.getStatus(), "address could not be added. Response status: \n" +
                    response2.getStatus());
            assertEquals(200, response3.getStatus(), "new address could not be found in /resources/address/" + address.getId() + ". Response status: \n" +
                    response3.getStatus());


            // ---------------------------------------------
            // delete new address from database
            Client client5 = ClientBuilder.newClient();
            Response response5 = client5.target(addressLocation).request(MediaType.APPLICATION_JSON).delete();
            client5.close();
        }
    }



    /**
     * This function tests the addition of a new customer with the post method
     * POST
     *
     * In addition, an address is also transferred to the newly created customer
     *
     *   __
     *   Create address
     *   Create customer
     *   add address
     *   add customer
     *   check if new customer exists
     *   check if customer attributes are correct
     *   check if customer->address attributes are correct
     *   delete address & customer
     */
    @Test
    public void createCustomer() throws IOException, InterruptedException {
        // set verification attributes
        Random random = new Random();
        String validFirstname = "Max" + random.nextInt(30);
        String validLastname = "Mustermann" + random.nextInt(30);


        String addressLocation;
        String customerLocation;

        // new address
        AddressPOJO address = new AddressPOJO();
        address.setAddress("123 street");
        address.setAddress2("");
        address.setCity("Hamilton");
        address.setDistrict("Hamilton");
        address.setCountry("New Zealand");
        address.setPostalCode("77948");
        address.setPhone("0179 6464646464");


        // new customer
        CustomerPOJO customer = new CustomerPOJO();
        customer.setFirstName(validFirstname);
        customer.setLastName(validLastname);
        customer.setEmail("test@gmail.com");
        customer.setActive(1);
        customer.setActivebool(true);
        customer.setCreateDate("23.12.2022");



        Client client1 = ClientBuilder.newClient();
        int status = client1.target(Configuration.DVD_RENTAL_CUSTOMER + "resources/status").request().get().getStatus();
        client1.close();

        assertEquals(200, status, "| resources/status not available. Statuscode: "+ status + " |");
        if(status == 200) {

            // add address
            Client client2 = ClientBuilder.newClient();
            Response response2 = client2.target(Configuration.DVD_RENTAL_CUSTOMER + "resources/addresses").request(MediaType.APPLICATION_JSON).post(Entity.json(address));
            client2.close();
            addressLocation = response2.getHeaderString("Location");
            try {
                address.setId(Integer.parseInt(addressLocation.substring(addressLocation.lastIndexOf('/') + 1))); // Get ID from JSON
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            AddressHref addressHref = new AddressHref();
            addressHref.setHref(Configuration.DVD_RENTAL_CUSTOMER+"resources/addresses/" + address.getId());
            customer.setAddress(addressHref);


            // add customer
            Client client3 = ClientBuilder.newClient();
            Response response3 = client3.target(Configuration.DVD_RENTAL_CUSTOMER + "resources/customers?address="+address.getId()+"&store=1").request(MediaType.APPLICATION_JSON).post(Entity.json(customer));
            client3.close();
            customerLocation = response3.getHeaderString("Location");
            try {
                customer.setId(Integer.parseInt(customerLocation.substring(customerLocation.lastIndexOf('/') + 1))); // Get ID from JSON
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            assertEquals(201, response3.getStatus(), "customer could not be added. Response status: \n" +
                    response2.getStatus());


            // search for the new customer
            Client client4 = ClientBuilder.newClient();
            Response response4 = client4.target(customerLocation).request(MediaType.APPLICATION_JSON).get();
            client4.close();
            assertEquals(200, response4.getStatus(), "new customer could not be found in /resources/customers/" + customer.getId() + ". Response status: \n" +
                    response3.getStatus());


            // check if attributes are correct
            HttpResponse<String> response5 = getJson(customerLocation);
            assertTrue(response5.body().contains(validFirstname), "Adding failed, wrong firstname");
            assertTrue(response5.body().contains(validLastname), "Adding failed, wrong lastname");
            assertTrue(response5.body().contains("test@gmail.com"), "Adding failed, wrong E-Mail");

            // check if customers address attributes are correct
            HttpResponse<String> response6 = getJson(customer.getAddress().getHref());
            assertTrue(response6.body().contains("123 street"), "Adding failed, wrong address->address");
            assertTrue(response6.body().contains("Hamilton"), "Adding failed, wrong address->city");
            assertTrue(response6.body().contains("New Zealand"), "Adding failed, wrong address->country");
            assertTrue(response6.body().contains("77948"), "Adding failed, wrong address->postalCode");
            assertTrue(response6.body().contains("0179 6464646464"), "Adding failed, wrong address->phone");


            // ---------------------------------------------
            // delete new address from database
            Client client7 = ClientBuilder.newClient();
            Response response7 = client7.target(addressLocation).request(MediaType.APPLICATION_JSON).delete();
            client7.close();

            // delete new customer from database
            Client client8 = ClientBuilder.newClient();
            Response response8 = client8.target(customerLocation).request(MediaType.APPLICATION_JSON).delete();
            client8.close();
        }
    }
}