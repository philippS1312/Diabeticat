package de.pdbm.business.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jochen")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getString(){
        return "Peter";
    }

}
